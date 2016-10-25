package com.magic.sangha.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.sangha.bean.PermissionBean;
import com.magic.sangha.bean.RoleBean;
import com.magic.sangha.service.IPermissionService;
import com.magic.sangha.service.IRolePowerService;
import com.magic.sangha.service.IRoleService;
import com.magic.sangha.util.RoleConstant;
import com.magic.sangha.util.StatusConstant;
import com.magic.sangha.util.ViewData;

/**
 *  角色 权限 控制层
 * @author QimouXie
 *
 */
@RequestMapping("/rolePower")
@Controller
public class RolePowerController extends BaseController {
	
	@Resource
	private IRoleService roleServiceImpl;
	@Resource
	private IPermissionService permissionServiceImpl;
	@Resource
	private IRolePowerService rolePowerServiceImpl;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@RequestMapping("/getRolePower")
	@ResponseBody
	public ViewData  getRolePower(){
		List<RoleBean> roles = roleServiceImpl.findAll();
		for (RoleBean role : roles) {
			if(role.getId().equals(RoleConstant.COMMON)){
				roles.remove(role);
				break;
			}
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", roles);
	}
	
	@RequestMapping("/getPower")
	@ResponseBody
	public ViewData getPowerByRole(Integer roleId){
		
		if(null == roleId){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		List<PermissionBean> data = permissionServiceImpl.queryPowerByRole(roleId);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}
	
	
	/***修改角色对应的权限*/
	@RequestMapping("/updateRolePower")
	@ResponseBody
	public ViewData updatePower(HttpServletRequest req){
		
		String strRoleId = req.getParameter("type");
		if(null == strRoleId){
			return buildSuccessCodeJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		Integer roleId = Integer.parseInt(req.getParameter("type"));
		if(null == roleId || roleId == 0){
			return buildFailureJson(StatusConstant.Fail_CODE, "参数异常");
		}
		String[] roleIds = req.getParameterValues("powerId");
		if(null == roleIds){
			roleIds = new String[0];
//			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "没有修改");
		}
		List<Integer> ids = new ArrayList<Integer>();
		if(RoleConstant.MANAGER == roleId){
			ids.add(18);
		}
		for (int i = 0; i < roleIds.length; i++) {
			Integer tempRoleId = Integer.parseInt(roleIds[i]);
			ids.add(tempRoleId);
		}
		try {
			rolePowerServiceImpl.updatePower(roleId, ids);
			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "操作成功");
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			return buildFailureJson(StatusConstant.Fail_CODE, "操作失败");
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
