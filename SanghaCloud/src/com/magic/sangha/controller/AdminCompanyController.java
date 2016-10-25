package com.magic.sangha.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.sangha.bean.CompanyBean;
import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.GroupOfficeBean;
import com.magic.sangha.service.ICompanyService;
import com.magic.sangha.service.IGroupOfficeService;
import com.magic.sangha.util.StatusConstant;
import com.magic.sangha.util.ViewData;


/**公司管理控制层*/
@Controller
@RequestMapping("/admin/company")
public class AdminCompanyController extends BaseController {

	@Resource
	private IGroupOfficeService groupOfficeServiceImpl;
	
	@Resource
	private ICompanyService companyServiceImpl;
	
	@RequestMapping("/getcompany")
	@ResponseBody
	public ViewData getComplany(Integer pageNO,Integer pageSize,String companyName,Integer flag){
		// flag 1 查 分公司  2 查 办事处
		if(null == pageNO || null == pageSize || null == flag ){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		if(null == companyName || companyName.trim().length() == 0){
			companyName = null;
		}
		if(flag == 2){
			CutPageBean<GroupOfficeBean> offices = groupOfficeServiceImpl.findLikeOfficeName(companyName, pageSize, pageNO);
			return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", offices);
		}else if(flag == 1){
			CutPageBean<CompanyBean> coms = companyServiceImpl.findCompanyByType(pageNO, pageSize, companyName, null);
			return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", coms);
		}else{
			return buildFailureJson(StatusConstant.Fail_CODE, "数据异常");
		}
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public ViewData addCompanyOrOffice(Integer type,String companyName,Integer companyId){
		
		if(null == type ||null == companyName || companyName.trim().length() == 0){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		if(type == 1){
			// 检查公司名称是否存在
			Integer count = companyServiceImpl.queryByCompanyName(companyName);
			if(count != 0){
				return buildFailureJson(StatusConstant.Fail_CODE, "名称已经存在");
			}
			// 添加到分公司
			CompanyBean company = new CompanyBean();
			company.setCompanyName(companyName);
			company.setType(StatusConstant.FILIALE);
			companyServiceImpl.add(company);
			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "操作成功");
		}else if(type  == 2){
			// 添加 办事处
			if(null == companyId || companyId ==0){
				return buildFailureJson(StatusConstant.Fail_CODE, "数据非法");
			}
			Integer count = groupOfficeServiceImpl.queryByOfficeName(companyName);
			if(count != 0){
				return buildFailureJson(StatusConstant.Fail_CODE, "名称已经存在");
			}
			GroupOfficeBean office = new GroupOfficeBean();
			office.setCompanyId(companyId);
			office.setOfficeName(companyName);
			groupOfficeServiceImpl.addOffice(office);
			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "操作成功");
		}else{
			return buildFailureJson(StatusConstant.Fail_CODE, "提交数据异常");
		}
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public ViewData editCompanyOrOffice(Integer id,Integer type,String companyName){
		
		if(null == id || null == type || null == companyName){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		if(companyName.trim().length() == 0){
			return buildFailureJson(StatusConstant.Fail_CODE, "名称不合法");
		}
		if(type == 2){
			// 办事处
			GroupOfficeBean office  = new GroupOfficeBean();
			office.setId(id);
			office.setOfficeName(companyName);
			groupOfficeServiceImpl.update(office);
		}else if(type == 1){
			// 分公司
			CompanyBean company = new CompanyBean();
			company.setId(id);
			company.setCompanyName(companyName);
			companyServiceImpl.update(company);
		}else{
			return buildFailureJson(StatusConstant.Fail_CODE, "数据不合法");
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "操作成功");
	}
	
}
