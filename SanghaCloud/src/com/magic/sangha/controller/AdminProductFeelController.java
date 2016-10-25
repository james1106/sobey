package com.magic.sangha.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.ProductFeelBean;
import com.magic.sangha.service.IProductFeelService;
import com.magic.sangha.util.StatusConstant;
import com.magic.sangha.util.ViewData;


@RequestMapping("/admin/productfeel")
@Controller
public class AdminProductFeelController extends BaseController {
	
	@Resource
	private IProductFeelService productFeelServiceImpl;
	
	@RequestMapping("/getData")
	@ResponseBody
	public ViewData getData(Integer pageNO,Integer pageSize){
		if(null == pageNO || null == pageSize){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		CutPageBean<ProductFeelBean> data = productFeelServiceImpl.quertPage(pageNO, pageSize);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}

}
