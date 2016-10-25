package com.magic.sangha.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.GroupUser;
import com.magic.sangha.bean.OrderBean;
import com.magic.sangha.service.IOrderService;
import com.magic.sangha.util.LoginHelper;
import com.magic.sangha.util.StatusConstant;
import com.magic.sangha.util.ViewData;

/**
 * 个人中心 控制层
 * 
 * @author QimouXie
 *
 */
@Controller
@RequestMapping("/admin/selfCenter")
public class SelfCenterController extends BaseController {

	@Resource
	private IOrderService orderServiceImpl;

	@RequestMapping("/getOrders")
	@ResponseBody
	public ViewData getAdminOrderList(String orderNumber,Integer pageNO, Integer pageSize,HttpServletRequest req) {
		
		GroupUser user = LoginHelper.getCurrentAdmin(req);
		if(null == user){
			return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "对象不存在");
		}
		if (null == pageNO || null == pageSize) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		if (orderNumber == null || orderNumber.trim().length() == 0) {
			orderNumber = null;
		}
		CutPageBean<OrderBean> data = orderServiceImpl.queryAdminPageByNumberAndTvId(orderNumber,null, pageNO, pageSize,user.getId(),user.getRoleId());
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}

}
