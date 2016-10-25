package com.magic.sangha.service;

import java.util.Date;
import java.util.List;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.HeadTechDevelopBean;
import com.magic.sangha.bean.OrderBean;

/**
 *  �ܲ��������ܲ��з� ��������  ҵ���ӿ�
 * @author QimouXie
 *
 */
public interface IHeadTechDevelopService {
	
	Integer addOrderDecribe(HeadTechDevelopBean decribe);
	
	/**
	 *  ���ݶ���ID ��ѯ �ö�����׷������
	 * @param orderId
	 * @return
	 */
	List<HeadTechDevelopBean> findByOrderId(Integer orderId,OrderBean order);
	
	Integer countIsFeedback(Integer orderId,Integer type);
	
	/**��ҳ��ѯ*/
	CutPageBean<HeadTechDevelopBean> queryPage(Integer pageNO,Integer pageSize,String realName,Date startTime,Date endTime);
	
	/**���ݶ����� �з���Ա ��ʼ���� ��ҳ��ѯ*/
	CutPageBean<HeadTechDevelopBean> queryPageByDevelop(Integer developId,Integer pageNO,Integer pageSize,String orderNumber,Date startTime,Date endTime);
}
