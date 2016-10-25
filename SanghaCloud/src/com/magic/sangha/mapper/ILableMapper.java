package com.magic.sangha.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.LableBean;

/**
 *  评价标签 持久层接口
 * @author QimouXie
 *
 */
public interface ILableMapper {
	
	/**
	 *  批量添加 标签
	 * @param lables
	 * @return
	 */
	Integer batchAdd(@Param("lables")List<LableBean> lables);
	
	
	/**
	 *  通过 角色类型 查询标签
	 * @param roleId
	 * @return
	 */
	List<LableBean> findByRoleId(@Param("roleId")Integer roleId);
	
	/**
	 *  批量查询
	 * @param ids
	 * @return
	 */
	List<LableBean> batchSelect(@Param("ids")List<Integer> ids);
	
	/**
	 *  批量更新 
	 * @param ids
	 * @param type
	 */
	void batchUpdateLables(@Param("ids")List<Integer> ids,@Param("type")Integer type);
	
	/**	 */
	List<LableBean> findAll(@Param("roleId")Integer roleId);
	
	/**批量删除*/
	void batchDel(@Param("ids")List<Integer> ids);
	
	
}
