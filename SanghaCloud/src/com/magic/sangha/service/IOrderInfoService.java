package com.magic.sangha.service;

import java.util.List;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.OrderInfoBean;

/**
 *  ��Ϣ���� ҵ���ӿ�
 * @author QimouXie
 *
 */
public interface IOrderInfoService {
	
	/**
	 *  �������� ����������Ϣ
	 * @param info
	 * @return
	 */
	void batchAddOrderInfo(List<OrderInfoBean> infos) throws Exception;
	
	/**
	 *  ��ҳ��ѯ
	 * @param userId
	 * @param groupUserId
	 * @param pageNO
	 * @param pageSize
	 * @return
	 */
	CutPageBean<OrderInfoBean> quertPage(Integer userId,Integer groupUserId,Integer pageNO,Integer pageSize);
	
	
	

}
