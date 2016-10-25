package com.magic.sangha.controller.mobile;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.sangha.bean.CompanyBean;
import com.magic.sangha.bean.GroupOfficeBean;
import com.magic.sangha.bean.GroupUser;
import com.magic.sangha.bean.TVBean;
import com.magic.sangha.bean.UserBean;
import com.magic.sangha.controller.BaseController;
import com.magic.sangha.service.ICompanyService;
import com.magic.sangha.service.IGroupOfficeService;
import com.magic.sangha.service.IGroupUserService;
import com.magic.sangha.service.ITVService;
import com.magic.sangha.service.IUserService;
import com.magic.sangha.util.LoginHelper;
import com.magic.sangha.util.MemcachedUtil;
import com.magic.sangha.util.RoleConstant;
import com.magic.sangha.util.SMSCode;
import com.magic.sangha.util.StatusConstant;
import com.magic.sangha.util.ViewData;

@Controller
@RequestMapping("/mobile")
public class UserController extends BaseController {

	@Resource
	private IGroupUserService groupUserServiceImpl;
	@Resource
	private IUserService userServiceImpl;
	
	@Resource
	private ITVService tvServiceImpl;
	
	@Resource
	private IGroupOfficeService groupOfficeServiceImpl;
	@Resource
	private ICompanyService companyServiceImpl;

	private String code;

	@RequestMapping("/login")
	@ResponseBody
	public ViewData login(HttpServletRequest req, String mobile, String password,String deviceToken,Integer deviceType) {

		GroupUser groupUser = groupUserServiceImpl.login(mobile, password);
		if (null == groupUser) {
			UserBean user = userServiceImpl.login(mobile, password);
			if (null == user) {
				return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "�û����������");
			} else {
				if(StatusConstant.NONPASS.equals(user.getStatus()) || StatusConstant.NOTPASS.equals(user.getStatus())){
					return buildFailureJson(StatusConstant.PENDING, "�ʺ��������");
				}
				if(StatusConstant.FROZEN.equals(user.getStatus())){
					return buildFailureJson(StatusConstant.LOGIN_ACCOUNT, "�ʺ��ѱ�����,����ϵ����Ա");
				}
				TVBean tv = tvServiceImpl.findById(user.getTvId());
				if(null != tv){
					user.setTvName(tv.getTvName());
				}
				user.setRoleType(RoleConstant.COMMON);
				if(null != deviceType){
					userServiceImpl.updateDeviceTypeAndToken(user.getId(), deviceType, deviceToken);
				}
				
				String token = LoginHelper.addToken(user);
				userServiceImpl.updateToken(token, user.getId());
				return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��¼�ɹ�", user);
			}
		} else {
			if(StatusConstant.NONPASS.equals(groupUser.getStatus()) || StatusConstant.NOTPASS.equals(groupUser.getStatus())){
				return buildFailureJson(StatusConstant.PENDING, "�ʺ��������");
			}
			if(StatusConstant.FROZEN.equals(groupUser.getStatus())){
				return buildFailureJson(StatusConstant.LOGIN_ACCOUNT, "�ʺ��ѱ�����,����ϵ����Ա");
			}
			if(null != groupUser.getOfficeId()){
				GroupOfficeBean office = groupOfficeServiceImpl.findById(groupUser.getOfficeId());
				if(null != office){
					groupUser.setOfficeName(office.getOfficeName());
				}
			}
			if(null != groupUser.getCompanyId()){
				CompanyBean com = companyServiceImpl.findById(groupUser.getCompanyId());
				if(null != com){
					groupUser.setOfficeName(com.getCompanyName());
				}
			}
			
			Integer type = 1;
			if(groupUser.getRole().getId().equals(RoleConstant.MANAGER)){
				type = RoleConstant.MANAGER;
			}else if(groupUser.getRole().getId().equals(RoleConstant.CUSTOMER)){
				type = RoleConstant.CUSTOMER;
			}else if(groupUser.getRole().getId().equals(RoleConstant.FILIALETECH)){
				type = RoleConstant.FILIALETECH;
			}else if(groupUser.getRole().getId().equals(RoleConstant.HEADCOMTECH)){
				type = RoleConstant.HEADCOMTECH;
			}else if(groupUser.getRole().getId().equals(RoleConstant.INVENT)){
				type = RoleConstant.INVENT;
			}else if(groupUser.getRole().getId().equals(RoleConstant.LEADER)){
				type = RoleConstant.LEADER;
			}else if(groupUser.getRole().getId().equals(RoleConstant.OPERATION)){
				type = RoleConstant.OPERATION;
			}else if(groupUser.getRole().getId().equals(RoleConstant.PRE_SALE)){
				type = RoleConstant.PRE_SALE;
			}else if(groupUser.getRole().getId().equals(RoleConstant.SALE)){
				type = RoleConstant.SALE;
			}else if(groupUser.getRole().getId().equals(RoleConstant.TECH_PERSON)){
				type = RoleConstant.TECH_PERSON;
			}else if(groupUser.getRole().getId().equals(RoleConstant.FILIALE_HEADER)){
				type = RoleConstant.FILIALE_HEADER;
			}else if(groupUser.getRole().getId().equals(RoleConstant.FILIALE_SALE_HEADER)){
				type = RoleConstant.FILIALE_SALE_HEADER;
			}else if(groupUser.getRole().getId().equals(RoleConstant.FILIALE_TECH_HEADER)){
				type = RoleConstant.FILIALE_TECH_HEADER;
			}
			groupUser.setRoleType(type);
			if(null != deviceType){
				groupUser.setDeviceToken(deviceToken);
				groupUser.setDeviceType(deviceType);
				groupUserServiceImpl.updateDeviceTypeAndToken(groupUser.getId(), deviceType, deviceToken);
			}
			String token = LoginHelper.addToken(groupUser);
			groupUserServiceImpl.updateToken(token, groupUser.getId(),new Date());
			return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��¼�ɹ�", groupUser);
		}
	}

	/**
	 * ע��
	 * 
	 * @param mobile
	 * @return
	 */
	@RequestMapping("/register")
	@ResponseBody
	public ViewData register(String mobile,String realName,String password,String email,Integer officeId,String type,Integer deviceType,HttpServletRequest req) {
		boolean isExist = groupUserServiceImpl.checkEmailIsExist(email);
		if(isExist){
			return buildFailureJson(StatusConstant.Fail_CODE, "�����ѱ�ע��");
		}
		if(StatusConstant.USER.equals(type)){
			UserBean user = new UserBean();
			user.setEmail(email);
			user.setMobile(mobile);
			user.setPassword(password);
			user.setRealName(realName);
			user.setTvId(officeId);
			user.setStatus(StatusConstant.NONPASS);
			user.setRoleId(RoleConstant.COMMON);
			user.setDeviceType(deviceType);
			try {
				userServiceImpl.register(user);
			} catch (Exception e) {
				logger.debug(e.getMessage(),e);
				return buildFailureJson(StatusConstant.Fail_CODE, "ע��ʧ��");
			}
			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "ע��ɹ�");
		}else if(StatusConstant.GROUP_USER.equals(type)){
			GroupUser groupUser = new GroupUser();
			groupUser.setEmail(email);
			groupUser.setMobile(mobile);
			groupUser.setPassword(password);
			groupUser.setRealName(realName);
			groupUser.setOfficeId(officeId);
			groupUser.setStatus(StatusConstant.NONPASS);
			groupUser.setRoleId(RoleConstant.SALE);
			groupUser.setDeviceType(deviceType);
			groupUser.setIsOnLine(StatusConstant.ONLINE);
			groupUser.setUserType(StatusConstant.OFFICE);
			try {
				groupUserServiceImpl.register(groupUser);
			} catch (Exception e) {
				logger.debug(e.getMessage(),e);
				return buildFailureJson(StatusConstant.Fail_CODE, "����ʧ��");
			}
			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "ע��ɹ�");
		}else if(StatusConstant.HEAD_USER.equals(type)){
			GroupUser groupUser = new GroupUser();
			groupUser.setEmail(email);
			groupUser.setMobile(mobile);
			groupUser.setPassword(password);
			groupUser.setRealName(realName);
			groupUser.setIsOnLine(StatusConstant.ONLINE);
			groupUser.setUserType(StatusConstant.HEAD_COMPLANY);
			List<CompanyBean> com = companyServiceImpl.findAllByType(StatusConstant.PARENT_COMPANY);
			if(null != com){
				groupUser.setCompanyId(com.get(0).getId());
			}
			groupUser.setStatus(StatusConstant.NONPASS);
			groupUser.setRoleId(RoleConstant.SALE);
			groupUser.setDeviceType(deviceType);
			try {
				groupUserServiceImpl.register(groupUser);
			} catch (Exception e) {
				logger.debug(e.getMessage(),e);
				return buildFailureJson(StatusConstant.Fail_CODE, "����ʧ��");
			}
			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "ע��ɹ�");
		}else if(StatusConstant.FILIALE_USER.equals(type)){
			GroupUser groupUser = new GroupUser();
			groupUser.setEmail(email);
			groupUser.setMobile(mobile);
			groupUser.setPassword(password);
			groupUser.setRealName(realName);
			groupUser.setCompanyId(officeId);
			groupUser.setIsOnLine(StatusConstant.ONLINE);
			groupUser.setStatus(StatusConstant.NONPASS);
			groupUser.setRoleId(RoleConstant.FILIALE_SALE_HEADER);
			groupUser.setDeviceType(deviceType);
			groupUser.setUserType(StatusConstant.BRANCH_OFFICE);
			try {
				groupUserServiceImpl.register(groupUser);
			} catch (Exception e) {
				logger.debug(e.getMessage(),e);
				return buildFailureJson(StatusConstant.Fail_CODE, "����ʧ��");
			}
			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "ע��ɹ�");
		}
		
		return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "ע��ʧ��");
	}
	
	/**
	 *  ��֤�ֻ��� �� �����Ƿ����
	 * @return
	 */
	@RequestMapping("/verifyMobileAndEmail")
	@ResponseBody
	public ViewData verifyMobileAndEmail(String mobile,String email){
		boolean isExit = groupUserServiceImpl.checkEmailIsExist(email);
		if(isExit){
			return buildFailureJson(StatusConstant.Fail_CODE, "�������Ѿ�����");
		}
		Integer id = userServiceImpl.verifiMobile(mobile);
		if (null == id) {
			Integer groupId = groupUserServiceImpl.verifiMobile(mobile);
			if (null == groupId) {
				return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "��֤ͨ��");
			}
			return buildFailureJson(StatusConstant.Fail_CODE, "���ֻ����Ѿ���ע��");
		} else {
			return buildFailureJson(StatusConstant.Fail_CODE, "���ֻ����Ѿ���ע��");
		}
	}
	
	/**
	 *  ��֤����
	 * @param email
	 * @return
	 */
	@RequestMapping("/verifyEmail")
	@ResponseBody
	public ViewData verifyEmail(String email){
		boolean isExit = groupUserServiceImpl.checkEmailIsExist(email);
		if(isExit){
			return buildFailureJson(StatusConstant.Fail_CODE, "�������Ѿ�����");
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "��֤ͨ��");
	}

	/**
	 * ��֤�ֻ����Ƿ�ע��
	 * 
	 * @return
	 */
	@RequestMapping("/verifiMobile")
	@ResponseBody
	public ViewData verifiMobile(String mobile) {
		Integer id = userServiceImpl.verifiMobile(mobile);
		if (null == id) {
			Integer groupId = groupUserServiceImpl.verifiMobile(mobile);
			if (null == groupId) {
				return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "���ֻ��ſ���ע��");
			}
			return buildFailureJson(StatusConstant.Fail_CODE, "���ֻ����Ѿ���ע��");
		} else {
			return buildFailureJson(StatusConstant.Fail_CODE, "���ֻ����Ѿ���ע��");
		}
	}

	/**
	 * ��֤ ��֤��
	 * 
	 * @param code
	 * @return
	 */
	@RequestMapping("/verifiCode")
	@ResponseBody
	public ViewData verifiMobileCode(String code) {
		if (null == code || code.trim().length() == 0) {
			return buildFailureJson(-1, "��֤�벻��Ϊ��");
		}
		if (code.equals(this.code)) {
			return buildSuccessCodeViewData(1, "��֤ͨ��");
		}
		return buildFailureJson(-1, "��֤�벻��ȷ");
	}

	/**
	 * ������֤��
	 * 
	 * @param mobile
	 * @return
	 */
	@RequestMapping("/sendCode")
	@ResponseBody
	public ViewData sendCode(String mobile) {
		
		Integer id = userServiceImpl.verifiMobile(mobile);
		if (null == id) {
			Integer groupId = groupUserServiceImpl.verifiMobile(mobile);
			if (null == groupId) {
				String code = SMSCode.createRandomCode();
				// ���͵���֤�뵽�ֻ�
				String text = MessageFormat.format(SMSCode.MOBILE_REGIST, code);
				boolean isSend = SMSCode.sendMessage(text, mobile);
				if(!isSend){
					return buildFailureJson(StatusConstant.Fail_CODE, "���ŷ���Ƶ��,�Ժ�����");
				}
				Map<String,Object> data = new HashMap<String,Object>();
				data.put("valicode", code);
				return buildSuccessJson(StatusConstant.SUCCESS_CODE, "���ͳɹ�", data);
			}
			return buildFailureJson(StatusConstant.Fail_CODE, "���ֻ����Ѿ���ע��");
		} else {
			return buildFailureJson(StatusConstant.Fail_CODE, "���ֻ����Ѿ���ע��");
		}
	}
	
	/**
	 * �޸ĸ�����Ϣ
	 * @return
	 */
	@RequestMapping("/updateInfo")
	@ResponseBody
	public ViewData updateInfo(HttpServletRequest req,String avatar,String realName,String email,String mobile){
		
		Object obj = LoginHelper.getCurrentUser();
		if(null == obj){
			return buildFailureJson(StatusConstant.NOTLOGIN, "δ��¼");
		}
		
		if(obj instanceof UserBean){
			UserBean user = (UserBean)obj;
			if(StatusConstant.FROZEN.equals(user.getStatus())){
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "�˻��ѱ�����");
			}
			if(!user.getEmail().equals(email)){
				boolean isExist = groupUserServiceImpl.checkEmailIsExist(email);
				if(isExist){
					return buildFailureJson(StatusConstant.Fail_CODE, "�����ѱ�ע��");
				}
			}
			UserBean temp = new UserBean();
			temp.setId(user.getId());
			temp.setAvatar(avatar);
			temp.setRealName(realName);
			temp.setEmail(email);
			if(null == mobile){
				temp.setMobile(user.getMobile());
			}else{
				temp.setMobile(mobile);
			}
			
			if(null != avatar){
				user.setAvatar(avatar);
			}
			if(null != realName){
				user.setRealName(realName);
			}
			if(null != email){
				user.setEmail(email);
			}
			user.setMobile(temp.getMobile());
			
			MemcachedUtil.getInstance().replace(user.getToken(), user);
			userServiceImpl.updateUser(temp);
			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "�޸ĳɹ�");
		}else if(obj instanceof GroupUser){
			GroupUser user = (GroupUser)obj;
			if(StatusConstant.FROZEN.equals(user.getStatus())){
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "�˻��ѱ�����");
			}
			if(!user.getEmail().equals(email)){
				boolean isExist = groupUserServiceImpl.checkEmailIsExist(email);
				if(isExist){
					return buildFailureJson(StatusConstant.Fail_CODE, "�����ѱ�ע��");
				}
			}
			GroupUser temp = new GroupUser();
			temp.setId(user.getId());
			temp.setAvatar(avatar);
			temp.setRealName(realName);
			temp.setEmail(email);
			if(null == mobile){
				temp.setMobile(user.getMobile());
			}else{
				temp.setMobile(mobile);
			}
			
			if(null != avatar){
				user.setAvatar(avatar);
			}
			if(null != realName){
				user.setRealName(realName);
			}
			if(null != email){
				user.setEmail(email);
			}
			user.setMobile(temp.getMobile());
			
			MemcachedUtil.getInstance().replace(user.getToken(), user);
			groupUserServiceImpl.updateGroupUser(temp);
			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "�޸ĳɹ�");
		}
		return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "�޸�ʧ��");
	}
	
	/**
	 *   ��ȡ�û���Ϣ
	 * @return
	 */
	@RequestMapping("/getInfo")
	@ResponseBody
	public ViewData getInfo(String token){
		Object obj = LoginHelper.getCurrentUser();
		if(null ==  obj){
			return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "���ȵ�¼");
		}
		if(obj instanceof UserBean){
			UserBean user = (UserBean)obj;
			UserBean temp = new UserBean();
			temp.setMobile(user.getMobile());
			temp.setId(user.getId());
			temp.setUpdateDate(new Date());
			userServiceImpl.updateUser(temp);
		}else if(obj instanceof GroupUser){
			GroupUser user = (GroupUser)obj;
			GroupUser temp = new GroupUser();
			temp.setMobile(user.getMobile());
			temp.setId(user.getId());
			temp.setUpdateDate(new Date());
			groupUserServiceImpl.updateUser(temp);
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�",obj);
	}
	
	/**
	 *  �˳���¼
	 * @return
	 */
	@RequestMapping("/loginout")
	@ResponseBody
	public ViewData loginout(){
		Object obj = LoginHelper.getCurrentUser();
		if(null == obj){
			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "�˳��ɹ�");
		}
		if(obj instanceof UserBean){
			UserBean user = (UserBean)obj;
			userServiceImpl.updateDeviceTypeAndToken(user.getId(), user.getDeviceType(), "");
		}else if(obj instanceof GroupUser){
			GroupUser user = (GroupUser)obj;
			groupUserServiceImpl.updateDeviceTypeAndToken(user.getId(), user.getDeviceType(), "");
		}
		LoginHelper.clearToken();
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "�˳��ɹ�");
	}
	/**
	 *  �޸�����
	 * @return
	 */
	@RequestMapping("/updatePassword")
	@ResponseBody
	public ViewData updatePassword(String oldpwd,String password){
		if(null == password || password.trim().length() == 0){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "�ֶβ���Ϊ��");
		}
		Object obj = LoginHelper.getCurrentUser();
		if(null == obj){
			return buildFailureJson(StatusConstant.NOTLOGIN, "���󲻴���");
		}
		
		if(obj instanceof UserBean){
			UserBean user = (UserBean)obj;
			if(StatusConstant.FROZEN.equals(user.getStatus())){
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "�˻��ѱ�����");
			}
			if(!oldpwd.equals(user.getPassword())){
				return buildFailureJson(StatusConstant.Fail_CODE,"ԭʼ���벻��");
			}
			if(password.equals(user.getPassword())){
				return buildFailureJson(StatusConstant.Fail_CODE,"������������벻����ͬ");
			}
			UserBean temp = new UserBean();
			temp.setId(user.getId());
			temp.setPassword(password);
			temp.setMobile(user.getMobile());
			
			user.setPassword(password);
			userServiceImpl.updateUser(temp);
			MemcachedUtil.getInstance().replace(user.getToken(), user);
			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "�޸ĳɹ�");
		}else if(obj instanceof GroupUser){
			GroupUser user = (GroupUser)obj;
			if(StatusConstant.FROZEN.equals(user.getStatus())){
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "�˻��ѱ�����");
			}
			if(!oldpwd.equals(user.getPassword())){
				return buildFailureJson(StatusConstant.Fail_CODE,"ԭʼ���벻��");
			}
			if(password.equals(user.getPassword())){
				return buildFailureJson(StatusConstant.Fail_CODE,"������������벻����ͬ");
			}
			GroupUser temp = new GroupUser();
			temp.setId(user.getId());
			temp.setPassword(password);
			temp.setMobile(user.getMobile());
			
			user.setPassword(password);
			boolean isSuccess = MemcachedUtil.getInstance().replace(user.getToken(), user);
			if(!isSuccess){
				return buildFailureJson(StatusConstant.Fail_CODE,"�޸�ʧ��");
			}
			groupUserServiceImpl.updateGroupUser(temp);
			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "�޸ĳɹ�");
		}
		
		return buildFailureJson(StatusConstant.Fail_CODE, "�޸�ʧ��");
	}
	
	/**
	 *  �������룬 ��������
	 * @param mobile
	 * @param password
	 * @return
	 */
	@RequestMapping("/resetpwd")
	@ResponseBody
	public ViewData forgetPassword(String mobile,String password){
		
		UserBean user = userServiceImpl.findByMobile(mobile);
		if(null == user){
			GroupUser group = groupUserServiceImpl.findByMobile(mobile);
			if(null == group){
				return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "�ֻ��Ų�����");
			}
			group.setPassword(password);
			groupUserServiceImpl.updateGroupUser(group);
			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "�޸ĳɹ�");
		}
		user.setPassword(password);
		userServiceImpl.updateUser(user);
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "�޸ĳɹ�");
	}
	
	/**
	 *  ���һ������ʱ�򣬷�����֤��
	 *   ����ֻ��Ƿ����
	 * @param mobile
	 * @return
	 */
	@RequestMapping("/forgetPwdCode")
	@ResponseBody
	public ViewData forgetPwdSendCode(String mobile){
		
		String code = SMSCode.createRandomCode();
		
		// ���͵���֤�뵽�ֻ�
		String text = MessageFormat.format(SMSCode.FORGET_PASSWORD, code);
		boolean isSend = SMSCode.sendMessage(text, mobile);
		if(!isSend){
			return buildFailureJson(StatusConstant.Fail_CODE, "���ŷ���Ƶ��,�Ժ�����");
		}
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("valicode", code);
		UserBean user = userServiceImpl.findByMobile(mobile);
		if(null == user){
			GroupUser group = groupUserServiceImpl.findByMobile(mobile);
			if(null == group){
				return buildFailureJson(StatusConstant.Fail_CODE, "�ֻ��Ų�����");
			}
			return buildSuccessViewData(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�", data);
		}
		 return buildSuccessViewData(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�", data);
	}
	
	/**
	 *  ��ȡ�ܲ�����
	 * @return
	 */
	@RequestMapping("/getHeadTechs")
	@ResponseBody
	public ViewData getHeadTech(Integer userId,Integer categoryId){
		if(null == userId || null == categoryId){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "�ֶβ���Ϊ��");
		}
		List<GroupUser> feibian = groupUserServiceImpl.findAllTechFeibian(userId, categoryId);
//		List<CompanyBean> com = companyServiceImpl.findAllByType(StatusConstant.PARENT_COMPANY);
//		List<GroupUser> groups = groupUserServiceImpl.findHeadTech(RoleConstant.HEADCOMTECH,com.get(0).getId());
		Map<String,Object> data =new HashMap<String, Object>();
		data.put("lists", feibian);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�", data);
	}

	/**
	 *  ��ȡ���� �� �쵼
	 * @return
	 */
	@RequestMapping("/getsaleandleader")
	@ResponseBody
	public ViewData getSaleAndHead(Integer userId){
		
		if(null == userId){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "�ֶβ���Ϊ��");
		}
		
		// ��ȡ�ܲ��쵼
		List<CompanyBean> com = companyServiceImpl.findAllByType(StatusConstant.PARENT_COMPANY);
		List<GroupUser> headers = groupUserServiceImpl.findHeadTech(RoleConstant.LEADER,com.get(0).getId());
		List<GroupUser> headSales = groupUserServiceImpl.findHeadTech(RoleConstant.SALE,com.get(0).getId());
		GroupOfficeBean office = groupOfficeServiceImpl.findByUserId(userId);
		// ��ȡ�ֹ�˾�� �쵼
		List<GroupUser> companyUsers = groupUserServiceImpl.findGroupUserToManyRoleId(office.getCompanyId());
		// ��ȡ���´�������
		List<GroupUser> sales = groupUserServiceImpl.findGroupUserByOfficeAndRole(office.getId(), RoleConstant.SALE);
		
		List<GroupUser> dataList = new ArrayList<GroupUser>();
		for (GroupUser header : headers) {
			dataList.add(header);
		}
		for (GroupUser header : headSales) {
			dataList.add(header);
		}
		for (GroupUser header : companyUsers) {
			dataList.add(header);
		}
		for (GroupUser header : sales) {
			dataList.add(header);
		}
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("lists", dataList);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�", data);
	}
	
	/**
	 *  ���ݵ���̨ �����û��ӿ�
	 * @param tvId
	 * @return
	 */
	@RequestMapping("/findUserByTV")
	@ResponseBody
	public ViewData queryByTV(Integer tvId){
		if(null == tvId){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "�ֶβ���Ϊ��");
		}
		List<UserBean> users = userServiceImpl.findByTVId(tvId);
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("lists", users);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�", data);
	}
	/**
	 * ��ѯ�ܲ��з�
	 * @return
	 */
	@RequestMapping("/getInvent")
	@ResponseBody
	public ViewData queryInvent(Integer categoryId){
		if(null ==categoryId ){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "�ֶβ���Ϊ��");
		}
		List<GroupUser> groups = groupUserServiceImpl.findAllDevelopFeibian(categoryId);
		Map<String,Object> data =new HashMap<String, Object>();
		data.put("lists", groups);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�", data);
	}
	
	
}



























