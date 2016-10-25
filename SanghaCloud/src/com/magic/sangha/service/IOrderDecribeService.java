package com.magic.sangha.service;

import java.util.List;

import com.magic.sangha.bean.OrderBean;
import com.magic.sangha.bean.OrderDecribeBean;

/**
 *  订单追加描述 持久层接口
 * @author QimouXie
 *
 */
public interface IOrderDecribeService {

	Integer addOrderDecribe(OrderDecribeBean decribe);
	
	/**
	 *  根据订单ID 查询 该订单的追加描述
	 * @param orderId
	 * @return
	 */
	List<OrderDecribeBean> findByOrderId(Integer orderId,OrderBean order);
	
}
