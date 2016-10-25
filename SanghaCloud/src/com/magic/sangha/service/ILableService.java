package com.magic.sangha.service;

import java.util.List;


import com.magic.sangha.bean.LableBean;

/**
 *  评论标签  业务层接口
 * @author QimouXie
 *
 */
public interface ILableService {
	

	/**
	 *  批量添加 标签
	 * @param lables
	 * @return
	 */
	Integer batchAdd(List<LableBean> lables);
	
	
	/**
	 *  通过 角色类型 查询标签
	 * @param roleId
	 * @return
	 */
	List<LableBean> findByRoleId(Integer roleId);
	
	/**
	 *  批量更新
	 * @param ids
	 * @param type
	 */
	void updateLables(List<Integer> ids,Integer type) throws Exception;
	
	List<LableBean> findAll(Integer roleId);
	
	void batchDel(List<Integer> ids);

}
