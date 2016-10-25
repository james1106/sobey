package com.magic.sangha.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.OrderBean;

/**
 *  ���� �־ò�ӿ�
 * @author QimouXie
 *
 */
public interface IOrderMapper {
	
	Integer addOrder(@Param("order")OrderBean order);
	
	/**
	 *  ���ݶ���״̬��ѯ
	 * @param status ����״̬
	 * @param isCheck �Ƿ�鿴
	 * @param isGroupUser �ǿͷ�: 0  ���Ǽ�����Ա(TSC) : 1
	 * @return
	 */
	List<OrderBean> findByStatus(@Param("status")Integer status,@Param("isGroupUser")Integer isGroupUser,@Param("isCheck")Integer isCheck,@Param("groupUserId")Integer groupUserId
			,@Param("pageSize")Integer pageSize,@Param("pageSizeLimit")Integer pageSizeLimit);
	
	/**
	 *  ��������ͳ������
	 * @param status
	 * @param isGroupUser
	 * @param isCheck
	 * @return
	 */
	Integer countByStatus(@Param("status")Integer status,@Param("isGroupUser")Integer isGroupUser,@Param("isCheck")Integer isCheck,@Param("groupUserId")Integer groupUserId);
	
	/**
	 *  ���¶���״̬
	 * @param status
	 * @return
	 */
	Integer updateOrderStatus(@Param("orders")OrderBean order);
	
	/**
	 *  �޸� �ͷ� ���� δ�鿴��״̬
	 * @param orderId
	 * @param isCheck
	 * @return
	 */
	Integer updateOrderServiceCheck(@Param("orderId")Integer orderId,@Param("isCheck")Integer isCheck);
	
	/**
	 *  �޸� ������Ա(TSC) ���� δ�鿴��״̬
	 * @param orderId
	 * @param isCheck
	 * @return
	 */
	Integer updateOrderTechCheck(@Param("orderId")Integer orderId,@Param("isCheck")Integer isCheck);
	
	/**
	 *  ����״̬ �� �û�ID �鿴������Ϣ
	 *  �������Ҫ���ֶΣ���дnull ����Ӵ�����
	 * @param status ״̬
	 * @param userId �û�ID
	 * @param serviceId �ͷ�ID
	 * @param tscId ������ԱID
	 * @param devolepId �з���ԱID
	 * @return ��������(δ����)
	 */
	List<OrderBean> findByStatusAndUser(@Param("status")Integer status,@Param("userId")Integer userId,@Param("serviceId")Integer serviceId,
			@Param("tscId")Integer tscId,@Param("devolepId")Integer devolepId,@Param("pageSize")Integer pageSize,@Param("pageSizeLimit")Integer pageSizeLimit
			,@Param("checked")Integer checked,@Param("serviceCheck")Integer serviceCheck,@Param("developCheck")Integer developCheck,@Param("headTechCheck")Integer headTechCheck,
			@Param("headTech")Integer headTechId,@Param("order")OrderBean order);
	
	/**
	 *  ͳ�����������������
	 * @param status
	 * @param userId
	 * @param serviceId
	 * @param tscId
	 * @param devolepId
	 * @return
	 */
	Integer countByStatusAndUser(@Param("status")Integer status,@Param("userId")Integer userId,@Param("serviceId")Integer serviceId,
			@Param("tscId")Integer tscId,@Param("devolepId")Integer devolepId);
	/**
	 *  ͳ�ƶ����������ٵĿͷ�
	 * @return
	 */
	OrderBean countMinOrder();
	
	OrderBean findById(@Param("id")Integer id);
	/**
	 *  ���ݶ���ID ��ѯ�����ĸ�������
	 * @param id
	 * @return
	 */
	OrderBean findByIdToOrderData(@Param("id")Integer id);
	
	/**
	 *  �޸Ķ��� �Ƿ���Ч
	 * @param orderId
	 * @param isValid
	 * @return
	 */
	Integer updateIsValid(@Param("id")Integer orderId,@Param("isValid")Integer isValid);

	/**
	 *  ͨ��ID ������ҳ��ѯ����
	 * @param ids
	 * @return
	 */
	List<OrderBean> batchQueryOrder(@Param("ids")List<Integer> ids,@Param("limit") Integer limit,@Param("limitSize")Integer limitSize,@Param("status")Integer status);
	
	/**
	 *  �޸��ܲ����� �Ƿ�鿴
	 * @param orderId
	 * @param isCheck
	 * @return
	 */
	Integer updateHeadTechIsCheck(@Param("id")Integer orderId,@Param("isCheck")Integer isCheck);
	/**
	 *  �޸��ܲ��з� �Ƿ�鿴
	 * @param orderId
	 * @param isCheck
	 * @return
	 */
	Integer updateDevelopIsCheck(@Param("id")Integer orderId,@Param("isCheck")Integer isCheck);
	
	/**
	 *  ���¶�����������
	 * @param id
	 * @param orderData
	 * @return
	 */
	Integer updateOrderData(@Param("id")Integer id,@Param("orderData")String orderData);
	
	/**
	 *  ���¶����������� Ա����
	 * @param id
	 * @param orderData
	 * @return
	 */
	Integer updateGroupOrderData(@Param("id")Integer id,@Param("orderData")String orderData);
	
	/**
	 *  �����Ƿ�����
	 * @param id
	 * @param isServiceComment
	 * @param isTSCComment
	 * @param isHeadDevelopComment
	 * @param isHeadTechComment
	 * @return
	 */
	Integer updateIsComment(@Param("id")Integer id,@Param("isServiceComment")Integer isServiceComment,@Param("isTSCComment")Integer isTSCComment,
			@Param("isHeadDevelopComment")Integer isHeadDevelopComment,@Param("isHeadTechComment")Integer isHeadTechComment,@Param("isUsercomment")Integer isUsercomment);
	/**
	 *  ͳ�� ������ɫ���ɵ����� ����Ҫͳ�ƵĽ�ɫ Ϊ��
	 * @param userId
	 * @param serviceId
	 * @param tscId
	 * @param headTechId
	 * @param developId
	 * @return
	 */
	Integer countOrders(@Param("userId")Integer userId,@Param("serviceId")Integer serviceId,@Param("tscId")Integer tscId,@Param("headTechId")Integer headTechId,@Param("developId")Integer developId);
	
	/**
	 *  ������ͳ�ƶ�������
	 * @param yearMonth ��ѡ����
	 * @param userId
	 * @param status δ���  ����� ͳ�� �����0 �����  1 δ���
	 * @return
	 */
	Integer countOrdersByMonth(@Param("yearmonth")String yearMonth,@Param("status")Integer status,@Param("order")OrderBean order);
	
	/**
	 *  ������ͳ����Ч�������� ���� ��������
	 * @param yearMonth ��ѡ����
	 * @return
	 */
	List<OrderBean> countOrdersByCategory(@Param("yearmonth")String yearMonth,@Param("order")OrderBean order);
	/**
	 *  ������ѯ����
	 * @param ids
	 * @return
	 */
	List<OrderBean> batchQueryOrders(@Param("ids")List<Integer> ids);
	/**
	 *  ���������ѯ������ ���� ������������
	 * @param ids
	 * @return
	 */
	List<OrderBean> batchQueryOrderByCategory(@Param("ids")List<Integer> ids);
	
	/**
	 *  ͳ�� ������ɫ ��������
	 * @param order
	 * @return
	 */
	Integer countBacklog(@Param("order")OrderBean order);
	
	/**
	 *  ͳ�ƴ����� �Ķ�������
	 * @param order
	 * @return
	 */
	Integer countAssigning(@Param("order")OrderBean order);
	
	/**
	 *  ͳ�� ������ɫ ������
	 * @param order
	 * @return
	 */
	Integer countPengding(@Param("order")OrderBean order);
	
	/**
	 *  ͳ�� ������ɫ �����ж�������
	 * @param order
	 * @return
	 */
	Integer countHandling(@Param("order")OrderBean order);
	
	/**
	 *  ͳ�Ƹ�����ɫ ����֤ ��������
	 * @param order
	 * @return
	 */
	Integer countAccepting(@Param("order")OrderBean order);
	
	/**
	 *  ͳ�Ƹ�����ɫ ������ ��������
	 * @param order
	 * @return
	 */
	Integer countEvaluating(@Param("order")OrderBean order);
	
	/**
	 *  ��̨ ��ҳ��ѯ ����
	 * @param tvId
	 * @param number
	 * @param limit
	 * @param limitSize
	 * @return
	 */
	List<OrderBean> queryAdminPageByNumberAndTvId(@Param("tvId")Integer tvId,@Param("number")String number,@Param("limit")Integer limit,@Param("limitSize")Integer limitSize,
			@Param("groupUserId")Integer groupUserId,@Param("roleId")Integer roleId);
	/**
	 *  ��̨ ͳ�ƶ���
	 * @param tvId
	 * @param number
	 * @return
	 */
	Integer countAdminPageByNumberAndTvId(@Param("tvId")Integer tvId,@Param("number")String number,@Param("groupUserId")Integer groupUserId,@Param("roleId")Integer roleId);
	
	/**
	 *  ���ݵ���ͳ�ƶ�������
	 * @param order
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Integer countOrdersByItem(@Param("order")OrderBean order,@Param("startDate")Date startDate,@Param("endDate")Date endDate);
	
	/**���¶���*/
	Integer updateOrder(@Param("order")OrderBean order);
	
	/**
	 *  ��ʱ������ ����15����û�����յĶ�����״̬Ϊ ����ͨ��
	 * @param preDate
	 * @return
	 */
	Integer updateAutoOrderStatus(@Param("preDate")Date preDate);
}





















