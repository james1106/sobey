package com.magic.sangha.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.magic.sangha.bean.CompanyBean;
import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.mapper.ICompanyMapper;
import com.magic.sangha.service.ICompanyService;

@Service
public class CompanyServiceImpl implements ICompanyService {
	@Resource
	private ICompanyMapper companyMapper;

	@Override
	public CompanyBean findById(Integer id) {
		return companyMapper.findById(id);
	}

	@Override
	public List<CompanyBean> queryAll() {
		return companyMapper.findAll();
	}

	@Override
	public List<CompanyBean> findAllByType(Integer type) {
		return companyMapper.findAllByType(type);
	}

	@Override
	public CutPageBean<CompanyBean> findCompanyByType(Integer pageNO,Integer pageSize, String companyName,Integer type) {
		Integer limit = null;
		Integer limitSize = null;
		if(pageSize != null && pageSize != null){
			limit = (pageNO-1)*pageSize;
			limitSize = pageSize;
		}
		String likeName = null;
		if(companyName != null){
			likeName = companyName;
		}
		List<CompanyBean> dataList = companyMapper.findCompanyByType(likeName, type, limit, limitSize);
		Integer count = companyMapper.countCompanyByType(likeName, type);
		Integer totalPage = 0;
		if(null != pageSize){
			if(count % pageSize == 0){
				totalPage = count / pageSize;
			}else{
				totalPage = count / pageSize + 1;
			}
		}
		CutPageBean<CompanyBean> page = new CutPageBean<CompanyBean>(dataList,count,totalPage);
		return page;
	}
	
	@Override
	public Integer add(CompanyBean company) {
		return companyMapper.addCompany(company);
	}
	@Override
	public Integer update(CompanyBean company) {
		return companyMapper.update(company);
	}
	@Override
	public Integer queryByCompanyName(String companyName) {
		return companyMapper.queryByCompanyName(companyName);
	}

}
