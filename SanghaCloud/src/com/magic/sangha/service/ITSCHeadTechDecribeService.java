package com.magic.sangha.service;

import java.util.List;

import com.magic.sangha.bean.OrderBean;
import com.magic.sangha.bean.TSCHeadTechDecribeBean;

/**
 *  �ֹ�˾���� �� �ܲ����� ��ͨ��¼ ҵ���ӿ�
 * @author QimouXie
 *
 */
public interface ITSCHeadTechDecribeService {
	
	
	
	Integer addOrderDecribe(TSCHeadTechDecribeBean decribe);
	
	/**
	 *  ���ݶ���ID ��ѯ �ö�����׷������
	 * @param orderId
	 * @return
	 */
	List<TSCHeadTechDecribeBean> findByOrderId(Integer orderId,OrderBean order);
}
