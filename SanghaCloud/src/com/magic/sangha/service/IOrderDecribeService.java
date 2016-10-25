package com.magic.sangha.service;

import java.util.List;

import com.magic.sangha.bean.OrderBean;
import com.magic.sangha.bean.OrderDecribeBean;

/**
 *  ����׷������ �־ò�ӿ�
 * @author QimouXie
 *
 */
public interface IOrderDecribeService {

	Integer addOrderDecribe(OrderDecribeBean decribe);
	
	/**
	 *  ���ݶ���ID ��ѯ �ö�����׷������
	 * @param orderId
	 * @return
	 */
	List<OrderDecribeBean> findByOrderId(Integer orderId,OrderBean order);
	
}
