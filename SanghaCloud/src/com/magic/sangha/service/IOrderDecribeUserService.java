package com.magic.sangha.service;

import java.util.List;

import com.magic.sangha.bean.OrderDecribeUser;

/**
 *  �û�׷������  ȫ�ֿɼ�   ҵ���ӿ�
 * @author QimouXie
 *
 */
public interface IOrderDecribeUserService {
	
	Integer addOrderDecribe(OrderDecribeUser decribe);
	
	List<OrderDecribeUser> findByOrderId(Integer orderId);

}
