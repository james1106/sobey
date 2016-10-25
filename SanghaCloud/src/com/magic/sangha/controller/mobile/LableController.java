package com.magic.sangha.controller.mobile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.sangha.bean.GroupUser;
import com.magic.sangha.bean.LableBean;
import com.magic.sangha.bean.OrderBean;
import com.magic.sangha.bean.UserBean;
import com.magic.sangha.controller.BaseController;
import com.magic.sangha.service.ILableService;
import com.magic.sangha.service.IOrderService;
import com.magic.sangha.util.LoginHelper;
import com.magic.sangha.util.OrderStatusConStant;
import com.magic.sangha.util.RoleConstant;
import com.magic.sangha.util.StatusConstant;
import com.magic.sangha.util.ViewData;

/**
 *  评价 标签 控制层
 * @author QimouXie
 *
 */
@RequestMapping("/lable")
@Controller
public class LableController extends BaseController {
	
	@Resource
	private ILableService lableServiceImpl;
	@Resource
	private IOrderService orderServiceImpl;
	
	@RequestMapping("/getlables")
	@ResponseBody
	public ViewData getLable(Integer orderId){
		
		if(null == orderId){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		Object obj = LoginHelper.getCurrentUser();
		if(null == obj){
			return buildFailureJson(StatusConstant.NOTLOGIN, "对象不能为空");
		}
		OrderBean order = orderServiceImpl.findById(orderId);
		if(null == order || OrderStatusConStant.NON_VALIED.equals(order.getIsValid())){
			return buildFailureJson(StatusConstant.Fail_CODE, "无效订单");
		}
		List<LableBean> lables = lableServiceImpl.findByRoleId(null);
		List<LableBean> serviceLable = new ArrayList<LableBean>();
		List<LableBean> tscLable = new ArrayList<LableBean>();
		List<LableBean> headTechLable = new ArrayList<LableBean>();
		List<LableBean> headDevelopLable = new ArrayList<LableBean>();
		for (LableBean lable : lables) {
			if(RoleConstant.CUSTOMER.equals(lable.getRoleId()) &&  lable.getNumber() != null){
				serviceLable.add(lable);
			}
			if(RoleConstant.FILIALETECH.equals(lable.getRoleId()) &&  lable.getNumber() != null){
				tscLable.add(lable);
				headTechLable.add(lable);
				headDevelopLable.add(lable);
			}
		}
		Map<String, Object> data = new HashMap<String, Object>();
		if(obj instanceof UserBean){
			data.put("serviceLable", serviceLable);
			data.put("tscLable", tscLable);
		}else if(obj instanceof GroupUser){
			GroupUser groupUser = (GroupUser)obj;
			if(RoleConstant.CUSTOMER.equals(groupUser.getRoleType())){
				if(null != order.getTscId()){
					data.put("tscLable", tscLable);
				}
				if(null != order.getHeadTechId()){
					data.put("headTechLable", headTechLable);
				}
				if(null != order.getDecelopId()){
					data.put("headDevelopLable", headDevelopLable);
				}
			}else if(RoleConstant.FILIALETECH.equals(groupUser.getRoleType())){
				data.put("serviceLable", serviceLable);
				if(null != order.getHeadTechId()){
					data.put("headTechLable", headTechLable);
				}
				if(null != order.getDecelopId()){
					data.put("headDevelopLable", headDevelopLable);
				}
			}else if(RoleConstant.HEADCOMTECH.equals(groupUser.getRoleType())){
				data.put("serviceLable", serviceLable);
				if(null != order.getTscId()){
					data.put("tscLable", tscLable);
				}
				if(null != order.getDecelopId()){
					data.put("headDevelopLable", headDevelopLable);
				}
			}else if(RoleConstant.INVENT.equals(groupUser.getRoleType())){
				data.put("serviceLable", serviceLable);
				if(null != order.getTscId()){
					data.put("tscLable", tscLable);
				}
				if(null != order.getHeadTechId()){
					data.put("headTechLable", headTechLable);
				}
			}
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}

}
