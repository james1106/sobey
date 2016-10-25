package com.magic.sangha.service;

import java.util.List;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.TVBean;

/**
 *  电视台管理 业务层接口
 * @author QimouXie
 *
 */
public interface ITVService {
	
	Integer addTV(TVBean tv);
	
	/**
	 *  根据 办事处 查询电视台
	 * @param officeId
	 * @return
	 */
	List<TVBean> findByOfficeId(Integer officeId);
	
	TVBean findById(Integer id);

	/**
	 *  批量添加 电视台
	 * @param tvs
	 * @return
	 */
	List<TVBean> batchAddTV(List<TVBean> tvs);
	/**
	 *  根据状态分页查询 电视台
	 * @param pageNO
	 * @param pageSize
	 * @param officeStatus
	 * @return
	 */
	CutPageBean<TVBean> findByStatus(Integer pageNO,Integer pageSize,Integer officeStatus,String tvName);
	
	Integer updateTVToOffice(Integer id,Integer officeId);
	/**
	 *  通过办事处 查询当下的电视台
	 * @param officeId
	 * @return
	 */
	List<TVBean> findTVSByOffice(Integer officeId);
	
	/**
	 *  获取用户 分配订单，订单所属的电视台
	 * @param userId
	 * @param groupUserId
	 * @return
	 */
	List<TVBean> findByUserIdAndOrder(Integer userId,Integer groupUserId,Integer roleId);
	
	/**
	 *  动态 模糊查询电视台
	 * @param tvName
	 * @return
	 */
	List<TVBean> dynamicGetTvs(String tvName);
	/***统计同名电视台 的数量*/
	Integer queryTVByTVName(String tvName);
	
	/**根据用户类型来查询 电视台*/
	List<TVBean> getTVByUserType(Integer roleId,Integer userId);
	
	Integer updateTV(TVBean tv);
}
