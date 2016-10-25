package com.magic.sangha.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.HeadTechDevelopBean;
import com.magic.sangha.service.IHeadTechDevelopService;
import com.magic.sangha.util.DateUtil;
import com.magic.sangha.util.StatusConstant;
import com.magic.sangha.util.ViewData;

/**
 *  补丁
 * @author QimouXie
 *
 */
@Controller
@RequestMapping("/admin/patch")
public class AdminPatchController extends BaseController {
	
	@Resource
	private IHeadTechDevelopService headTechDevelopServiceImpl;
	
	@RequestMapping("/getPatchData")
	@ResponseBody
	public ViewData getPatchData(Integer pageNO,Integer pageSize,String realName,String startTime,String endTime){
		
		if(null == pageNO || null == pageSize){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		if(null == realName ||realName.trim().length() == 0 ){
			realName = null;
		}
		
		Date start = DateUtil.getDate(startTime);
		Date end  = DateUtil.getNextDay(DateUtil.getDate(endTime), 1);
		CutPageBean<HeadTechDevelopBean> data = headTechDevelopServiceImpl.queryPage(pageNO, pageSize, realName, start, end);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}
	
	/**根据ID 获取 补丁详细信息*/
	@RequestMapping("/getDetailPatchByUserId")
	@ResponseBody
	public ViewData getDetailPatchByUserId(Integer developId,Integer pageNO,Integer pageSize,String orderNumber,String startTime,String endTime){
		
		if(null == pageNO || null == pageSize || null == developId){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		if(null == orderNumber || orderNumber.trim().length() == 0){
			orderNumber = null;
		}
		Date start = DateUtil.getDate(startTime);
		Date end  = DateUtil.getNextDay(DateUtil.getDate(endTime), 1);
		CutPageBean<HeadTechDevelopBean> data = headTechDevelopServiceImpl.queryPageByDevelop(developId, pageNO, pageSize, orderNumber, start, end);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}

}



















