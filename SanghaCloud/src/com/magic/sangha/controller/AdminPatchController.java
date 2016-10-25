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
 *  ����
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
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "�ֶβ���Ϊ��");
		}
		if(null == realName ||realName.trim().length() == 0 ){
			realName = null;
		}
		
		Date start = DateUtil.getDate(startTime);
		Date end  = DateUtil.getNextDay(DateUtil.getDate(endTime), 1);
		CutPageBean<HeadTechDevelopBean> data = headTechDevelopServiceImpl.queryPage(pageNO, pageSize, realName, start, end);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�", data);
	}
	
	/**����ID ��ȡ ������ϸ��Ϣ*/
	@RequestMapping("/getDetailPatchByUserId")
	@ResponseBody
	public ViewData getDetailPatchByUserId(Integer developId,Integer pageNO,Integer pageSize,String orderNumber,String startTime,String endTime){
		
		if(null == pageNO || null == pageSize || null == developId){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "�ֶβ���Ϊ��");
		}
		if(null == orderNumber || orderNumber.trim().length() == 0){
			orderNumber = null;
		}
		Date start = DateUtil.getDate(startTime);
		Date end  = DateUtil.getNextDay(DateUtil.getDate(endTime), 1);
		CutPageBean<HeadTechDevelopBean> data = headTechDevelopServiceImpl.queryPageByDevelop(developId, pageNO, pageSize, orderNumber, start, end);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�", data);
	}

}



















