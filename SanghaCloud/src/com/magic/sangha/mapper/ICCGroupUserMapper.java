package com.magic.sangha.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.CCGroupUserBean;

/**
 *  ���Ͷ��� �־ò�ӿ�
 * @author QimouXie
 *
 */
public interface ICCGroupUserMapper {
	
	/**
	 *  ��������
	 * @param ccs
	 * @return
	 */
	Integer batchAdd(@Param("ccs")List<CCGroupUserBean> ccs);
	
	/**
	 *  �����û�ID ��ѯ ���Ͷ���
	 * @param userId
	 * @return
	 */
	List<CCGroupUserBean> findBygroupUserId(@Param("userId")Integer userId);

	/**
	 *  ���ݶ���ID ��ѯ ���Ͷ���
	 * @param userId
	 * @return
	 */
	List<CCGroupUserBean> findByOrderId(@Param("orderId")Integer orderId);
	/**
	 *  ͳ�� �������û��Ķ�������
	 * @param groupUserId
	 * @return
	 */
	Integer countOrders(@Param("groupUserId")Integer groupUserId);
	
	/**
	 *  ��������ͳ�ƶ�������
	 * @param groupUserId
	 * @param categoryId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Integer countOrderByItem(@Param("status")Integer status,@Param("groupUserId")Integer groupUserId,@Param("categoryId")Integer categoryId,@Param("startDate")Date startDate,@Param("endDate")Date endDate);

}
