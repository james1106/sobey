package com.magic.sangha.controller.mobile;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.sangha.bean.CCGroupUserBean;
import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.GroupUser;
import com.magic.sangha.bean.HeadTechDevelopBean;
import com.magic.sangha.bean.OrderBean;
import com.magic.sangha.bean.OrderCategoryBean;
import com.magic.sangha.bean.OrderDecribeBean;
import com.magic.sangha.bean.OrderDecribeHeadTechBean;
import com.magic.sangha.bean.OrderDecribeUser;
import com.magic.sangha.bean.OrderInfoBean;
import com.magic.sangha.bean.OrderStatusBean;
import com.magic.sangha.bean.OrderTrackBean;
import com.magic.sangha.bean.TSCHeadTechDecribeBean;
import com.magic.sangha.bean.UserBean;
import com.magic.sangha.controller.BaseController;
import com.magic.sangha.service.ICCGroupUserService;
import com.magic.sangha.service.IGroupUserCategoryService;
import com.magic.sangha.service.IGroupUserService;
import com.magic.sangha.service.IHeadTechDevelopService;
import com.magic.sangha.service.IOrderCategoryService;
import com.magic.sangha.service.IOrderDecribeHeadTechService;
import com.magic.sangha.service.IOrderDecribeService;
import com.magic.sangha.service.IOrderDecribeUserService;
import com.magic.sangha.service.IOrderInfoService;
import com.magic.sangha.service.IOrderService;
import com.magic.sangha.service.ITSCHeadTechDecribeService;
import com.magic.sangha.service.IUserService;
import com.magic.sangha.util.LoginHelper;
import com.magic.sangha.util.OrderStatusConStant;
import com.magic.sangha.util.PushMessageUtil;
import com.magic.sangha.util.RoleConstant;
import com.magic.sangha.util.StatusConstant;
import com.magic.sangha.util.ViewData;

/**
 * 订单管理控制器
 * 
 * @author QimouXie
 *
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {

	@Resource
	private IOrderService orderServiceImpl;
	@Resource
	private IOrderCategoryService orderCategoryImpl;
	@Resource
	private IUserService userServiceImpl;
	@Resource
	private IGroupUserService groupUserServiceImpl;
	@Resource
	private IOrderDecribeService orderDecribeServiceImpl;
	@Resource
	private IGroupUserCategoryService groupUserCategoryServiceImpl;
	@Resource
	private ICCGroupUserService cCgroupUserServiceImpl;
	@Resource
	private IHeadTechDevelopService headTechDevelopServiceImpl;
	@Resource
	private ITSCHeadTechDecribeService tSCHeadTechDecribeServiceImpl;
	@Resource
	private IOrderDecribeHeadTechService orderDecribeHeadTechServiceImpl;
	@Resource
	private IOrderDecribeUserService orderDecribeUserServiceImpl;
	@Resource
	private IOrderInfoService orderInfoServiceImpl;

	private Logger log = Logger.getLogger(this.getClass());

	/**
	 * 新增订单
	 * 
	 * @return
	 */
	@RequestMapping("/addorder")
	@ResponseBody
	public ViewData addOrder(String images, String videos, String voices,Integer categoryId, String content, Integer userId) {

		Object obj = LoginHelper.getCurrentUser();
		if (null == obj) {
			return buildFailureJson(StatusConstant.NOTLOGIN, "请先登录");
		}
		if (obj instanceof GroupUser) {
			GroupUser groupUser = (GroupUser) obj;
			if (!(RoleConstant.CUSTOMER.equals(groupUser.getRole().getId()))) {
				return buildFailureJson(StatusConstant.NON_ALLOW, "没有权限");
			}
			if (StatusConstant.FROZEN.equals(groupUser.getStatus())) {
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户已被冻结");
			}
			OrderBean order = new OrderBean();
			order.setImages(images);
			order.setVideos(videos);
			order.setVoices(voices);

			order.setUserId(userId);
			order.setServiceCheck(OrderStatusConStant.CHECKED);
			order.setServiceId(groupUser.getId());
			order.setStatus(OrderStatusConStant.SUSPENDING);
			order.setOrderCategory(categoryId);
			order.setOrderDescri(content);
			order.setOrderNumber(order.buildOrderNumber());
			OrderTrackBean trackone = new OrderTrackBean(
					OrderStatusConStant.SUSPENDING,
					OrderStatusConStant.ORDER_SUBMITED, new Date());
			OrderTrackBean tracktwo = new OrderTrackBean(
					OrderStatusConStant.SUSPENDING,
					OrderStatusConStant.ORDER_ACCEPTED, new Date());
			OrderTrackBean trackGroupone = new OrderTrackBean(
					OrderStatusConStant.SUSPENDING,
					OrderStatusConStant.QUESTION_SUBMITED, new Date());
			OrderTrackBean trackGrouptwo = new OrderTrackBean(
					OrderStatusConStant.SUSPENDING,
					OrderStatusConStant.ALLOTING_TO_TECH, new Date());
			List<Object> trackdata = new ArrayList<Object>();
			trackdata.add(trackone.createOrderTrack());
			trackdata.add(tracktwo.createOrderTrack());
			List<Object> trackGroupData = new ArrayList<Object>();
			trackGroupData.add(trackGroupone.createOrderTrack());
			trackGroupData.add(trackGrouptwo.createOrderTrack());
			order.setGroupOrderData(JSONArray.fromObject(trackGroupData).toString());
			order.setOrderData(JSONArray.fromObject(trackdata).toString());
			try {
				Integer orderId = orderServiceImpl.addOrder(order);
				// 消息推送
				UserBean user = userServiceImpl.findById(userId);
				PushMessageUtil.pushMessages(user, "小贝已将您的申请快递给索贝客服啦，请您耐心等候~",new HashMap<String, String>());
				// 存储推送消息
				List<OrderInfoBean> infos = new ArrayList<OrderInfoBean>();
				OrderInfoBean orderInfoBean = new OrderInfoBean(orderId,user.getId(), null, "小贝已将您的申请快递给索贝客服啦，请您耐心等候~",order.getOrderNumber());
				infos.add(orderInfoBean);
				orderInfoServiceImpl.batchAddOrderInfo(infos);
				return buildSuccessCodeViewData(StatusConstant.SUCCESS_CODE,"提交成功");
			} catch (Exception e) {
				e.printStackTrace();
				return buildSuccessCodeViewData(StatusConstant.Fail_CODE,"提交失败");
			}

		} else if (obj instanceof UserBean) {
			UserBean temp = (UserBean) obj;
			if (StatusConstant.FROZEN.equals(temp.getStatus())) {
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户已被冻结");
			}
			OrderBean order = new OrderBean();
			order.setImages(images);
			order.setVideos(videos);
			order.setVoices(voices);

			order.setUserId(temp.getId());
			order.setStatus(OrderStatusConStant.SUSPENDING);
			order.setOrderCategory(categoryId);
			order.setOrderDescri(content);
			order.setOrderNumber(order.buildOrderNumber());
			OrderTrackBean track = new OrderTrackBean(
					OrderStatusConStant.SUSPENDING,
					OrderStatusConStant.ORDER_SUBMITED, new Date());
			OrderTrackBean trackGroupone = new OrderTrackBean(
					OrderStatusConStant.SUSPENDING,
					OrderStatusConStant.QUESTION_SUBMITED, new Date());
			List<Object> trackdata = new ArrayList<Object>();
			trackdata.add(track.createOrderTrack());
			List<Object> trackGroupData = new ArrayList<Object>();
			trackGroupData.add(trackGroupone.createOrderTrack());
			order.setGroupOrderData(JSONArray.fromObject(trackGroupData)
					.toString());
			order.setOrderData(JSONArray.fromObject(trackdata).toString());
			try {
				orderServiceImpl.addOrder(order);
				return buildSuccessCodeViewData(StatusConstant.SUCCESS_CODE,
						"提交成功");
			} catch (Exception e) {
				e.printStackTrace();
				return buildSuccessCodeViewData(StatusConstant.Fail_CODE,
						"提交失败");
			}

		}
		return buildFailureJson(StatusConstant.Fail_CODE, "提交失败");
	}

	/**
	 * 获取订单的类型分类信息
	 * 
	 * @param type
	 * @return
	 */
	@RequestMapping("/getcategory")
	@ResponseBody
	public ViewData getOrderCategory(Integer type) {

		if (null == type) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}

		List<OrderCategoryBean> orderca = orderCategoryImpl.findByType(type);

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("lists", orderca);

		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}

	/**
	 * 获取订单列表
	 * 
	 * @return
	 */
	@RequestMapping("/getorderlist")
	@ResponseBody
	public ViewData getOrder(Integer pageNO, Integer pageSize, Integer status,Integer isCheck, Integer isComment, Integer isAccept) {

		Object obj = LoginHelper.getCurrentUser();
		if (null == obj) {
			return buildFailureJson(StatusConstant.NOTLOGIN, "对象不存在");
		}
		Map<String, Object> data = new HashMap<String, Object>();
		OrderBean order = new OrderBean();
		order.setStatus(status);
		if (obj instanceof UserBean) {
			UserBean user = (UserBean) obj;
			if (StatusConstant.FROZEN.equals(user.getStatus())) {
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户已被冻结");
			}
			order.setUserId(user.getId());
		} else if (obj instanceof GroupUser) {
			GroupUser groupUser = (GroupUser) obj;
			if (StatusConstant.FROZEN.equals(groupUser.getStatus())) {
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户已被冻结");
			}
			if (RoleConstant.CUSTOMER.equals(groupUser.getRoleType())) {
				order.setServiceId(groupUser.getId());
				order.setServiceCheck(isCheck);
				order.setIsServiceComment(isComment);
				order.setServiceIsAccept(isAccept);
			} else if (RoleConstant.FILIALETECH.equals(groupUser.getRoleType())) {
				order.setTscId(groupUser.getId());
				order.setTechCheck(isCheck);
				order.setIsTSCComment(isComment);
				order.setTscIsAccept(isAccept);
			} else if (RoleConstant.INVENT.equals(groupUser.getRoleType())) {
				order.setDecelopId(groupUser.getId());
				order.setDevelopCheck(isCheck);
				order.setIsHeadDevelopComment(isComment);
				order.setDevelopIsAccept(isAccept);
			} else if (RoleConstant.HEADCOMTECH.equals(groupUser.getRoleType())) {
				order.setHeadTechId(groupUser.getId());
				order.setHeadTechCheck(isCheck);
				order.setIsHeadTechComment(isComment);
				order.setHeadTechIsAccept(isAccept);
			} else if (RoleConstant.SALE.equals(groupUser.getRoleType()) || RoleConstant.OPERATION.equals(groupUser.getRoleType())) {
				List<CCGroupUserBean> ccList = cCgroupUserServiceImpl.findBygroupUserId(groupUser.getId());
				if (null != ccList) {
					List<Integer> ids = null;
					if (ccList.size() != 0) {
						ids = new ArrayList<Integer>();
					}
					for (CCGroupUserBean cc : ccList) {
						ids.add(cc.getOrderId());
					}
					List<OrderBean> orders = new ArrayList<OrderBean>();
					if (ids != null) {
						orders = orderServiceImpl.batchQueryOrder(ids, pageNO,
								pageSize, status);
					}
					data.put("lists", orders);
				}
				return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功",data);
			} else if (RoleConstant.FILIALE_HEADER.equals(groupUser
					.getRoleType())) {
				List<CCGroupUserBean> ccList = cCgroupUserServiceImpl
						.findBygroupUserId(groupUser.getId());
				if (null != ccList) {
					List<Integer> ids = new ArrayList<Integer>();
					for (CCGroupUserBean cc : ccList) {
						ids.add(cc.getOrderId());
					}
					List<OrderBean> orders = new ArrayList<OrderBean>();
					if (ids != null) {
						orders = orderServiceImpl.batchQueryOrder(ids, pageNO,
								pageSize, status);
					}
					data.put("lists", orders);
				}
				return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功",
						data);
			} else if (RoleConstant.LEADER.equals(groupUser.getRoleType())) {
				List<CCGroupUserBean> ccList = cCgroupUserServiceImpl
						.findBygroupUserId(groupUser.getId());
				if (null != ccList && ccList.size() != 0) {
					List<Integer> ids = new ArrayList<Integer>();
					for (CCGroupUserBean cc : ccList) {
						ids.add(cc.getOrderId());
					}
					List<OrderBean> orders = new ArrayList<OrderBean>();
					if (ids != null) {
						orders = orderServiceImpl.batchQueryOrder(ids, pageNO,
								pageSize, status);
					}
					data.put("lists", orders);
				}
				return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功",
						data);
			} else if (RoleConstant.FILIALE_HEADER.equals(groupUser
					.getRoleType())) {
				List<CCGroupUserBean> ccList = cCgroupUserServiceImpl
						.findBygroupUserId(groupUser.getId());
				if (null != ccList) {
					List<Integer> ids = new ArrayList<Integer>();
					for (CCGroupUserBean cc : ccList) {
						ids.add(cc.getOrderId());
					}
					List<OrderBean> orders = new ArrayList<OrderBean>();
					if (ids != null) {
						orders = orderServiceImpl.batchQueryOrder(ids, pageNO,
								pageSize, status);
					}
					data.put("lists", orders);
				}
				return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功",
						data);
			} else if (RoleConstant.FILIALE_SALE_HEADER.equals(groupUser
					.getRoleType())) {
				List<CCGroupUserBean> ccList = cCgroupUserServiceImpl
						.findBygroupUserId(groupUser.getId());
				if (null != ccList) {
					List<Integer> ids = new ArrayList<Integer>();
					for (CCGroupUserBean cc : ccList) {
						ids.add(cc.getOrderId());
					}
					List<OrderBean> orders = new ArrayList<OrderBean>();
					if (ids != null) {
						orders = orderServiceImpl.batchQueryOrder(ids, pageNO,
								pageSize, status);
					}
					data.put("lists", orders);
				}
				return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功",
						data);
			} else if (RoleConstant.FILIALE_TECH_HEADER.equals(groupUser
					.getRoleType())) {
				List<CCGroupUserBean> ccList = cCgroupUserServiceImpl
						.findBygroupUserId(groupUser.getId());
				if (null != ccList) {
					List<Integer> ids = new ArrayList<Integer>();
					for (CCGroupUserBean cc : ccList) {
						ids.add(cc.getOrderId());
					}
					List<OrderBean> orders = new ArrayList<OrderBean>();
					if (ids != null) {
						orders = orderServiceImpl.batchQueryOrder(ids, pageNO,
								pageSize, status);
					}
					data.put("lists", orders);
				}
				return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功",
						data);
			}
		}
		CutPageBean<OrderBean> page = orderServiceImpl.findByStatusAndUser(
				order, pageNO, pageSize);

		data.put("lists", page.getDataList());
		data.put("count", page.getCount());
		data.put("totalPage", page.getTotalPage());
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}

	// @RequestMapping("/orderDetail")
	// @ResponseBody
	// public ViewData getOrderDetail(Integer orderId){
	//
	// OrderBean order = orderServiceImpl.findById(orderId);
	// if(null == order){
	// return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "该订单不存在");
	// }
	// Map<String,Object> data = new HashMap<String, Object>();
	// data.put("order", order);
	// UserBean user = userServiceImpl.findById(order.getUserId());
	// data.put("user", user);
	// if(null != order.getTscId()){
	// GroupUser groupuser =
	// groupUserServiceImpl.findByIdToName(order.getTscId());
	// data.put("tsc", groupuser);
	// }
	// return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	// }

	/**
	 * 获取订单追加描述
	 * 
	 * @return
	 */
	@RequestMapping("/getOrderdecribe")
	@ResponseBody
	public ViewData getOrderDecribe(Integer orderId) {

		Object obj = LoginHelper.getCurrentUser();
		if (null == obj) {
			return buildFailureJson(StatusConstant.NOTLOGIN, "对象不能为空");
		}

		if (null == orderId) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		OrderBean order = orderServiceImpl.findById(orderId);

		if (null == order) {
			return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "该订单不存在");
		}
		if (OrderStatusConStant.NON_VALIED.equals(order.getIsValid())) {
			return buildFailureJson(StatusConstant.ORDER_INVALID, "无效的订单");
		}
		List<OrderDecribeUser> decribesUser = orderDecribeUserServiceImpl
				.findByOrderId(orderId);
		Map<String, Object> data = new HashMap<String, Object>();

		if (obj instanceof UserBean) {

			UserBean tuser = (UserBean) obj;
			if (StatusConstant.FROZEN.equals(tuser.getStatus())) {
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户已被冻结");
			}

			List<OrderDecribeHeadTechBean> decribes = orderDecribeHeadTechServiceImpl
					.findByOrderId(orderId, order);
			// 如果此订单是直接分配给了 总技术的
			if (OrderStatusConStant.HEADTECH.equals(order.getIsHeadTech())) {

				List<OrderDecribeBean> orderDecribes = orderDecribeServiceImpl
						.findByOrderId(orderId, order);
				orderDecribes.remove(0);
				List<Object> decribeList = new ArrayList<Object>();

				if (null != decribes) {
					for (OrderDecribeHeadTechBean decribe : decribes) {
						decribeList.add(decribe);
					}
				}

				if (null != orderDecribes) {
					for (OrderDecribeBean orderDecribe : orderDecribes) {
						decribeList.add(orderDecribe);
					}
				}

				if (null != decribesUser) {
					for (OrderDecribeUser decribeUser : decribesUser) {
						decribeList.add(decribeUser);
					}
				}
				Collections.sort(decribeList, new Comparator<Object>() {

					@Override
					public int compare(Object o1, Object o2) {
						Long o1time = null;
						Long o2time = null;
						if (o1 instanceof OrderDecribeBean) {
							OrderDecribeBean o1obj = (OrderDecribeBean) o1;
							o1time = o1obj.getCreateTime().getTime();
						} else if (o1 instanceof OrderDecribeHeadTechBean) {
							OrderDecribeHeadTechBean o1obj = (OrderDecribeHeadTechBean) o1;
							o1time = o1obj.getCreateTime().getTime();
						} else if (o1 instanceof OrderDecribeUser) {
							OrderDecribeUser o1obj = (OrderDecribeUser) o1;
							o1time = o1obj.getCreateTime().getTime();
						}
						if (o2 instanceof OrderDecribeBean) {
							OrderDecribeBean o2obj = (OrderDecribeBean) o2;
							o2time = o2obj.getCreateTime().getTime();
						} else if (o2 instanceof OrderDecribeHeadTechBean) {
							OrderDecribeHeadTechBean o2obj = (OrderDecribeHeadTechBean) o2;
							o2time = o2obj.getCreateTime().getTime();
						} else if (o2 instanceof OrderDecribeUser) {
							OrderDecribeUser o2obj = (OrderDecribeUser) o2;
							o2time = o2obj.getCreateTime().getTime();
						}
						return o1time.compareTo(o2time);
					}
				});

				if (null != order.getTscId()) {
					GroupUser groupuser = groupUserServiceImpl
							.findByIdToName(order.getTscId());
					data.put("tsc", groupuser);
				} else if (null != order.getHeadTechId()
						&& null == order.getTscId()) {
					GroupUser groupuser = groupUserServiceImpl
							.findByIdToName(order.getHeadTechId());
					data.put("tsc", groupuser);
				}
				UserBean user = userServiceImpl.findById(order.getUserId());
				data.put("user", user);
				data.put("lists", decribeList);
				data.put("order", order);
				return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功",
						data);
			}

			decribes.remove(0);
			List<Object> decribeList = new ArrayList<Object>();
			List<OrderDecribeBean> decribess = orderDecribeServiceImpl
					.findByOrderId(orderId, order);
			if (null != decribes) {
				for (OrderDecribeBean orderDecri : decribess) {
					decribeList.add(orderDecri);
				}
			}
			if (null != decribes) {
				for (OrderDecribeHeadTechBean decribe : decribes) {
					decribeList.add(decribe);
				}
			}
			if (null != decribesUser) {
				for (OrderDecribeUser decribeUser : decribesUser) {
					decribeList.add(decribeUser);
				}
			}
			Collections.sort(decribeList, new Comparator<Object>() {

				@Override
				public int compare(Object o1, Object o2) {
					Long o1time = null;
					Long o2time = null;
					if (o1 instanceof OrderDecribeBean) {
						OrderDecribeBean o1obj = (OrderDecribeBean) o1;
						o1time = o1obj.getCreateTime().getTime();
					} else if (o1 instanceof OrderDecribeUser) {
						OrderDecribeUser o1obj = (OrderDecribeUser) o1;
						o1time = o1obj.getCreateTime().getTime();
					} else if (o1 instanceof OrderDecribeHeadTechBean) {
						OrderDecribeHeadTechBean o1obj = (OrderDecribeHeadTechBean) o1;
						o1time = o1obj.getCreateTime().getTime();
					}
					if (o2 instanceof OrderDecribeBean) {
						OrderDecribeBean o2obj = (OrderDecribeBean) o2;
						o2time = o2obj.getCreateTime().getTime();
					} else if (o2 instanceof OrderDecribeUser) {
						OrderDecribeUser o2obj = (OrderDecribeUser) o2;
						o2time = o2obj.getCreateTime().getTime();
					} else if (o2 instanceof OrderDecribeHeadTechBean) {
						OrderDecribeHeadTechBean o2obj = (OrderDecribeHeadTechBean) o2;
						o2time = o2obj.getCreateTime().getTime();
					}
					return o1time.compareTo(o2time);
				}
			});
			data.put("lists", decribeList);
		} else if (obj instanceof GroupUser) {
			GroupUser groupUser = (GroupUser) obj;
			if (StatusConstant.FROZEN.equals(groupUser.getStatus())) {
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户已被冻结");
			}
			if (RoleConstant.FILIALETECH.equals(groupUser.getRoleType())) {
				List<OrderDecribeBean> decribes = orderDecribeServiceImpl
						.findByOrderId(orderId, order);
				List<TSCHeadTechDecribeBean> tsces = tSCHeadTechDecribeServiceImpl
						.findByOrderId(orderId, order);
				List<OrderDecribeHeadTechBean> decribess = null;
				List<Object> decribeList = new ArrayList<Object>();
				// 如果此订单是直接分配给了 总技术的
				if (OrderStatusConStant.HEADTECH.equals(order.getIsHeadTech())) {
					decribess = orderDecribeHeadTechServiceImpl.findByOrderId(
							orderId, order);
					decribes.remove(0);
				}
				tsces.remove(0);
				if (null != decribes) {
					for (OrderDecribeBean decriBean : decribes) {
						decribeList.add(decriBean);
					}
				}
				if (null != tsces) {
					for (TSCHeadTechDecribeBean tesc : tsces) {
						decribeList.add(tesc);
					}
				}
				if (null != decribess) {
					for (OrderDecribeHeadTechBean tesc : decribess) {
						decribeList.add(tesc);
					}
				}
				if (null != decribesUser) {
					for (OrderDecribeUser decribeUser : decribesUser) {
						decribeList.add(decribeUser);
					}
				}

				Collections.sort(decribeList, new Comparator<Object>() {
					@Override
					public int compare(Object o1, Object o2) {
						Long o1time = null;
						Long o2time = null;
						if (o1 instanceof OrderDecribeBean) {
							OrderDecribeBean o1obj = (OrderDecribeBean) o1;
							o1time = o1obj.getCreateTime().getTime();
						} else if (o1 instanceof OrderDecribeHeadTechBean) {
							OrderDecribeHeadTechBean o1obj = (OrderDecribeHeadTechBean) o1;
							o1time = o1obj.getCreateTime().getTime();
						} else if (o1 instanceof OrderDecribeUser) {
							OrderDecribeUser o1obj = (OrderDecribeUser) o1;
							o1time = o1obj.getCreateTime().getTime();
						} else {
							TSCHeadTechDecribeBean o1obj = (TSCHeadTechDecribeBean) o1;
							o1time = o1obj.getCreateTime().getTime();
						}
						if (o2 instanceof OrderDecribeBean) {
							OrderDecribeBean o2obj = (OrderDecribeBean) o2;
							o2time = o2obj.getCreateTime().getTime();
						} else if (o2 instanceof OrderDecribeHeadTechBean) {
							OrderDecribeHeadTechBean o2obj = (OrderDecribeHeadTechBean) o2;
							o2time = o2obj.getCreateTime().getTime();
						} else if (o2 instanceof OrderDecribeUser) {
							OrderDecribeUser o2obj = (OrderDecribeUser) o2;
							o2time = o2obj.getCreateTime().getTime();
						} else {
							TSCHeadTechDecribeBean o2obj = (TSCHeadTechDecribeBean) o2;
							o2time = o2obj.getCreateTime().getTime();
						}
						return o1time.compareTo(o2time);
					}
				});
				data.put("lists", decribeList);
			} else if (RoleConstant.HEADCOMTECH.equals(groupUser.getRoleType())) {
				List<TSCHeadTechDecribeBean> tsces = tSCHeadTechDecribeServiceImpl.findByOrderId(orderId, order);
				List<HeadTechDevelopBean> devlopes = headTechDevelopServiceImpl.findByOrderId(orderId, order);
				List<OrderDecribeHeadTechBean> decribes = null;
				List<OrderDecribeBean> tecHead = null;
				List<Object> decribeList = new ArrayList<Object>();
				// 跨级反馈
				decribes = orderDecribeHeadTechServiceImpl.findByOrderId(orderId, order);
				decribes.remove(0);
				// 如果此订单是直接分配给了 总技术的
				if (OrderStatusConStant.HEADTECH.equals(order.getIsHeadTech())) {
					tecHead = orderDecribeServiceImpl.findByOrderId(orderId,order);
					tecHead.remove(0);
				}
				devlopes.remove(0);

				if (null != devlopes) {
					for (HeadTechDevelopBean decriBean : devlopes) {
						decribeList.add(decriBean);
					}
				}

				if (null != tsces) {
					for (TSCHeadTechDecribeBean tesc : tsces) {
						decribeList.add(tesc);
					}
				}

				if (null != decribes) {
					for (OrderDecribeHeadTechBean tesc : decribes) {
						decribeList.add(tesc);
					}
				}
				if (null != decribesUser) {
					for (OrderDecribeUser decribeUser : decribesUser) {
						decribeList.add(decribeUser);
					}
				}
				if (null != tecHead) {
					for (OrderDecribeBean tesc : tecHead) {
						decribeList.add(tesc);
					}
				}
				Collections.sort(decribeList, new Comparator<Object>() {
					@Override
					public int compare(Object o1, Object o2) {
						Long o1time = null;
						Long o2time = null;
						if (o1 instanceof TSCHeadTechDecribeBean) {
							TSCHeadTechDecribeBean o1obj = (TSCHeadTechDecribeBean) o1;
							o1time = o1obj.getCreateTime().getTime();
						} else if (o1 instanceof OrderDecribeHeadTechBean) {
							OrderDecribeHeadTechBean o1obj = (OrderDecribeHeadTechBean) o1;
							o1time = o1obj.getCreateTime().getTime();
						} else if (o1 instanceof OrderDecribeBean) {
							OrderDecribeBean o1obj = (OrderDecribeBean) o1;
							o1time = o1obj.getCreateTime().getTime();
						} else if (o1 instanceof OrderDecribeUser) {
							OrderDecribeUser o1obj = (OrderDecribeUser) o1;
							o1time = o1obj.getCreateTime().getTime();
						} else {
							HeadTechDevelopBean o1obj = (HeadTechDevelopBean) o1;
							o1time = o1obj.getCreateTime().getTime();
						}
						if (o2 instanceof TSCHeadTechDecribeBean) {
							TSCHeadTechDecribeBean o2obj = (TSCHeadTechDecribeBean) o2;
							o2time = o2obj.getCreateTime().getTime();
						} else if (o2 instanceof OrderDecribeHeadTechBean) {
							OrderDecribeHeadTechBean o2obj = (OrderDecribeHeadTechBean) o2;
							o2time = o2obj.getCreateTime().getTime();
						} else if (o2 instanceof OrderDecribeBean) {
							OrderDecribeBean o2obj = (OrderDecribeBean) o2;
							o2time = o2obj.getCreateTime().getTime();
						} else if (o2 instanceof OrderDecribeUser) {
							OrderDecribeUser o2obj = (OrderDecribeUser) o2;
							o2time = o2obj.getCreateTime().getTime();
						} else {
							HeadTechDevelopBean o2obj = (HeadTechDevelopBean) o2;
							o2time = o2obj.getCreateTime().getTime();
						}
						return o1time.compareTo(o2time);
					}
				});

				data.put("lists", decribeList);
			} else if (RoleConstant.INVENT.equals(groupUser.getRoleType())) {
				List<HeadTechDevelopBean> devlopes = headTechDevelopServiceImpl
						.findByOrderId(orderId, order);
				// 1表示 反馈过
				Integer result = headTechDevelopServiceImpl.countIsFeedback(
						orderId, 0);
				if (result == 0) {
					order.setIsFeedback(0);
				} else {
					order.setIsFeedback(1);
				}
				List<Object> decribeList = new ArrayList<Object>();
				if (null != devlopes) {
					for (HeadTechDevelopBean devlope : devlopes) {
						decribeList.add(devlope);
					}
				}
				if (null != decribesUser) {
					for (OrderDecribeUser decribeUser : decribesUser) {
						decribeList.add(decribeUser);
					}
				}
				Collections.sort(decribeList, new Comparator<Object>() {
					@Override
					public int compare(Object o1, Object o2) {
						Long o1time = null;
						Long o2time = null;
						if (o1 instanceof OrderDecribeUser) {
							OrderDecribeUser o1obj = (OrderDecribeUser) o1;
							o1time = o1obj.getCreateTime().getTime();
						} else {
							HeadTechDevelopBean o1obj = (HeadTechDevelopBean) o1;
							o1time = o1obj.getCreateTime().getTime();
						}
						if (o2 instanceof OrderDecribeUser) {
							OrderDecribeUser o2obj = (OrderDecribeUser) o2;
							o2time = o2obj.getCreateTime().getTime();
						} else {
							HeadTechDevelopBean o2obj = (HeadTechDevelopBean) o2;
							o2time = o2obj.getCreateTime().getTime();
						}
						return o1time.compareTo(o2time);
					}
				});
				data.put("lists", decribeList);
			} else if (RoleConstant.CUSTOMER.equals(groupUser.getRoleType())
					|| RoleConstant.FILIALE_HEADER.equals(groupUser
							.getRoleType())
					|| RoleConstant.FILIALE_SALE_HEADER.equals(groupUser
							.getRoleType())
					|| RoleConstant.FILIALE_TECH_HEADER.equals(groupUser
							.getRoleType())
					|| RoleConstant.LEADER.equals(groupUser.getRoleType())
					|| RoleConstant.OPERATION.equals(groupUser.getRoleType())
					|| RoleConstant.SALE.equals(groupUser.getRoleType())
					|| RoleConstant.PRE_SALE.equals(groupUser.getRoleType())) {
				List<OrderDecribeBean> decribes = orderDecribeServiceImpl
						.findByOrderId(orderId, order);
				List<TSCHeadTechDecribeBean> tsces = tSCHeadTechDecribeServiceImpl
						.findByOrderId(orderId, order);
				List<HeadTechDevelopBean> devlopes = headTechDevelopServiceImpl
						.findByOrderId(orderId, order);
				// 跨级反馈的信息
				List<OrderDecribeHeadTechBean> orderDecribes = orderDecribeHeadTechServiceImpl
						.findByOrderId(orderId, order);
				tsces.remove(0);
				devlopes.remove(0);
				orderDecribes.remove(0);
				List<Object> decribeList = new ArrayList<Object>();
				if (null != devlopes) {
					for (HeadTechDevelopBean decriBean : devlopes) {
						decribeList.add(decriBean);
					}
				}
				if (null != tsces) {
					for (TSCHeadTechDecribeBean tesc : tsces) {
						decribeList.add(tesc);
					}
				}
				if (null != decribes) {
					for (OrderDecribeBean orderD : decribes) {
						decribeList.add(orderD);
					}
				}

				if (null != orderDecribes) {
					for (OrderDecribeHeadTechBean orderDecribe : orderDecribes) {
						decribeList.add(orderDecribe);
					}
				}
				if (null != decribesUser) {
					for (OrderDecribeUser decribeUser : decribesUser) {
						decribeList.add(decribeUser);
					}
				}

				Collections.sort(decribeList, new Comparator<Object>() {
					@Override
					public int compare(Object o1, Object o2) {
						Long o1time = null;
						Long o2time = null;
						if (o1 instanceof TSCHeadTechDecribeBean) {
							TSCHeadTechDecribeBean o1obj = (TSCHeadTechDecribeBean) o1;
							o1time = o1obj.getCreateTime().getTime();
						} else if (o1 instanceof HeadTechDevelopBean) {
							HeadTechDevelopBean o1obj = (HeadTechDevelopBean) o1;
							o1time = o1obj.getCreateTime().getTime();
						} else if (o1 instanceof OrderDecribeHeadTechBean) {
							OrderDecribeHeadTechBean o1obj = (OrderDecribeHeadTechBean) o1;
							o1time = o1obj.getCreateTime().getTime();
						} else if (o1 instanceof OrderDecribeUser) {
							OrderDecribeUser o1obj = (OrderDecribeUser) o1;
							o1time = o1obj.getCreateTime().getTime();
						} else {
							OrderDecribeBean o1obj = (OrderDecribeBean) o1;
							o1time = o1obj.getCreateTime().getTime();
						}
						if (o2 instanceof TSCHeadTechDecribeBean) {
							TSCHeadTechDecribeBean o2obj = (TSCHeadTechDecribeBean) o2;
							o2time = o2obj.getCreateTime().getTime();
						} else if (o2 instanceof HeadTechDevelopBean) {
							HeadTechDevelopBean o2obj = (HeadTechDevelopBean) o2;
							o2time = o2obj.getCreateTime().getTime();
						} else if (o2 instanceof OrderDecribeHeadTechBean) {
							OrderDecribeHeadTechBean o2obj = (OrderDecribeHeadTechBean) o2;
							o2time = o2obj.getCreateTime().getTime();
						} else if (o2 instanceof OrderDecribeUser) {
							OrderDecribeUser o2obj = (OrderDecribeUser) o2;
							o2time = o2obj.getCreateTime().getTime();
						} else {
							OrderDecribeBean o2obj = (OrderDecribeBean) o2;
							o2time = o2obj.getCreateTime().getTime();
						}
						return o1time.compareTo(o2time);
					}
				});
				data.put("lists", decribeList);
			}
		}
		data.put("order", order);
		UserBean user = userServiceImpl.findById(order.getUserId());
		data.put("user", user);
		if (null != order.getTscId()) {
			GroupUser groupuser = groupUserServiceImpl.findByIdToName(order
					.getTscId());
			data.put("tsc", groupuser);
		} else if (null != order.getHeadTechId() && null == order.getTscId()) {
			GroupUser groupuser = groupUserServiceImpl.findByIdToName(order
					.getHeadTechId());
			data.put("tsc", groupuser);
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}

	/**
	 * 添加描述 或者 补丁
	 * 
	 * @param orderId
	 * @param content
	 * @param images
	 * @param videos
	 * @param voices
	 * @param flag
	 *            标记 0 向上反馈 1 向下反馈
	 * @return
	 */
	@RequestMapping("/addOrderDecribe")
	@ResponseBody
	public ViewData addOrderDecribe(Integer orderId, String content,String images, String videos, String voices, Integer flag,
			Integer roleId, Integer to, Integer isFeedback) {
		// isFeedback 0 表示 反馈 1 表示 补丁
		if (null == content || null == flag || null == orderId) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		Object obj = LoginHelper.getCurrentUser();
		if (null == obj) {
			return buildFailureJson(StatusConstant.NOTLOGIN, "请先登录");
		}
		OrderBean order = orderServiceImpl.findById(orderId);
		if (OrderStatusConStant.NON_VALIED.equals(order.getIsValid())) {
			return buildFailureJson(StatusConstant.ORDER_INVALID, "无效的订单");
		}
		if (order.getStatus().equals(OrderStatusConStant.OVERALLPENGDING)) {
			return buildFailureJson(StatusConstant.Fail_CODE, "订单已完成,无法操作");
		}
		// // 如果该订单是直接分配给总公司技术的
		// if (order.getIsHeadTech().equals(OrderStatusConStant.HEADTECH)) {
		// OrderDecribeHeadTechBean temp = new OrderDecribeHeadTechBean();
		// temp.setContent(content);
		// temp.setImages(images);
		// temp.setOrderId(orderId);
		// temp.setVideos(videos);
		// temp.setVoices(voices);
		//
		// orderDecribeHeadTechServiceImpl.addDecribe(temp);
		// return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "添加成功");
		// }
		OrderDecribeBean decribe = new OrderDecribeBean();
		if (obj instanceof UserBean) {
			if (flag.equals(StatusConstant.DOWN)) {
				return buildFailureJson(StatusConstant.Fail_CODE, "不能向下反馈");
			}
			UserBean user = (UserBean) obj;
			if (StatusConstant.FROZEN.equals(user.getStatus())) {
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户已被冻结");
			}
			if (flag.equals(StatusConstant.UPORDOWNWARD)) {
				// 如果是跨级反馈
				OrderDecribeHeadTechBean temp = new OrderDecribeHeadTechBean();
				temp.setContent(content);
				temp.setImages(images);
				temp.setOrderId(orderId);
				temp.setVideos(videos);
				temp.setVoices(voices);
				temp.setUser(user.getId());
				orderDecribeHeadTechServiceImpl.addDecribe(temp);
				return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "添加成功");
			}
			if (flag.equals(StatusConstant.ORDER_DECRIBE_USER)) {
				// 如果是用户反馈 全局可见的信息
				OrderDecribeUser temp = new OrderDecribeUser();
				temp.setContent(content);
				temp.setImages(images);
				temp.setOrderId(orderId);
				temp.setVideos(videos);
				temp.setVoices(voices);
				temp.setUserId(user.getId());
				orderDecribeUserServiceImpl.addOrderDecribe(temp);
				return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "添加成功");
			}

			decribe.setContent(content);
			decribe.setImages(images);
			decribe.setOrderId(orderId);
			decribe.setVideos(videos);
			decribe.setVoices(voices);

			decribe.setUserId(user.getId());
			orderDecribeServiceImpl.addOrderDecribe(decribe);

			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "添加成功");
		} else if (obj instanceof GroupUser) {
			GroupUser user = (GroupUser) obj;
			if (StatusConstant.FROZEN.equals(user.getStatus())) {
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户已被冻结");
			}
			if (RoleConstant.FILIALETECH.equals(user.getRoleType())) {
				// 如果是分公司技术

				if (flag.equals(StatusConstant.UP)) {
					TSCHeadTechDecribeBean tscbean = new TSCHeadTechDecribeBean();
					tscbean.setContent(content);
					tscbean.setImages(images);
					tscbean.setOrderId(orderId);
					tscbean.setVideos(videos);
					tscbean.setVoices(voices);
					tscbean.setTSCId(user.getId());
					tSCHeadTechDecribeServiceImpl.addOrderDecribe(tscbean);
				} else if (flag.equals(StatusConstant.DOWN)) {
					decribe.setContent(content);
					decribe.setImages(images);
					decribe.setOrderId(orderId);
					decribe.setVideos(videos);
					decribe.setVoices(voices);

					decribe.setTSCId(user.getId());
					orderDecribeServiceImpl.addOrderDecribe(decribe);
				}
				return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "添加成功");

			} else if (RoleConstant.HEADCOMTECH.equals(user.getRoleType())) {
				// 如果是总部技术
				if (flag.equals(StatusConstant.UP)) {
					HeadTechDevelopBean develop = new HeadTechDevelopBean();
					develop.setContent(content);
					develop.setImages(images);
					develop.setOrderId(orderId);
					develop.setVideos(videos);
					develop.setVoices(voices);

					develop.setHeadTechId(user.getId());
					headTechDevelopServiceImpl.addOrderDecribe(develop);
				} else if (flag.equals(StatusConstant.DOWN)) {
					TSCHeadTechDecribeBean tscbean = new TSCHeadTechDecribeBean();
					tscbean.setContent(content);
					tscbean.setImages(images);
					tscbean.setOrderId(orderId);
					tscbean.setVideos(videos);
					tscbean.setVoices(voices);
					tscbean.setHeadTechId(user.getId());
					tSCHeadTechDecribeServiceImpl.addOrderDecribe(tscbean);
				} else if (flag.equals(StatusConstant.UPORDOWNWARD)) {
					// 如果是跨级反馈
					OrderDecribeHeadTechBean temp = new OrderDecribeHeadTechBean();
					temp.setContent(content);
					temp.setImages(images);
					temp.setOrderId(orderId);
					temp.setVideos(videos);
					temp.setVoices(voices);
					temp.setHeadTeah(user.getId());
					orderDecribeHeadTechServiceImpl.addDecribe(temp);
					return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE,"添加成功");
				}
				return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "添加成功");
			} else if (RoleConstant.INVENT.equals(user.getRoleType())) {
				// 如果是 研发
				if (flag.equals(StatusConstant.UP)) {
					return buildFailureJson(StatusConstant.Fail_CODE, "不能向上反馈");
				} else if (flag.equals(StatusConstant.DOWN)) {
					// isFeedback 0 表示 反馈 1 表示 补丁
					HeadTechDevelopBean develop = new HeadTechDevelopBean();
					develop.setContent(content);
					develop.setImages(images);
					develop.setOrderId(orderId);
					develop.setVideos(videos);
					develop.setVoices(voices);
					develop.setDevelopId(user.getId());
					if (isFeedback == null) {
						develop.setType(0);
					} else {
						develop.setType(isFeedback);
					}

					headTechDevelopServiceImpl.addOrderDecribe(develop);
					return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE,"添加成功");
				} else {
					return buildFailureJson(StatusConstant.NON_ALLOW, "没有权限");
				}
			}
		}
		return buildFailureJson(StatusConstant.NON_ALLOW, "没有权限");
	}

	/**
	 * 修改查看操作
	 * 
	 * @return
	 */
	@RequestMapping("/updateCheck")
	@ResponseBody
	public ViewData updateServiceCheck(Integer orderId) {

		Object obj = LoginHelper.getCurrentUser();
		if (null == obj) {
			return buildFailureJson(StatusConstant.NOTLOGIN, "对象不存在");
		}
		if (obj instanceof UserBean) {
			return buildFailureJson(StatusConstant.NON_ALLOW, "没有权限");
		}
		OrderBean order = orderServiceImpl.findById(orderId);
		if (null == order
				|| order.getIsValid().equals(OrderStatusConStant.NON_VALIED)) {
			return buildFailureJson(StatusConstant.Fail_CODE, "订单无效");
		}
		OrderBean tempOrder = new OrderBean();
		tempOrder.setId(orderId);
		tempOrder.setStatus(order.getStatus());
		if (obj instanceof GroupUser) {
			GroupUser user = (GroupUser) obj;
			if (StatusConstant.FROZEN.equals(user.getStatus())) {
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户已被冻结");
			}
			if (user.getRoleType().equals(RoleConstant.CUSTOMER)) {
				// orderServiceImpl.updateOrderServiceCheck(orderId,OrderStatusConStant.CHECKED);
				if (OrderStatusConStant.CHECKED.equals(order.getServiceCheck())) {
					return buildFailureJson(StatusConstant.Fail_CODE, "已经查看");
				}
				tempOrder.setServiceCheck(OrderStatusConStant.CHECKED);
				tempOrder
						.setServiceIsAccept(OrderStatusConStant.NON_ORDER_ACCEPT);
				JSONArray jsonArray = null;
				JSONArray jsonArrayUser = null;
				try {
					jsonArray = JSONArray.fromObject(order.getGroupOrderData());
					jsonArrayUser = JSONArray.fromObject(order.getOrderData());
					OrderTrackBean trackUser = new OrderTrackBean(
							OrderStatusConStant.SUSPENDING,
							OrderStatusConStant.ORDER_ACCEPTED, new Date());
					OrderTrackBean track = new OrderTrackBean(
							order.getStatus(),
							OrderStatusConStant.ALLOTING_TO_TECH, new Date());
					jsonArray.add(track.createOrderTrack());
					jsonArrayUser.add(trackUser.createOrderTrack());
					orderServiceImpl.updateGroupOrderData(orderId,
							jsonArray.toString());
					orderServiceImpl.updateOrderData(orderId,
							jsonArrayUser.toString());
				} catch (Exception e) {
					log.debug(e.getMessage(), e);
				}
			} else if (user.getRoleType().equals(RoleConstant.FILIALETECH)) {

				if (OrderStatusConStant.CHECKED.equals(order.getTechCheck())) {
					return buildFailureJson(StatusConstant.Fail_CODE, "已经查看");
				}
				tempOrder.setTscIsAccept(OrderStatusConStant.NON_ORDER_ACCEPT);
				tempOrder.setTechCheck(OrderStatusConStant.CHECKED);
				if (OrderStatusConStant.NON_HEADTECH.equals(order
						.getIsHeadTech())) {
					JSONArray jsonArray = null;
					try {
						jsonArray = JSONArray.fromObject(order.getOrderData());
						OrderTrackBean track = new OrderTrackBean(
								order.getStatus(),
								OrderStatusConStant.ORDER_STARTHANDLE,
								new Date());
						jsonArray.add(track.createOrderTrack());
						orderServiceImpl.updateOrderData(orderId,
								jsonArray.toString());
					} catch (Exception e) {
						log.debug(e.getMessage(), e);
					}
				}
			} else if (user.getRoleType().equals(RoleConstant.HEADCOMTECH)) {
				// orderServiceImpl.updateHeadTechIsCheck(orderId,OrderStatusConStant.CHECKED);
				if (OrderStatusConStant.CHECKED
						.equals(order.getHeadTechCheck())) {
					return buildFailureJson(StatusConstant.Fail_CODE, "已经查看");
				}
				tempOrder
						.setHeadTechIsAccept(OrderStatusConStant.NON_ORDER_ACCEPT);
				tempOrder.setHeadTechCheck(OrderStatusConStant.CHECKED);
				if (OrderStatusConStant.HEADTECH.equals(order.getIsHeadTech())) {
					JSONArray jsonArray = null;
					try {
						jsonArray = JSONArray.fromObject(order.getOrderData());
						OrderTrackBean track = new OrderTrackBean(
								order.getStatus(),
								OrderStatusConStant.ORDER_STARTHANDLE,
								new Date());
						jsonArray.add(track.createOrderTrack());
						orderServiceImpl.updateOrderData(orderId,
								jsonArray.toString());
					} catch (Exception e) {
						log.debug(e.getMessage(), e);
					}
				}
			} else if (user.getRoleType().equals(RoleConstant.INVENT)) {
				// orderServiceImpl.updateDevelopIsCheck(orderId,OrderStatusConStant.CHECKED);
				if (OrderStatusConStant.CHECKED.equals(order.getDevelopCheck())) {
					return buildFailureJson(StatusConstant.Fail_CODE, "已经查看");
				}
				tempOrder.setDevelopCheck(OrderStatusConStant.CHECKED);
				tempOrder
						.setDevelopIsAccept(OrderStatusConStant.NON_ORDER_ACCEPT);
			} else {
				return buildFailureJson(StatusConstant.NON_ALLOW, "没有权限");
			}
			orderServiceImpl.updateOrderStatus(tempOrder);
			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "修改成功");
		}
		return buildFailureJson(StatusConstant.Fail_CODE, "无法修改");
	}

	/**
	 * 接受任务
	 * 
	 * @param orderId
	 * @return
	 */
	@RequestMapping("/acceptOrder")
	@ResponseBody
	public ViewData updateIsAcceptOrder(Integer orderId) {

		Object obj = LoginHelper.getCurrentUser();
		if (null == obj) {
			return buildFailureJson(StatusConstant.NOTLOGIN, "对象不存在");
		}
		if (obj instanceof UserBean) {
			return buildFailureJson(StatusConstant.NON_ALLOW, "没有权限");
		}
		OrderBean order = orderServiceImpl.findById(orderId);
		if (null == order
				|| order.getIsValid().equals(OrderStatusConStant.NON_VALIED)) {
			return buildFailureJson(StatusConstant.Fail_CODE, "订单无效");
		}

		OrderBean tempOrder = new OrderBean();
		tempOrder.setId(orderId);
		tempOrder.setStatus(order.getStatus());
		if (obj instanceof GroupUser) {
			GroupUser user = (GroupUser) obj;
			if (StatusConstant.FROZEN.equals(user.getStatus())) {
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户已被冻结");
			}
			if (RoleConstant.CUSTOMER.equals(user.getRoleType())) {
				return buildFailureJson(StatusConstant.NON_ALLOW, "没有权限");
			}
			if (RoleConstant.FILIALETECH.equals(user.getRoleType())) {
				tempOrder.setStatus(OrderStatusConStant.HANDLING);
				tempOrder.setTscIsAccept(OrderStatusConStant.ORDER_ACCEPT);
			}
			if (RoleConstant.HEADCOMTECH.equals(user.getRoleType())) {
				tempOrder.setStatus(OrderStatusConStant.HANDLING);
				tempOrder.setHeadTechIsAccept(OrderStatusConStant.ORDER_ACCEPT);
			}
			if (RoleConstant.INVENT.equals(user.getRoleType())) {
				tempOrder.setDevelopIsAccept(OrderStatusConStant.ORDER_ACCEPT);
			}
		}
		orderServiceImpl.updateOrderStatus(tempOrder);
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "修改成功");
	}

	/**
	 * 修改订单状态 之 客服 分配订单给 TSC
	 * 
	 * @return
	 */
	@RequestMapping("/allotorder")
	@ResponseBody
	public ViewData getOrderByStatus(Integer orderId, Integer tscId,Integer roleType, String ccs) {
		if (null == orderId || null == tscId) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		GroupUser user = groupUserServiceImpl.findByIdToName(tscId);
		if (null == user) {
			return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "对象不存在");
		}
		OrderBean ordert = orderServiceImpl.findById(orderId);
		if (null == ordert) {
			return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "订单不存在");
		}
		
		try {
			if (OrderStatusConStant.HEADTECH.equals(ordert.getIsHeadTech())) {
				OrderBean order = new OrderBean();
				order.setId(orderId);
				order.setStatus(ordert.getStatus());
				order.setTscId(tscId);
				order.setTechStartTime(new Date());
				order.setTechCheck(OrderStatusConStant.NON_CHECK);
				JSONArray ccArray = null;
				if (null != ccs) {
					ccArray = JSONArray.fromObject(ccs);
				}
				List<CCGroupUserBean> cclist = null;
				List<Integer> groupIds = null;
				if (null != ccArray) {
					cclist = new ArrayList<CCGroupUserBean>();
					groupIds = new ArrayList<Integer>();
					for (Object cc : ccArray) {
						Integer jsobj = (Integer) cc;
						CCGroupUserBean temp = new CCGroupUserBean();
						temp.setGroupuserId(jsobj);
						temp.setOrderId(orderId);
						cclist.add(temp);
						groupIds.add(jsobj);
					}
				}
				if (null != cclist) {
					cCgroupUserServiceImpl.batchAdd(cclist);
				}
				orderServiceImpl.updateOrderStatus(order);

			} else {
				OrderBean order = new OrderBean();
				order.setId(orderId);
				order.setStatus(OrderStatusConStant.SUSPENDING);
				if (RoleConstant.HEADCOMTECH.equals(roleType)) {
					order.setHeadTechId(tscId);
					order.setHeadTechCheck(OrderStatusConStant.NON_CHECK);
					order.setIsHeadTech(OrderStatusConStant.HEADTECH);
					order.setHeadTechStartTime(new Date());
				} else if (RoleConstant.FILIALETECH.equals(roleType)) {
					order.setTscId(tscId);
					order.setTechCheck(OrderStatusConStant.NON_CHECK);
					order.setTechStartTime(new Date());
				}
				orderServiceImpl.updateOrderStatus(order);
			}
			JSONArray jsonArray = null;
			try {
				jsonArray = JSONArray.fromObject(ordert.getGroupOrderData());
				OrderTrackBean track = new OrderTrackBean(ordert.getStatus(),
						MessageFormat.format(
								OrderStatusConStant.ALLOTED_TO_TECH,
								user.getRealName()), new Date());
				jsonArray.add(track.createOrderTrack());
				orderServiceImpl.updateGroupOrderData(orderId,
						jsonArray.toString());
			} catch (Exception e) {
				log.debug(e.getMessage(), e);
			}

			// 推送消息
			GroupUser tscUser = groupUserServiceImpl.findTypeTokeById(tscId);
			if (null != tscUser) {
				PushMessageUtil.pushMessages(tscUser, "收到来自客服分配的订单",new HashMap<String, String>());
			}
			UserBean usert = userServiceImpl.findById(ordert.getUserId());
			if (null != usert) {
				PushMessageUtil.pushMessages(usert, "小贝已将技术方案散发下去啦，请您持续关注~",new HashMap<String, String>());
			}
			// 存储推送消息
			List<OrderInfoBean> infos = new ArrayList<OrderInfoBean>();
			OrderInfoBean orderInfoO = new OrderInfoBean(orderId,usert.getId(), null, "小贝已将技术方案散发下去啦，请您持续关注~",ordert.getOrderNumber());
			OrderInfoBean orderInfoTwo = new OrderInfoBean(orderId, null,tscUser.getId(), "收到来自客服分配的订单", ordert.getOrderNumber());
			infos.add(orderInfoO);
			infos.add(orderInfoTwo);
			orderInfoServiceImpl.batchAddOrderInfo(infos);
		} catch (Exception e) {
			log.debug(e.getMessage(), e);
			return buildSuccessCodeJson(StatusConstant.Fail_CODE, "操作失败");
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "分配成功");
	}

	/**
	 * 通过订单问题类型 获取 TSC 和 非编技术员工
	 * 
	 * @param categoryId
	 * @return
	 */
	@RequestMapping("/getTSC")
	@ResponseBody
	public ViewData getTSC(Integer categoryId, Integer orderId, Integer userId) {
		if (null == categoryId || null == orderId || userId == null) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		Map<String, Object> data = new HashMap<String, Object>();
		// 查询出 分公司技术
		List<GroupUser> tscs = groupUserServiceImpl.findByUserId(userId);

		List<GroupUser> feibian = groupUserServiceImpl.findAllTechFeibian(userId, categoryId);
		OrderBean order = orderServiceImpl.findById(orderId);
		if (null == order) {
			return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "该订单不存在");
		}
		data.put("order", order);
		UserBean user = userServiceImpl.findById(order.getUserId());
		data.put("user", user);
		data.put("tsc", tscs);
		data.put("feibian", feibian);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}

	/**
	 * 单独获取 分公司下的TSC
	 * 
	 * @return
	 */
	@RequestMapping("/getTSCOnly")
	@ResponseBody
	public ViewData getTsc(Integer categoryId, Integer orderId, Integer userId) {

		if (null == categoryId || null == orderId || userId == null) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		Map<String, Object> data = new HashMap<String, Object>();
		// 查询出分公司技术
		List<GroupUser> tscs = groupUserServiceImpl.findByUserId(userId);
		// List<GroupUser> feibian =
		// groupUserServiceImpl.findAllTechFeibian(userId, categoryId);

		OrderBean order = orderServiceImpl.findById(orderId);
		if (null == order) {
			return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "该订单不存在");
		}
		data.put("order", order);
		UserBean user = userServiceImpl.findById(order.getUserId());
		data.put("user", user);
		data.put("tsc", tscs);
		// data.put("feibian", feibian);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);

	}

	// /**
	// * 订单被分配给技术人员后，技术人员查看订单， 修改是否查看状态
	// *
	// * @return
	// */
	//
	// public ViewData updateTSCChecked(Integer orderId) {
	//
	// Object obj = LoginHelper.getCurrentUser();
	// if (null == obj) {
	// return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "对象不存在");
	// }
	// if (obj instanceof UserBean) {
	// return buildFailureJson(StatusConstant.NON_ALLOW, "没有权限");
	// }
	// OrderBean order = orderServiceImpl.findById(orderId);
	// if (null == order||
	// order.getIsValid().equals(OrderStatusConStant.NON_VALIED)) {
	// return buildFailureJson(StatusConstant.Fail_CODE, "订单无效");
	// }
	// if (obj instanceof GroupUser) {
	// GroupUser user = (GroupUser) obj;
	// if (!user.getRoleType().equals(RoleConstant.FILIALETECH)) {
	// return buildFailureJson(StatusConstant.NON_ALLOW, "没有权限");
	// }
	// orderServiceImpl.updateOrderTechCheck(orderId,OrderStatusConStant.CHECKED);
	// JSONArray jsonArray = null;
	// try {
	// jsonArray = JSONArray.fromObject(order.getOrderData());
	// OrderTrackBean track = new
	// OrderTrackBean(order.getStatus(),OrderStatusConStant.ORDER_STARTHANDLE,
	// new Date());
	// jsonArray.add(track.createOrderTrack());
	// orderServiceImpl.updateOrderData(orderId, jsonArray.toString());
	// } catch (Exception e) {
	// log.debug(e.getMessage(), e);
	// }
	// return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "修改成功");
	// }
	//
	// return buildFailureJson(StatusConstant.Fail_CODE, "修改失败");
	// }

	/**
	 * 分公司技术 不能处理 请求给 总部技术
	 * 
	 * @return
	 */
	@RequestMapping("/applicationHeadTech")
	@ResponseBody
	public ViewData applicationHeaderTech(Integer orderId, Integer headTechId,String ccs) {
		if (null == orderId || headTechId == null) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		Object obj = LoginHelper.getCurrentUser();
		if (null == obj) {
			return buildFailureJson(StatusConstant.NOTLOGIN, "对象不存在");
		}

		GroupUser headTech = groupUserServiceImpl.findByIdToName(headTechId);
		if (null == headTech) {
			return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "对象不存在");
		}
		OrderBean ordert = orderServiceImpl.findById(orderId);
		if (null == ordert) {
			return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "订单不存在");
		}
		JSONArray ccArray = null;
		if (null != ccs) {
			ccArray = JSONArray.fromObject(ccs);
		}
		if (obj instanceof UserBean) {
			return buildFailureJson(StatusConstant.NON_ALLOW, "没有权限");
		}
		if (obj instanceof GroupUser) {
			GroupUser user = (GroupUser) obj;
			if (StatusConstant.FROZEN.equals(user.getStatus())) {
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户已被冻结");
			}
			if (!user.getRoleType().equals(RoleConstant.FILIALETECH)) {
				return buildFailureJson(StatusConstant.NON_ALLOW, "没有权限");
			}
			OrderBean order = new OrderBean();
			order.setId(orderId);
			order.setStatus(OrderStatusConStant.HANDLING);
			order.setHeadTechId(headTechId);
			order.setHeadTechStartTime(new Date());
			List<CCGroupUserBean> cclist = null;
			List<Integer> groupIds = null;
			if (null != ccArray) {
				cclist = new ArrayList<CCGroupUserBean>();
				groupIds = new ArrayList<Integer>();
				for (Object cc : ccArray) {
					Integer jsobj = (Integer) cc;
					CCGroupUserBean temp = new CCGroupUserBean();
					temp.setGroupuserId(jsobj);
					temp.setOrderId(orderId);
					cclist.add(temp);
					groupIds.add(jsobj);
				}
			}
			if (null != cclist) {
				cCgroupUserServiceImpl.batchAdd(cclist);
			}
			orderServiceImpl.updateOrderStatus(order);
			orderServiceImpl.updateHeadTechIsCheck(orderId,OrderStatusConStant.NON_CHECK);
			JSONArray jsonArray = null;
			try {
				jsonArray = JSONArray.fromObject(ordert.getGroupOrderData());
				OrderTrackBean track = new OrderTrackBean(ordert.getStatus(),MessageFormat.format(OrderStatusConStant.ALLOTED_TO_HEAD_TECH,headTech.getRealName()), new Date());
				jsonArray.add(track.createOrderTrack());
				orderServiceImpl.updateGroupOrderData(orderId,jsonArray.toString());
			} catch (Exception e) {
				log.debug(e.getMessage(), e);
			}

			List<OrderInfoBean> infos = new ArrayList<OrderInfoBean>();
			// 推送消息
			GroupUser headUser = groupUserServiceImpl.findTypeTokeById(headTechId);
			if (null != headUser) {
				PushMessageUtil.pushMessages(headUser, "收到来自分公司技术的技术指导",new HashMap<String, String>());
				OrderInfoBean orderInfoBean = new OrderInfoBean(orderId, null,headUser.getId(), "收到来自分公司技术的技术指导",ordert.getOrderNumber());
				infos.add(orderInfoBean);
			}
			GroupUser serviceUser = groupUserServiceImpl.findTypeTokeById(ordert.getServiceId());
			if (null != serviceUser) {
				PushMessageUtil.pushMessages(serviceUser, "分公司技术请求总公司技术的指导",new HashMap<String, String>());
				OrderInfoBean orderInfoBean = new OrderInfoBean(orderId, null,serviceUser.getId(), "分公司技术请求总公司技术的指导",ordert.getOrderNumber());
				infos.add(orderInfoBean);
			}
			
//			if (null != groupIds) {
//				List<GroupUser> groupUsers = groupUserServiceImpl.batchQueryGroupUser(groupIds);
//				for (GroupUser groupUserT : groupUsers) {
//					if (null != groupUserT) {
//						PushMessageUtil.pushMessages(groupUserT,"分公司技术请求总公司技术的指导",new HashMap<String, String>());
//						OrderInfoBean orderInfoBean = new OrderInfoBean(orderId, null, groupUserT.getId(),"分公司技术请求总公司技术的指导", ordert.getOrderNumber());
//						infos.add(orderInfoBean);
//					}
//				}
//			}
			// 存储推送消息
			try {
				orderInfoServiceImpl.batchAddOrderInfo(infos);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "申请成功");
		}
		return buildFailureJson(StatusConstant.Fail_CODE, "申请失败");
	}

	// /**
	// * 总部技术 点击 订单 修改订单状态为查看
	// *
	// * @return
	// */
	// @RequestMapping("/headCheckOrder")
	// @ResponseBody
	// public ViewData headTechCheckOrder(Integer orderId) {
	//
	// if (null == orderId) {
	// return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
	// }
	// Object obj = LoginHelper.getCurrentUser();
	// if (null == obj) {
	// return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "对象不存在");
	// }
	// if (obj instanceof UserBean) {
	// return buildFailureJson(StatusConstant.NON_ALLOW, "没有权限");
	// }
	// GroupUser user = (GroupUser) obj;
	// if (!RoleConstant.HEADCOMTECH.equals(user.getRoleType())) {
	// return buildFailureJson(StatusConstant.NON_ALLOW, "没有权限");
	// }
	// orderServiceImpl.updateHeadTechIsCheck(orderId,OrderStatusConStant.CHECKED);
	//
	// return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "修改成功");
	// }

	/**
	 * 取消订单
	 * 
	 * @param orderId
	 * @return
	 */
	@RequestMapping("/cancleOrder")
	@ResponseBody
	public ViewData cancleOrder(Integer orderId, Integer userId) {
		if (orderId == null || userId == null) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		Object obj = LoginHelper.getCurrentUser();
		if (null == obj) {
			return buildFailureJson(StatusConstant.NOTLOGIN, "对象不存在");
		}
		if (obj instanceof GroupUser) {
			return buildFailureJson(StatusConstant.NON_ALLOW, "没有权限");
		}
		UserBean user = (UserBean) obj;
		if (StatusConstant.FROZEN.equals(user.getStatus())) {
			return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户已被冻结");
		}
		if (!userId.equals(user.getId())) {
			return buildFailureJson(StatusConstant.NON_ALLOW, "不是你的订单，无权限");
		}
		OrderBean order = orderServiceImpl.findById(orderId);
		if (null == order) {
			return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "订单不存在");
		}
		if (OrderStatusConStant.HANDLING.equals(order.getStatus())) {
			return buildFailureJson(StatusConstant.ORDER_STATUS_ABNORMITY,
					"订单状态异常");
		}
		orderServiceImpl.updateIsValid(orderId, OrderStatusConStant.NON_VALIED);
		// 存储推送消息
		List<OrderInfoBean> infos = new ArrayList<OrderInfoBean>();

		// 订单推送
		if (order.getServiceId() != null) {
			GroupUser serviceUser = groupUserServiceImpl.findTypeTokeById(order
					.getServiceId());
			if (null != serviceUser) {
				PushMessageUtil.pushMessages(serviceUser, "用户已取消订单",
						new HashMap<String, String>());
				OrderInfoBean orderInfo = new OrderInfoBean(orderId, null,
						serviceUser.getId(), "用户已取消订单", order.getOrderNumber());
				infos.add(orderInfo);
			}
		}
		if (order.getTscId() != null) {
			GroupUser tscUser = groupUserServiceImpl.findTypeTokeById(order
					.getTscId());
			if (null != tscUser) {
				PushMessageUtil.pushMessages(tscUser, "用户已取消订单",
						new HashMap<String, String>());
				OrderInfoBean orderInfo = new OrderInfoBean(orderId, null,
						tscUser.getId(), "用户已取消订单", order.getOrderNumber());
				infos.add(orderInfo);
			}
		}
		if (order.getHeadTechId() != null) {
			GroupUser headUser = groupUserServiceImpl.findTypeTokeById(order
					.getHeadTechId());
			if (null != headUser) {
				PushMessageUtil.pushMessages(headUser, "用户已取消订单",new HashMap<String, String>());
				OrderInfoBean orderInfo = new OrderInfoBean(orderId, null,headUser.getId(), "用户已取消订单", order.getOrderNumber());
				infos.add(orderInfo);
			}
		}
		if (order.getDecelopId() != null) {
			GroupUser developUser = groupUserServiceImpl.findTypeTokeById(order
					.getDecelopId());
			if (null != developUser) {
				PushMessageUtil.pushMessages(developUser, "用户已取消订单",
						new HashMap<String, String>());
				OrderInfoBean orderInfo = new OrderInfoBean(orderId, null,
						developUser.getId(), "用户已取消订单", order.getOrderNumber());
				infos.add(orderInfo);
			}
		}

		List<CCGroupUserBean> ccs = cCgroupUserServiceImpl
				.findByOrderId(orderId);
		if (null != ccs) {
			List<Integer> ids = new ArrayList<Integer>();
			for (CCGroupUserBean cc : ccs) {
				ids.add(cc.getGroupuserId());
			}
			if (ids.size() != 0) {
				List<GroupUser> groupUsers = groupUserServiceImpl
						.batchQueryGroupUser(ids);
				if (groupUsers != null) {
					for (GroupUser groupUserT : groupUsers) {
						if (null != groupUserT) {
							PushMessageUtil.pushMessages(groupUserT, "用户已取消订单",
									new HashMap<String, String>());
							OrderInfoBean orderInfo = new OrderInfoBean(
									orderId, null, groupUserT.getId(),
									"用户已取消订单", order.getOrderNumber());
							infos.add(orderInfo);
						}
					}
				}
			}
		}
		if (infos.size() != 0) {
			try {
				orderInfoServiceImpl.batchAddOrderInfo(infos);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "更新成功");
	}

	/**
	 * 总部技术 申请 研发处理
	 * 
	 * @return
	 */
	@RequestMapping("/applicationInvent")
	@ResponseBody
	public ViewData applicationInvent(Integer orderId, Integer developId) {

		if (null == orderId) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		Object obj = LoginHelper.getCurrentUser();
		if (null == obj) {
			return buildFailureJson(StatusConstant.NOTLOGIN, "对象不存在");
		}
		GroupUser develor = groupUserServiceImpl.findByIdToName(developId);
		if (null == develor) {
			return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "对象不存在");
		}
		if (obj instanceof UserBean) {
			return buildFailureJson(StatusConstant.NON_ALLOW, "没有权限");
		}
		GroupUser user = (GroupUser) obj;
		if (StatusConstant.FROZEN.equals(user.getStatus())) {
			return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户已被冻结");
		}
		if (!RoleConstant.HEADCOMTECH.equals(user.getRoleType())) {
			return buildFailureJson(StatusConstant.NON_ALLOW, "没有权限");
		}
		OrderBean order = orderServiceImpl.findById(orderId);
		if (order == null) {
			return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "订单不存在");
		}

		OrderBean tempOrder = new OrderBean();
		tempOrder.setId(order.getId());
		tempOrder.setStatus(order.getStatus());
		tempOrder.setDecelopId(developId);
		orderServiceImpl.updateOrderStatus(tempOrder);
		orderServiceImpl.updateDevelopIsCheck(orderId,
				OrderStatusConStant.NON_CHECK);
		JSONArray jsonArray = null;
		try {
			jsonArray = JSONArray.fromObject(order.getGroupOrderData());
			OrderTrackBean track = new OrderTrackBean(order.getStatus(),
					MessageFormat.format(
							OrderStatusConStant.ALLOTED_TO_HEAD_DEVELOP,
							develor.getRealName()), new Date());
			jsonArray.add(track.createOrderTrack());
			orderServiceImpl
					.updateGroupOrderData(orderId, jsonArray.toString());
		} catch (Exception e) {
			log.debug(e.getMessage(), e);
		}
		// 存储推送消息
		List<OrderInfoBean> infos = new ArrayList<OrderInfoBean>();

		// 消息推送
		// 推给研发
		PushMessageUtil.pushMessages(develor, "您有新的订单，请查收",new HashMap<String, String>());
		OrderInfoBean orderInfoO = new OrderInfoBean(orderId,null,develor.getId(), "您有新的订单，请查收", order.getOrderNumber());
		infos.add(orderInfoO);
		// 推给客服
		if (null != order.getServiceId()) {
			GroupUser serviceUser = groupUserServiceImpl.findTypeTokeById(order.getServiceId());
			if (null != serviceUser) {
				PushMessageUtil.pushMessages(serviceUser, "总部技术已申请总部研发支持",new HashMap<String, String>());
				OrderInfoBean orderInfo = new OrderInfoBean(orderId, null, serviceUser.getId(),"总部技术已申请总部研发支持",order.getOrderNumber());
				infos.add(orderInfo);
			}
		}
		try {
			orderInfoServiceImpl.batchAddOrderInfo(infos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "申请成功");
	}

	/**
	 * 总部研发 点击 订单 修改订单状态为查看
	 * 
	 * @param orderId
	 * @return
	 */
	@RequestMapping("/inventCheckOrder")
	@ResponseBody
	public ViewData InventCheckOrder(Integer orderId) {
		if (null == orderId) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		Object obj = LoginHelper.getCurrentUser();
		if (null == obj) {
			return buildFailureJson(StatusConstant.NOTLOGIN, "对象不存在");
		}
		if (obj instanceof UserBean) {
			return buildFailureJson(StatusConstant.NON_ALLOW, "没有权限");
		}
		GroupUser user = (GroupUser) obj;
		if (StatusConstant.FROZEN.equals(user.getStatus())) {
			return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户已被冻结");
		}
		if (!RoleConstant.INVENT.equals(user.getRoleType())) {
			return buildFailureJson(StatusConstant.NON_ALLOW, "没有权限");
		}
		orderServiceImpl.updateDevelopIsCheck(orderId,
				OrderStatusConStant.CHECKED);
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "修改成功");
	}

	/**
	 * 技术提交订单处理结果 更新订单状态 至 待验证
	 * 
	 * @return
	 */
	@RequestMapping("/statusToVerifi")
	@ResponseBody
	public ViewData updateOrderStatusTo(Integer orderId) {

		Object obj = LoginHelper.getCurrentUser();
		if (null == obj) {
			return buildFailureJson(StatusConstant.NOTLOGIN, "对象不存在");
		}
		if (obj instanceof UserBean) {
			return buildFailureJson(StatusConstant.NON_ALLOW, "没有权限");
		}
		GroupUser user = (GroupUser) obj;
		if (StatusConstant.FROZEN.equals(user.getStatus())) {
			return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户已被冻结");
		}
		if (!(RoleConstant.FILIALETECH.equals(user.getRoleType()) || RoleConstant.HEADCOMTECH
				.equals(user.getRoleType()))) {
			return buildFailureJson(StatusConstant.NON_ALLOW, "没有权限");
		}
		OrderBean order = orderServiceImpl.findById(orderId);
		if (null == order
				|| OrderStatusConStant.NON_VALIED.equals(order.getIsValid())) {
			return buildFailureJson(StatusConstant.NON_ALLOW, "订单无效");
		}
		if (!OrderStatusConStant.HANDLING.equals(order.getStatus())) {
			return buildFailureJson(StatusConstant.NON_ALLOW, "订单状态无效");
		}
		OrderBean temp = new OrderBean();
		temp.setId(orderId);
		temp.setStatus(OrderStatusConStant.VERIFICATIONPENDING);
		Date now = new Date();
		temp.setUpdateTime(now);
		if(null != order.getTechStartTime()){
			temp.setTechEndTime(now);
		}
		if(null != order.getHeadTechStartTime()){
			temp.setHeadTechEndTime(now);
		}
		orderServiceImpl.updateOrderStatus(temp);
		JSONArray jsonArray = null;
		JSONArray orderJsonArray = null;
		try {
			OrderTrackBean trackone = new OrderTrackBean(OrderStatusConStant.SUSPENDING,OrderStatusConStant.ORDER_VERIFY, new Date());
			orderJsonArray = JSONArray.fromObject(order.getOrderData());
			orderJsonArray.add(trackone.createOrderTrack());
			orderServiceImpl.updateOrderData(orderId, orderJsonArray.toString());

			jsonArray = JSONArray.fromObject(order.getGroupOrderData());
			OrderTrackBean track = new OrderTrackBean(order.getStatus(),OrderStatusConStant.QUEST_VERIFI, new Date());
			jsonArray.add(track.createOrderTrack());
			orderServiceImpl.updateGroupOrderData(orderId, jsonArray.toString());
		} catch (Exception e) {
			log.debug(e.getMessage(), e);
		}

		List<OrderInfoBean> infos = new ArrayList<OrderInfoBean>();

		// 推给客服
		if (null != order.getServiceId()) {
			GroupUser serviceUser = groupUserServiceImpl.findTypeTokeById(order
					.getServiceId());
			if (null != serviceUser) {
				PushMessageUtil.pushMessages(serviceUser, "订单状态已更新至 待验证",
						new HashMap<String, String>());
				OrderInfoBean orderInfoBean = new OrderInfoBean(orderId, null,serviceUser.getId(), "订单状态已更新至 待验证",order.getOrderNumber());
				infos.add(orderInfoBean);
			}
		}
		if (RoleConstant.FILIALETECH.equals(user.getRoleType())) {
			// 推给总公司技术
			if (null != order.getHeadTechId()) {
				GroupUser headUser = groupUserServiceImpl.findTypeTokeById(order.getHeadTechId());
				if (null != headUser) {
					PushMessageUtil.pushMessages(headUser, "订单状态已更新至 待验证",
							new HashMap<String, String>());
					OrderInfoBean orderInfoBean = new OrderInfoBean(orderId,
							null, headUser.getId(), "订单状态已更新至 待验证",
							order.getOrderNumber());
					infos.add(orderInfoBean);
				}
			}
		} else {
			// 推给分公司技术
			if (null != order.getTscId()) {
				GroupUser tscUser = groupUserServiceImpl.findTypeTokeById(order
						.getTscId());
				if (null != tscUser) {
					PushMessageUtil.pushMessages(tscUser, "订单状态已更新至 待验证",
							new HashMap<String, String>());
					OrderInfoBean orderInfoBean = new OrderInfoBean(orderId,
							null, tscUser.getId(), "订单状态已更新至 待验证",
							order.getOrderNumber());
					infos.add(orderInfoBean);
				}
			}
		}
		if (null != order.getDecelopId()) {
			GroupUser developUser = groupUserServiceImpl.findTypeTokeById(order
					.getDecelopId());
			if (null != developUser) {
				PushMessageUtil.pushMessages(developUser, "订单状态已更新至 待验证",
						new HashMap<String, String>());
				OrderInfoBean orderInfoBean = new OrderInfoBean(orderId, null,
						developUser.getId(), "订单状态已更新至 待验证",
						order.getOrderNumber());
				infos.add(orderInfoBean);
			}
		}
		if (null != order.getUserId()) {
			UserBean usert = userServiceImpl.findById(order.getUserId());
			if (null != usert) {
				PushMessageUtil.pushMessages(usert, "订单状态已更新至 待验证",
						new HashMap<String, String>());
				OrderInfoBean orderInfoBean = new OrderInfoBean(orderId,
						usert.getId(), null, "订单状态已更新至 待验证",
						order.getOrderNumber());
				infos.add(orderInfoBean);
			}
		}
		try {
			orderInfoServiceImpl.batchAddOrderInfo(infos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "提交成功");
	}

	/**
	 * 用户更新订单状态至 待评价 接受 技术处理的结果
	 * 
	 * @return
	 */
	@RequestMapping("/statusToAppraise")
	@ResponseBody
	public ViewData updateStatusToOver(Integer orderId, Integer isaccept) {
		// isaccept 是否接受 0 表示 接受 1 不接受
		if (null == orderId) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		Object obj = LoginHelper.getCurrentUser();
		if (null == obj) {
			return buildFailureJson(StatusConstant.NOTLOGIN, "对象不存在");
		}
		if (obj instanceof GroupUser) {
			return buildFailureJson(StatusConstant.NON_ALLOW, "没有权限");
		}
		UserBean tempUser = (UserBean) obj;
		if (StatusConstant.FROZEN.equals(tempUser.getStatus())) {
			return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户已被冻结");
		}
		OrderBean order = orderServiceImpl.findById(orderId);
		if (null == order || !order.getStatus().equals(	OrderStatusConStant.VERIFICATIONPENDING)
				|| OrderStatusConStant.NON_VALIED.equals(order.getIsValid())) {
			return buildFailureJson(StatusConstant.Fail_CODE, "操作失败");
		}
		if (isaccept == OrderStatusConStant.NON_ACCEPT) {
			OrderBean temp = new OrderBean();
			temp.setId(orderId);
			temp.setStatus(OrderStatusConStant.HANDLING);
			try {
				orderServiceImpl.updateOrderStatus(temp);
				JSONArray jsonArrays = null;
				jsonArrays = JSONArray.fromObject(order.getGroupOrderData());
				OrderTrackBean track = new OrderTrackBean(order.getStatus(),
						OrderStatusConStant.NO_PASS_ORDER, new Date());
				jsonArrays.add(track.createOrderTrack());
				orderServiceImpl.updateGroupOrderData(orderId,
						jsonArrays.toString());

				JSONArray orderJsonArray = JSONArray.fromObject(order
						.getOrderData());
				orderJsonArray.remove(orderJsonArray.size() - 1);
				orderServiceImpl.updateOrderData(orderId,
						orderJsonArray.toString());
				// jsonArray = JSONArray.fromObject(order.getGroupOrderData());
				// jsonArray.remove(jsonArray.size() - 1);
				// orderServiceImpl.updateGroupOrderData(orderId,jsonArray.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}

			List<OrderInfoBean> infos = new ArrayList<OrderInfoBean>();

			// 推给客服
			if (null != order.getServiceId()) {
				GroupUser serviceUser = groupUserServiceImpl
						.findTypeTokeById(order.getServiceId());
				if (null != serviceUser) {
					PushMessageUtil.pushMessages(serviceUser, "用户拒绝验收",
							new HashMap<String, String>());
					OrderInfoBean orderInfoBean = new OrderInfoBean(orderId,
							null, serviceUser.getId(), "用户拒绝验收",
							order.getOrderNumber());
					infos.add(orderInfoBean);
				}
			}
			// 推给总公司技术
			if (null != order.getHeadTechId()) {
				GroupUser headUser = groupUserServiceImpl
						.findTypeTokeById(order.getHeadTechId());
				if (null != headUser) {
					PushMessageUtil.pushMessages(headUser, "用户拒绝验收",
							new HashMap<String, String>());
					OrderInfoBean orderInfoBean = new OrderInfoBean(orderId,
							null, headUser.getId(), "用户拒绝验收",
							order.getOrderNumber());
					infos.add(orderInfoBean);
				}
				// 推给分公司技术
				if (null != order.getTscId()) {
					GroupUser tscUser = groupUserServiceImpl
							.findTypeTokeById(order.getTscId());
					if (null != tscUser) {
						PushMessageUtil.pushMessages(tscUser, "用户拒绝验收",
								new HashMap<String, String>());
						OrderInfoBean orderInfoBean = new OrderInfoBean(
								orderId, null, tscUser.getId(), "用户拒绝验收",
								order.getOrderNumber());
						infos.add(orderInfoBean);
					}
				}
				if (null != order.getDecelopId()) {
					GroupUser developUser = groupUserServiceImpl
							.findTypeTokeById(order.getDecelopId());
					if (null != developUser) {
						PushMessageUtil.pushMessages(developUser, "用户拒绝验收",
								new HashMap<String, String>());
						OrderInfoBean orderInfoBean = new OrderInfoBean(
								orderId, null, developUser.getId(), "用户拒绝验收",
								order.getOrderNumber());
						infos.add(orderInfoBean);
					}
				}

				try {
					orderInfoServiceImpl.batchAddOrderInfo(infos);
				} catch (Exception e) {
					e.printStackTrace();
				}
				;
			}
			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "提交成功");
		}
		OrderBean temp = new OrderBean();
		temp.setId(orderId);
		temp.setStatus(OrderStatusConStant.OVERALLPENGDING);
		JSONArray jsonArray = null;
		try {
			orderServiceImpl.updateOrderStatus(temp);
			jsonArray = JSONArray.fromObject(order.getOrderData());
			OrderTrackBean track = new OrderTrackBean(temp.getStatus(),
					OrderStatusConStant.ORDER_OVER, new Date());
			jsonArray.add(track.createOrderTrack());
			orderServiceImpl.updateOrderData(orderId, jsonArray.toString());
		} catch (Exception e) {
			log.debug(e.getMessage(), e);
		}
		JSONArray jsonArrays = null;
		try {
			jsonArrays = JSONArray.fromObject(order.getGroupOrderData());
			JSONObject jsobj = JSONObject.fromObject(jsonArrays.get((jsonArrays
					.size() - 1)));
			if (OrderStatusConStant.NO_PASS_ORDER.equals(jsobj
					.getString("content"))) {
				jsonArrays.remove((jsonArrays.size() - 1));
			}
			OrderTrackBean track = new OrderTrackBean(order.getStatus(),
					OrderStatusConStant.QUEST_OVER, new Date());
			jsonArrays.add(track.createOrderTrack());
			orderServiceImpl.updateGroupOrderData(orderId,
					jsonArrays.toString());
		} catch (Exception e) {
			log.debug(e.getMessage(), e);
		}
		temp.setIsHeadDevelopComment(OrderStatusConStant.NON_COMMENT);
		temp.setIsHeadTechComment(OrderStatusConStant.NON_COMMENT);
		temp.setIsTSCComment(OrderStatusConStant.NON_COMMENT);
		temp.setIsServiceComment(OrderStatusConStant.NON_COMMENT);
		temp.setIsUsercomment(OrderStatusConStant.NON_COMMENT);
		orderServiceImpl.updateIsComment(temp);

		List<OrderInfoBean> infos = new ArrayList<OrderInfoBean>();

		// 推给客服
		if (null != order.getServiceId()) {
			GroupUser serviceUser = groupUserServiceImpl.findTypeTokeById(order
					.getServiceId());
			if (null != serviceUser) {
				PushMessageUtil.pushMessages(serviceUser, "用户接受验收",
						new HashMap<String, String>());
				OrderInfoBean orderInfoBean = new OrderInfoBean(orderId, null,
						serviceUser.getId(), "用户接受验收", order.getOrderNumber());
				infos.add(orderInfoBean);
			}
		}
		// 推给总公司技术
		if (null != order.getHeadTechId()) {
			GroupUser headUser = groupUserServiceImpl.findTypeTokeById(order
					.getHeadTechId());
			if (null != headUser) {
				PushMessageUtil.pushMessages(headUser, "用户接受验收",
						new HashMap<String, String>());
				OrderInfoBean orderInfoBean = new OrderInfoBean(orderId, null,
						headUser.getId(), "用户接受验收", order.getOrderNumber());
				infos.add(orderInfoBean);
			}
			// 推给分公司技术
			if (null != order.getTscId()) {
				GroupUser tscUser = groupUserServiceImpl.findTypeTokeById(order
						.getTscId());
				if (null != tscUser) {
					PushMessageUtil.pushMessages(tscUser, "用户接受验收",
							new HashMap<String, String>());
					OrderInfoBean orderInfoBean = new OrderInfoBean(orderId,
							null, tscUser.getId(), "用户接受验收",
							order.getOrderNumber());
					infos.add(orderInfoBean);
				}
			}
			if (null != order.getDecelopId()) {
				GroupUser developUser = groupUserServiceImpl
						.findTypeTokeById(order.getDecelopId());
				if (null != developUser) {
					PushMessageUtil.pushMessages(developUser, "用户接受验收",
							new HashMap<String, String>());
					OrderInfoBean orderInfoBean = new OrderInfoBean(orderId,
							null, developUser.getId(), "用户接受验收",
							order.getOrderNumber());
					infos.add(orderInfoBean);
				}
			}
			// 推给CC
			List<CCGroupUserBean> ccs = cCgroupUserServiceImpl.findByOrderId(orderId);
			if (null != ccs) {
				List<Integer> ids = new ArrayList<Integer>();
				for (CCGroupUserBean cc : ccs) {
					ids.add(cc.getGroupuserId());
				}
				if (ids.size() != 0) {
					List<GroupUser> groupUsers = groupUserServiceImpl.batchQueryGroupUser(ids);
					if (groupUsers != null) {
						for (GroupUser groupUserT : groupUsers) {
							if (null != groupUserT) {
								PushMessageUtil.pushMessages(groupUserT, "用户接受验收",new HashMap<String, String>());
								OrderInfoBean orderInfo = new OrderInfoBean(orderId, null, groupUserT.getId(),"用户接受验收", order.getOrderNumber());
								infos.add(orderInfo);
							}
						}
					}
				}
			}
			try {
				orderInfoServiceImpl.batchAddOrderInfo(infos);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "提交成功");
	}

	/**
	 * 用户 拒绝问题被解决 返回给技术，修改状态为 处理中
	 * 
	 * @return
	 */
	// @RequestMapping("/rejectaccept")
	// @ResponseBody
	// public ViewData backStatus(Integer orderId) {
	// if (null == orderId) {
	// return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
	// }
	// Object obj = LoginHelper.getCurrentUser();
	// if (null == obj) {
	// return buildFailureJson(StatusConstant.NOTLOGIN, "对象不存在");
	// }
	// if (obj instanceof GroupUser) {
	// return buildFailureJson(StatusConstant.NON_ALLOW, "没有权限");
	// }
	// OrderBean order = orderServiceImpl.findById(orderId);
	// if (null == order
	// || !order.getStatus().equals(
	// OrderStatusConStant.VERIFICATIONPENDING)
	// || OrderStatusConStant.NON_VALIED.equals(order.getIsValid())) {
	// return buildFailureJson(StatusConstant.Fail_CODE, "操作失败");
	// }
	// // UserBean user = (UserBean)obj;
	// OrderBean temp = new OrderBean();
	// temp.setId(orderId);
	// temp.setStatus(OrderStatusConStant.HANDLING);
	// orderServiceImpl.updateOrderStatus(order);
	// JSONArray jsonArray = null;
	// try {
	// jsonArray = JSONArray.fromObject(order.getGroupOrderData());
	// jsonArray.remove(jsonArray.size() - 1);
	// orderServiceImpl.updateGroupOrderData(orderId, jsonArray.toString());
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "提交成功");
	// }

	/**
	 * 获取 订单跟踪状态
	 * 
	 * @return
	 */
	@RequestMapping("/getOrderTrack")
	@ResponseBody
	public ViewData getOrderTrack(Integer orderId) {

		if (null == orderId) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		OrderBean order = orderServiceImpl.findById(orderId);
		if (null == order
				|| OrderStatusConStant.NON_VALIED.equals(order.getIsValid())) {
			return buildFailureJson(StatusConstant.Fail_CODE, "无效的订单");
		}
		Object obj = LoginHelper.getCurrentUser();
		if (null == obj) {
			return buildFailureJson(StatusConstant.NOTLOGIN, "对象不存在");
		}
		Map<String, Object> data = new HashMap<String, Object>();
		if (obj instanceof UserBean) {
			JSONArray obj1 = JSONArray.fromObject(order.getOrderData());
			data.put("lists", obj1);
		} else if (obj instanceof GroupUser) {
			data.put("lists", JSONArray.fromObject(order.getGroupOrderData()));
		}
		data.put("order", order);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}

	/**
	 * 累计派单
	 * 
	 * @return
	 */
	@RequestMapping("/countOrders")
	@ResponseBody
	public ViewData countOrder() {

		Object obj = LoginHelper.getCurrentUser();
		if (null == obj) {
			return buildFailureJson(StatusConstant.NOTLOGIN, "未登录");
		}
		Integer count = 0, userId = null, tscId = null, serviceId = null, headTechId = null, developId = null;
		Map<String, Object> data = new HashMap<String, Object>();
		if (obj instanceof UserBean) {
			UserBean user = (UserBean) obj;
			if (StatusConstant.FROZEN.equals(user.getStatus())) {
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户已被冻结");
			}
			userId = user.getId();
		} else if (obj instanceof GroupUser) {
			GroupUser user = (GroupUser) obj;
			if (StatusConstant.FROZEN.equals(user.getStatus())) {
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户已被冻结");
			}
			if (RoleConstant.CUSTOMER.equals(user.getRoleType())) {
				serviceId = user.getId();
			} else if (RoleConstant.FILIALETECH.equals(user.getRoleType())) {
				tscId = user.getId();
			} else if (RoleConstant.HEADCOMTECH.equals(user.getRoleType())) {
				headTechId = user.getId();
			} else if (RoleConstant.INVENT.equals(user.getRoleType())) {
				developId = user.getId();
			} else {
				Integer ccUser = cCgroupUserServiceImpl.countOrders(user
						.getId());
				data.put("count", ccUser);
				return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功",
						data);
			}
		}
		count = orderServiceImpl.countOrders(userId, serviceId, tscId,
				headTechId, developId);
		data.put("count", count);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}

	/**
	 * 按月统计订单
	 * 
	 * @return
	 */
	@RequestMapping("/countOrdersMonth")
	@ResponseBody
	public ViewData countOrderByMonth(String yearM) {
		if (null == yearM) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		Object obj = LoginHelper.getCurrentUser();
		if (null == obj) {
			return buildSuccessCodeJson(StatusConstant.NOTLOGIN, "未登录");
		}
		OrderBean order = new OrderBean();
		if (obj instanceof UserBean) {
			UserBean user = (UserBean) obj;
			if (StatusConstant.FROZEN.equals(user.getStatus())) {
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户已被冻结");
			}
			order.setUserId(user.getId());
		} else if (obj instanceof GroupUser) {
			GroupUser user = (GroupUser) obj;
			if (StatusConstant.FROZEN.equals(user.getStatus())) {
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户已被冻结");
			}
			if (RoleConstant.CUSTOMER.equals(user.getRoleType())) {
				// 如果是客服
				order.setServiceId(user.getId());
			} else if (RoleConstant.FILIALETECH.equals(user.getRoleType())) {
				// 如果是分公司技术
				order.setTscId(user.getId());
			} else if (RoleConstant.HEADCOMTECH.equals(user.getRoleType())) {
				// 总部技术
				order.setHeadTechId(user.getId());
			} else if (RoleConstant.INVENT.equals(user.getRoleType())) {
				// 研发
				order.setDecelopId(user.getId());
			} else if (RoleConstant.FILIALE_HEADER.equals(user.getRoleType())
					|| RoleConstant.FILIALE_SALE_HEADER.equals(user
							.getRoleType())
					|| RoleConstant.FILIALE_TECH_HEADER.equals(user
							.getRoleType())
					|| RoleConstant.LEADER.equals(user.getRoleType())
					|| RoleConstant.OPERATION.equals(user.getRoleType())
					|| RoleConstant.SALE.equals(user.getRoleType())
					|| RoleConstant.PRE_SALE.equals(user.getRoleType())) {
				// 被抄送的人
				Map<String, Integer> data = orderServiceImpl.countOrdersByCC(
						yearM, user.getId());
				return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功",
						data);
			}
		}
		Map<String, Integer> data = orderServiceImpl.countOrdersByMonth(yearM,
				order);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}

	/**
	 * 根据问题类型统计 订单数量
	 * 
	 * @return
	 */
	@RequestMapping("/countOrderCategory")
	@ResponseBody
	public ViewData countOrderByCategory(String yearM) {
		if (null == yearM) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		Object obj = LoginHelper.getCurrentUser();
		if (null == obj) {
			return buildSuccessCodeJson(StatusConstant.NOTLOGIN, "未登录");
		}

		OrderBean order = new OrderBean();
		if (obj instanceof UserBean) {
			UserBean user = (UserBean) obj;
			if (StatusConstant.FROZEN.equals(user.getStatus())) {
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户已被冻结");
			}
			order.setUserId(user.getId());
		} else if (obj instanceof GroupUser) {
			GroupUser user = (GroupUser) obj;
			if (StatusConstant.FROZEN.equals(user.getStatus())) {
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户已被冻结");
			}
			if (RoleConstant.CUSTOMER.equals(user.getRoleType())) {
				// 如果是客服
				order.setServiceId(user.getId());
			} else if (RoleConstant.FILIALETECH.equals(user.getRoleType())) {
				// 如果是分公司技术
				order.setTscId(user.getId());
			} else if (RoleConstant.HEADCOMTECH.equals(user.getRoleType())) {
				// 总部技术
				order.setHeadTechId(user.getId());
			} else if (RoleConstant.INVENT.equals(user.getRoleType())) {
				// 研发
				order.setDecelopId(user.getId());
			} else if (RoleConstant.FILIALE_HEADER.equals(user.getRoleType())
					|| RoleConstant.FILIALE_SALE_HEADER.equals(user
							.getRoleType())
					|| RoleConstant.FILIALE_TECH_HEADER.equals(user
							.getRoleType())
					|| RoleConstant.LEADER.equals(user.getRoleType())
					|| RoleConstant.OPERATION.equals(user.getRoleType())
					|| RoleConstant.SALE.equals(user.getRoleType())
					|| RoleConstant.PRE_SALE.equals(user.getRoleType())) {
				// 被抄送的人

				Map<String, Integer> tempData = orderServiceImpl
						.countOrderByCategoryAndCC(yearM, user.getId());
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("lists", tempData);
				return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功",
						data);
			}
		}

		Map<String, Integer> tempData = orderServiceImpl.countOrdersByCategory(
				yearM, order);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("lists", tempData);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}

	/**
	 * 根据不同角色统计不同的状态下的订单数量
	 * 
	 * @return
	 */
	@RequestMapping("/countOrderByRole")
	@ResponseBody
	public ViewData countOrderByRoleType() {

		Object obj = LoginHelper.getCurrentUser();
		if (null == obj) {
			return buildFailureJson(StatusConstant.NOTLOGIN, "未登录");
		}
		OrderStatusBean orderStatus = new OrderStatusBean();
		if (obj instanceof UserBean) {
			UserBean user = (UserBean) obj;
			if (StatusConstant.FROZEN.equals(user.getStatus())) {
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户已被冻结");
			}
			orderStatus = orderServiceImpl.countUserOrder(user.getId());
		}
		if (obj instanceof GroupUser) {
			GroupUser groupUser = (GroupUser) obj;
			if (StatusConstant.FROZEN.equals(groupUser.getStatus())) {
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户已被冻结");
			}
			if (RoleConstant.CUSTOMER.equals(groupUser.getRoleType())) {
				orderStatus = orderServiceImpl.countServiceOrder(groupUser.getId());
			} else if (RoleConstant.FILIALETECH.equals(groupUser.getRoleType())) {
				orderStatus = orderServiceImpl.countTSCOrder(groupUser.getId());
			} else if (RoleConstant.HEADCOMTECH.equals(groupUser.getRoleType())) {
				orderStatus = orderServiceImpl.countHeadTechOrder(groupUser.getId());
			} else if (RoleConstant.INVENT.equals(groupUser.getRoleType())) {
				orderStatus = orderServiceImpl.countDevelopOrder(groupUser.getId());
			} else {
				orderStatus = orderServiceImpl.countCCGroupUserOrder(groupUser.getId());
			}
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功",orderStatus);
	}

}
