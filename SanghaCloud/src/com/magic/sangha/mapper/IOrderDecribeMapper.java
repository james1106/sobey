package com.magic.sangha.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.OrderDecribeBean;

/**
 *   ׷�Ӷ��������Ľӿ�
 * @author QimouXie
 *
 */
public interface IOrderDecribeMapper {
	
	Integer addOrderDecribe(@Param("decribe")OrderDecribeBean decribe);
	
	List<OrderDecribeBean> findByOrderId(@Param("orderId")Integer orderId);
	
	/**
	 *  ���ݶ���ID �� �ּ���ID ��ѯ���һ�� TSC��¼
	 * @param orderId
	 * @param tscId
	 * @return
	 */
	OrderDecribeBean queryByOrderIdAndTSC(Map<String,Object> map);

}
