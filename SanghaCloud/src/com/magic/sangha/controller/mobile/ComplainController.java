package com.magic.sangha.controller.mobile;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.sangha.bean.ComplainBean;
import com.magic.sangha.bean.GroupUser;
import com.magic.sangha.bean.UserBean;
import com.magic.sangha.controller.BaseController;
import com.magic.sangha.service.IComplainService;
import com.magic.sangha.util.LoginHelper;
import com.magic.sangha.util.StatusConstant;
import com.magic.sangha.util.ViewData;

/**
 *  投诉
 * @author QimouXie
 *
 */
@Controller
@RequestMapping("/complain")
public class ComplainController extends BaseController {
	
	@Resource
	private IComplainService complainServiceImpl;
	/**
	 *  新增投诉
	 * @return
	 */
	@RequestMapping("/addcomplain")
	@ResponseBody
	public ViewData addComplain(Integer orderId,Integer type,String content){
		if(null == orderId || null == type || null == content){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		Object obj = LoginHelper.getCurrentUser();
		if(null == obj){
			return buildFailureJson(StatusConstant.NOTLOGIN, "对象不存在");
		}
		if(obj instanceof GroupUser){
			return buildFailureJson(StatusConstant.NON_ALLOW, "没有权限");
		}
		ComplainBean com = complainServiceImpl.findByOrderId(orderId);
		if(null != com){
			return buildFailureJson(StatusConstant.Fail_CODE, "此订单已经投诉过");
		}
		if(obj instanceof UserBean){
			UserBean user = (UserBean)obj;
			if(StatusConstant.FROZEN.equals(user.getStatus())){
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户已被冻结");
			}
			ComplainBean temp = new ComplainBean();
			temp.setContent(content);
			temp.setOrderId(orderId);
			temp.setType(type);
			complainServiceImpl.addComplain(temp);
			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "投诉成功");
		}
		return buildFailureJson(StatusConstant.Fail_CODE, "投诉失败");
	}
	/**
	 *  检查此订单是否投诉过
	 * @return
	 */
	@RequestMapping("/checkcomplain")
	@ResponseBody
	public ViewData checkIsCpmplain(Integer orderId){
		
		Object obj = LoginHelper.getCurrentUser();
		if(null == obj){
			return buildFailureJson(StatusConstant.NOTLOGIN, "未登录");
		}
		if(obj instanceof GroupUser){
			return buildFailureJson(StatusConstant.NON_ALLOW, "没有权限");
		}
		ComplainBean temp = complainServiceImpl.findByOrderId(orderId);
		if(null == temp){
			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "未投诉过");
		}
		return buildSuccessCodeJson(StatusConstant.Fail_CODE, "投诉过");
	}

}
