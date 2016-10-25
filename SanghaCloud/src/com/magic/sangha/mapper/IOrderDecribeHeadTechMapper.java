package com.magic.sangha.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.OrderDecribeHeadTechBean;

/**
 *  订单直接分配给总公司技术 之间的描述 持久层接口
 * @author QimouXie
 *
 */
public interface IOrderDecribeHeadTechMapper {
	
	/**
	 *   添加记录
	 * @param decribe
	 * @return
	 */
	Integer addDecribe(@Param("decribe")OrderDecribeHeadTechBean decribe);
	
	/**
	 *  根据订单ID查询记录
	 * @param orderId
	 * @return
	 */
	List<OrderDecribeHeadTechBean> findByOrderId(@Param("orderId")Integer orderId);
	
	/**
	 *  查询总部技术
	 * @param map
	 * @return
	 */
	OrderDecribeHeadTechBean queryHeadTech(Map<String,Object> map);
	

}
