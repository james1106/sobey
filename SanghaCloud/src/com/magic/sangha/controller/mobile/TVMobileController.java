package com.magic.sangha.controller.mobile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.sangha.bean.GroupUser;
import com.magic.sangha.bean.TVBean;
import com.magic.sangha.bean.UserBean;
import com.magic.sangha.controller.BaseController;
import com.magic.sangha.service.ITVService;
import com.magic.sangha.util.LoginHelper;
import com.magic.sangha.util.RoleConstant;
import com.magic.sangha.util.StatusConstant;
import com.magic.sangha.util.ViewData;

/**
 *  移动端电视台 接口
 * @author QimouXie
 *
 */
@Controller
@RequestMapping("/mobile/tv")
public class TVMobileController extends BaseController {
	
	@Resource
	private ITVService tvServiceImpl;

	@RequestMapping("/getTVs")
	@ResponseBody
	public ViewData getTVs(){
		
		Object obj = LoginHelper.getCurrentUser();
		if(null == obj){
			return buildFailureJson(StatusConstant.NOTLOGIN, "未登录");
		}
		List<TVBean> tvs = null;
		Integer userId = null;
		Integer groupUserId = null;
		Integer roleId = null;
		if(obj instanceof UserBean){
			UserBean user = (UserBean)obj;
			if(StatusConstant.FROZEN.equals(user.getStatus())){
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户被冻结");
			}
			// 查询TV
			userId = user.getId();
		}else if(obj instanceof GroupUser){
			GroupUser user = (GroupUser)obj;
			if(StatusConstant.FROZEN.equals(user.getStatus())){
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户被冻结");
			}
			groupUserId = user.getId();
			// 查询TV
			if(RoleConstant.CUSTOMER.equals(user.getRoleType())){
				roleId = RoleConstant.CUSTOMER;
			}else if(RoleConstant.FILIALETECH.equals(user.getRoleType())){
				roleId = RoleConstant.FILIALETECH;
			}else if(RoleConstant.HEADCOMTECH.equals(user.getRoleType())){
				roleId = RoleConstant.HEADCOMTECH;
			}else if(RoleConstant.INVENT.equals(user.getRoleType())){
				roleId = RoleConstant.INVENT;
			}
		}
		tvs = tvServiceImpl.findByUserIdAndOrder(userId, groupUserId, roleId);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("lists", tvs);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}
	
}
