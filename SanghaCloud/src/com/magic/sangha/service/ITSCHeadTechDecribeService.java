package com.magic.sangha.service;

import java.util.List;

import com.magic.sangha.bean.OrderBean;
import com.magic.sangha.bean.TSCHeadTechDecribeBean;

/**
 *  分公司技术 和 总部技术 沟通记录 业务层接口
 * @author QimouXie
 *
 */
public interface ITSCHeadTechDecribeService {
	
	
	
	Integer addOrderDecribe(TSCHeadTechDecribeBean decribe);
	
	/**
	 *  根据订单ID 查询 该订单的追加描述
	 * @param orderId
	 * @return
	 */
	List<TSCHeadTechDecribeBean> findByOrderId(Integer orderId,OrderBean order);
}
