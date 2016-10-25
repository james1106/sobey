package com.magic.sangha.controller.mobile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.sangha.bean.CompanyBean;
import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.controller.BaseController;
import com.magic.sangha.service.ICompanyService;
import com.magic.sangha.util.StatusConstant;
import com.magic.sangha.util.ViewData;

@Controller
@RequestMapping("/companyMobile")
public class CompanyController extends BaseController {

	@Resource
	private ICompanyService companyServiceImpl;
	
	@RequestMapping("/queryCompany")
	@ResponseBody
	public ViewData cutPageFindCompany(Integer pageNO,Integer pageSize,String companyName){
		CutPageBean<CompanyBean> page = companyServiceImpl.findCompanyByType(pageNO, pageSize, companyName, StatusConstant.FILIALE);
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("lists", page.getDataList());
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}
	
	@RequestMapping("/queryCompanyByType")
	@ResponseBody
	public ViewData findCompanyByType(){
		List<CompanyBean> coms = companyServiceImpl.findAllByType(StatusConstant.FILIALE);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", coms);
	}
	
	@RequestMapping("/queryHeadCompany")
	@ResponseBody
	public ViewData findHeadCompany(){
		List<CompanyBean> coms = companyServiceImpl.findAllByType(StatusConstant.PARENT_COMPANY);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", coms);
	}
	
	
}
