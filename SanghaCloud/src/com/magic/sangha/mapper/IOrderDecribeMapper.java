package com.magic.sangha.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.OrderDecribeBean;

/**
 *   追加订单描述的接口
 * @author QimouXie
 *
 */
public interface IOrderDecribeMapper {
	
	Integer addOrderDecribe(@Param("decribe")OrderDecribeBean decribe);
	
	List<OrderDecribeBean> findByOrderId(@Param("orderId")Integer orderId);
	
	/**
	 *  根据订单ID 和 分技术ID 查询最后一条 TSC记录
	 * @param orderId
	 * @param tscId
	 * @return
	 */
	OrderDecribeBean queryByOrderIdAndTSC(Map<String,Object> map);

}
