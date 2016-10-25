package com.magic.sangha.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.OrderDecribeHeadTechBean;

/**
 *  ����ֱ�ӷ�����ܹ�˾���� ֮������� �־ò�ӿ�
 * @author QimouXie
 *
 */
public interface IOrderDecribeHeadTechMapper {
	
	/**
	 *   ��Ӽ�¼
	 * @param decribe
	 * @return
	 */
	Integer addDecribe(@Param("decribe")OrderDecribeHeadTechBean decribe);
	
	/**
	 *  ���ݶ���ID��ѯ��¼
	 * @param orderId
	 * @return
	 */
	List<OrderDecribeHeadTechBean> findByOrderId(@Param("orderId")Integer orderId);
	
	/**
	 *  ��ѯ�ܲ�����
	 * @param map
	 * @return
	 */
	OrderDecribeHeadTechBean queryHeadTech(Map<String,Object> map);
	

}
