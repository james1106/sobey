package com.magic.sangha.controller.mobile;

import java.util.Date;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.sangha.bean.CommentBean;
import com.magic.sangha.bean.GroupUser;
import com.magic.sangha.bean.OrderBean;
import com.magic.sangha.bean.OrderTrackBean;
import com.magic.sangha.bean.UserBean;
import com.magic.sangha.controller.BaseController;
import com.magic.sangha.service.ICommentService;
import com.magic.sangha.service.IOrderService;
import com.magic.sangha.util.LoginHelper;
import com.magic.sangha.util.OrderStatusConStant;
import com.magic.sangha.util.RoleConstant;
import com.magic.sangha.util.StatusConstant;
import com.magic.sangha.util.ViewData;

/**
 *  评论接口
 * @author QimouXie
 *
 */
@RequestMapping("/comment")
@Controller
public class CommentController extends BaseController {
	
	@Resource
	private ICommentService commentServiceImpl;
	
	@Resource
	private IOrderService orderServiceImpl;
	private Logger log = Logger.getLogger(this.getClass());

	@RequestMapping("/addcomment")
	@ResponseBody
	public ViewData addComment(String serviceData,String TSCData,String headTechData,String headDevelopData,Integer orderId,String commentContent){
		
		if(null == orderId ||null == commentContent ){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		Object obj = LoginHelper.getCurrentUser();
		if(null == obj){
			return buildFailureJson(StatusConstant.NOTLOGIN, "对象不能为空");
		}
		OrderBean order = orderServiceImpl.findById(orderId);
		if(null == order){
			return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "对象不存在");
		}
		
		CommentBean comment = new CommentBean();
		comment.setOrderId(orderId);
		comment.setServiceData(serviceData);
		comment.setTSCData(TSCData);
		comment.setHeadDevelopData(headDevelopData);
		comment.setHeadTechData(headTechData);
		comment.setCommentContent(commentContent);
		OrderBean temp = new OrderBean();
		if(obj instanceof UserBean){
			if(!OrderStatusConStant.OVERALLPENGDING.equals(order.getStatus())){
				return buildFailureJson(StatusConstant.Fail_CODE, "订单未验收");
			}
			UserBean user = (UserBean)obj;
			if(StatusConstant.FROZEN.equals(user.getStatus())){
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户已被冻结");
			}
			comment.setType(1);
			temp.setId(orderId);
			temp.setStatus(OrderStatusConStant.SOLVED);
			orderServiceImpl.updateOrderStatus(temp);
			JSONArray jsonArray = null;
			try {
				jsonArray = JSONArray.fromObject(order.getOrderData());
				OrderTrackBean track = new OrderTrackBean(temp.getStatus(),OrderStatusConStant.ORDER_THX, new Date());
				jsonArray.add(track.createOrderTrack());
				orderServiceImpl.updateOrderData(orderId, jsonArray.toString());
			} catch (Exception e) {
				log.debug(e.getMessage(), e);
			}
		}else if(obj instanceof GroupUser){
			comment.setType(0);
			GroupUser groupUser = (GroupUser)obj;
			if(StatusConstant.FROZEN.equals(groupUser.getStatus())){
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户已被冻结");
			}
			OrderBean tempOrder = new OrderBean();
			tempOrder.setId(orderId);
			if(RoleConstant.CUSTOMER.equals(groupUser.getRoleType())){
				if(!OrderStatusConStant.NON_COMMENT.equals(order.getIsServiceComment())){
					return buildFailureJson(StatusConstant.Fail_CODE, "已经评价");
				}
				tempOrder.setIsServiceComment(OrderStatusConStant.COMMENTED);
			}
			if(RoleConstant.FILIALETECH.equals(groupUser.getRoleType())){
				if(!OrderStatusConStant.NON_COMMENT.equals(order.getIsTSCComment())){
					return buildFailureJson(StatusConstant.Fail_CODE, "已经评价");
				}
				tempOrder.setIsTSCComment(OrderStatusConStant.COMMENTED);
			}
			if(RoleConstant.HEADCOMTECH.equals(groupUser.getRoleType())){
				if(!OrderStatusConStant.NON_COMMENT.equals(order.getIsHeadTechComment())){
					return buildFailureJson(StatusConstant.Fail_CODE, "已经评价");
				}
				tempOrder.setIsHeadTechComment(OrderStatusConStant.COMMENTED);
			}
			if(RoleConstant.INVENT.equals(groupUser.getRoleType())){
				if(!OrderStatusConStant.NON_COMMENT.equals(order.getIsHeadDevelopComment())){
					return buildFailureJson(StatusConstant.Fail_CODE, "已经评价");
				}
				tempOrder.setIsHeadDevelopComment(OrderStatusConStant.COMMENTED);
			}
			orderServiceImpl.updateIsComment(tempOrder);
		}
		try {
			commentServiceImpl.addComment(comment,obj);
		} catch (Exception e) {
			log.debug(e.getMessage(),e);
			return buildFailureJson(StatusConstant.Fail_CODE, "评论失败");
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "评论成功");
	}
	
	/**
	 *  获取评论信息
	 * @return
	 */
	@RequestMapping("/getcomment")
	@ResponseBody
	public ViewData getCommentInfo(Integer orderId){
		if(null == orderId){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		Object obj = LoginHelper.getCurrentUser();
		if(null  == obj){
			return buildFailureJson(StatusConstant.NOTLOGIN, "对象不存在");
		}
		OrderBean order = orderServiceImpl.findById(orderId);
		if(null == order || OrderStatusConStant.NON_VALIED.equals(order.getIsValid())){
			return buildFailureJson(StatusConstant.Fail_CODE, "无效的订单");
		}
		CommentBean com = null;
		if(obj instanceof UserBean){
			UserBean user = (UserBean)obj;
			if(StatusConstant.FROZEN.equals(user.getStatus())){
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户已被冻结");
			}
			com = commentServiceImpl.findByOrderIdAndType(null, orderId, 1);
			
		}
		if(obj instanceof GroupUser){
			GroupUser groupUser = (GroupUser)obj;
			if(StatusConstant.FROZEN.equals(groupUser.getStatus())){
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户已被冻结");
			}
			if(RoleConstant.CUSTOMER.equals(groupUser.getRoleType())){
				com = commentServiceImpl.findByOrderIdAndType(RoleConstant.CUSTOMER, orderId, 0);
//				CommentBean temp = commentServiceImpl.findByOrderIdAndType(null, orderId, 1);
//				if(null != temp){
//					com.setJsUserServiceData(temp.getJsServiceData());
//					com.setJsUserTechData(temp.getJsHeadTechData());
//				}
				return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", com);
			}else if(RoleConstant.FILIALETECH.equals(groupUser.getRoleType())){
				com = commentServiceImpl.findByOrderIdAndType(RoleConstant.FILIALETECH, orderId, 0);
			}else if(RoleConstant.HEADCOMTECH.equals(groupUser.getRoleType())){
				com = commentServiceImpl.findByOrderIdAndType(RoleConstant.HEADCOMTECH, orderId, 0);
			}else if(RoleConstant.INVENT.equals(groupUser.getRoleType())){
				com = commentServiceImpl.findByOrderIdAndType(RoleConstant.INVENT, orderId, 0);
			}
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", com);
	}
	
	
}







