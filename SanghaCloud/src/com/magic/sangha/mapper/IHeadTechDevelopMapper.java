package com.magic.sangha.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.HeadTechDevelopBean;


/**
 *  �ܲ����� �� �ܲ��з� �Ĳ���������¼  �־ò�ӿ�
 * @author QimouXie
 *
 */
public interface IHeadTechDevelopMapper {

	
	/**
	 *   ��Ӽ�¼
	 * @param decribe
	 * @return
	 */
	Integer addDecribe(@Param("decribe")HeadTechDevelopBean decribe);
	
	/**
	 *  ���ݶ���ID��ѯ��¼
	 * @param orderId
	 * @return
	 */
	List<HeadTechDevelopBean> findByOrderId(@Param("orderId")Integer orderId);
	
	/**
	 *   ͳ�� ��ǰ ���� �Ƿ� ������
	 * @param orderId
	 * @param type
	 * @return
	 */
	Integer countIsFeedback(@Param("orderId")Integer orderId,@Param("type")Integer type);
	
	
	List<HeadTechDevelopBean> queryPage(@Param("limitSize")Integer limitSize,@Param("limit")Integer limit,@Param("realName")String realName,
			@Param("startTime")Date startTime,@Param("endTime")Date endTime);
	
	Integer countPage(@Param("realName")String realName,@Param("startTime")Date startTime,@Param("endTime")Date endTime);
	
	List<HeadTechDevelopBean> queryPageByDevelopId(@Param("developId")Integer developId,@Param("orderNumber")String orderNumber,@Param("limit")Integer limit,
			@Param("limitSize")Integer limitSize,@Param("startTime")Date startTime,@Param("endTime")Date endTime);
	
	Integer countPageByDevelopId(@Param("developId")Integer developId,@Param("orderNumber")String orderNumber,@Param("startTime")Date startTime,@Param("endTime")Date endTime);
	
	/**
	 *  �������ϲ�ѯ
	 * @param map
	 * @return
	 */
	HeadTechDevelopBean queryHeadTech(Map<String,Object> map);
	/**
	 *  �������ϲ�ѯ
	 * @param map
	 * @return
	 */
	HeadTechDevelopBean queryDevelop(Map<String,Object> map);

}














