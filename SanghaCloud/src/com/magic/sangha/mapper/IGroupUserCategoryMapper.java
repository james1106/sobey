package com.magic.sangha.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.GroupUserCategoryBean;

public interface IGroupUserCategoryMapper {
	/**
	 *  添加记录
	 * @param gcb
	 * @return
	 */
	Integer addGroupUserCategory(@Param("gcb")GroupUserCategoryBean gcb);
	
	/**
	 *  根据类别ID 查询所对应的技术人员
	 * @param categoryId
	 * @return
	 */
	List<GroupUserCategoryBean> findByCategory(@Param("categoryId")Integer categoryId);
	
	/**
	 *  查询所有的非编技术
	 * @return
	 */
	List<GroupUserCategoryBean> findAllByGroupUser();
	
	/**
	 *   根据技术员工ID 查询所对应的 技能分类
	 * @param groupUserId
	 * @return
	 */
	List<GroupUserCategoryBean> findCategoryByGroupUserId(@Param("groupUserId")Integer groupUserId);
	
	/**
	 *  清除 指定员工的指定技能
	 * @param groupUserId
	 * @return
	 */
	Integer delCategoryByGroupUserId(@Param("groupUserId")Integer groupUserId);
	
	/**
	 *   查询出所有的 分类问题，包括该员工所拥有的 分类技能
	 * @param groupUserId
	 * @return
	 */
	List<GroupUserCategoryBean> findCategoryAdmin(@Param("groupUserId")Integer groupUserId);
	
	/**
	 *  批量新增
	 * @return
	 */
	Integer batchAdd(@Param("categorys")List<GroupUserCategoryBean> categorys);

}
