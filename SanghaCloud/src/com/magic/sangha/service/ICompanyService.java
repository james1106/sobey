package com.magic.sangha.service;

import java.util.List;

import com.magic.sangha.bean.CompanyBean;
import com.magic.sangha.bean.CutPageBean;

/**
 *  ��˾ҵ���ӿ�
 * @author QimouXie
 *
 */
public interface ICompanyService {
	
	CompanyBean findById(Integer id);
	
	List<CompanyBean> queryAll();
	
	List<CompanyBean> findAllByType(Integer type);
	
	/**
	 *  ��ҳ��ѯ ָ�����͹�˾
	 * @param pageNO
	 * @param pageSize
	 * @param companyName
	 * @return
	 */
	CutPageBean<CompanyBean> findCompanyByType(Integer pageNO,Integer pageSize,String companyName,Integer type);
	
	Integer add(CompanyBean company);
	
	Integer update(CompanyBean company);
	
	/**���ݹ�˾���� ��ѯ��˾*/
	Integer queryByCompanyName(String companyName);

}
