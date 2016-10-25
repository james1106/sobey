package com.magic.sangha.service;

import java.util.List;


import com.magic.sangha.bean.CCGroupUserBean;

public interface ICCGroupUserService {

	/**
	 *  批量新增
	 * @param ccs
	 * @return
	 */
	Integer batchAdd(List<CCGroupUserBean> ccs);
	
	/**
	 *  根据用户ID 查询 抄送订单
	 * @param userId
	 * @return
	 */
	List<CCGroupUserBean> findBygroupUserId(Integer userId);
	/**
	 *  根据订单ID 查询 抄送订单
	 * @param userId
	 * @return
	 */
	List<CCGroupUserBean> findByOrderId(Integer orderId);
	/**
	 *  统计 被抄送用户的订单数量
	 * @param groupUserId
	 * @return
	 */
	Integer countOrders(Integer groupUserId);


	
}
