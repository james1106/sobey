package com.magic.sangha.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.CompanyBean;

/**
 *  公司持久层接口
 * @author QimouXie
 *
 */
public interface ICompanyMapper {
	
	int addCompany(@Param("company")CompanyBean company);
	
	CompanyBean findById(@Param("id")Integer id);
	
	List<CompanyBean> findAll();
	
	List<CompanyBean> findAllByType(@Param("type")Integer type);
	
	/**
	 *   分页查询分公司
	 * @param typed
	 * @return
	 */
	List<CompanyBean> findCompanyByType(@Param("companyName")String companyName,@Param("type")Integer type,@Param("limit")Integer limit,@Param("limitSize")Integer limitSize ); 
	
	/**
	 *  计算满足条件 的总数
	 * @param companyName
	 * @return
	 */
	Integer countCompanyByType(@Param("companyName")String companyName,@Param("type")Integer type);
	
	Integer update(@Param("companyBean")CompanyBean company);
	
	/**根据公司名称 查询公司*/
	Integer queryByCompanyName(@Param("companyName")String companyName);

}
