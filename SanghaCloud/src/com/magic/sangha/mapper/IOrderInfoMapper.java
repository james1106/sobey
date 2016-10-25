package com.magic.sangha.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.OrderInfoBean;

/**
 *  订单消息推送列表  持久层接口
 * @author QimouXie
 *
 */
public interface IOrderInfoMapper {

	/**
	 *  批量新增 订单推送信息
	 * @param info
	 * @return
	 */
	Integer batchAddOrderInfo(@Param("infos")List<OrderInfoBean> infos);
	
	/**
	 *  分页查询 当前用户的订单推送消息
	 * @param userId
	 * @param groupUserId
	 * @param limit
	 * @param limitSize
	 * @return
	 */
	List<OrderInfoBean> queryInfoList(@Param("userId")Integer userId,@Param("groupUserId")Integer groupUserId,@Param("limit")Integer limit,@Param("limitSize")Integer limitSize);
	
	/**
	 *   统计当前用户的 订单推送消息数量
	 * @param userId
	 * @param groupUserId
	 * @return
	 */
	Integer countInfo(@Param("userId")Integer userId,@Param("groupUserId")Integer groupUserId);
	
}
