package com.magic.sangha.service;


import java.util.Date;
import java.util.List;
import java.util.Map;
import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.OrderBean;
import com.magic.sangha.bean.OrderStatusBean;

/**
 *  ���� ҵ���ӿ�
 * @author QimouXie
 *
 */
public interface IOrderService {
	
	/**
	 *  ���Ӷ���
	 * @param order
	 * @return
	 */
	Integer addOrder(OrderBean order) throws Exception;
	/**
	 *  ���ݶ�����״̬ ��ҳ��ѯ
	 *  �˷�ҳ��ѯ�� ��ѯ ��Ա���µĶ�������ѯ����: �Ѿ����鿴 | δ�鿴
	 *  ��Ҫ������ֽ�ɫ: TSC������Ա  AND  �ͷ�
	 * @param order
	 * @param pageNO
	 * @param pageSize
	 * @return
	 */
	CutPageBean<OrderBean> findByStatus(OrderBean order,Integer pageNO,Integer pageSize,Integer isCheck);
	/**
	 *  �޸Ķ���״̬
	 *  �ڴ˶��������У��޸Ķ�����״̬
	 *  ��ĳЩ״̬�޸ĵ�ʱ����Ҫ�޸����Ӳ���Ա(���磺TSC)
	 * @param order
	 * @return
	 */
	Integer updateOrderStatus(OrderBean order);
	/**
	 *  �޸Ķ����Ĳ鿴״̬
	 *  ��ɫΪ  �ͷ�
	 * @param orderId
	 * @param isCheck
	 * @return
	 */
	Integer updateOrderServiceCheck(Integer orderId,Integer isCheck);
	/**
	 *  �޸Ķ����Ĳ鿴״̬
	 *  ��ɫΪ TSC������Ա
	 * @param orderId
	 * @param isCheck
	 * @return
	 */
	Integer updateOrderTechCheck(Integer orderId,Integer isCheck);
	/**
	 * ����״̬ �� �û�ID ��ҳ��ѯ������Ϣ
	 * �������Ҫ���ֶΣ���дnull ����Ӵ�����
	 * @param order
	 * @param pageNO
	 * @param pageSize
	 * @return
	 */
	CutPageBean<OrderBean> findByStatusAndUser(OrderBean order,Integer pageNO,Integer pageSize);
	/**
	 *  ����ID��ѯ���������ֶ�
	 * @param id
	 * @return
	 */
	OrderBean findById(Integer id);
	/**
	 *  ���ݶ���ID ��ѯ�����ĸ�������
	 * @param id
	 * @return
	 */
	OrderBean findByIdToOrderData(Integer id);
	/**
	 *  �޸Ķ��� �Ƿ���Ч
	 * @param orderId
	 * @param isValid
	 * @return
	 */
	Integer updateIsValid(Integer orderId,Integer isValid);

	/**
	 *  ͨ��ID ������ҳ��ѯ����
	 * @param ids
	 * @return
	 */
	List<OrderBean> batchQueryOrder(List<Integer> ids,Integer pageNO,Integer pageSize,Integer status);
	/**
	 *  �޸��ܲ����� �Ƿ�鿴
	 * @param orderId
	 * @param isCheck
	 * @return
	 */
	Integer updateHeadTechIsCheck(Integer orderId,Integer isCheck);
	/**
	 *  �޸��ܲ��з� �Ƿ�鿴
	 * @param orderId
	 * @param isCheck
	 * @return
	 */
	Integer updateDevelopIsCheck(Integer orderId,Integer isCheck);
	
	/**
	 *  ���¶�����������
	 * @param id
	 * @param orderData
	 * @return
	 */
	Integer updateOrderData(Integer id,String orderData);
	/**
	 *  ���¶����������� Ա����
	 * @param id
	 * @param orderData
	 * @return
	 */
	Integer updateGroupOrderData(Integer id,String orderData);
	
	/**
	 *  �����Ƿ�����
	 * @param order
	 * @return
	 */
	Integer updateIsComment(OrderBean order);
	/**
	 *  ͳ�� ������ɫ���ɵ����� ����Ҫͳ�ƵĽ�ɫ Ϊ��
	 * @param userId
	 * @param serviceId
	 * @param tscId
	 * @param headTechId
	 * @param developId
	 * @return
	 */
	Integer countOrders(Integer userId,Integer serviceId,Integer tscId,Integer headTechId,Integer developId);
	/**
	 *  ������ͳ�ƶ�������
	 * @param order
	 * @param yearMonth
	 * @return
	 */
	Map<String,Integer> countOrdersByMonth(String yearMonth,OrderBean order);
	
	/**
	 *  ������ͳ����Ч�������� ���� ��������
	 * @param yearMonth ��ѡ����
	 * @return
	 */
	Map<String,Integer> countOrdersByCategory(String yearMonth,OrderBean order);
	
	/**
	 *  ͳ�Ʊ������˵Ķ���
	 * @param yearMonth
	 * @return
	 */
	Map<String,Integer> countOrdersByCC(String yearMonth,Integer groupUserId);
	
	/**
	 *   ���ݶ������� ͳ�Ʊ�������Ա�Ķ���״̬
	 * @param yearMonth
	 * @param groupUserId
	 * @return
	 */
	Map<String,Integer> countOrderByCategoryAndCC(String yearMonth,Integer groupUserId);
	
	/**
	 *  ͳ�ƿͷ��µ� ��ͬ״̬�Ķ�������
	 * @param groupUserId
	 * @return
	 */
	OrderStatusBean countServiceOrder(Integer groupUserId);
	/**
	 * ͳ���û��µ� ��ͬ״̬�Ķ�������
	 * @param userId
	 * @return
	 */
	OrderStatusBean countUserOrder(Integer userId);
	
	/**
	 *  ͳ��TSC�µ� ��ͬ״̬�Ķ�������
	 * @param groupUserId
	 * @return
	 */
	OrderStatusBean countTSCOrder(Integer groupUserId);
	
	/**
	 *  ͳ���ܲ������µ� ��ͬ״̬�Ķ�������
	 * @param groupUserId
	 * @return
	 */
	OrderStatusBean countHeadTechOrder(Integer groupUserId);
	
	/**
	 *  ͳ���з��µ� ��ͬ״̬�Ķ�������
	 * @param groupUserId
	 * @return
	 */
	OrderStatusBean countDevelopOrder(Integer groupUserId);
	
	/**
	 *  ͳ�Ʊ�������Ա�µ� ��ͬ״̬�Ķ�������
	 * @param groupUserId
	 * @return
	 */
	OrderStatusBean countCCGroupUserOrder(Integer groupUserId);
	/**
	 *  ��̨ ������ҳ��ѯ
	 * @param orderNumber
	 * @param tvId
	 * @param pageNO
	 * @param pageSize
	 * @return
	 */
	CutPageBean<OrderBean> queryAdminPageByNumberAndTvId(String orderNumber,Integer tvId,Integer pageNO,Integer pageSize,Integer groupUserId,Integer roleId);
	
	/**
	 *  ��������ͳ�� ��������
	 * @param groupUserId
	 * @param roleId
	 * @param categoryId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Integer countOrdersByItem(Integer status,Integer groupUserId,Integer roleId,Integer categoryId,Date startDate,Date endDate);
	
	/**
	 *  ��ʱ������ ����15����û�����յĶ�����״̬Ϊ ����ͨ��
	 * @param preDate
	 * @return
	 */
	Integer updateAutoOrderStatus(Date preDate);

	
//	/**�������� ֮ ���˶���*/
//	CutPageBean<OrderBean> queryAdminSelfCenterOrder(String orderNumber,Integer pageNO,Integer pageSize,Integer groupUserId,Integer roleId);
	
}
