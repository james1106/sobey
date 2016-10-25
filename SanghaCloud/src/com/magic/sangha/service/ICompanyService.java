package com.magic.sangha.service;

import java.util.List;

import com.magic.sangha.bean.CompanyBean;
import com.magic.sangha.bean.CutPageBean;

/**
 *  公司业务层接口
 * @author QimouXie
 *
 */
public interface ICompanyService {
	
	CompanyBean findById(Integer id);
	
	List<CompanyBean> queryAll();
	
	List<CompanyBean> findAllByType(Integer type);
	
	/**
	 *  分页查询 指定类型公司
	 * @param pageNO
	 * @param pageSize
	 * @param companyName
	 * @return
	 */
	CutPageBean<CompanyBean> findCompanyByType(Integer pageNO,Integer pageSize,String companyName,Integer type);
	
	Integer add(CompanyBean company);
	
	Integer update(CompanyBean company);
	
	/**根据公司名称 查询公司*/
	Integer queryByCompanyName(String companyName);

}
