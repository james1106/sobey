package com.magic.sangha.service;

import java.util.List;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.OrderInfoBean;

/**
 *  消息中心 业务层接口
 * @author QimouXie
 *
 */
public interface IOrderInfoService {
	
	/**
	 *  批量新增 订单推送信息
	 * @param info
	 * @return
	 */
	void batchAddOrderInfo(List<OrderInfoBean> infos) throws Exception;
	
	/**
	 *  分页查询
	 * @param userId
	 * @param groupUserId
	 * @param pageNO
	 * @param pageSize
	 * @return
	 */
	CutPageBean<OrderInfoBean> quertPage(Integer userId,Integer groupUserId,Integer pageNO,Integer pageSize);
	
	
	

}
