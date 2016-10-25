package com.magic.sangha.service;

import java.util.Date;
import java.util.List;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.HeadTechDevelopBean;
import com.magic.sangha.bean.OrderBean;

/**
 *  总部技术和总部研发 补丁描述  业务层接口
 * @author QimouXie
 *
 */
public interface IHeadTechDevelopService {
	
	Integer addOrderDecribe(HeadTechDevelopBean decribe);
	
	/**
	 *  根据订单ID 查询 该订单的追加描述
	 * @param orderId
	 * @return
	 */
	List<HeadTechDevelopBean> findByOrderId(Integer orderId,OrderBean order);
	
	Integer countIsFeedback(Integer orderId,Integer type);
	
	/**分页查询*/
	CutPageBean<HeadTechDevelopBean> queryPage(Integer pageNO,Integer pageSize,String realName,Date startTime,Date endTime);
	
	/**根据订单号 研发人员 起始日期 分页查询*/
	CutPageBean<HeadTechDevelopBean> queryPageByDevelop(Integer developId,Integer pageNO,Integer pageSize,String orderNumber,Date startTime,Date endTime);
}
