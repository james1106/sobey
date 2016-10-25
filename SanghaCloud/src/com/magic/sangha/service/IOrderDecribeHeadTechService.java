package com.magic.sangha.service;

import java.util.List;



import com.magic.sangha.bean.OrderBean;
import com.magic.sangha.bean.OrderDecribeHeadTechBean;

/**
 *  ����ֱ�ӷ�����ܹ�˾���� ֮������� ҵ���ӿ�
 * @author QimouXie
 *
 */
public interface IOrderDecribeHeadTechService {
	
	/**
	 *   ��Ӽ�¼
	 * @param decribe
	 * @return
	 */
	Integer addDecribe(OrderDecribeHeadTechBean decribe);
	
	/**
	 *  ���ݶ���ID��ѯ��¼
	 * @param orderId
	 * @return
	 */
	List<OrderDecribeHeadTechBean> findByOrderId(Integer orderId,OrderBean order);

}
