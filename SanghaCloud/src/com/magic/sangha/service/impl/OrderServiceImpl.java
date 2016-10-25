package com.magic.sangha.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;











//import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.magic.sangha.bean.CCGroupUserBean;
import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.GroupUser;
import com.magic.sangha.bean.OrderBean;
import com.magic.sangha.bean.OrderCategoryBean;
import com.magic.sangha.bean.OrderInfoBean;
import com.magic.sangha.bean.OrderStatusBean;
import com.magic.sangha.mapper.ICCGroupUserMapper;
import com.magic.sangha.mapper.IGroupUserMapper;
import com.magic.sangha.mapper.IOrderCategoryMapper;
import com.magic.sangha.mapper.IOrderInfoMapper;
import com.magic.sangha.mapper.IOrderMapper;
import com.magic.sangha.service.IOrderService;
import com.magic.sangha.util.DateUtil;
import com.magic.sangha.util.OrderStatusConStant;
import com.magic.sangha.util.PushMessageUtil;
import com.magic.sangha.util.RoleConstant;

/**
 * 订单业务层接口实现
 * 
 * @author QimouXie
 *
 */
@Service
public class OrderServiceImpl implements IOrderService {

	@Resource
	private IOrderMapper orderMapper;
	@Resource
	private IGroupUserMapper groupMapper;
	@Resource
	private ICCGroupUserMapper ccGroupUserMapper;
	@Resource
	private IOrderCategoryMapper orderCategoryMapper;
	@Resource
	private IOrderInfoMapper orderInfoMapper;

//	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public Integer addOrder(OrderBean order)  throws Exception {
		GroupUser user = null;
		if (order.getServiceId() == null) {
			OrderBean temp = orderMapper.countMinOrder();
			Integer serviceId = 0;
			if (null == temp) {
				serviceId = 1;
			} else {
				serviceId = temp.getServiceId();
			}
			order.setServiceId(serviceId);
			order.setServiceCheck(OrderStatusConStant.NON_CHECK);
			// 消息推送
			 user = groupMapper.findById(serviceId);
			if (null != user) {
				PushMessageUtil.pushMessages(user, "您有新的订单",new HashMap<String, String>());
			}
		} else {
			order.setServiceCheck(OrderStatusConStant.CHECKED);
		}
		orderMapper.addOrder(order);
		// 存储推送消息
		if(user != null){
			List<OrderInfoBean> infos = new ArrayList<OrderInfoBean>();
			OrderInfoBean orderInfoBean = new OrderInfoBean(order.getId(),null,user.getId(),"您有新的订单",order.getOrderNumber());
			infos.add(orderInfoBean);
			orderInfoMapper.batchAddOrderInfo(infos);
		}
		return order.getId();
	}

	@Override
	public CutPageBean<OrderBean> findByStatus(OrderBean order, Integer pageNO,
			Integer pageSize, Integer isCheck) {
		Integer isGroupUser = null;
		Integer groupUserId = null;
		if (null == order) {
			return null;
		}
		if (null != order.getServiceId()) {
			isGroupUser = OrderStatusConStant.SERVICE_USER;
			groupUserId = order.getServiceId();
		}
		if (null != order.getTscId()) {
			isGroupUser = OrderStatusConStant.TECHTSC_USER;
			groupUserId = order.getTscId();
		}
		List<OrderBean> data = orderMapper.findByStatus(order.getStatus(),
				isGroupUser, isCheck, groupUserId, (pageNO - 1) * pageSize,
				pageSize);
		Integer count = orderMapper.countByStatus(order.getStatus(),
				isGroupUser, isCheck, groupUserId);
		Integer totalPage = 0;
		if (count % pageSize == 0) {
			totalPage = count / pageSize;
		} else {
			totalPage = count / pageSize + 1;
		}
		CutPageBean<OrderBean> page = new CutPageBean<OrderBean>(data, count,
				totalPage);
		return page;
	}

	@Override
	public Integer updateOrderStatus(OrderBean order) {
		return orderMapper.updateOrderStatus(order);
	}

	@Override
	public Integer updateOrderServiceCheck(Integer orderId, Integer isCheck) {
		return orderMapper.updateOrderServiceCheck(orderId, isCheck);
	}

	@Override
	public Integer updateOrderTechCheck(Integer orderId, Integer isCheck) {
		return orderMapper.updateOrderTechCheck(orderId, isCheck);
	}

	@Override
	public CutPageBean<OrderBean> findByStatusAndUser(OrderBean order,
			Integer pageNO, Integer pageSize) {

		List<OrderBean> dataList = orderMapper.findByStatusAndUser(
				order.getStatus(), order.getUserId(), order.getServiceId(),
				order.getTscId(), order.getDecelopId(),
				(pageNO - 1) * pageSize, pageSize, order.getTechCheck(),
				order.getServiceCheck(), order.getDevelopCheck(),
				order.getHeadTechCheck(), order.getHeadTechId(), order);

		for (OrderBean orderBean : dataList) {

			if (orderBean.getImages() != null) {
				JSONArray js = JSONArray.fromObject(orderBean.getImages());
				orderBean.setJsImages(js);
			}
			if (orderBean.getVideos() != null) {
				JSONArray js = JSONArray.fromObject(orderBean.getVideos());
				orderBean.setJsVideos(js);
			}
			if (orderBean.getVoices() != null) {
				JSONArray js = JSONArray.fromObject(orderBean.getVoices());
				orderBean.setJsVoices(js);
			}
		}

		int count = orderMapper.countByStatusAndUser(order.getStatus(),
				order.getUserId(), order.getServiceId(), order.getTscId(),
				order.getDecelopId());
		int totalPage = 0;
		if (count % pageSize == 0) {
			totalPage = count / pageSize;
		} else {
			totalPage = count / pageSize + 1;
		}
		CutPageBean<OrderBean> page = new CutPageBean<OrderBean>(dataList,
				count, totalPage);
		return page;
	}

	@Override
	public OrderBean findById(Integer id) {
		return orderMapper.findById(id);
	}

	@Override
	public OrderBean findByIdToOrderData(Integer id) {
		return orderMapper.findByIdToOrderData(id);
	}

	@Override
	public Integer updateIsValid(Integer orderId, Integer isValid) {
		return orderMapper.updateIsValid(orderId, isValid);
	}

	@Override
	public List<OrderBean> batchQueryOrder(List<Integer> ids, Integer pageNO,
			Integer pageSize, Integer status) {
		
		List<OrderBean> orders = new ArrayList<OrderBean>();
		if( ids.size() == 0){
			return orders;
		}
		orders = orderMapper.batchQueryOrder(ids, (pageNO - 1) * pageSize, pageSize, status);
		for (OrderBean orderBean : orders) {

			if (orderBean.getImages() != null) {
				JSONArray js = JSONArray.fromObject(orderBean.getImages());
				orderBean.setJsImages(js);
			}
			if (orderBean.getVideos() != null) {
				JSONArray js = JSONArray.fromObject(orderBean.getVideos());
				orderBean.setJsVideos(js);
			}
			if (orderBean.getVoices() != null) {
				JSONArray js = JSONArray.fromObject(orderBean.getVoices());
				orderBean.setJsVoices(js);
			}
		}
		return orders;
	}

	@Override
	public Integer updateHeadTechIsCheck(Integer orderId, Integer isCheck) {
		return orderMapper.updateHeadTechIsCheck(orderId, isCheck);
	}

	@Override
	public Integer updateDevelopIsCheck(Integer orderId, Integer isCheck) {
		return orderMapper.updateDevelopIsCheck(orderId, isCheck);
	}

	@Override
	public Integer updateOrderData(Integer id, String orderData) {
		return orderMapper.updateOrderData(id, orderData);
	}

	@Override
	public Integer updateGroupOrderData(Integer id, String orderData) {
		return orderMapper.updateGroupOrderData(id, orderData);
	}

	@Override
	public Integer updateIsComment(OrderBean order) {

		Integer isServiceComment = null;
		Integer isTSCComment = null;
		Integer isHeadDevelopComment = null;
		Integer isHeadTechComment = null;
		Integer isUsercomment = null;
		if (null != order.getIsHeadDevelopComment()) {
			isHeadDevelopComment = order.getIsHeadDevelopComment();
		}
		if (null != order.getIsServiceComment()) {
			isServiceComment = order.getIsServiceComment();
		}
		if (null != order.getIsHeadTechComment()) {
			isHeadTechComment = order.getIsHeadTechComment();
		}
		if (null != order.getIsTSCComment()) {
			isTSCComment = order.getIsTSCComment();
		}
		if (null != order.getIsUsercomment()) {
			isUsercomment = order.getIsUsercomment();
		}
		return orderMapper.updateIsComment(order.getId(), isServiceComment,
				isTSCComment, isHeadDevelopComment, isHeadTechComment,
				isUsercomment);
	}

	@Override
	public Integer countOrders(Integer userId, Integer serviceId,
			Integer tscId, Integer headTechId, Integer developId) {
		return orderMapper.countOrders(userId, serviceId, tscId, headTechId,
				developId);
	}

	@Override
	public Map<String, Integer> countOrdersByMonth(String yearMonth,
			OrderBean order) {
		Integer overCount = orderMapper.countOrdersByMonth(yearMonth,
				OrderStatusConStant.OVER, order);
		Integer nonOverCount = orderMapper.countOrdersByMonth(yearMonth,
				OrderStatusConStant.NON_OVER, order);
		Map<String, Integer> data = new HashMap<String, Integer>();
		data.put("finished", overCount);
		data.put("nonFinished", nonOverCount);
		return data;
	}

	@Override
	public Map<String, Integer> countOrdersByCategory(String yearMonth,
			OrderBean order) {

		List<OrderBean> orders = orderMapper.countOrdersByCategory(yearMonth,
				order);
		Map<String, Integer> data = new HashMap<String, Integer>();
		for (OrderBean tempOrder : orders) {
			data.put(tempOrder.getOrderCategoryName(), tempOrder.getCount());
		}
		return data;
	}

	@Override
	public Map<String, Integer> countOrdersByCC(String yearMonth,
			Integer groupUserId) {

		List<CCGroupUserBean> ccs = ccGroupUserMapper.findBygroupUserId(groupUserId);
		List<Integer> orderIds = new ArrayList<Integer>();
		Map<String, Integer> data = new HashMap<String, Integer>();
		if (ccs != null && ccs.size() != 0) {
			for (CCGroupUserBean cc : ccs) {
				orderIds.add(cc.getOrderId());
			}
			List<OrderBean> orders = orderMapper.batchQueryOrders(orderIds);
			List<OrderBean> finisheds = new ArrayList<OrderBean>();
			List<OrderBean> nonFinished = new ArrayList<OrderBean>();
			for (OrderBean order : orders) {
				Date createTime = order.getCreateTime();
				String yyyyMM = DateUtil.DateToyyyyMM(createTime);
				if (yearMonth.equals(yyyyMM)) {
					if (OrderStatusConStant.OVERALLPENGDING.equals(order.getStatus()) || OrderStatusConStant.SOLVED.equals(order.getStatus())) {
						finisheds.add(order);
					} else {
						nonFinished.add(order);
					}
				}
			}
			data.put("finished", finisheds.size());
			data.put("nonFinished", nonFinished.size());
		} else {
			data.put("finished", 0);
			data.put("nonFinished", 0);
		}
		return data;
	}

	@Override
	public Map<String, Integer> countOrderByCategoryAndCC(String yearMonth,Integer groupUserId) {

		List<CCGroupUserBean> ccs = ccGroupUserMapper.findBygroupUserId(groupUserId);
		List<OrderCategoryBean> cates = orderCategoryMapper.findAll();
		List<Integer> orderIds = new ArrayList<Integer>();
		Map<String, Integer> data = new HashMap<String, Integer>();

		if (ccs != null) {
			for (CCGroupUserBean cc : ccs) {
				orderIds.add(cc.getOrderId());
			}

			List<OrderBean> orders = orderMapper.batchQueryOrderByCategory(orderIds);
			List<OrderBean> orderMonth = new ArrayList<OrderBean>();
			for (OrderBean order : orders) {
				String yyyyMM = DateUtil.DateToyyyyMM(order.getCreateTime());
				if (yearMonth.equals(yyyyMM)) {
					orderMonth.add(order);
				}
			}
			Map<String, Integer> tempData = countList(orderMonth, cates);

			for (String key : tempData.keySet()) {
				if (tempData.get(key) != 0) {
					data.put(key, tempData.get(key));
				}
			}
		}
		return data;
	}

	private Map<String, Integer> countList(List<OrderBean> orders,List<OrderCategoryBean> cates) {
		Map<String, Integer> data = new HashMap<String, Integer>();
		for (OrderCategoryBean cate : cates) {
			List<OrderBean> orderByCate = new ArrayList<OrderBean>();
			for (OrderBean order : orders) {
				if (order.getOrderCategoryName().equals(cate.getCategoryName())) {
					orderByCate.add(order);
				}
			}
			data.put(cate.getCategoryName(), orderByCate.size());
		}
		return data;
	}

	@Override
	public OrderStatusBean countServiceOrder(Integer groupUserId) {
		OrderStatusBean orderStatus = new OrderStatusBean();

		// 待办任务
		OrderBean orderBacklog = new OrderBean();
		orderBacklog.setServiceId(groupUserId);
		orderBacklog.setStatus(OrderStatusConStant.SUSPENDING);
		orderBacklog.setServiceCheck(OrderStatusConStant.NON_CHECK);

		Integer backlog = orderMapper.countBacklog(orderBacklog);
		orderStatus.setBacklog(backlog);

		// 待分配
		OrderBean orderAssigning = new OrderBean();
		orderAssigning.setServiceId(groupUserId);
		orderAssigning.setStatus(OrderStatusConStant.SUSPENDING);
		orderAssigning.setServiceCheck(OrderStatusConStant.CHECKED);

		Integer assigning = orderMapper.countAssigning(orderAssigning);
		orderStatus.setAssigning(assigning);

		// 待处理
		OrderBean orderPengding = new OrderBean();
		orderPengding.setServiceId(groupUserId);
		orderPengding.setStatus(OrderStatusConStant.SUSPENDING);

		Integer pengding = orderMapper.countPengding(orderPengding);
		orderStatus.setPengding(pengding);

		// 处理中
		OrderBean orderHandling = new OrderBean();
		orderHandling.setServiceId(groupUserId);
		orderHandling.setStatus(OrderStatusConStant.HANDLING);

		Integer handing = orderMapper.countHandling(orderHandling);
		orderStatus.setHandling(handing);

		// 待验收
		OrderBean orderAccepting = new OrderBean();
		orderAccepting.setServiceId(groupUserId);
		orderAccepting.setStatus(OrderStatusConStant.VERIFICATIONPENDING);

		Integer accepting = orderMapper.countAccepting(orderAccepting);
		orderStatus.setAccepting(accepting);

		// 待评价
		OrderBean orderEvaluating = new OrderBean();
		orderEvaluating.setServiceId(groupUserId);
		orderEvaluating.setIsServiceComment(OrderStatusConStant.NON_COMMENT);

		Integer evaluating = orderMapper.countEvaluating(orderEvaluating);
		orderStatus.setEvaluating(evaluating);
		return orderStatus;
	}

	@Override
	public OrderStatusBean countUserOrder(Integer userId) {
		
		OrderStatusBean orderStatus = new OrderStatusBean();
		
		// 待处理
		OrderBean orderPengding = new OrderBean();
		orderPengding.setUserId(userId);
		orderPengding.setStatus(OrderStatusConStant.SUSPENDING);

		Integer pengding = orderMapper.countPengding(orderPengding);
		orderStatus.setPengding(pengding);

		// 处理中
		OrderBean orderHandling = new OrderBean();
		orderHandling.setUserId(userId);
		orderHandling.setStatus(OrderStatusConStant.HANDLING);

		Integer handing = orderMapper.countHandling(orderHandling);
		orderStatus.setHandling(handing);

		// 待验收
		OrderBean orderAccepting = new OrderBean();
		orderAccepting.setUserId(userId);
		orderAccepting.setStatus(OrderStatusConStant.VERIFICATIONPENDING);

		Integer accepting = orderMapper.countAccepting(orderAccepting);
		orderStatus.setAccepting(accepting);

		// 待评价
		OrderBean orderEvaluating = new OrderBean();
		orderEvaluating.setUserId(userId);
		orderEvaluating.setIsServiceComment(OrderStatusConStant.NON_COMMENT);

		Integer evaluating = orderMapper.countEvaluating(orderEvaluating);
		orderStatus.setEvaluating(evaluating);
		return orderStatus;
	}

	@Override
	public OrderStatusBean countTSCOrder(Integer groupUserId) {

		OrderStatusBean orderStatus = new OrderStatusBean();
		
		// 待办
		OrderBean orderbacklog = new OrderBean();
		orderbacklog.setTscId(groupUserId);
		orderbacklog.setTechCheck(OrderStatusConStant.NON_CHECK);
		
		Integer backlog = orderMapper.countBacklog(orderbacklog);
		orderStatus.setBacklog(backlog);
		
		// 待处理
		OrderBean orderPengding = new OrderBean();
		orderPengding.setTscId(groupUserId);
		orderPengding.setTechCheck(OrderStatusConStant.CHECKED);

		Integer pengding = orderMapper.countPengding(orderPengding);
		orderStatus.setPengding(pengding);

		// 处理中
		OrderBean orderHandling = new OrderBean();
		orderHandling.setTscId(groupUserId);
		orderHandling.setTechCheck(OrderStatusConStant.CHECKED);
		orderHandling.setStatus(OrderStatusConStant.HANDLING);

		Integer handing = orderMapper.countHandling(orderHandling);
		orderStatus.setHandling(handing);

		// 待验收
		OrderBean orderAccepting = new OrderBean();
		orderAccepting.setTscId(groupUserId);
		orderAccepting.setStatus(OrderStatusConStant.VERIFICATIONPENDING);

		Integer accepting = orderMapper.countAccepting(orderAccepting);
		orderStatus.setAccepting(accepting);

		// 待评价
		OrderBean orderEvaluating = new OrderBean();
		orderEvaluating.setTscId(groupUserId);
		orderEvaluating.setIsTSCComment(OrderStatusConStant.NON_COMMENT);

		Integer evaluating = orderMapper.countEvaluating(orderEvaluating);
		orderStatus.setEvaluating(evaluating);
		return orderStatus;
	}

	@Override
	public OrderStatusBean countHeadTechOrder(Integer groupUserId) {

		OrderStatusBean orderStatus = new OrderStatusBean();
		
		// 待办
		OrderBean orderbacklog = new OrderBean();
		orderbacklog.setHeadTechId(groupUserId);
		orderbacklog.setHeadTechCheck(OrderStatusConStant.NON_CHECK);
		
		Integer backlog = orderMapper.countBacklog(orderbacklog);
		orderStatus.setBacklog(backlog);
		
		// 待处理
		OrderBean orderPengding = new OrderBean();
		orderPengding.setHeadTechId(groupUserId);
		orderPengding.setHeadTechCheck(OrderStatusConStant.CHECKED);

		Integer pengding = orderMapper.countPengding(orderPengding);
		orderStatus.setPengding(pengding);

		// 处理中
		OrderBean orderHandling = new OrderBean();
		orderHandling.setHeadTechId(groupUserId);
		orderHandling.setHeadTechCheck(OrderStatusConStant.CHECKED);
		orderHandling.setStatus(OrderStatusConStant.HANDLING);

		Integer handing = orderMapper.countHandling(orderHandling);
		orderStatus.setHandling(handing);

		// 待验收
		OrderBean orderAccepting = new OrderBean();
		orderAccepting.setHeadTechId(groupUserId);
		orderAccepting.setStatus(OrderStatusConStant.VERIFICATIONPENDING);

		Integer accepting = orderMapper.countAccepting(orderAccepting);
		orderStatus.setAccepting(accepting);

		// 待评价
		OrderBean orderEvaluating = new OrderBean();
		orderEvaluating.setHeadTechId(groupUserId);
		orderEvaluating.setIsHeadTechComment(OrderStatusConStant.NON_COMMENT);

		Integer evaluating = orderMapper.countEvaluating(orderEvaluating);
		orderStatus.setEvaluating(evaluating);
		return orderStatus;		
	}

	@Override
	public OrderStatusBean countDevelopOrder(Integer groupUserId) {

		OrderStatusBean orderStatus = new OrderStatusBean();
		
		// 待办
		OrderBean orderbacklog = new OrderBean();
		orderbacklog.setDecelopId(groupUserId);
		orderbacklog.setStatus(OrderStatusConStant.HANDLING);
		orderbacklog.setDevelopCheck(OrderStatusConStant.NON_CHECK);
		
		Integer backlog = orderMapper.countBacklog(orderbacklog);
		orderStatus.setBacklog(backlog);
		
		// 待处理
		OrderBean orderPengding = new OrderBean();
		orderPengding.setDecelopId(groupUserId);
		orderPengding.setStatus(OrderStatusConStant.HANDLING);
		orderPengding.setDevelopCheck(OrderStatusConStant.CHECKED);

		Integer pengding = orderMapper.countPengding(orderPengding);
		orderStatus.setPengding(pengding);

		// 处理中
		OrderBean orderHandling = new OrderBean();
		orderHandling.setDecelopId(groupUserId);
		orderHandling.setDevelopCheck(OrderStatusConStant.CHECKED);
		orderHandling.setStatus(OrderStatusConStant.HANDLING);

		Integer handing = orderMapper.countHandling(orderHandling);
		orderStatus.setHandling(handing);

		// 待验收
		OrderBean orderAccepting = new OrderBean();
		orderAccepting.setDecelopId(groupUserId);
		orderAccepting.setStatus(OrderStatusConStant.VERIFICATIONPENDING);

		Integer accepting = orderMapper.countAccepting(orderAccepting);
		orderStatus.setAccepting(accepting);

		// 待评价
		OrderBean orderEvaluating = new OrderBean();
		orderEvaluating.setDecelopId(groupUserId);
		orderEvaluating.setIsHeadDevelopComment(OrderStatusConStant.NON_COMMENT);

		Integer evaluating = orderMapper.countEvaluating(orderEvaluating);
		orderStatus.setEvaluating(evaluating);
		return orderStatus;		
	}

	@Override
	public OrderStatusBean countCCGroupUserOrder(Integer groupUserId) {
		
		List<CCGroupUserBean> ccs = ccGroupUserMapper.findBygroupUserId(groupUserId);
		if(null == ccs || ccs.size() == 0 ){
			return  null;
		}
		OrderStatusBean orderStatus = new OrderStatusBean();
		List<Integer> ids = new ArrayList<Integer>();
		for (CCGroupUserBean orderId : ccs) {
			ids.add(orderId.getOrderId());
		}
		
		List<OrderBean> orders = orderMapper.batchQueryOrders(ids);
		// 进行中
		Integer happening = 0;
		// 待验收
		Integer accepting = 0;
		// 已完成
		Integer solved = 0;
		for (OrderBean order : orders) {
			if(OrderStatusConStant.VERIFICATIONPENDING.equals(order.getStatus())){
				accepting++;
			}else if(OrderStatusConStant.OVERALLPENGDING.equals(order.getStatus()) || OrderStatusConStant.SOLVED.equals(order.getStatus())){
				solved++;
			}else{
				happening++;
			}
		}
		orderStatus.setHappening(happening);
		orderStatus.setAccepting(accepting);
		orderStatus.setSolved(solved);
		return orderStatus;
	}

	@Override
	public CutPageBean<OrderBean> queryAdminPageByNumberAndTvId(String orderNumber, Integer tvId, Integer pageNO, Integer pageSize,Integer groupUserId,Integer roleId) {
		List<OrderBean> dataList = orderMapper.queryAdminPageByNumberAndTvId(tvId, orderNumber, (pageNO - 1) * pageSize, pageSize,groupUserId,roleId);
		for (OrderBean order : dataList) {
			if(null != order.getDevelopDecribe()){
				order.setDevelopTime(order.getDevelopDecribe().getCreateTime());
			}
			if(null != order.getTscDecribe() && null != order.getTscDecribeD()){
				if(order.getTscDecribe().getCreateTime().getTime() >= order.getTscDecribeD().getCreateTime().getTime()){
					order.setTechLastTime(order.getTscDecribe().getCreateTime());
				}else{
					order.setTechLastTime(order.getTscDecribeD().getCreateTime());
				}
			}
			if(null != order.getTscDecribe() && null == order.getTscDecribeD()){
				order.setTechLastTime(order.getTscDecribe().getCreateTime());
			}
			if(null == order.getTscDecribe() && null != order.getTscDecribeD()){
				order.setTechLastTime(order.getTscDecribeD().getCreateTime());
			}
			if(null != order.getHeadDecribe() && null != order.getHeadDecribeD() && null != order.getHeadDecribeT()){
				List<Date> longTime = new ArrayList<Date>();
				longTime.add(order.getHeadDecribe().getCreateTime());
				longTime.add(order.getHeadDecribeD().getCreateTime());
				longTime.add(order.getHeadDecribeT().getCreateTime());
				Collections.sort(longTime, new Comparator<Object>(){
					@Override
					public int compare(Object o1, Object o2) {
						Date o1Date = (Date)o1;
						Date o2Date = (Date)o2;
						if(o1Date.getTime() < o2Date.getTime()){
							return 1;
						}else if(o1Date.getTime() > o2Date.getTime()){
							return -1;
						}else{
							return 0;
						}
					}
				});
				order.setHeadLastTime(longTime.get(0));
			}
			if(null != order.getHeadDecribe() && null != order.getHeadDecribeD() && null == order.getHeadDecribeT()){
				order.setHeadLastTime(comparatoDate(order.getHeadDecribe().getCreateTime(),order.getHeadDecribeD().getCreateTime()));
			}else if(null != order.getHeadDecribe() && null == order.getHeadDecribeD() && null != order.getHeadDecribeT()){
				order.setHeadLastTime(comparatoDate(order.getHeadDecribe().getCreateTime(),order.getHeadDecribeT().getCreateTime()));
			}else if(null == order.getHeadDecribe() && null != order.getHeadDecribeD() && null != order.getHeadDecribeT()){
				order.setHeadLastTime(comparatoDate(order.getHeadDecribeD().getCreateTime(),order.getHeadDecribeT().getCreateTime()));
			}else if(null != order.getHeadDecribe() && null == order.getHeadDecribeD() && null == order.getHeadDecribeT()){
				order.setHeadLastTime(order.getHeadDecribe().getCreateTime());
			}else if(null == order.getHeadDecribe() && null != order.getHeadDecribeD() && null == order.getHeadDecribeT()){
				order.setHeadLastTime(order.getHeadDecribeD().getCreateTime());
			}else if(null == order.getHeadDecribe() && null == order.getHeadDecribeD() && null != order.getHeadDecribeT()){
				order.setHeadLastTime(order.getHeadDecribeT().getCreateTime());
			}
		}
		Integer count = orderMapper.countAdminPageByNumberAndTvId(tvId, orderNumber,groupUserId,roleId);
		Integer totalPage = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
		CutPageBean<OrderBean> page = new CutPageBean<OrderBean>(dataList, count, totalPage);
		return page;
	}
	
	private Date comparatoDate(Date date1,Date date2){
		if(date1.getTime() >= date2.getTime()){
			return date1;
		}else{
			return date2;
		}
	}

	@Override
	public Integer countOrdersByItem(Integer status,Integer groupUserId, Integer roleId,Integer categoryId, Date startDate, Date endDate) {
		Integer count = null;
		OrderBean order = new OrderBean();
		order.setOrderCategory(categoryId);
		order.setStatus(status);
		if(RoleConstant.CUSTOMER.equals(roleId)){
			order.setServiceId(groupUserId);
		}else if(RoleConstant.FILIALETECH.equals(roleId)){
			order.setTscId(groupUserId);
		}else if(RoleConstant.HEADCOMTECH.equals(groupUserId)){
			order.setHeadTechId(groupUserId);
		}else if(RoleConstant.INVENT.equals(roleId)){
			order.setDecelopId(groupUserId);
		}else{
			if(null != groupUserId){
				count = ccGroupUserMapper.countOrderByItem(status,groupUserId, categoryId, startDate, endDate);
			}
		}
		if( count== null){
			count = orderMapper.countOrdersByItem(order, startDate, endDate);
		}
		return count;
	}

	@Override
	public Integer updateAutoOrderStatus(Date preDate) {
		return orderMapper.updateAutoOrderStatus(preDate);
	}
	
//	@Override
//	public CutPageBean<OrderBean> queryAdminSelfCenterOrder(String orderNumber,Integer pageNO, Integer pageSize, Integer groupUserId,Integer roleId) {
//		
//		if(null !=  groupUserId && null != roleId){
//			if(RoleConstant.CUSTOMER != roleId && RoleConstant.INVENT != roleId && RoleConstant.FILIALETECH != roleId && RoleConstant.HEADCOMTECH != roleId){
//				
//			}else{
//				List<OrderBean> dataList = orderMapper.queryAdminPageByNumberAndTvId(null, orderNumber, (pageNO - 1) * pageSize, pageSize,groupUserId,roleId);
//				Integer count = orderMapper.countAdminPageByNumberAndTvId(null, orderNumber,groupUserId,roleId);
//				Integer totalPage = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
//				CutPageBean<OrderBean> page = new CutPageBean<OrderBean>(dataList, count, totalPage);
//				return page;
//			}
//		}
//		return null;
//	}
}
