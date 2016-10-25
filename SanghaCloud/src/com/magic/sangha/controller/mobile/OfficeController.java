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
	 *  获取分页的办事处
	 * @return
	 */
	@RequestMapping("/getOffice")
	@ResponseBody
	public ViewData getOffice(String officeName,Integer pageSize,Integer pageNO,Integer companyId){
//		if(null == pageSize || pageSize == 0){
//			return buildFailureJson(-1, "每页显示的数量不合法");
//		}
//		if(null == pageNO || pageNO == 0){
//			return buildFailureJson(-1,"页码不合法");
//		}
		Map<String,Object> data = new HashMap<String, Object>();
		if( companyId == null){
			CutPageBean<GroupOfficeBean> pageData = groupServiceImpl.findLikeOfficeName(officeName, pageSize, pageNO);
			if(null == pageData){
				return buildFailureJson(StatusConstant.Fail_CODE, "当前没有数据");
			}
			data.put("lists", pageData.getDataList());
		}else{
			data.put("lists",groupServiceImpl.findByComplanyId(companyId));
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取数据成功", data);
	}
	
	@RequestMapping("/getTv")
	@ResponseBody
	public ViewData getTV(Integer officeId){
		if(null == officeId){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		List<TVBean> tvs = tvServiceImpl.findByOfficeId(officeId);
		Map<String,Object> tvmap = new HashMap<String, Object>();
		tvmap.put("lists", tvs);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", tvmap);
	}
	
	@RequestMapping("/getOfficeByCompany")
	@ResponseBody
	public ViewData getOfficeByCompany(Integer companyId){
		if(null == companyId){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		if(0 == companyId){
			return buildFailureJson(StatusConstant.NO_DATA, "没有数据");
		}
		List<GroupOfficeBean> officeList = groupServiceImpl.findByComplanyId(companyId);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", officeList);
	}
	
	

}















