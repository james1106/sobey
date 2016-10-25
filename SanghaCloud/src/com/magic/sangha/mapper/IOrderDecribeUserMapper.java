package com.magic.sangha.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.OrderDecribeUser;
/**
 *  �û�׷������(ȫ�ֿɼ�) �־ò�ӿ�
 * @author QimouXie
 *
 */
public interface IOrderDecribeUserMapper {
	
	Integer addOrderDecribe(@Param("decribe")OrderDecribeUser decribe);
	
	List<OrderDecribeUser> findByOrderId(@Param("orderId")Integer orderId);

}
