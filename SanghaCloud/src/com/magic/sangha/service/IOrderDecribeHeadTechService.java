package com.magic.sangha.service;

import java.util.List;



import com.magic.sangha.bean.OrderBean;
import com.magic.sangha.bean.OrderDecribeHeadTechBean;

/**
 *  订单直接分配给总公司技术 之间的描述 业务层接口
 * @author QimouXie
 *
 */
public interface IOrderDecribeHeadTechService {
	
	/**
	 *   添加记录
	 * @param decribe
	 * @return
	 */
	Integer addDecribe(OrderDecribeHeadTechBean decribe);
	
	/**
	 *  根据订单ID查询记录
	 * @param orderId
	 * @return
	 */
	List<OrderDecribeHeadTechBean> findByOrderId(Integer orderId,OrderBean order);

}
