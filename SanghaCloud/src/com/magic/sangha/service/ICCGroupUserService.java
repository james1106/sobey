package com.magic.sangha.service;

import java.util.List;


import com.magic.sangha.bean.CCGroupUserBean;

public interface ICCGroupUserService {

	/**
	 *  ��������
	 * @param ccs
	 * @return
	 */
	Integer batchAdd(List<CCGroupUserBean> ccs);
	
	/**
	 *  �����û�ID ��ѯ ���Ͷ���
	 * @param userId
	 * @return
	 */
	List<CCGroupUserBean> findBygroupUserId(Integer userId);
	/**
	 *  ���ݶ���ID ��ѯ ���Ͷ���
	 * @param userId
	 * @return
	 */
	List<CCGroupUserBean> findByOrderId(Integer orderId);
	/**
	 *  ͳ�� �������û��Ķ�������
	 * @param groupUserId
	 * @return
	 */
	Integer countOrders(Integer groupUserId);


	
}
