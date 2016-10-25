package com.magic.sangha.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.OrderBean;

/**
 *  订单 持久层接口
 * @author QimouXie
 *
 */
public interface IOrderMapper {
	
	Integer addOrder(@Param("order")OrderBean order);
	
	/**
	 *  根据订单状态查询
	 * @param status 订单状态
	 * @param isCheck 是否查看
	 * @param isGroupUser 是客服: 0  还是技术人员(TSC) : 1
	 * @return
	 */
	List<OrderBean> findByStatus(@Param("status")Integer status,@Param("isGroupUser")Integer isGroupUser,@Param("isCheck")Integer isCheck,@Param("groupUserId")Integer groupUserId
			,@Param("pageSize")Integer pageSize,@Param("pageSizeLimit")Integer pageSizeLimit);
	
	/**
	 *  根据条件统计数量
	 * @param status
	 * @param isGroupUser
	 * @param isCheck
	 * @return
	 */
	Integer countByStatus(@Param("status")Integer status,@Param("isGroupUser")Integer isGroupUser,@Param("isCheck")Integer isCheck,@Param("groupUserId")Integer groupUserId);
	
	/**
	 *  更新订单状态
	 * @param status
	 * @return
	 */
	Integer updateOrderStatus(@Param("orders")OrderBean order);
	
	/**
	 *  修改 客服 订单 未查看的状态
	 * @param orderId
	 * @param isCheck
	 * @return
	 */
	Integer updateOrderServiceCheck(@Param("orderId")Integer orderId,@Param("isCheck")Integer isCheck);
	
	/**
	 *  修改 技术人员(TSC) 订单 未查看的状态
	 * @param orderId
	 * @param isCheck
	 * @return
	 */
	Integer updateOrderTechCheck(@Param("orderId")Integer orderId,@Param("isCheck")Integer isCheck);
	
	/**
	 *  根据状态 和 用户ID 查看订单信息
	 *  如果不需要的字段，填写null 则不添加此条件
	 * @param status 状态
	 * @param userId 用户ID
	 * @param serviceId 客服ID
	 * @param tscId 技术人员ID
	 * @param devolepId 研发人员ID
	 * @return 订单集合(未排序)
	 */
	List<OrderBean> findByStatusAndUser(@Param("status")Integer status,@Param("userId")Integer userId,@Param("serviceId")Integer serviceId,
			@Param("tscId")Integer tscId,@Param("devolepId")Integer devolepId,@Param("pageSize")Integer pageSize,@Param("pageSizeLimit")Integer pageSizeLimit
			,@Param("checked")Integer checked,@Param("serviceCheck")Integer serviceCheck,@Param("developCheck")Integer developCheck,@Param("headTechCheck")Integer headTechCheck,
			@Param("headTech")Integer headTechId,@Param("order")OrderBean order);
	
	/**
	 *  统计满足此条件的总数
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
	 *  统计订单数量最少的客服
	 * @return
	 */
	OrderBean countMinOrder();
	
	OrderBean findById(@Param("id")Integer id);
	/**
	 *  根据订单ID 查询订单的跟踪数据
	 * @param id
	 * @return
	 */
	OrderBean findByIdToOrderData(@Param("id")Integer id);
	
	/**
	 *  修改订单 是否有效
	 * @param orderId
	 * @param isValid
	 * @return
	 */
	Integer updateIsValid(@Param("id")Integer orderId,@Param("isValid")Integer isValid);

	/**
	 *  通过ID 批量分页查询订单
	 * @param ids
	 * @return
	 */
	List<OrderBean> batchQueryOrder(@Param("ids")List<Integer> ids,@Param("limit") Integer limit,@Param("limitSize")Integer limitSize,@Param("status")Integer status);
	
	/**
	 *  修改总部技术 是否查看
	 * @param orderId
	 * @param isCheck
	 * @return
	 */
	Integer updateHeadTechIsCheck(@Param("id")Integer orderId,@Param("isCheck")Integer isCheck);
	/**
	 *  修改总部研发 是否查看
	 * @param orderId
	 * @param isCheck
	 * @return
	 */
	Integer updateDevelopIsCheck(@Param("id")Integer orderId,@Param("isCheck")Integer isCheck);
	
	/**
	 *  更新订单跟踪数据
	 * @param id
	 * @param orderData
	 * @return
	 */
	Integer updateOrderData(@Param("id")Integer id,@Param("orderData")String orderData);
	
	/**
	 *  更新订单跟踪数据 员工版
	 * @param id
	 * @param orderData
	 * @return
	 */
	Integer updateGroupOrderData(@Param("id")Integer id,@Param("orderData")String orderData);
	
	/**
	 *  更新是否评论
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
	 *  统计 各个角色的派单数量 不需要统计的角色 为空
	 * @param userId
	 * @param serviceId
	 * @param tscId
	 * @param headTechId
	 * @param developId
	 * @return
	 */
	Integer countOrders(@Param("userId")Integer userId,@Param("serviceId")Integer serviceId,@Param("tscId")Integer tscId,@Param("headTechId")Integer headTechId,@Param("developId")Integer developId);
	
	/**
	 *  按年月统计订单数量
	 * @param yearMonth 必选参数
	 * @param userId
	 * @param status 未完成  已完成 统计 如果是0 已完成  1 未完成
	 * @return
	 */
	Integer countOrdersByMonth(@Param("yearmonth")String yearMonth,@Param("status")Integer status,@Param("order")OrderBean order);
	
	/**
	 *  按年月统计有效订单数量 根据 问题类型
	 * @param yearMonth 必选参数
	 * @return
	 */
	List<OrderBean> countOrdersByCategory(@Param("yearmonth")String yearMonth,@Param("order")OrderBean order);
	/**
	 *  批量查询订单
	 * @param ids
	 * @return
	 */
	List<OrderBean> batchQueryOrders(@Param("ids")List<Integer> ids);
	/**
	 *  批量联表查询订单， 联表 订单问题类型
	 * @param ids
	 * @return
	 */
	List<OrderBean> batchQueryOrderByCategory(@Param("ids")List<Integer> ids);
	
	/**
	 *  统计 各个角色 待办任务
	 * @param order
	 * @return
	 */
	Integer countBacklog(@Param("order")OrderBean order);
	
	/**
	 *  统计待分配 的订单数量
	 * @param order
	 * @return
	 */
	Integer countAssigning(@Param("order")OrderBean order);
	
	/**
	 *  统计 各个角色 待处理
	 * @param order
	 * @return
	 */
	Integer countPengding(@Param("order")OrderBean order);
	
	/**
	 *  统计 各个角色 处理中订单数量
	 * @param order
	 * @return
	 */
	Integer countHandling(@Param("order")OrderBean order);
	
	/**
	 *  统计各个角色 待验证 订单数量
	 * @param order
	 * @return
	 */
	Integer countAccepting(@Param("order")OrderBean order);
	
	/**
	 *  统计各个角色 待评价 订单数量
	 * @param order
	 * @return
	 */
	Integer countEvaluating(@Param("order")OrderBean order);
	
	/**
	 *  后台 分页查询 订单
	 * @param tvId
	 * @param number
	 * @param limit
	 * @param limitSize
	 * @return
	 */
	List<OrderBean> queryAdminPageByNumberAndTvId(@Param("tvId")Integer tvId,@Param("number")String number,@Param("limit")Integer limit,@Param("limitSize")Integer limitSize,
			@Param("groupUserId")Integer groupUserId,@Param("roleId")Integer roleId);
	/**
	 *  后台 统计订单
	 * @param tvId
	 * @param number
	 * @return
	 */
	Integer countAdminPageByNumberAndTvId(@Param("tvId")Integer tvId,@Param("number")String number,@Param("groupUserId")Integer groupUserId,@Param("roleId")Integer roleId);
	
	/**
	 *  根据调教统计订单数量
	 * @param order
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Integer countOrdersByItem(@Param("order")OrderBean order,@Param("startDate")Date startDate,@Param("endDate")Date endDate);
	
	/**更新订单*/
	Integer updateOrder(@Param("order")OrderBean order);
	
	/**
	 *  定时任务器 更新15天内没有验收的订单的状态为 验收通过
	 * @param preDate
	 * @return
	 */
	Integer updateAutoOrderStatus(@Param("preDate")Date preDate);
}





















