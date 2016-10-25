package com.magic.sangha.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.SuggestBean;
import com.magic.sangha.service.ISuggestService;
import com.magic.sangha.util.StatusConstant;
import com.magic.sangha.util.ViewData;


@Controller
@RequestMapping("/admin/suggest")
public class AdminSuggestController extends BaseController {
	
	@Resource
	private ISuggestService suggestServiceImpl;
	
	@RequestMapping("/getSuggest")
	@ResponseBody
	public ViewData getSuggest(Integer pageNO,Integer pageSize){
		
		if(null == pageNO || null == pageSize){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		CutPageBean<SuggestBean> data = suggestServiceImpl.queryPage(pageNO, pageSize);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
		
	}

}
