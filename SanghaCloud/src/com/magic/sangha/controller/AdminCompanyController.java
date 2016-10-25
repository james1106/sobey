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


/**��˾������Ʋ�*/
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
		// flag 1 �� �ֹ�˾  2 �� ���´�
		if(null == pageNO || null == pageSize || null == flag ){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "�ֶβ���Ϊ��");
		}
		if(null == companyName || companyName.trim().length() == 0){
			companyName = null;
		}
		if(flag == 2){
			CutPageBean<GroupOfficeBean> offices = groupOfficeServiceImpl.findLikeOfficeName(companyName, pageSize, pageNO);
			return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�", offices);
		}else if(flag == 1){
			CutPageBean<CompanyBean> coms = companyServiceImpl.findCompanyByType(pageNO, pageSize, companyName, null);
			return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�", coms);
		}else{
			return buildFailureJson(StatusConstant.Fail_CODE, "�����쳣");
		}
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public ViewData addCompanyOrOffice(Integer type,String companyName,Integer companyId){
		
		if(null == type ||null == companyName || companyName.trim().length() == 0){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "�ֶβ���Ϊ��");
		}
		if(type == 1){
			// ��鹫˾�����Ƿ����
			Integer count = companyServiceImpl.queryByCompanyName(companyName);
			if(count != 0){
				return buildFailureJson(StatusConstant.Fail_CODE, "�����Ѿ�����");
			}
			// ��ӵ��ֹ�˾
			CompanyBean company = new CompanyBean();
			company.setCompanyName(companyName);
			company.setType(StatusConstant.FILIALE);
			companyServiceImpl.add(company);
			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "�����ɹ�");
		}else if(type  == 2){
			// ��� ���´�
			if(null == companyId || companyId ==0){
				return buildFailureJson(StatusConstant.Fail_CODE, "���ݷǷ�");
			}
			Integer count = groupOfficeServiceImpl.queryByOfficeName(companyName);
			if(count != 0){
				return buildFailureJson(StatusConstant.Fail_CODE, "�����Ѿ�����");
			}
			GroupOfficeBean office = new GroupOfficeBean();
			office.setCompanyId(companyId);
			office.setOfficeName(companyName);
			groupOfficeServiceImpl.addOffice(office);
			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "�����ɹ�");
		}else{
			return buildFailureJson(StatusConstant.Fail_CODE, "�ύ�����쳣");
		}
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public ViewData editCompanyOrOffice(Integer id,Integer type,String companyName){
		
		if(null == id || null == type || null == companyName){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "�ֶβ���Ϊ��");
		}
		if(companyName.trim().length() == 0){
			return buildFailureJson(StatusConstant.Fail_CODE, "���Ʋ��Ϸ�");
		}
		if(type == 2){
			// ���´�
			GroupOfficeBean office  = new GroupOfficeBean();
			office.setId(id);
			office.setOfficeName(companyName);
			groupOfficeServiceImpl.update(office);
		}else if(type == 1){
			// �ֹ�˾
			CompanyBean company = new CompanyBean();
			company.setId(id);
			company.setCompanyName(companyName);
			companyServiceImpl.update(company);
		}else{
			return buildFailureJson(StatusConstant.Fail_CODE, "���ݲ��Ϸ�");
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "�����ɹ�");
	}
	
}
