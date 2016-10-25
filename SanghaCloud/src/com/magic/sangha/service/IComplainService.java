package com.magic.sangha.service;


import java.util.Date;

import com.magic.sangha.bean.ComplainBean;
import com.magic.sangha.bean.CutPageBean;

/**
 *  Ͷ�� ҵ���ӿ�
 * @author QimouXie
 *
 */
public interface IComplainService {

	/**
	 *  ����Ͷ��
	 * @param com
	 * @return
	 */
	Integer addComplain(ComplainBean com);
	
	/**
	 *  �鿴�����Ƿ�Ͷ��
	 * @param orderId
	 * @return
	 */
	ComplainBean findByOrderId(Integer orderId);
	
	/**
	 *  ��ҳ��ѯ
	 * @param pageNO
	 * @param pageSize
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	CutPageBean<ComplainBean> queryPage(Integer pageNO,Integer pageSize,Date startTime,Date endTime);
	
}
