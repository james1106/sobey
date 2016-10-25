package com.magic.sangha.controller;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.OrderCategoryBean;
import com.magic.sangha.service.IOrderCategoryService;
import com.magic.sangha.util.StatusConstant;
import com.magic.sangha.util.ViewData;

/**
 *  订单分类
 * @author QimouXie
 *
 */
@RequestMapping("/admin/ordercategory")
@Controller
public class AdminOrderCategoryController extends BaseController {

	@Resource
	private IOrderCategoryService orderCategoryServiceImpl;
	
	@RequestMapping("/getcategory")
	@ResponseBody
	public ViewData getOrderCategory(Integer type,Integer pageNO,Integer pageSize){
		
		if(null == pageNO || null == pageSize){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		if(type == 3){
			type = null;
		}
		CutPageBean<OrderCategoryBean> page = orderCategoryServiceImpl.queryPage(pageNO, pageSize, type);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", page);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public ViewData updateCategory(Integer id,String categoryName){
		
		if(null == id || categoryName == null || categoryName.trim().length() == 0){
			return buildFailureJson(StatusConstant.Fail_CODE, "数据非法");
		}
		OrderCategoryBean category = new  OrderCategoryBean();
		category.setId(id);
		category.setCategoryName(categoryName);
		orderCategoryServiceImpl.updateCategory(category);
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "操作成功");
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public ViewData addCategory(String categoryName,Integer type){
		
		if(null == categoryName || null == type || categoryName.trim().length() == 0 || type == 3){
			return buildFailureJson(StatusConstant.Fail_CODE, "数据非法");
		}
		String[] cates = categoryName.split("\\|");
		List<OrderCategoryBean> cateList = new ArrayList<OrderCategoryBean>();
		for (int i = 0; i < cates.length; i++) {
			OrderCategoryBean category = new  OrderCategoryBean();
			category.setCategoryName(cates[i]);
			category.setType(type);
			cateList.add(category);
		}
		if(cateList.size() != 0){
			orderCategoryServiceImpl.batchAdd(cateList);
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "操作成功");
	}
	
}
