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
				return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "用户名密码错误");
			} else {
				if(StatusConstant.NONPASS.equals(user.getStatus()) || StatusConstant.NOTPASS.equals(user.getStatus())){
					return buildFailureJson(StatusConstant.PENDING, "帐号正在审核");
				}
				if(StatusConstant.FROZEN.equals(user.getStatus())){
					return buildFailureJson(StatusConstant.LOGIN_ACCOUNT, "帐号已被冻结,请联系管理员");
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
				return buildSuccessJson(StatusConstant.SUCCESS_CODE, "登录成功", user);
			}
		} else {
			if(StatusConstant.NONPASS.equals(groupUser.getStatus()) || StatusConstant.NOTPASS.equals(groupUser.getStatus())){
				return buildFailureJson(StatusConstant.PENDING, "帐号正在审核");
			}
			if(StatusConstant.FROZEN.equals(groupUser.getStatus())){
				return buildFailureJson(StatusConstant.LOGIN_ACCOUNT, "帐号已被冻结,请联系管理员");
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
			return buildSuccessJson(StatusConstant.SUCCESS_CODE, "登录成功", groupUser);
		}
	}

	/**
	 * 注册
	 * 
	 * @param mobile
	 * @return
	 */
	@RequestMapping("/register")
	@ResponseBody
	public ViewData register(String mobile,String realName,String password,String email,Integer officeId,String type,Integer deviceType,HttpServletRequest req) {
		boolean isExist = groupUserServiceImpl.checkEmailIsExist(email);
		if(isExist){
			return buildFailureJson(StatusConstant.Fail_CODE, "邮箱已被注册");
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
				return buildFailureJson(StatusConstant.Fail_CODE, "注册失败");
			}
			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "注册成功");
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
				return buildFailureJson(StatusConstant.Fail_CODE, "操作失败");
			}
			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "注册成功");
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
				return buildFailureJson(StatusConstant.Fail_CODE, "操作失败");
			}
			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "注册成功");
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
				return buildFailureJson(StatusConstant.Fail_CODE, "操作失败");
			}
			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "注册成功");
		}
		
		return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "注册失败");
	}
	
	/**
	 *  验证手机号 和 邮箱是否存在
	 * @return
	 */
	@RequestMapping("/verifyMobileAndEmail")
	@ResponseBody
	public ViewData verifyMobileAndEmail(String mobile,String email){
		boolean isExit = groupUserServiceImpl.checkEmailIsExist(email);
		if(isExit){
			return buildFailureJson(StatusConstant.Fail_CODE, "该邮箱已经存在");
		}
		Integer id = userServiceImpl.verifiMobile(mobile);
		if (null == id) {
			Integer groupId = groupUserServiceImpl.verifiMobile(mobile);
			if (null == groupId) {
				return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "验证通过");
			}
			return buildFailureJson(StatusConstant.Fail_CODE, "该手机号已经被注册");
		} else {
			return buildFailureJson(StatusConstant.Fail_CODE, "该手机号已经被注册");
		}
	}
	
	/**
	 *  验证邮箱
	 * @param email
	 * @return
	 */
	@RequestMapping("/verifyEmail")
	@ResponseBody
	public ViewData verifyEmail(String email){
		boolean isExit = groupUserServiceImpl.checkEmailIsExist(email);
		if(isExit){
			return buildFailureJson(StatusConstant.Fail_CODE, "该邮箱已经存在");
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "验证通过");
	}

	/**
	 * 验证手机号是否被注册
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
				return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "该手机号可以注册");
			}
			return buildFailureJson(StatusConstant.Fail_CODE, "该手机号已经被注册");
		} else {
			return buildFailureJson(StatusConstant.Fail_CODE, "该手机号已经被注册");
		}
	}

	/**
	 * 验证 验证码
	 * 
	 * @param code
	 * @return
	 */
	@RequestMapping("/verifiCode")
	@ResponseBody
	public ViewData verifiMobileCode(String code) {
		if (null == code || code.trim().length() == 0) {
			return buildFailureJson(-1, "验证码不能为空");
		}
		if (code.equals(this.code)) {
			return buildSuccessCodeViewData(1, "验证通过");
		}
		return buildFailureJson(-1, "验证码不正确");
	}

	/**
	 * 发送验证码
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
				// 发送到验证码到手机
				String text = MessageFormat.format(SMSCode.MOBILE_REGIST, code);
				boolean isSend = SMSCode.sendMessage(text, mobile);
				if(!isSend){
					return buildFailureJson(StatusConstant.Fail_CODE, "短信发送频繁,稍后再试");
				}
				Map<String,Object> data = new HashMap<String,Object>();
				data.put("valicode", code);
				return buildSuccessJson(StatusConstant.SUCCESS_CODE, "发送成功", data);
			}
			return buildFailureJson(StatusConstant.Fail_CODE, "该手机号已经被注册");
		} else {
			return buildFailureJson(StatusConstant.Fail_CODE, "该手机号已经被注册");
		}
	}
	
	/**
	 * 修改个人信息
	 * @return
	 */
	@RequestMapping("/updateInfo")
	@ResponseBody
	public ViewData updateInfo(HttpServletRequest req,String avatar,String realName,String email,String mobile){
		
		Object obj = LoginHelper.getCurrentUser();
		if(null == obj){
			return buildFailureJson(StatusConstant.NOTLOGIN, "未登录");
		}
		
		if(obj instanceof UserBean){
			UserBean user = (UserBean)obj;
			if(StatusConstant.FROZEN.equals(user.getStatus())){
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户已被冻结");
			}
			if(!user.getEmail().equals(email)){
				boolean isExist = groupUserServiceImpl.checkEmailIsExist(email);
				if(isExist){
					return buildFailureJson(StatusConstant.Fail_CODE, "邮箱已被注册");
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
			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "修改成功");
		}else if(obj instanceof GroupUser){
			GroupUser user = (GroupUser)obj;
			if(StatusConstant.FROZEN.equals(user.getStatus())){
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户已被冻结");
			}
			if(!user.getEmail().equals(email)){
				boolean isExist = groupUserServiceImpl.checkEmailIsExist(email);
				if(isExist){
					return buildFailureJson(StatusConstant.Fail_CODE, "邮箱已被注册");
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
			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "修改成功");
		}
		return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "修改失败");
	}
	
	/**
	 *   获取用户信息
	 * @return
	 */
	@RequestMapping("/getInfo")
	@ResponseBody
	public ViewData getInfo(String token){
		Object obj = LoginHelper.getCurrentUser();
		if(null ==  obj){
			return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "请先登录");
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
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功",obj);
	}
	
	/**
	 *  退出登录
	 * @return
	 */
	@RequestMapping("/loginout")
	@ResponseBody
	public ViewData loginout(){
		Object obj = LoginHelper.getCurrentUser();
		if(null == obj){
			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "退出成功");
		}
		if(obj instanceof UserBean){
			UserBean user = (UserBean)obj;
			userServiceImpl.updateDeviceTypeAndToken(user.getId(), user.getDeviceType(), "");
		}else if(obj instanceof GroupUser){
			GroupUser user = (GroupUser)obj;
			groupUserServiceImpl.updateDeviceTypeAndToken(user.getId(), user.getDeviceType(), "");
		}
		LoginHelper.clearToken();
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "退出成功");
	}
	/**
	 *  修改密码
	 * @return
	 */
	@RequestMapping("/updatePassword")
	@ResponseBody
	public ViewData updatePassword(String oldpwd,String password){
		if(null == password || password.trim().length() == 0){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		Object obj = LoginHelper.getCurrentUser();
		if(null == obj){
			return buildFailureJson(StatusConstant.NOTLOGIN, "对象不存在");
		}
		
		if(obj instanceof UserBean){
			UserBean user = (UserBean)obj;
			if(StatusConstant.FROZEN.equals(user.getStatus())){
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户已被冻结");
			}
			if(!oldpwd.equals(user.getPassword())){
				return buildFailureJson(StatusConstant.Fail_CODE,"原始密码不对");
			}
			if(password.equals(user.getPassword())){
				return buildFailureJson(StatusConstant.Fail_CODE,"新密码与旧密码不能相同");
			}
			UserBean temp = new UserBean();
			temp.setId(user.getId());
			temp.setPassword(password);
			temp.setMobile(user.getMobile());
			
			user.setPassword(password);
			userServiceImpl.updateUser(temp);
			MemcachedUtil.getInstance().replace(user.getToken(), user);
			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "修改成功");
		}else if(obj instanceof GroupUser){
			GroupUser user = (GroupUser)obj;
			if(StatusConstant.FROZEN.equals(user.getStatus())){
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户已被冻结");
			}
			if(!oldpwd.equals(user.getPassword())){
				return buildFailureJson(StatusConstant.Fail_CODE,"原始密码不对");
			}
			if(password.equals(user.getPassword())){
				return buildFailureJson(StatusConstant.Fail_CODE,"新密码与旧密码不能相同");
			}
			GroupUser temp = new GroupUser();
			temp.setId(user.getId());
			temp.setPassword(password);
			temp.setMobile(user.getMobile());
			
			user.setPassword(password);
			boolean isSuccess = MemcachedUtil.getInstance().replace(user.getToken(), user);
			if(!isSuccess){
				return buildFailureJson(StatusConstant.Fail_CODE,"修改失败");
			}
			groupUserServiceImpl.updateGroupUser(temp);
			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "修改成功");
		}
		
		return buildFailureJson(StatusConstant.Fail_CODE, "修改失败");
	}
	
	/**
	 *  忘记密码， 重置密码
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
				return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "手机号不存在");
			}
			group.setPassword(password);
			groupUserServiceImpl.updateGroupUser(group);
			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "修改成功");
		}
		user.setPassword(password);
		userServiceImpl.updateUser(user);
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "修改成功");
	}
	
	/**
	 *  在找回密码的时候，发送验证码
	 *   检查手机是否存在
	 * @param mobile
	 * @return
	 */
	@RequestMapping("/forgetPwdCode")
	@ResponseBody
	public ViewData forgetPwdSendCode(String mobile){
		
		String code = SMSCode.createRandomCode();
		
		// 发送到验证码到手机
		String text = MessageFormat.format(SMSCode.FORGET_PASSWORD, code);
		boolean isSend = SMSCode.sendMessage(text, mobile);
		if(!isSend){
			return buildFailureJson(StatusConstant.Fail_CODE, "短信发送频繁,稍后再试");
		}
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("valicode", code);
		UserBean user = userServiceImpl.findByMobile(mobile);
		if(null == user){
			GroupUser group = groupUserServiceImpl.findByMobile(mobile);
			if(null == group){
				return buildFailureJson(StatusConstant.Fail_CODE, "手机号不存在");
			}
			return buildSuccessViewData(StatusConstant.SUCCESS_CODE, "获取成功", data);
		}
		 return buildSuccessViewData(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}
	
	/**
	 *  获取总部技术
	 * @return
	 */
	@RequestMapping("/getHeadTechs")
	@ResponseBody
	public ViewData getHeadTech(Integer userId,Integer categoryId){
		if(null == userId || null == categoryId){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		List<GroupUser> feibian = groupUserServiceImpl.findAllTechFeibian(userId, categoryId);
//		List<CompanyBean> com = companyServiceImpl.findAllByType(StatusConstant.PARENT_COMPANY);
//		List<GroupUser> groups = groupUserServiceImpl.findHeadTech(RoleConstant.HEADCOMTECH,com.get(0).getId());
		Map<String,Object> data =new HashMap<String, Object>();
		data.put("lists", feibian);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}

	/**
	 *  获取销售 和 领导
	 * @return
	 */
	@RequestMapping("/getsaleandleader")
	@ResponseBody
	public ViewData getSaleAndHead(Integer userId){
		
		if(null == userId){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		
		// 获取总部领导
		List<CompanyBean> com = companyServiceImpl.findAllByType(StatusConstant.PARENT_COMPANY);
		List<GroupUser> headers = groupUserServiceImpl.findHeadTech(RoleConstant.LEADER,com.get(0).getId());
		List<GroupUser> headSales = groupUserServiceImpl.findHeadTech(RoleConstant.SALE,com.get(0).getId());
		GroupOfficeBean office = groupOfficeServiceImpl.findByUserId(userId);
		// 获取分公司的 领导
		List<GroupUser> companyUsers = groupUserServiceImpl.findGroupUserToManyRoleId(office.getCompanyId());
		// 获取办事处的销售
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
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}
	
	/**
	 *  根据电视台 查找用户接口
	 * @param tvId
	 * @return
	 */
	@RequestMapping("/findUserByTV")
	@ResponseBody
	public ViewData queryByTV(Integer tvId){
		if(null == tvId){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		List<UserBean> users = userServiceImpl.findByTVId(tvId);
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("lists", users);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}
	/**
	 * 查询总部研发
	 * @return
	 */
	@RequestMapping("/getInvent")
	@ResponseBody
	public ViewData queryInvent(Integer categoryId){
		if(null ==categoryId ){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		List<GroupUser> groups = groupUserServiceImpl.findAllDevelopFeibian(categoryId);
		Map<String,Object> data =new HashMap<String, Object>();
		data.put("lists", groups);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}
	
	
}



























