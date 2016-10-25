package com.magic.sangha.service;

import java.util.List;

import com.magic.sangha.bean.OrderDecribeUser;

/**
 *  用户追加描述  全局可见   业务层接口
 * @author QimouXie
 *
 */
public interface IOrderDecribeUserService {
	
	Integer addOrderDecribe(OrderDecribeUser decribe);
	
	List<OrderDecribeUser> findByOrderId(Integer orderId);

}
