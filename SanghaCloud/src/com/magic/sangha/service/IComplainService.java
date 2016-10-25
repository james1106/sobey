package com.magic.sangha.service;


import java.util.Date;

import com.magic.sangha.bean.ComplainBean;
import com.magic.sangha.bean.CutPageBean;

/**
 *  投诉 业务层接口
 * @author QimouXie
 *
 */
public interface IComplainService {

	/**
	 *  新增投诉
	 * @param com
	 * @return
	 */
	Integer addComplain(ComplainBean com);
	
	/**
	 *  查看订单是否被投诉
	 * @param orderId
	 * @return
	 */
	ComplainBean findByOrderId(Integer orderId);
	
	/**
	 *  分页查询
	 * @param pageNO
	 * @param pageSize
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	CutPageBean<ComplainBean> queryPage(Integer pageNO,Integer pageSize,Date startTime,Date endTime);
	
}
