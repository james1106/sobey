package com.magic.sangha.service;

import java.util.List;


import com.magic.sangha.bean.GroupUserCategoryBean;

/**
 *  技术员工和相关专业 业务层接口
 * @author QimouXie
 *
 */
public interface IGroupUserCategoryService {
	/**
	 *  添加记录
	 * @param gcb
	 * @return
	 */
	Integer addGroupUserCategory(GroupUserCategoryBean gcb);
	
	/**
	 *  根据类别ID 查询所对应的技术人员
	 * @param categoryId
	 * @return
	 */
	List<GroupUserCategoryBean> findByCategory(Integer categoryId);
	
	/**
	 *  仅仅只有 groupUser字段
	 * @return
	 */
	List<GroupUserCategoryBean> findAllByGroupUser();
	

	/**
	 *  清除 指定员工的指定技能
	 * @param groupUserId
	 * @return
	 */
	Integer delCategoryByGroupUserId(Integer groupUserId) throws Exception;
	
	/**
	 *   查询出所有的 分类问题，包括该员工所拥有的 分类技能
	 * @param groupUserId
	 * @return
	 */
	List<GroupUserCategoryBean> findCategoryAdmin(Integer groupUserId);
	
	void updateGroupUserTech(List<GroupUserCategoryBean> categorys,Integer groupUserId) throws Exception;

}
