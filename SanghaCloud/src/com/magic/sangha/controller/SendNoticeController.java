package com.magic.sangha.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.sangha.bean.SystemInfoBean;
import com.magic.sangha.service.ISystemInfoService;
import com.magic.sangha.util.StatusConstant;
import com.magic.sangha.util.ViewData;

@Controller
@RequestMapping("/admin/notice")
public class SendNoticeController extends BaseController {
	
	@Resource
	private ISystemInfoService systemInfoServiceImpl;
	
	private Logger logger = Logger.getLogger(this.getClass());
	@RequestMapping("/delNotice")
	public ViewData delNotice(Integer id){
		if(null == id){
			return buildFailureJson(StatusConstant.Fail_CODE, "字段不能为空");
		}
		try {
			systemInfoServiceImpl.delSystemInfo(id);
			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "操作成功");
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			return buildFailureJson(StatusConstant.Fail_CODE, "操作失败");
		}
		
	}
	
	@RequestMapping("/getSystemInfo")
	@ResponseBody
	public ViewData getInfoById(Integer systemId){
		if(null == systemId){
			return buildFailureJson(StatusConstant.Fail_CODE, "字段不能为空");
		}
		SystemInfoBean info = systemInfoServiceImpl.findById(systemId);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", info);
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/updateInfo")
	@ResponseBody
	public ViewData updateSystemInfo(Integer systemInfoId,String title,String content,String brief){
		
		if(null == systemInfoId){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		SystemInfoBean info = new SystemInfoBean();
		info.setId(systemInfoId);
		info.setTitle(title);
		info.setBrief(brief);
		info.setContent(content);
		try {
			systemInfoServiceImpl.updateSystemInfo(info);
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			return buildFailureJson(StatusConstant.Fail_CODE, "数据异常");
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "操作成功");
	}
	
}
