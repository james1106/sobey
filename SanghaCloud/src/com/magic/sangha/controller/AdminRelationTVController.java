package com.magic.sangha.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.sangha.bean.RelationTVBean;
import com.magic.sangha.service.IRelationTVService;
import com.magic.sangha.util.StatusConstant;
import com.magic.sangha.util.ViewData;


@RequestMapping("/admin/relation")
@Controller
public class AdminRelationTVController extends BaseController {

	@Resource
	private IRelationTVService relationTVServiceImpl;
	
	@RequestMapping("/getRelationTV")
	@ResponseBody
	public ViewData getRelationTv(Integer userId){
		if(null == userId){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		List<RelationTVBean> data = relationTVServiceImpl.queryByUserId(userId);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}
	
	@RequestMapping("/updateRelationTV")
	@ResponseBody
	public ViewData updateRelationTV(HttpServletRequest req){
		String strUserId = req.getParameter("userId");
		if(null == strUserId || strUserId.trim().length() == 0){
			return buildFailureJson(StatusConstant.Fail_CODE, "数据不合法");
		}
		String[] relationTVs = req.getParameterValues("relationTV");
		if(null == relationTVs || relationTVs.length == 0){
			return buildFailureJson(StatusConstant.Fail_CODE, "请选择电视台");
		}
		List<Integer> ids = new ArrayList<Integer>();
		for (int i = 0; i < relationTVs.length; i++) {
			ids.add(Integer.parseInt(relationTVs[i]));
		}
		HashSet<Integer> hashIds = new HashSet<Integer>(ids);
		List<Integer> tempIds = new ArrayList<Integer>();
		for (Integer id : hashIds) {
			tempIds.add(id);
		}
		try {
			relationTVServiceImpl.addBatchRelationTV(tempIds, Integer.parseInt(strUserId));
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "设置成功");
	}
	
	@RequestMapping("/clearAll")
	@ResponseBody
	public ViewData clearAllTV(Integer userId){
		if(null == userId){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		relationTVServiceImpl.delDataByUser(userId);
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "操作成功");
	}
	
}
