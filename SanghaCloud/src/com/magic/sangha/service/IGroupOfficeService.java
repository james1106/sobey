package com.magic.sangha.service;

import java.util.List;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.GroupOfficeBean;

/**
 *  办事处 业务层接口
 * @author QimouXie
 *
 */
public interface IGroupOfficeService {

	
	Integer addOffice(GroupOfficeBean office);
	
	/**
	 *  根据公司 查询办事处
	 * @param companyId
	 * @return
	 */
	List<GroupOfficeBean> findByComplanyId(Integer companyId);
	
	/**
	 *  分页模糊查询办事处
	 * @param officeName
	 * @param pageSize
	 * @param pageSizeLimit
	 * @return
	 */
	CutPageBean<GroupOfficeBean> findLikeOfficeName (String officeName,Integer pageSize,Integer pageNO);
	
	GroupOfficeBean findById(Integer id);
	/**
	 *  根据普通用户ID 查询办事处
	 * @param userId
	 * @return
	 */
	GroupOfficeBean findByUserId(Integer userId);
	
	Integer update(GroupOfficeBean office);
	
	/**根据公司名称查询公司*/
	Integer queryByOfficeName(String officeName);
}
