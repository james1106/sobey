package com.magic.sangha.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.GroupUser;
import com.magic.sangha.bean.SignBean;
import com.magic.sangha.bean.UserBean;
import com.magic.sangha.service.IGroupUserService;
import com.magic.sangha.service.ISignService;
import com.magic.sangha.service.IUserService;
import com.magic.sangha.util.StatusConstant;
import com.magic.sangha.util.ViewData;


/**
 *  签到统计 控制层
 * @author QimouXie
 *
 */

@RequestMapping("/admin/sign")
@Controller
public class AdminSignController extends BaseController {
	
	@Resource
	private IGroupUserService groupUserServiceImpl;
	@Resource
	private IUserService userServiceImpl;
	
	@Resource
	private ISignService signServiceImpl;
	
	/**
	 *  分页查询获取 签到记录
	 * @param pageNO
	 * @param pageSize
	 * @param realName
	 * @return
	 */
	@RequestMapping("/getSignList")
	@ResponseBody
	public ViewData getSignRecord(Integer pageNO,Integer pageSize,String realName,Integer type){
		//type  0 员工 1 用户
		if(null == pageNO || null == pageSize || null == type){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		if(realName != null && realName.trim().length() == 0){
			realName = null;
		}
		if(type == 0){
			CutPageBean<GroupUser> data = groupUserServiceImpl.queryPageByUser(pageNO, pageSize, realName);
			return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
		}else if(type  == 1){
			CutPageBean<UserBean> data = userServiceImpl.queryPageByUser(pageNO, pageSize, realName);
			return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
		}
		return buildFailureJson(StatusConstant.Fail_CODE, "参数异常");
	}
	
	@RequestMapping("/getDatailSign")
	@ResponseBody
	public ViewData getDetailSign(Integer pageNO,Integer pageSize,Integer id,Integer type){
		//type  0 员工 1 用户
		if(null == id || null == type || pageNO == null || null == pageSize){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		Integer userId = null;
		Integer groupUserId = null;
		if(type == 0){
			groupUserId = id;
		}else if(type == 1){
			userId = id;
		}
		CutPageBean<SignBean> data = signServiceImpl.findSignByUser(pageNO, pageSize, userId, groupUserId);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}
	
}
