package com.magic.sangha.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.GroupUser;
import com.magic.sangha.bean.OrderBean;
import com.magic.sangha.bean.OrderCategoryBean;
import com.magic.sangha.service.IGroupUserService;
import com.magic.sangha.service.IOrderCategoryService;
import com.magic.sangha.service.IOrderService;
import com.magic.sangha.service.IUserService;
import com.magic.sangha.util.DateUtil;
import com.magic.sangha.util.OrderStatusConStant;
import com.magic.sangha.util.StatusConstant;
import com.magic.sangha.util.ViewData;


/**
 *  ��̨�������� ���Ʋ�
 * @author QimouXie
 *
 */
@Controller
@RequestMapping("/admin/order")
public class AdminOrderController extends BaseController {

	@Resource
	private IOrderService orderServiceImpl;
	@Resource
	private IOrderCategoryService orderCategoryServiceImpl;
	@Resource
	private IGroupUserService groupUserServiceImpl;
	@Resource
	private IUserService userServiceImpl;
	
	@RequestMapping("/getOrderList")
	@ResponseBody
	public ViewData getAdminOrderList(String orderNumber,Integer tvId,Integer pageNO,Integer pageSize){
		
		if(null == pageNO || null == pageSize){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "�ֶβ���Ϊ��");
		}
		if(orderNumber == null || orderNumber.trim().length() == 0){
			orderNumber = null;
		}
		if(null == tvId || tvId == 0){
			tvId = null;
		}
		CutPageBean<OrderBean> data = orderServiceImpl.queryAdminPageByNumberAndTvId(orderNumber, tvId, pageNO, pageSize,null,null);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�", data);
	}
	
	@RequestMapping("/getcategorys")
	@ResponseBody
	public ViewData getCategorys(){
		List<OrderCategoryBean> data = orderCategoryServiceImpl.findAll();
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�", data);
	}
	@RequestMapping("/getemployee")
	@ResponseBody
	public ViewData getEmployee(){
		List<GroupUser> data = groupUserServiceImpl.findAllToToken();
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�", data);
	}
	
	/**
	 *  �������� ͳ�ƶ�������
	 * @return
	 */
	@RequestMapping("/countByItem")
	@ResponseBody
	public ViewData countByItem(String userId,Integer categoryId,String startDate,String endDate){
		Integer groupUserId = null;
		Integer roleId = null;
		if(userId != null){
			if(userId.trim().length() != 0){
				String[] items =  userId.split("\\|");
				groupUserId = Integer.parseInt(items[0]);
				roleId = Integer.parseInt(items[1]);
			}
		}
		Date start = DateUtil.getDate(startDate);
		 Date end = DateUtil.getPreSecondDate(DateUtil.getDate(endDate), -1);
		Integer over = orderServiceImpl.countOrdersByItem(OrderStatusConStant.OVER,groupUserId, roleId, categoryId, start, end);
		Integer nonOver = orderServiceImpl.countOrdersByItem(OrderStatusConStant.NON_OVER,groupUserId, roleId, categoryId, start, end);
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("over", over);
		data.put("nonOver", nonOver);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�", data);
	}
	
	/**
	 *  ��ҳͳ��
	 * @return
	 */
	@RequestMapping("/countIndex")
	@ResponseBody
	public ViewData countIndex(){
		Integer over = orderServiceImpl.countOrdersByItem(OrderStatusConStant.OVER,null, null, null, null, null);
		Integer nonOver = orderServiceImpl.countOrdersByItem(OrderStatusConStant.NON_OVER,null, null, null, null, null);
		Map<String,Integer> groupUser = groupUserServiceImpl.countByStatus();
		Map<String,Integer> user = userServiceImpl.countUserByStatus();
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("groupUserData", groupUser);
		data.put("userData", user);
		data.put("over", over);
		data.put("nonOver", nonOver);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�", data);
	}
	
}
