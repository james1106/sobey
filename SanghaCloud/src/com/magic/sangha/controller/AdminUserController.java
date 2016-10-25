package com.magic.sangha.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.sangha.bean.CompanyBean;
import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.GroupUser;
import com.magic.sangha.bean.GroupUserCategoryBean;
import com.magic.sangha.bean.UserBean;
import com.magic.sangha.service.ICompanyService;
import com.magic.sangha.service.IGroupUserCategoryService;
import com.magic.sangha.service.IGroupUserService;
import com.magic.sangha.service.IRoleService;
import com.magic.sangha.service.ITVService;
import com.magic.sangha.service.IUserService;
import com.magic.sangha.util.LoginHelper;
import com.magic.sangha.util.RoleConstant;
import com.magic.sangha.util.SMSCode;
import com.magic.sangha.util.StatusConstant;
import com.magic.sangha.util.ViewData;

/**
 * 后台 用户管理控制器
 * 
 * @author QimouXie
 *
 */
@Controller
@RequestMapping("/admin/user")
public class AdminUserController extends BaseController {

	@Resource
	private IUserService userServiceImpl;
	@Resource
	private IGroupUserService groupUserServiceImpl;
	@Resource
	private ITVService tVServiceImpl;
	@Resource
	private IRoleService roleServiceImpl;
	@Resource
	private ICompanyService companyServiceImpl;
	@Resource
	private IGroupUserCategoryService groupUserCategoryServiceImpl;

	@RequestMapping("/audituser")
	@ResponseBody
	public ViewData getPengingAuditUser(HttpServletRequest req, Integer flag,
			Integer pageNO, Integer pageSize) {
		// flag 为 1 请求 用户 flag为2 请求员工
		GroupUser user = LoginHelper.getCurrentAdmin(req);
		if (null == user) {
			return buildFailureJson(StatusConstant.NOTLOGIN, "未登录");
		}
		if (null == flag) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		if (flag.equals(1)) {
			CutPageBean<UserBean> userData = userServiceImpl.findByAudit(
					StatusConstant.NONPASS, pageNO, pageSize, null);
			return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功",
					userData);
		} else if (flag.equals(2)) {
			CutPageBean<GroupUser> groupUserData = groupUserServiceImpl.findByAudit(null,StatusConstant.NONPASS, pageNO, pageSize, null);
			return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功",
					groupUserData);
		}
		return buildFailureJson(StatusConstant.Fail_CODE, "获取失败");
	}

	@RequestMapping("/approved")
	@ResponseBody
	public ViewData AduitAccount(Integer id, Integer roleId, Integer flag,Integer isPUser) {
		// flag 为 2 的时候 是拒绝通过， 为 1的时候 是通过审核
		if (StatusConstant.NOTPASS.equals(flag)) {
			if (RoleConstant.COMMON.equals(roleId)) {
				try {
					UserBean user = new UserBean();
					user.setId(id);
					userServiceImpl.delUser(user);
				} catch (Exception e) {
					logger.debug(e.getMessage(),e);
					return buildFailureJson(StatusConstant.Fail_CODE, "操作失败");
				}
				return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "操作成功");
			} else {
				try {
					groupUserServiceImpl.delGroupUser(id);
//					groupUserServiceImpl.updateStatusAndRole(id,StatusConstant.NOTPASS, roleId);
				} catch (Exception e) {
					logger.debug(e.getMessage(),e);
					return buildFailureJson(StatusConstant.Fail_CODE, "操作失败");
				}
				return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "操作成功");
			}
		} else if (StatusConstant.PASS.equals(flag)) {
			if (RoleConstant.COMMON.equals(roleId)) {
				
				try {
					userServiceImpl.updateStatus(id, StatusConstant.PASS,isPUser);
					UserBean user = userServiceImpl.findById(id);
					// 发送到验证码到手机
					String text = MessageFormat.format(SMSCode.ACCOUNT_PASS, user.getRealName());
					boolean isSend = SMSCode.sendMessage(text, user.getMobile());
					if(!isSend){
						return buildFailureJson(StatusConstant.Fail_CODE, "短信通知失败");
					}
				} catch (Exception e) {
					logger.debug(e.getMessage(),e);
					return buildFailureJson(StatusConstant.Fail_CODE, "操作失败");
				}
				return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "操作成功");
			} else {
				try {
					groupUserServiceImpl.updateStatusAndRole(id,StatusConstant.PASS, roleId);
					GroupUser user = groupUserServiceImpl.findByIdToName(id);
					// 发送到验证码到手机
					String text = MessageFormat.format(SMSCode.ACCOUNT_PASS, user.getRealName());
					boolean isSend = SMSCode.sendMessage(text, user.getMobile());
					if(!isSend){
						return buildFailureJson(StatusConstant.Fail_CODE, "短信通知失败");
					}
				} catch (Exception e) {
					logger.debug(e.getMessage(),e);
					return buildFailureJson(StatusConstant.Fail_CODE, "操作失败");
				}
				return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "操作成功");
			}
		}
		return buildFailureJson(StatusConstant.Fail_CODE, "操作失败");
	}

	/**
	 * 请求用户列表
	 * 
	 * @return
	 */
	@RequestMapping("/getUsers")
	@ResponseBody
	public ViewData getUserInfo(HttpServletRequest req, Integer flag,Integer pageNO, Integer pageSize, String realName,Integer roleId) {
		// flag 为 1 请求 用户 flag为2 请求员工
		GroupUser user = LoginHelper.getCurrentAdmin(req);
		if (null == user) {
			return buildFailureJson(StatusConstant.NOTLOGIN, "未登录");
		}
		if (null == flag || null == pageNO || pageSize == null) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		if(null == roleId || roleId == 0){
			roleId = null;
		}
		if(null == realName || realName.trim().length() == 0){
			realName = null;
		}
		if (flag.equals(1)) {
			CutPageBean<UserBean> userData = userServiceImpl.findByAudit(10, pageNO, pageSize, realName);
			return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功",
					userData);
		} else if (flag.equals(2)) {
			CutPageBean<GroupUser> groupUserData = groupUserServiceImpl.findByAudit(roleId,10, pageNO, pageSize,realName);
			return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功",
					groupUserData);
		}
		return buildFailureJson(StatusConstant.Fail_CODE, "获取失败");
	}

	@RequestMapping("/addUser")
	@ResponseBody
	public ViewData registerUser(HttpServletRequest req) {

		String type = req.getParameter("type");
		if (StatusConstant.REGISTER_TYPE_NON.equals(type)) {
			return buildFailureJson(StatusConstant.Fail_CODE, "没有选择注册类型");
		}

		String mobile = req.getParameter("mobile");
		String realName = req.getParameter("realName");
		String pwd = req.getParameter("pwd");
		String email = req.getParameter("email");
		String roleId = req.getParameter("roleId");
		String qq = req.getParameter("qq");
		if (null == mobile || null == realName || pwd == null || null == email
				|| null == roleId || null == type) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		GroupUser groupUser = new GroupUser();
		if (StatusConstant.REGISTER_TYPE_USER.equals(type)) {
			String tvId = req.getParameter("tvId");
			if (null == tvId) {
				return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
			}
			String saleId = req.getParameter("saleId");
			Integer isPUser = Integer.parseInt(req.getParameter("isPUser"));
			if(isPUser == 3){
				return buildFailureJson(StatusConstant.Fail_CODE, "没有选择产品用户");
			}
			UserBean user = new UserBean();
			user.setMobile(mobile);
			user.setRealName(realName);
			user.setPassword(pwd);
			user.setEmail(email);
			user.setRoleId(Integer.valueOf(roleId));
			user.setTvId(Integer.valueOf(tvId));
			user.setSales(Integer.valueOf(saleId));
			user.setStatus(StatusConstant.PASS);
			user.setQq(qq);
			user.setIsPUser(isPUser);
			try {
				userServiceImpl.register(user);
				// 发送到验证码到手机
				String text = MessageFormat.format(SMSCode.ADMIN_REGISTER,realName,"111111");
				boolean isSend = SMSCode.sendMessage(text, mobile);
				if(!isSend){
					return buildFailureJson(StatusConstant.Fail_CODE, "短信发送频繁,稍后再试");
				}
			} catch (Exception e) {
				logger.debug(e.getMessage(),e);
				return buildFailureJson(StatusConstant.Fail_CODE, "操作失败");
			}
			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "操作成功");
		} else if (StatusConstant.REGISTER_TYPE_HEAD_EMPLOYEE.equals(type)) {
			List<CompanyBean> coms = companyServiceImpl.findAllByType(StatusConstant.PARENT_COMPANY);
			groupUser.setCompanyId(coms.get(0).getId());
			groupUser.setUserType(StatusConstant.HEAD_COMPLANY);
		} else if (StatusConstant.REGISTER_TYPE_BRANCH_EMPLOYEE.equals(type)) {
			String companyId = req.getParameter("companyId");
			groupUser.setCompanyId(Integer.valueOf(companyId));
			groupUser.setUserType(StatusConstant.BRANCH_OFFICE);
		} else if (StatusConstant.REGISTER_TYPE_OFFICE_EMPLOYEE.equals(type)) {
			String officeId = req.getParameter("officeId");
			groupUser.setOfficeId(Integer.valueOf(officeId));
			groupUser.setUserType(StatusConstant.OFFICE);
		}
		String introduce = req.getParameter("introduce");
		String jobTitle = req.getParameter("jobTitle");
		groupUser.setRoleId(Integer.valueOf(roleId));
		groupUser.setIntroduce(introduce);
		groupUser.setJobTitle(jobTitle);
		groupUser.setStatus(StatusConstant.PASS);
		groupUser.setMobile(mobile);
		groupUser.setPassword(pwd);
		groupUser.setEmail(email);
		groupUser.setQq(qq);
		groupUser.setIsOnLine(StatusConstant.ONLINE);
		groupUser.setRealName(realName);
		try {
			groupUserServiceImpl.register(groupUser);
			// 发送到验证码到手机
			String text = MessageFormat.format(SMSCode.ADMIN_REGISTER,realName,"111111");
			boolean isSend = SMSCode.sendMessage(text, mobile);
			if(!isSend){
				return buildFailureJson(StatusConstant.Fail_CODE, "短信发送频繁,稍后再试");
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			return buildFailureJson(StatusConstant.Fail_CODE, "操作失败");
		}
		
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "操作成功");
	}

	/**
	 * 冻结/解冻 账户
	 * 
	 * @param userId
	 * @param type
	 * @return
	 */
	@RequestMapping("/frozenAccount")
	@ResponseBody
	public ViewData frozenAccount(Integer userId, Integer type, Integer flag) {
		// flag 0为冻结账户 1为解冻账户
		// type 1 用户 2 员工
		if (userId == null || type == null || flag == null) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}

		if (flag == 0) {
			// 冻结账户
			if (type == 1) {
				// 用户
				try {
					userServiceImpl.updateStatus(userId, StatusConstant.FROZEN,null);
				} catch (Exception e) {
					logger.debug(e.getMessage(),e);
					return buildFailureJson(StatusConstant.Fail_CODE, "操作失败");
				}
				return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "更新成功");
			} else {
				// 员工
				try {
					groupUserServiceImpl.updateStatusAndRole(userId,StatusConstant.FROZEN, null);
				} catch (Exception e) {
					logger.debug(e.getMessage(),e);
					return buildFailureJson(StatusConstant.Fail_CODE, "操作失败");
				}
				return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "更新成功");
			}
		} else if (flag == 1) {
			// 解冻账户
			if (type == 1) {
				// 用户
				try {
					userServiceImpl.updateStatus(userId, StatusConstant.PASS,null);
				} catch (Exception e) {
					logger.debug(e.getMessage(),e);
					return buildFailureJson(StatusConstant.Fail_CODE, "操作失败");
				}
				return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "更新成功");
			} else {
				// 员工
				try {
					groupUserServiceImpl.updateStatusAndRole(userId,StatusConstant.PASS, null);
				} catch (Exception e) {
					logger.debug(e.getMessage(),e);
					return buildFailureJson(StatusConstant.Fail_CODE, "操作失败");
				}
				return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "更新成功");
			}
		}
		return buildFailureJson(StatusConstant.Fail_CODE, "操作失败");
	}
	
	/**
	 *  根据ID查询 员工 包括 所属公司类型
	 * @param userId
	 * @return
	 */
	@RequestMapping("/getUserById")
	@ResponseBody
	public ViewData getUserById(Integer userId,Integer type){
		if(null == userId || null ==type){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		Object user = null;
		if(type == 1){
			//用户
			user = userServiceImpl.findByIdAllFields(userId);
		}else if(type == 2){
			// 企业用户
			user = groupUserServiceImpl.findById(userId);
		}
		if(null == user){
			return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "对象不存在");
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", user);
	}
	
	/**
	 *  PC端修改用户信息
	 * @return
	 */
	@RequestMapping("/updateUser")
	@ResponseBody
	public ViewData updateUserInfo(HttpServletRequest req){
		
		String mobile = req.getParameter("mobile");
		String realName = req.getParameter("realName");
		String email = req.getParameter("email");
		String roleId = req.getParameter("roleId");
		String qq = req.getParameter("qq");
		String id = req.getParameter("id");
		String isOfficeSelect = req.getParameter("isOfficeSelect");
		
		String type = req.getParameter("type");
		if(1 == Integer.parseInt(type)){
			// 用户
			String tempPUser = req.getParameter("isPUser");
			if(null == tempPUser || tempPUser.trim().length() == 0){
				return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "没有选择是否是产品用户");
			}
			Integer isPUser = Integer.parseInt(req.getParameter("isPUser"));
			String saleId = req.getParameter("sale");
			
			UserBean user = new UserBean();
			user.setId(Integer.valueOf(id));
			user.setMobile(mobile);
			user.setRealName(realName);
			user.setEmail(email);
			user.setRoleId(Integer.valueOf(roleId));
			user.setQq(qq);
			user.setIsPUser(isPUser);
			if(1 == Integer.parseInt(isOfficeSelect)){
				String tvId = req.getParameter("tvId");
				user.setTvId(Integer.valueOf(tvId));
			}
			user.setSales(Integer.valueOf(saleId));
			// 执行更新操作
			userServiceImpl.updateUser(user);
			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "更新成功");
		}else if(2 == Integer.parseInt(type)){
			// 员工
			String introduce = req.getParameter("introduce");
			String jobTitle = req.getParameter("jobTitle");
			
			
//			GroupUser user = new GroupUser();
			GroupUser user = groupUserServiceImpl.findById(Integer.valueOf(id));
			if(null == user){
				return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "对象不存在");
			}
			user.setId(Integer.valueOf(id));
			user.setMobile(mobile);
			user.setRealName(realName);
			user.setEmail(email);
			user.setRoleId(Integer.valueOf(roleId));
			user.setQq(qq);
			user.setIntroduce(introduce);
			user.setJobTitle(jobTitle);
			if(1 == Integer.parseInt(isOfficeSelect)){
				String companyType = req.getParameter("companyType");
				String companyId = req.getParameter("companyId");
				if(StatusConstant.OFFICE_COMPANY == Integer.parseInt(companyType)){
					user.setOfficeId(Integer.parseInt(companyId));
					user.setCompanyId(null);
				}else{
					user.setOfficeId(null);
					user.setCompanyId(Integer.parseInt(companyId));
				}
			}
			groupUserServiceImpl.updateUser(user);
			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "更新成功");
		}
		return buildFailureJson(StatusConstant.Fail_CODE, "操作失败");
	}
	
	/**
	 *  获取 办事处下的销售列表
	 * @return
	 */
	@RequestMapping("/getSales")
	@ResponseBody
	public ViewData getOfficeSale(Integer officeId,Integer tvId){
		List<GroupUser> data = null;
		if(tvId != null){
			data = groupUserServiceImpl.findOfficeSaleByTvId(tvId);
		}else{
			data = groupUserServiceImpl.findGroupUserByOfficeAndRole(officeId, RoleConstant.SALE);
			if(data != null && data.size() == 0){
				return buildFailureJson(StatusConstant.NO_DATA, "没有数据");
			}
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}
	
	/**
	 *  根据ID 获取User信息
	 * @return
	 */
	@RequestMapping("/getUser")
	@ResponseBody
	public ViewData getUserDetail(Integer userId,Integer type){
		
		if(null == userId || null == type){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		Object obj = null;
		if(type == 1){
			//用户
			obj = userServiceImpl.findByIdAllFields(userId);
		}else if(type == 2){
			// 员工
			obj = groupUserServiceImpl.findById(userId);
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", obj);
	}
	
	@RequestMapping("/getServiceUserList")
	@ResponseBody
	public ViewData getServiceUser(Integer pageNO,Integer pageSize){
		CutPageBean<GroupUser> page = groupUserServiceImpl.queryPageByRole(null,pageNO, pageSize, RoleConstant.CUSTOMER);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", page);
	}
	/**
	 *  设置 客服 在线状态
	 * @return
	 */
	@RequestMapping("/servicemanage/setonline")
	@ResponseBody
	public ViewData updateOnline(Integer userId,Integer type){
		//  type 0 设置在线   1 设置离线
		if(null == userId || null == type){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		GroupUser user = new GroupUser();
		user.setId(userId);
		if(StatusConstant.ONLINE.equals(type)){
			user.setIsOnLine(StatusConstant.ONLINE);
		}else if(StatusConstant.UNLINE.equals(type)){
			Integer count = groupUserServiceImpl.countOnLineByService();
			if(null == count || count == 1 ){
				return buildFailureJson(StatusConstant.Fail_CODE, "必须有客服在线");
			}
			user.setIsOnLine(StatusConstant.UNLINE);
		}
		groupUserServiceImpl.updateUser(user);
		return buildSuccessCodeViewData(StatusConstant.SUCCESS_CODE, "操作成功");
	}
	
	/**
	 *   获取 技术 员工列表 
	 * @return
	 */
	@RequestMapping("/getTechUser")
	@ResponseBody
	public ViewData getTechUser(Integer pageNO,Integer pageSize,String realName){
		
		if(realName == null || realName.trim().length() == 0){
			realName = null;
		}
		CutPageBean<GroupUser> data = groupUserServiceImpl.queryPageLikeNameByRole(pageNO, pageSize, realName);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}
	
	/**
	 *   更新 技术员工的 非编技术等
	 * @return
	 */
	@RequestMapping("/updateUserCate")
	@ResponseBody
	public ViewData updateGroupUserCate(Integer flag,Integer groupUserId,String params,HttpServletRequest req){
		// flag 0 设置 非专技术 1 设置 非编技术
		if(null == flag ){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		if(flag == 0){
			try {
				groupUserCategoryServiceImpl.delCategoryByGroupUserId(groupUserId);
				return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "操作成功");
			} catch (Exception e) {
				e.printStackTrace();
				return buildFailureJson(StatusConstant.Fail_CODE, "数据异常");
			}
		}else if(flag == 1){
			String[] cateIds = req.getParameterValues("categoryId");
			if(null == cateIds || cateIds.length == 0){
				return buildFailureJson(StatusConstant.Fail_CODE, "必须选择一项");
			}
			List<GroupUserCategoryBean> categoryIds = new ArrayList<GroupUserCategoryBean>();
			for(int i=0 ; i < cateIds.length; i++){
				GroupUserCategoryBean cate = new GroupUserCategoryBean();
				cate.setCategoryId(Integer.parseInt(cateIds[i]));
				cate.setGroupuserId(groupUserId);
				categoryIds.add(cate);
			}
			try {
				groupUserCategoryServiceImpl.updateGroupUserTech(categoryIds, groupUserId);
				return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "操作成功");
			} catch (Exception e) {
				e.printStackTrace();
				return buildFailureJson(StatusConstant.Fail_CODE, "数据异常");
			}
		}
		return buildFailureJson(StatusConstant.Fail_CODE, "操作失败");
	}
	
	/**
	 *  获取 技术的 专长 和 所有 专长列表
	 * @param id
	 * @return
	 */
	@RequestMapping("/getallcate")
	@ResponseBody
	public ViewData getAllCateAndGroupUser(Integer id){
		
		if(null == id){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		List<GroupUserCategoryBean> data = groupUserCategoryServiceImpl.findCategoryAdmin(id);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}
	
	@RequestMapping("/getUserByItem")
	@ResponseBody
	public ViewData getUserByItem(Integer companyId,Integer officeId){
		if(null == companyId || companyId == 0){
			companyId = null;
		}
		if(null == officeId || officeId == 0){
			officeId = null;
		}
		List<GroupUser> data = groupUserServiceImpl.queryByCompanyOrOffice(companyId, officeId);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}

	@RequestMapping("/delUser")
	@ResponseBody
	public ViewData delUser(Integer userId,Integer type){
		// type 1 用户 2 员工
		if(null == userId || null == type){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		if(type == 1){
			UserBean userTemp = userServiceImpl.findById(userId);
			UserBean user = new UserBean();
			user.setId(userId);
			userServiceImpl.delUser(user);
			if(null != userTemp && null != userTemp.getToken()){
				LoginHelper.delObjByToken(userTemp.getToken());
			}
		}else{
			GroupUser user = groupUserServiceImpl.findById(userId);
			groupUserServiceImpl.delGroupUser(userId);
			if(null != user && null != user.getToken()){
				LoginHelper.delObjByToken(user.getToken());
			}
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "操作成功");
	}
	
	@RequestMapping("/getUserByTvId")
	@ResponseBody
	public ViewData getUserByTV(Integer tvId){
		if(null == tvId){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		List<UserBean> data = userServiceImpl.findByTVId(tvId);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成", data);
	}
	
	
	
	
	
	
	
	
	
}
