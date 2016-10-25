package com.magic.sangha.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.OrderInfoBean;

/**
 *  ������Ϣ�����б�  �־ò�ӿ�
 * @author QimouXie
 *
 */
public interface IOrderInfoMapper {

	/**
	 *  �������� ����������Ϣ
	 * @param info
	 * @return
	 */
	Integer batchAddOrderInfo(@Param("infos")List<OrderInfoBean> infos);
	
	/**
	 *  ��ҳ��ѯ ��ǰ�û��Ķ���������Ϣ
	 * @param userId
	 * @param groupUserId
	 * @param limit
	 * @param limitSize
	 * @return
	 */
	List<OrderInfoBean> queryInfoList(@Param("userId")Integer userId,@Param("groupUserId")Integer groupUserId,@Param("limit")Integer limit,@Param("limitSize")Integer limitSize);
	
	/**
	 *   ͳ�Ƶ�ǰ�û��� ����������Ϣ����
	 * @param userId
	 * @param groupUserId
	 * @return
	 */
	Integer countInfo(@Param("userId")Integer userId,@Param("groupUserId")Integer groupUserId);
	
}
