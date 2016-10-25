package com.magic.sangha.service;


import java.util.Date;
import java.util.List;
import java.util.Map;
import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.OrderBean;
import com.magic.sangha.bean.OrderStatusBean;

/**
 *  订单 业务层接口
 * @author QimouXie
 *
 */
public interface IOrderService {
	
	/**
	 *  增加订单
	 * @param order
	 * @return
	 */
	Integer addOrder(OrderBean order) throws Exception;
	/**
	 *  根据订单的状态 分页查询
	 *  此分页查询是 查询 该员工下的订单，查询条件: 已经被查看 | 未查看
	 *  主要针对两种角色: TSC技术人员  AND  客服
	 * @param order
	 * @param pageNO
	 * @param pageSize
	 * @return
	 */
	CutPageBean<OrderBean> findByStatus(OrderBean order,Integer pageNO,Integer pageSize,Integer isCheck);
	/**
	 *  修改订单状态
	 *  在此订单操作中，修改订单的状态
	 *  在某些状态修改的时候，需要修改增加操作员(比如：TSC)
	 * @param order
	 * @return
	 */
	Integer updateOrderStatus(OrderBean order);
	/**
	 *  修改订单的查看状态
	 *  角色为  客服
	 * @param orderId
	 * @param isCheck
	 * @return
	 */
	Integer updateOrderServiceCheck(Integer orderId,Integer isCheck);
	/**
	 *  修改订单的查看状态
	 *  角色为 TSC技术人员
	 * @param orderId
	 * @param isCheck
	 * @return
	 */
	Integer updateOrderTechCheck(Integer orderId,Integer isCheck);
	/**
	 * 根据状态 和 用户ID 分页查询订单信息
	 * 如果不需要的字段，填写null 则不添加此条件
	 * @param order
	 * @param pageNO
	 * @param pageSize
	 * @return
	 */
	CutPageBean<OrderBean> findByStatusAndUser(OrderBean order,Integer pageNO,Integer pageSize);
	/**
	 *  根据ID查询订单所有字段
	 * @param id
	 * @return
	 */
	OrderBean findById(Integer id);
	/**
	 *  根据订单ID 查询订单的跟踪数据
	 * @param id
	 * @return
	 */
	OrderBean findByIdToOrderData(Integer id);
	/**
	 *  修改订单 是否有效
	 * @param orderId
	 * @param isValid
	 * @return
	 */
	Integer updateIsValid(Integer orderId,Integer isValid);

	/**
	 *  通过ID 批量分页查询订单
	 * @param ids
	 * @return
	 */
	List<OrderBean> batchQueryOrder(List<Integer> ids,Integer pageNO,Integer pageSize,Integer status);
	/**
	 *  修改总部技术 是否查看
	 * @param orderId
	 * @param isCheck
	 * @return
	 */
	Integer updateHeadTechIsCheck(Integer orderId,Integer isCheck);
	/**
	 *  修改总部研发 是否查看
	 * @param orderId
	 * @param isCheck
	 * @return
	 */
	Integer updateDevelopIsCheck(Integer orderId,Integer isCheck);
	
	/**
	 *  更新订单跟踪数据
	 * @param id
	 * @param orderData
	 * @return
	 */
	Integer updateOrderData(Integer id,String orderData);
	/**
	 *  更新订单跟踪数据 员工版
	 * @param id
	 * @param orderData
	 * @return
	 */
	Integer updateGroupOrderData(Integer id,String orderData);
	
	/**
	 *  更新是否评论
	 * @param order
	 * @return
	 */
	Integer updateIsComment(OrderBean order);
	/**
	 *  统计 各个角色的派单数量 不需要统计的角色 为空
	 * @param userId
	 * @param serviceId
	 * @param tscId
	 * @param headTechId
	 * @param developId
	 * @return
	 */
	Integer countOrders(Integer userId,Integer serviceId,Integer tscId,Integer headTechId,Integer developId);
	/**
	 *  按年月统计订单数量
	 * @param order
	 * @param yearMonth
	 * @return
	 */
	Map<String,Integer> countOrdersByMonth(String yearMonth,OrderBean order);
	
	/**
	 *  按年月统计有效订单数量 根据 问题类型
	 * @param yearMonth 必选参数
	 * @return
	 */
	Map<String,Integer> countOrdersByCategory(String yearMonth,OrderBean order);
	
	/**
	 *  统计被抄送人的订单
	 * @param yearMonth
	 * @return
	 */
	Map<String,Integer> countOrdersByCC(String yearMonth,Integer groupUserId);
	
	/**
	 *   根据订单类型 统计被抄送人员的订单状态
	 * @param yearMonth
	 * @param groupUserId
	 * @return
	 */
	Map<String,Integer> countOrderByCategoryAndCC(String yearMonth,Integer groupUserId);
	
	/**
	 *  统计客服下的 不同状态的订单数量
	 * @param groupUserId
	 * @return
	 */
	OrderStatusBean countServiceOrder(Integer groupUserId);
	/**
	 * 统计用户下的 不同状态的订单数量
	 * @param userId
	 * @return
	 */
	OrderStatusBean countUserOrder(Integer userId);
	
	/**
	 *  统计TSC下的 不同状态的订单数量
	 * @param groupUserId
	 * @return
	 */
	OrderStatusBean countTSCOrder(Integer groupUserId);
	
	/**
	 *  统计总部技术下的 不同状态的订单数量
	 * @param groupUserId
	 * @return
	 */
	OrderStatusBean countHeadTechOrder(Integer groupUserId);
	
	/**
	 *  统计研发下的 不同状态的订单数量
	 * @param groupUserId
	 * @return
	 */
	OrderStatusBean countDevelopOrder(Integer groupUserId);
	
	/**
	 *  统计被抄送人员下的 不同状态的订单数量
	 * @param groupUserId
	 * @return
	 */
	OrderStatusBean countCCGroupUserOrder(Integer groupUserId);
	/**
	 *  后台 订单分页查询
	 * @param orderNumber
	 * @param tvId
	 * @param pageNO
	 * @param pageSize
	 * @return
	 */
	CutPageBean<OrderBean> queryAdminPageByNumberAndTvId(String orderNumber,Integer tvId,Integer pageNO,Integer pageSize,Integer groupUserId,Integer roleId);
	
	/**
	 *  根据条件统计 订单数量
	 * @param groupUserId
	 * @param roleId
	 * @param categoryId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Integer countOrdersByItem(Integer status,Integer groupUserId,Integer roleId,Integer categoryId,Date startDate,Date endDate);
	
	/**
	 *  定时任务器 更新15天内没有验收的订单的状态为 验收通过
	 * @param preDate
	 * @return
	 */
	Integer updateAutoOrderStatus(Date preDate);

	
//	/**个人中心 之 个人订单*/
//	CutPageBean<OrderBean> queryAdminSelfCenterOrder(String orderNumber,Integer pageNO,Integer pageSize,Integer groupUserId,Integer roleId);
	
}
