package com.magic.sangha.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.CompanyBean;

/**
 *  ��˾�־ò�ӿ�
 * @author QimouXie
 *
 */
public interface ICompanyMapper {
	
	int addCompany(@Param("company")CompanyBean company);
	
	CompanyBean findById(@Param("id")Integer id);
	
	List<CompanyBean> findAll();
	
	List<CompanyBean> findAllByType(@Param("type")Integer type);
	
	/**
	 *   ��ҳ��ѯ�ֹ�˾
	 * @param typed
	 * @return
	 */
	List<CompanyBean> findCompanyByType(@Param("companyName")String companyName,@Param("type")Integer type,@Param("limit")Integer limit,@Param("limitSize")Integer limitSize ); 
	
	/**
	 *  ������������ ������
	 * @param companyName
	 * @return
	 */
	Integer countCompanyByType(@Param("companyName")String companyName,@Param("type")Integer type);
	
	Integer update(@Param("companyBean")CompanyBean company);
	
	/**���ݹ�˾���� ��ѯ��˾*/
	Integer queryByCompanyName(@Param("companyName")String companyName);

}
