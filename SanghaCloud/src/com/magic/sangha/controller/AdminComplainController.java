package com.magic.sangha.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.sangha.bean.ComplainBean;
import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.service.IComplainService;
import com.magic.sangha.util.DateUtil;
import com.magic.sangha.util.StatusConstant;
import com.magic.sangha.util.ViewData;

/**
 *  投诉 后台管理 控制层
 * @author QimouXie
 *
 */
@Controller
@RequestMapping("/admin/complain")
public class AdminComplainController extends BaseController {
	
	@Resource
	private IComplainService complainServiceImpl;
	
	@RequestMapping("/getpageComplain")
	@ResponseBody
	public ViewData getComplain(Integer pageNO,Integer pageSize,String startTime,String endTime){
		
		if(null == pageNO || pageSize == null){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		Date start = DateUtil.getDate(startTime);
		Date end = DateUtil.getPreSecondDate(DateUtil.getDate(endTime), -1);
		CutPageBean<ComplainBean> data = complainServiceImpl.queryPage(pageNO, pageSize, start, end);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE,"获取成功", data);
	}

}
