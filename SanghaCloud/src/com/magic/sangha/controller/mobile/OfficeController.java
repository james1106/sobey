package com.magic.sangha.controller.mobile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.GroupOfficeBean;
import com.magic.sangha.bean.TVBean;
import com.magic.sangha.controller.BaseController;
import com.magic.sangha.service.IGroupOfficeService;
import com.magic.sangha.service.ITVService;
import com.magic.sangha.util.StatusConstant;
import com.magic.sangha.util.ViewData;


@Controller
@RequestMapping("/officeMobile")
public class OfficeController extends BaseController {
	
	@Resource
	private IGroupOfficeService groupServiceImpl;
	
	@Resource
	private ITVService tvServiceImpl;
	
	
	/**
	 *  ��ȡ��ҳ�İ��´�
	 * @return
	 */
	@RequestMapping("/getOffice")
	@ResponseBody
	public ViewData getOffice(String officeName,Integer pageSize,Integer pageNO,Integer companyId){
//		if(null == pageSize || pageSize == 0){
//			return buildFailureJson(-1, "ÿҳ��ʾ���������Ϸ�");
//		}
//		if(null == pageNO || pageNO == 0){
//			return buildFailureJson(-1,"ҳ�벻�Ϸ�");
//		}
		Map<String,Object> data = new HashMap<String, Object>();
		if( companyId == null){
			CutPageBean<GroupOfficeBean> pageData = groupServiceImpl.findLikeOfficeName(officeName, pageSize, pageNO);
			if(null == pageData){
				return buildFailureJson(StatusConstant.Fail_CODE, "��ǰû������");
			}
			data.put("lists", pageData.getDataList());
		}else{
			data.put("lists",groupServiceImpl.findByComplanyId(companyId));
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ���ݳɹ�", data);
	}
	
	@RequestMapping("/getTv")
	@ResponseBody
	public ViewData getTV(Integer officeId){
		if(null == officeId){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "�ֶβ���Ϊ��");
		}
		List<TVBean> tvs = tvServiceImpl.findByOfficeId(officeId);
		Map<String,Object> tvmap = new HashMap<String, Object>();
		tvmap.put("lists", tvs);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�", tvmap);
	}
	
	@RequestMapping("/getOfficeByCompany")
	@ResponseBody
	public ViewData getOfficeByCompany(Integer companyId){
		if(null == companyId){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "�ֶβ���Ϊ��");
		}
		if(0 == companyId){
			return buildFailureJson(StatusConstant.NO_DATA, "û������");
		}
		List<GroupOfficeBean> officeList = groupServiceImpl.findByComplanyId(companyId);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�", officeList);
	}
	
	

}















