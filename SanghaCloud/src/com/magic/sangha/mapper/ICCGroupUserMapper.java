package com.magic.sangha.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.CCGroupUserBean;

/**
 *  抄送订单 持久层接口
 * @author QimouXie
 *
 */
public interface ICCGroupUserMapper {
	
	/**
	 *  批量新增
	 * @param ccs
	 * @return
	 */
	Integer batchAdd(@Param("ccs")List<CCGroupUserBean> ccs);
	
	/**
	 *  根据用户ID 查询 抄送订单
	 * @param userId
	 * @return
	 */
	List<CCGroupUserBean> findBygroupUserId(@Param("userId")Integer userId);

	/**
	 *  根据订单ID 查询 抄送订单
	 * @param userId
	 * @return
	 */
	List<CCGroupUserBean> findByOrderId(@Param("orderId")Integer orderId);
	/**
	 *  统计 被抄送用户的订单数量
	 * @param groupUserId
	 * @return
	 */
	Integer countOrders(@Param("groupUserId")Integer groupUserId);
	
	/**
	 *  根据条件统计订单数量
	 * @param groupUserId
	 * @param categoryId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Integer countOrderByItem(@Param("status")Integer status,@Param("groupUserId")Integer groupUserId,@Param("categoryId")Integer categoryId,@Param("startDate")Date startDate,@Param("endDate")Date endDate);

}
