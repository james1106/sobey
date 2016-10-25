package com.magic.sangha.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.TSCHeadTechDecribeBean;


/**
 *  TSC���ܲ������Ĺ�ͨ��¼  �־ò�ӿ�
 * @author QimouXie
 *
 */
public interface ITSCHeadTechDecribeMapper {
	
	/**
	 *   ��Ӽ�¼
	 * @param decribe
	 * @return
	 */
	Integer addDecribe(@Param("decribe")TSCHeadTechDecribeBean decribe);
	
	/**
	 *  ���ݶ���ID��ѯ��¼
	 * @param orderId
	 * @return
	 */
	List<TSCHeadTechDecribeBean> findByOrderId(@Param("orderId")Integer orderId);
	
	/**
	 *  �������ϲ�ѯ
	 * @param map
	 * @return
	 */
	TSCHeadTechDecribeBean queryTSC(Map<String,Object> map);
	/**
	 *  �������ϲ�ѯ
	 * @param map
	 * @return
	 */
	TSCHeadTechDecribeBean queryHeadTech(Map<String,Object> map);
}
