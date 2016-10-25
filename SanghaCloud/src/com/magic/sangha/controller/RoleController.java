package com.magic.sangha.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.sangha.bean.RoleBean;
import com.magic.sangha.service.IRoleService;
import com.magic.sangha.util.RoleConstant;
import com.magic.sangha.util.StatusConstant;
import com.magic.sangha.util.ViewData;

/**
 *  角色控制器
 * @author QimouXie
 *
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {
	
	@Resource
	private IRoleService roleServiceImpl;
	
	@RequestMapping("/getrole")
	@ResponseBody
	public ViewData getRole(Integer userType){
		//  userType 员工类型 0 总公司 1 分公司 2 办事处
	
		List<RoleBean> roles = roleServiceImpl.findAll();
		for (int i = 0; i < roles.size(); i++) {
			if(roles.get(i).getId().equals(RoleConstant.COMMON)){
				roles.remove(i);
				break;
			}
		}
		if(null  == userType){
			return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", roles);
		}
		// 总部 角色组
		Integer[] headRoles = new Integer[]{RoleConstant.MANAGER,RoleConstant.CUSTOMER,RoleConstant.SALE,RoleConstant.OPERATION,RoleConstant.INVENT,RoleConstant.LEADER,RoleConstant.HEADCOMTECH};
		// 分公司 角色组
		Integer[] branchRoles = new Integer[]{RoleConstant.SALE,RoleConstant.FILIALE_HEADER,RoleConstant.FILIALE_TECH_HEADER,RoleConstant.FILIALE_SALE_HEADER};
		// 办事处 角色组
		Integer[] officeRoles = new Integer[]{RoleConstant.FILIALETECH,RoleConstant.SALE};
		List<RoleBean> roleList = new ArrayList<RoleBean>();
		if(StatusConstant.HEAD_COMPLANY.equals(userType)){
			for (RoleBean role : roles) {
				for (int i = 0; i < headRoles.length; i++) {
					if(role.getId() == headRoles[i]){
						roleList.add(role);
					}
				}
			}
		}
		if(StatusConstant.BRANCH_OFFICE.equals(userType)){
			for (RoleBean role : roles) {
				for (int i = 0; i < branchRoles.length; i++) {
					if(role.getId() == branchRoles[i]){
						roleList.add(role);
					}
				}
			}
		}
		if(StatusConstant.OFFICE.equals(userType)){
			for (RoleBean role : roles) {
				for (int i = 0; i < officeRoles.length; i++) {
					if(role.getId() == officeRoles[i]){
						roleList.add(role);
					}
				}
			}
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", roleList);
	}

}
