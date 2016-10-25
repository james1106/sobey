package com.magic.sangha.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.OrderDecribeUser;
/**
 *  用户追加描述(全局可见) 持久层接口
 * @author QimouXie
 *
 */
public interface IOrderDecribeUserMapper {
	
	Integer addOrderDecribe(@Param("decribe")OrderDecribeUser decribe);
	
	List<OrderDecribeUser> findByOrderId(@Param("orderId")Integer orderId);

}
