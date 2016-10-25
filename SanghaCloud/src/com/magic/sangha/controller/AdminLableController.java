package com.magic.sangha.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.sangha.bean.LableBean;
import com.magic.sangha.service.ILableService;
import com.magic.sangha.util.RoleConstant;
import com.magic.sangha.util.StatusConstant;
import com.magic.sangha.util.ViewData;

/**
 *  标签管理控制器
 * @author QimouXie
 *
 */
@Controller
@RequestMapping("/admin/lable")
public class AdminLableController extends BaseController {

	@Resource
	private ILableService lableServiceImpl;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@RequestMapping("/getLables")
	@ResponseBody
	public ViewData getLables(){
		List<LableBean> lables = lableServiceImpl.findAll(null);
		List<LableBean> serviceLables = null;
		List<LableBean> techLables = null;
		if(lables != null){
			serviceLables = new ArrayList<LableBean>();
			techLables = new ArrayList<LableBean>();
			for (LableBean lable : lables) {
				if(RoleConstant.CUSTOMER.equals(lable.getRoleId())){
					serviceLables.add(lable);
				}
				if(RoleConstant.FILIALETECH.equals(lable.getRoleId())){
					techLables.add(lable);
				}
			}
		}else{
			return buildFailureJson(StatusConstant.NO_DATA, "没有数据");
		}
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("serviceLable", serviceLables);
		data.put("techLable", techLables);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public ViewData addLable(Integer type,String lable){
		
		if(null == type || null == lable || type == 0){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		
		if(lable.trim().length() == 0){
			return buildFailureJson(StatusConstant.Fail_CODE, "标签不能为空");
		}
		
		String[] lables = lable.split("\\|");
		List<LableBean> lableList = new ArrayList<LableBean>();
		for (int i = 0; i < lables.length; i++) {
			LableBean lableBean = new LableBean();
			lableBean.setLable(lables[i].trim());
			lableBean.setRoleId(type);
			lableList.add(lableBean);
		}
		if(lableList.size() != 0){
			lableServiceImpl.batchAdd(lableList);
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "添加成功");
	}
	
	@RequestMapping("/getLablesByRole")
	@ResponseBody
	public ViewData getLables(Integer type){
		if(null == type){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		List<LableBean> lables = lableServiceImpl.findAll(type);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", lables);
	}
	
	@RequestMapping("/updateLables")
	@ResponseBody
	public ViewData updateLables(HttpServletRequest req,Integer type){
		String[] lables = req.getParameterValues("lables");
		if(lables == null || lables.length == 0 || null == type || type == 0){
			return buildFailureJson(StatusConstant.Fail_CODE, "数据异常");
		}
		List<Integer> ids = new ArrayList<Integer>();
		for (int i = 0; i < lables.length; i++) {
			ids.add(Integer.parseInt(lables[i]));
		}
		try {
			lableServiceImpl.updateLables(ids, type);
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			return buildFailureJson(StatusConstant.Fail_CODE, "更新失败");
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "更新成功");
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public ViewData delLables(Integer type,HttpServletRequest  req){
		String[] lables = req.getParameterValues("lables");
		if(lables == null || lables.length == 0 || null == type || type == 0){
			return buildFailureJson(StatusConstant.Fail_CODE, "数据异常");
		}
		List<Integer> ids = new ArrayList<Integer>();
		for (int i = 0; i < lables.length; i++) {
			ids.add(Integer.parseInt(lables[i]));
		}
		lableServiceImpl.batchDel(ids);
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "更新成功");
	}
	
}
