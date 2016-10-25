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
 * �������������
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
	 * ��������
	 * 
	 * @return
	 */
	@RequestMapping("/addorder")
	@ResponseBody
	public ViewData addOrder(String images, String videos, String voices,Integer categoryId, String content, Integer userId) {

		Object obj = LoginHelper.getCurrentUser();
		if (null == obj) {
			return buildFailureJson(StatusConstant.NOTLOGIN, "���ȵ�¼");
		}
		if (obj instanceof GroupUser) {
			GroupUser groupUser = (GroupUser) obj;
			if (!(RoleConstant.CUSTOMER.equals(groupUser.getRole().getId()))) {
				return buildFailureJson(StatusConstant.NON_ALLOW, "û��Ȩ��");
			}
			if (StatusConstant.FROZEN.equals(groupUser.getStatus())) {
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "�˻��ѱ�����");
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
				// ��Ϣ����
				UserBean user = userServiceImpl.findById(userId);
				PushMessageUtil.pushMessages(user, "С���ѽ����������ݸ������ͷ������������ĵȺ�~",new HashMap<String, String>());
				// �洢������Ϣ
				List<OrderInfoBean> infos = new ArrayList<OrderInfoBean>();
				OrderInfoBean orderInfoBean = new OrderInfoBean(orderId,user.getId(), null, "С���ѽ����������ݸ������ͷ������������ĵȺ�~",order.getOrderNumber());
				infos.add(orderInfoBean);
				orderInfoServiceImpl.batchAddOrderInfo(infos);
				return buildSuccessCodeViewData(StatusConstant.SUCCESS_CODE,"�ύ�ɹ�");
			} catch (Exception e) {
				e.printStackTrace();
				return buildSuccessCodeViewData(StatusConstant.Fail_CODE,"�ύʧ��");
			}

		} else if (obj instanceof UserBean) {
			UserBean temp = (UserBean) obj;
			if (StatusConstant.FROZEN.equals(temp.getStatus())) {
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "�˻��ѱ�����");
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
						"�ύ�ɹ�");
			} catch (Exception e) {
				e.printStackTrace();
				return buildSuccessCodeViewData(StatusConstant.Fail_CODE,
						"�ύʧ��");
			}

		}
		return buildFailureJson(StatusConstant.Fail_CODE, "�ύʧ��");
	}

	/**
	 * ��ȡ���������ͷ�����Ϣ
	 * 
	 * @param type
	 * @return
	 */
	@RequestMapping("/getcategory")
	@ResponseBody
	public ViewData getOrderCategory(Integer type) {

		if (null == type) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "�ֶβ���Ϊ��");
		}

		List<OrderCategoryBean> orderca = orderCategoryImpl.findByType(type);

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("lists", orderca);

		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�", data);
	}

	/**
	 * ��ȡ�����б�
	 * 
	 * @return
	 */
	@RequestMapping("/getorderlist")
	@ResponseBody
	public ViewData getOrder(Integer pageNO, Integer pageSize, Integer status,Integer isCheck, Integer isComment, Integer isAccept) {

		Object obj = LoginHelper.getCurrentUser();
		if (null == obj) {
			return buildFailureJson(StatusConstant.NOTLOGIN, "���󲻴���");
		}
		Map<String, Object> data = new HashMap<String, Object>();
		OrderBean order = new OrderBean();
		order.setStatus(status);
		if (obj instanceof UserBean) {
			UserBean user = (UserBean) obj;
			if (StatusConstant.FROZEN.equals(user.getStatus())) {
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "�˻��ѱ�����");
			}
			order.setUserId(user.getId());
		} else if (obj instanceof GroupUser) {
			GroupUser groupUser = (GroupUser) obj;
			if (StatusConstant.FROZEN.equals(groupUser.getStatus())) {
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "�˻��ѱ�����");
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
				return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�",data);
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
				return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�",
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
				return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�",
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
				return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�",
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
				return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�",
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
				return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�",
						data);
			}
		}
		CutPageBean<OrderBean> page = orderServiceImpl.findByStatusAndUser(
				order, pageNO, pageSize);

		data.put("lists", page.getDataList());
		data.put("count", page.getCount());
		data.put("totalPage", page.getTotalPage());
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�", data);
	}

	// @RequestMapping("/orderDetail")
	// @ResponseBody
	// public ViewData getOrderDetail(Integer orderId){
	//
	// OrderBean order = orderServiceImpl.findById(orderId);
	// if(null == order){
	// return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "�ö���������");
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
	// return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�", data);
	// }

	/**
	 * ��ȡ����׷������
	 * 
	 * @return
	 */
	@RequestMapping("/getOrderdecribe")
	@ResponseBody
	public ViewData getOrderDecribe(Integer orderId) {

		Object obj = LoginHelper.getCurrentUser();
		if (null == obj) {
			return buildFailureJson(StatusConstant.NOTLOGIN, "������Ϊ��");
		}

		if (null == orderId) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "�ֶβ���Ϊ��");
		}
		OrderBean order = orderServiceImpl.findById(orderId);

		if (null == order) {
			return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "�ö���������");
		}
		if (OrderStatusConStant.NON_VALIED.equals(order.getIsValid())) {
			return buildFailureJson(StatusConstant.ORDER_INVALID, "��Ч�Ķ���");
		}
		List<OrderDecribeUser> decribesUser = orderDecribeUserServiceImpl
				.findByOrderId(orderId);
		Map<String, Object> data = new HashMap<String, Object>();

		if (obj instanceof UserBean) {

			UserBean tuser = (UserBean) obj;
			if (StatusConstant.FROZEN.equals(tuser.getStatus())) {
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "�˻��ѱ�����");
			}

			List<OrderDecribeHeadTechBean> decribes = orderDecribeHeadTechServiceImpl
					.findByOrderId(orderId, order);
			// ����˶�����ֱ�ӷ������ �ܼ�����
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
				return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�",
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
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "�˻��ѱ�����");
			}
			if (RoleConstant.FILIALETECH.equals(groupUser.getRoleType())) {
				List<OrderDecribeBean> decribes = orderDecribeServiceImpl
						.findByOrderId(orderId, order);
				List<TSCHeadTechDecribeBean> tsces = tSCHeadTechDecribeServiceImpl
						.findByOrderId(orderId, order);
				List<OrderDecribeHeadTechBean> decribess = null;
				List<Object> decribeList = new ArrayList<Object>();
				// ����˶�����ֱ�ӷ������ �ܼ�����
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
				// �缶����
				decribes = orderDecribeHeadTechServiceImpl.findByOrderId(orderId, order);
				decribes.remove(0);
				// ����˶�����ֱ�ӷ������ �ܼ�����
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
				// 1��ʾ ������
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
				// �缶��������Ϣ
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
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�", data);
	}

	/**
	 * ������� ���� ����
	 * 
	 * @param orderId
	 * @param content
	 * @param images
	 * @param videos
	 * @param voices
	 * @param flag
	 *            ��� 0 ���Ϸ��� 1 ���·���
	 * @return
	 */
	@RequestMapping("/addOrderDecribe")
	@ResponseBody
	public ViewData addOrderDecribe(Integer orderId, String content,String images, String videos, String voices, Integer flag,
			Integer roleId, Integer to, Integer isFeedback) {
		// isFeedback 0 ��ʾ ���� 1 ��ʾ ����
		if (null == content || null == flag || null == orderId) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "�ֶβ���Ϊ��");
		}
		Object obj = LoginHelper.getCurrentUser();
		if (null == obj) {
			return buildFailureJson(StatusConstant.NOTLOGIN, "���ȵ�¼");
		}
		OrderBean order = orderServiceImpl.findById(orderId);
		if (OrderStatusConStant.NON_VALIED.equals(order.getIsValid())) {
			return buildFailureJson(StatusConstant.ORDER_INVALID, "��Ч�Ķ���");
		}
		if (order.getStatus().equals(OrderStatusConStant.OVERALLPENGDING)) {
			return buildFailureJson(StatusConstant.Fail_CODE, "���������,�޷�����");
		}
		// // ����ö�����ֱ�ӷ�����ܹ�˾������
		// if (order.getIsHeadTech().equals(OrderStatusConStant.HEADTECH)) {
		// OrderDecribeHeadTechBean temp = new OrderDecribeHeadTechBean();
		// temp.setContent(content);
		// temp.setImages(images);
		// temp.setOrderId(orderId);
		// temp.setVideos(videos);
		// temp.setVoices(voices);
		//
		// orderDecribeHeadTechServiceImpl.addDecribe(temp);
		// return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "��ӳɹ�");
		// }
		OrderDecribeBean decribe = new OrderDecribeBean();
		if (obj instanceof UserBean) {
			if (flag.equals(StatusConstant.DOWN)) {
				return buildFailureJson(StatusConstant.Fail_CODE, "�������·���");
			}
			UserBean user = (UserBean) obj;
			if (StatusConstant.FROZEN.equals(user.getStatus())) {
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "�˻��ѱ�����");
			}
			if (flag.equals(StatusConstant.UPORDOWNWARD)) {
				// ����ǿ缶����
				OrderDecribeHeadTechBean temp = new OrderDecribeHeadTechBean();
				temp.setContent(content);
				temp.setImages(images);
				temp.setOrderId(orderId);
				temp.setVideos(videos);
				temp.setVoices(voices);
				temp.setUser(user.getId());
				orderDecribeHeadTechServiceImpl.addDecribe(temp);
				return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "��ӳɹ�");
			}
			if (flag.equals(StatusConstant.ORDER_DECRIBE_USER)) {
				// ������û����� ȫ�ֿɼ�����Ϣ
				OrderDecribeUser temp = new OrderDecribeUser();
				temp.setContent(content);
				temp.setImages(images);
				temp.setOrderId(orderId);
				temp.setVideos(videos);
				temp.setVoices(voices);
				temp.setUserId(user.getId());
				orderDecribeUserServiceImpl.addOrderDecribe(temp);
				return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "��ӳɹ�");
			}

			decribe.setContent(content);
			decribe.setImages(images);
			decribe.setOrderId(orderId);
			decribe.setVideos(videos);
			decribe.setVoices(voices);

			decribe.setUserId(user.getId());
			orderDecribeServiceImpl.addOrderDecribe(decribe);

			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "��ӳɹ�");
		} else if (obj instanceof GroupUser) {
			GroupUser user = (GroupUser) obj;
			if (StatusConstant.FROZEN.equals(user.getStatus())) {
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "�˻��ѱ�����");
			}
			if (RoleConstant.FILIALETECH.equals(user.getRoleType())) {
				// ����Ƿֹ�˾����

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
				return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "��ӳɹ�");

			} else if (RoleConstant.HEADCOMTECH.equals(user.getRoleType())) {
				// ������ܲ�����
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
					// ����ǿ缶����
					OrderDecribeHeadTechBean temp = new OrderDecribeHeadTechBean();
					temp.setContent(content);
					temp.setImages(images);
					temp.setOrderId(orderId);
					temp.setVideos(videos);
					temp.setVoices(voices);
					temp.setHeadTeah(user.getId());
					orderDecribeHeadTechServiceImpl.addDecribe(temp);
					return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE,"��ӳɹ�");
				}
				return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "��ӳɹ�");
			} else if (RoleConstant.INVENT.equals(user.getRoleType())) {
				// ����� �з�
				if (flag.equals(StatusConstant.UP)) {
					return buildFailureJson(StatusConstant.Fail_CODE, "�������Ϸ���");
				} else if (flag.equals(StatusConstant.DOWN)) {
					// isFeedback 0 ��ʾ ���� 1 ��ʾ ����
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
					return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE,"��ӳɹ�");
				} else {
					return buildFailureJson(StatusConstant.NON_ALLOW, "û��Ȩ��");
				}
			}
		}
		return buildFailureJson(StatusConstant.NON_ALLOW, "û��Ȩ��");
	}

	/**
	 * �޸Ĳ鿴����
	 * 
	 * @return
	 */
	@RequestMapping("/updateCheck")
	@ResponseBody
	public ViewData updateServiceCheck(Integer orderId) {

		Object obj = LoginHelper.getCurrentUser();
		if (null == obj) {
			return buildFailureJson(StatusConstant.NOTLOGIN, "���󲻴���");
		}
		if (obj instanceof UserBean) {
			return buildFailureJson(StatusConstant.NON_ALLOW, "û��Ȩ��");
		}
		OrderBean order = orderServiceImpl.findById(orderId);
		if (null == order
				|| order.getIsValid().equals(OrderStatusConStant.NON_VALIED)) {
			return buildFailureJson(StatusConstant.Fail_CODE, "������Ч");
		}
		OrderBean tempOrder = new OrderBean();
		tempOrder.setId(orderId);
		tempOrder.setStatus(order.getStatus());
		if (obj instanceof GroupUser) {
			GroupUser user = (GroupUser) obj;
			if (StatusConstant.FROZEN.equals(user.getStatus())) {
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "�˻��ѱ�����");
			}
			if (user.getRoleType().equals(RoleConstant.CUSTOMER)) {
				// orderServiceImpl.updateOrderServiceCheck(orderId,OrderStatusConStant.CHECKED);
				if (OrderStatusConStant.CHECKED.equals(order.getServiceCheck())) {
					return buildFailureJson(StatusConstant.Fail_CODE, "�Ѿ��鿴");
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
					return buildFailureJson(StatusConstant.Fail_CODE, "�Ѿ��鿴");
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
					return buildFailureJson(StatusConstant.Fail_CODE, "�Ѿ��鿴");
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
					return buildFailureJson(StatusConstant.Fail_CODE, "�Ѿ��鿴");
				}
				tempOrder.setDevelopCheck(OrderStatusConStant.CHECKED);
				tempOrder
						.setDevelopIsAccept(OrderStatusConStant.NON_ORDER_ACCEPT);
			} else {
				return buildFailureJson(StatusConstant.NON_ALLOW, "û��Ȩ��");
			}
			orderServiceImpl.updateOrderStatus(tempOrder);
			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "�޸ĳɹ�");
		}
		return buildFailureJson(StatusConstant.Fail_CODE, "�޷��޸�");
	}

	/**
	 * ��������
	 * 
	 * @param orderId
	 * @return
	 */
	@RequestMapping("/acceptOrder")
	@ResponseBody
	public ViewData updateIsAcceptOrder(Integer orderId) {

		Object obj = LoginHelper.getCurrentUser();
		if (null == obj) {
			return buildFailureJson(StatusConstant.NOTLOGIN, "���󲻴���");
		}
		if (obj instanceof UserBean) {
			return buildFailureJson(StatusConstant.NON_ALLOW, "û��Ȩ��");
		}
		OrderBean order = orderServiceImpl.findById(orderId);
		if (null == order
				|| order.getIsValid().equals(OrderStatusConStant.NON_VALIED)) {
			return buildFailureJson(StatusConstant.Fail_CODE, "������Ч");
		}

		OrderBean tempOrder = new OrderBean();
		tempOrder.setId(orderId);
		tempOrder.setStatus(order.getStatus());
		if (obj instanceof GroupUser) {
			GroupUser user = (GroupUser) obj;
			if (StatusConstant.FROZEN.equals(user.getStatus())) {
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "�˻��ѱ�����");
			}
			if (RoleConstant.CUSTOMER.equals(user.getRoleType())) {
				return buildFailureJson(StatusConstant.NON_ALLOW, "û��Ȩ��");
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
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "�޸ĳɹ�");
	}

	/**
	 * �޸Ķ���״̬ ֮ �ͷ� ���䶩���� TSC
	 * 
	 * @return
	 */
	@RequestMapping("/allotorder")
	@ResponseBody
	public ViewData getOrderByStatus(Integer orderId, Integer tscId,Integer roleType, String ccs) {
		if (null == orderId || null == tscId) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "�ֶβ���Ϊ��");
		}
		GroupUser user = groupUserServiceImpl.findByIdToName(tscId);
		if (null == user) {
			return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "���󲻴���");
		}
		OrderBean ordert = orderServiceImpl.findById(orderId);
		if (null == ordert) {
			return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "����������");
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

			// ������Ϣ
			GroupUser tscUser = groupUserServiceImpl.findTypeTokeById(tscId);
			if (null != tscUser) {
				PushMessageUtil.pushMessages(tscUser, "�յ����Կͷ�����Ķ���",new HashMap<String, String>());
			}
			UserBean usert = userServiceImpl.findById(ordert.getUserId());
			if (null != usert) {
				PushMessageUtil.pushMessages(usert, "С���ѽ���������ɢ����ȥ��������������ע~",new HashMap<String, String>());
			}
			// �洢������Ϣ
			List<OrderInfoBean> infos = new ArrayList<OrderInfoBean>();
			OrderInfoBean orderInfoO = new OrderInfoBean(orderId,usert.getId(), null, "С���ѽ���������ɢ����ȥ��������������ע~",ordert.getOrderNumber());
			OrderInfoBean orderInfoTwo = new OrderInfoBean(orderId, null,tscUser.getId(), "�յ����Կͷ�����Ķ���", ordert.getOrderNumber());
			infos.add(orderInfoO);
			infos.add(orderInfoTwo);
			orderInfoServiceImpl.batchAddOrderInfo(infos);
		} catch (Exception e) {
			log.debug(e.getMessage(), e);
			return buildSuccessCodeJson(StatusConstant.Fail_CODE, "����ʧ��");
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "����ɹ�");
	}

	/**
	 * ͨ�������������� ��ȡ TSC �� �Ǳ༼��Ա��
	 * 
	 * @param categoryId
	 * @return
	 */
	@RequestMapping("/getTSC")
	@ResponseBody
	public ViewData getTSC(Integer categoryId, Integer orderId, Integer userId) {
		if (null == categoryId || null == orderId || userId == null) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "�ֶβ���Ϊ��");
		}
		Map<String, Object> data = new HashMap<String, Object>();
		// ��ѯ�� �ֹ�˾����
		List<GroupUser> tscs = groupUserServiceImpl.findByUserId(userId);

		List<GroupUser> feibian = groupUserServiceImpl.findAllTechFeibian(userId, categoryId);
		OrderBean order = orderServiceImpl.findById(orderId);
		if (null == order) {
			return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "�ö���������");
		}
		data.put("order", order);
		UserBean user = userServiceImpl.findById(order.getUserId());
		data.put("user", user);
		data.put("tsc", tscs);
		data.put("feibian", feibian);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�", data);
	}

	/**
	 * ������ȡ �ֹ�˾�µ�TSC
	 * 
	 * @return
	 */
	@RequestMapping("/getTSCOnly")
	@ResponseBody
	public ViewData getTsc(Integer categoryId, Integer orderId, Integer userId) {

		if (null == categoryId || null == orderId || userId == null) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "�ֶβ���Ϊ��");
		}
		Map<String, Object> data = new HashMap<String, Object>();
		// ��ѯ���ֹ�˾����
		List<GroupUser> tscs = groupUserServiceImpl.findByUserId(userId);
		// List<GroupUser> feibian =
		// groupUserServiceImpl.findAllTechFeibian(userId, categoryId);

		OrderBean order = orderServiceImpl.findById(orderId);
		if (null == order) {
			return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "�ö���������");
		}
		data.put("order", order);
		UserBean user = userServiceImpl.findById(order.getUserId());
		data.put("user", user);
		data.put("tsc", tscs);
		// data.put("feibian", feibian);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�", data);

	}

	// /**
	// * �����������������Ա�󣬼�����Ա�鿴������ �޸��Ƿ�鿴״̬
	// *
	// * @return
	// */
	//
	// public ViewData updateTSCChecked(Integer orderId) {
	//
	// Object obj = LoginHelper.getCurrentUser();
	// if (null == obj) {
	// return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "���󲻴���");
	// }
	// if (obj instanceof UserBean) {
	// return buildFailureJson(StatusConstant.NON_ALLOW, "û��Ȩ��");
	// }
	// OrderBean order = orderServiceImpl.findById(orderId);
	// if (null == order||
	// order.getIsValid().equals(OrderStatusConStant.NON_VALIED)) {
	// return buildFailureJson(StatusConstant.Fail_CODE, "������Ч");
	// }
	// if (obj instanceof GroupUser) {
	// GroupUser user = (GroupUser) obj;
	// if (!user.getRoleType().equals(RoleConstant.FILIALETECH)) {
	// return buildFailureJson(StatusConstant.NON_ALLOW, "û��Ȩ��");
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
	// return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "�޸ĳɹ�");
	// }
	//
	// return buildFailureJson(StatusConstant.Fail_CODE, "�޸�ʧ��");
	// }

	/**
	 * �ֹ�˾���� ���ܴ��� ����� �ܲ�����
	 * 
	 * @return
	 */
	@RequestMapping("/applicationHeadTech")
	@ResponseBody
	public ViewData applicationHeaderTech(Integer orderId, Integer headTechId,String ccs) {
		if (null == orderId || headTechId == null) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "�ֶβ���Ϊ��");
		}
		Object obj = LoginHelper.getCurrentUser();
		if (null == obj) {
			return buildFailureJson(StatusConstant.NOTLOGIN, "���󲻴���");
		}

		GroupUser headTech = groupUserServiceImpl.findByIdToName(headTechId);
		if (null == headTech) {
			return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "���󲻴���");
		}
		OrderBean ordert = orderServiceImpl.findById(orderId);
		if (null == ordert) {
			return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "����������");
		}
		JSONArray ccArray = null;
		if (null != ccs) {
			ccArray = JSONArray.fromObject(ccs);
		}
		if (obj instanceof UserBean) {
			return buildFailureJson(StatusConstant.NON_ALLOW, "û��Ȩ��");
		}
		if (obj instanceof GroupUser) {
			GroupUser user = (GroupUser) obj;
			if (StatusConstant.FROZEN.equals(user.getStatus())) {
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "�˻��ѱ�����");
			}
			if (!user.getRoleType().equals(RoleConstant.FILIALETECH)) {
				return buildFailureJson(StatusConstant.NON_ALLOW, "û��Ȩ��");
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
			// ������Ϣ
			GroupUser headUser = groupUserServiceImpl.findTypeTokeById(headTechId);
			if (null != headUser) {
				PushMessageUtil.pushMessages(headUser, "�յ����Էֹ�˾�����ļ���ָ��",new HashMap<String, String>());
				OrderInfoBean orderInfoBean = new OrderInfoBean(orderId, null,headUser.getId(), "�յ����Էֹ�˾�����ļ���ָ��",ordert.getOrderNumber());
				infos.add(orderInfoBean);
			}
			GroupUser serviceUser = groupUserServiceImpl.findTypeTokeById(ordert.getServiceId());
			if (null != serviceUser) {
				PushMessageUtil.pushMessages(serviceUser, "�ֹ�˾���������ܹ�˾������ָ��",new HashMap<String, String>());
				OrderInfoBean orderInfoBean = new OrderInfoBean(orderId, null,serviceUser.getId(), "�ֹ�˾���������ܹ�˾������ָ��",ordert.getOrderNumber());
				infos.add(orderInfoBean);
			}
			
//			if (null != groupIds) {
//				List<GroupUser> groupUsers = groupUserServiceImpl.batchQueryGroupUser(groupIds);
//				for (GroupUser groupUserT : groupUsers) {
//					if (null != groupUserT) {
//						PushMessageUtil.pushMessages(groupUserT,"�ֹ�˾���������ܹ�˾������ָ��",new HashMap<String, String>());
//						OrderInfoBean orderInfoBean = new OrderInfoBean(orderId, null, groupUserT.getId(),"�ֹ�˾���������ܹ�˾������ָ��", ordert.getOrderNumber());
//						infos.add(orderInfoBean);
//					}
//				}
//			}
			// �洢������Ϣ
			try {
				orderInfoServiceImpl.batchAddOrderInfo(infos);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "����ɹ�");
		}
		return buildFailureJson(StatusConstant.Fail_CODE, "����ʧ��");
	}

	// /**
	// * �ܲ����� ��� ���� �޸Ķ���״̬Ϊ�鿴
	// *
	// * @return
	// */
	// @RequestMapping("/headCheckOrder")
	// @ResponseBody
	// public ViewData headTechCheckOrder(Integer orderId) {
	//
	// if (null == orderId) {
	// return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "�ֶβ���Ϊ��");
	// }
	// Object obj = LoginHelper.getCurrentUser();
	// if (null == obj) {
	// return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "���󲻴���");
	// }
	// if (obj instanceof UserBean) {
	// return buildFailureJson(StatusConstant.NON_ALLOW, "û��Ȩ��");
	// }
	// GroupUser user = (GroupUser) obj;
	// if (!RoleConstant.HEADCOMTECH.equals(user.getRoleType())) {
	// return buildFailureJson(StatusConstant.NON_ALLOW, "û��Ȩ��");
	// }
	// orderServiceImpl.updateHeadTechIsCheck(orderId,OrderStatusConStant.CHECKED);
	//
	// return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "�޸ĳɹ�");
	// }

	/**
	 * ȡ������
	 * 
	 * @param orderId
	 * @return
	 */
	@RequestMapping("/cancleOrder")
	@ResponseBody
	public ViewData cancleOrder(Integer orderId, Integer userId) {
		if (orderId == null || userId == null) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "�ֶβ���Ϊ��");
		}
		Object obj = LoginHelper.getCurrentUser();
		if (null == obj) {
			return buildFailureJson(StatusConstant.NOTLOGIN, "���󲻴���");
		}
		if (obj instanceof GroupUser) {
			return buildFailureJson(StatusConstant.NON_ALLOW, "û��Ȩ��");
		}
		UserBean user = (UserBean) obj;
		if (StatusConstant.FROZEN.equals(user.getStatus())) {
			return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "�˻��ѱ�����");
		}
		if (!userId.equals(user.getId())) {
			return buildFailureJson(StatusConstant.NON_ALLOW, "������Ķ�������Ȩ��");
		}
		OrderBean order = orderServiceImpl.findById(orderId);
		if (null == order) {
			return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "����������");
		}
		if (OrderStatusConStant.HANDLING.equals(order.getStatus())) {
			return buildFailureJson(StatusConstant.ORDER_STATUS_ABNORMITY,
					"����״̬�쳣");
		}
		orderServiceImpl.updateIsValid(orderId, OrderStatusConStant.NON_VALIED);
		// �洢������Ϣ
		List<OrderInfoBean> infos = new ArrayList<OrderInfoBean>();

		// ��������
		if (order.getServiceId() != null) {
			GroupUser serviceUser = groupUserServiceImpl.findTypeTokeById(order
					.getServiceId());
			if (null != serviceUser) {
				PushMessageUtil.pushMessages(serviceUser, "�û���ȡ������",
						new HashMap<String, String>());
				OrderInfoBean orderInfo = new OrderInfoBean(orderId, null,
						serviceUser.getId(), "�û���ȡ������", order.getOrderNumber());
				infos.add(orderInfo);
			}
		}
		if (order.getTscId() != null) {
			GroupUser tscUser = groupUserServiceImpl.findTypeTokeById(order
					.getTscId());
			if (null != tscUser) {
				PushMessageUtil.pushMessages(tscUser, "�û���ȡ������",
						new HashMap<String, String>());
				OrderInfoBean orderInfo = new OrderInfoBean(orderId, null,
						tscUser.getId(), "�û���ȡ������", order.getOrderNumber());
				infos.add(orderInfo);
			}
		}
		if (order.getHeadTechId() != null) {
			GroupUser headUser = groupUserServiceImpl.findTypeTokeById(order
					.getHeadTechId());
			if (null != headUser) {
				PushMessageUtil.pushMessages(headUser, "�û���ȡ������",new HashMap<String, String>());
				OrderInfoBean orderInfo = new OrderInfoBean(orderId, null,headUser.getId(), "�û���ȡ������", order.getOrderNumber());
				infos.add(orderInfo);
			}
		}
		if (order.getDecelopId() != null) {
			GroupUser developUser = groupUserServiceImpl.findTypeTokeById(order
					.getDecelopId());
			if (null != developUser) {
				PushMessageUtil.pushMessages(developUser, "�û���ȡ������",
						new HashMap<String, String>());
				OrderInfoBean orderInfo = new OrderInfoBean(orderId, null,
						developUser.getId(), "�û���ȡ������", order.getOrderNumber());
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
							PushMessageUtil.pushMessages(groupUserT, "�û���ȡ������",
									new HashMap<String, String>());
							OrderInfoBean orderInfo = new OrderInfoBean(
									orderId, null, groupUserT.getId(),
									"�û���ȡ������", order.getOrderNumber());
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
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "���³ɹ�");
	}

	/**
	 * �ܲ����� ���� �з�����
	 * 
	 * @return
	 */
	@RequestMapping("/applicationInvent")
	@ResponseBody
	public ViewData applicationInvent(Integer orderId, Integer developId) {

		if (null == orderId) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "�ֶβ���Ϊ��");
		}
		Object obj = LoginHelper.getCurrentUser();
		if (null == obj) {
			return buildFailureJson(StatusConstant.NOTLOGIN, "���󲻴���");
		}
		GroupUser develor = groupUserServiceImpl.findByIdToName(developId);
		if (null == develor) {
			return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "���󲻴���");
		}
		if (obj instanceof UserBean) {
			return buildFailureJson(StatusConstant.NON_ALLOW, "û��Ȩ��");
		}
		GroupUser user = (GroupUser) obj;
		if (StatusConstant.FROZEN.equals(user.getStatus())) {
			return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "�˻��ѱ�����");
		}
		if (!RoleConstant.HEADCOMTECH.equals(user.getRoleType())) {
			return buildFailureJson(StatusConstant.NON_ALLOW, "û��Ȩ��");
		}
		OrderBean order = orderServiceImpl.findById(orderId);
		if (order == null) {
			return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "����������");
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
		// �洢������Ϣ
		List<OrderInfoBean> infos = new ArrayList<OrderInfoBean>();

		// ��Ϣ����
		// �Ƹ��з�
		PushMessageUtil.pushMessages(develor, "�����µĶ����������",new HashMap<String, String>());
		OrderInfoBean orderInfoO = new OrderInfoBean(orderId,null,develor.getId(), "�����µĶ����������", order.getOrderNumber());
		infos.add(orderInfoO);
		// �Ƹ��ͷ�
		if (null != order.getServiceId()) {
			GroupUser serviceUser = groupUserServiceImpl.findTypeTokeById(order.getServiceId());
			if (null != serviceUser) {
				PushMessageUtil.pushMessages(serviceUser, "�ܲ������������ܲ��з�֧��",new HashMap<String, String>());
				OrderInfoBean orderInfo = new OrderInfoBean(orderId, null, serviceUser.getId(),"�ܲ������������ܲ��з�֧��",order.getOrderNumber());
				infos.add(orderInfo);
			}
		}
		try {
			orderInfoServiceImpl.batchAddOrderInfo(infos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "����ɹ�");
	}

	/**
	 * �ܲ��з� ��� ���� �޸Ķ���״̬Ϊ�鿴
	 * 
	 * @param orderId
	 * @return
	 */
	@RequestMapping("/inventCheckOrder")
	@ResponseBody
	public ViewData InventCheckOrder(Integer orderId) {
		if (null == orderId) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "�ֶβ���Ϊ��");
		}
		Object obj = LoginHelper.getCurrentUser();
		if (null == obj) {
			return buildFailureJson(StatusConstant.NOTLOGIN, "���󲻴���");
		}
		if (obj instanceof UserBean) {
			return buildFailureJson(StatusConstant.NON_ALLOW, "û��Ȩ��");
		}
		GroupUser user = (GroupUser) obj;
		if (StatusConstant.FROZEN.equals(user.getStatus())) {
			return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "�˻��ѱ�����");
		}
		if (!RoleConstant.INVENT.equals(user.getRoleType())) {
			return buildFailureJson(StatusConstant.NON_ALLOW, "û��Ȩ��");
		}
		orderServiceImpl.updateDevelopIsCheck(orderId,
				OrderStatusConStant.CHECKED);
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "�޸ĳɹ�");
	}

	/**
	 * �����ύ���������� ���¶���״̬ �� ����֤
	 * 
	 * @return
	 */
	@RequestMapping("/statusToVerifi")
	@ResponseBody
	public ViewData updateOrderStatusTo(Integer orderId) {

		Object obj = LoginHelper.getCurrentUser();
		if (null == obj) {
			return buildFailureJson(StatusConstant.NOTLOGIN, "���󲻴���");
		}
		if (obj instanceof UserBean) {
			return buildFailureJson(StatusConstant.NON_ALLOW, "û��Ȩ��");
		}
		GroupUser user = (GroupUser) obj;
		if (StatusConstant.FROZEN.equals(user.getStatus())) {
			return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "�˻��ѱ�����");
		}
		if (!(RoleConstant.FILIALETECH.equals(user.getRoleType()) || RoleConstant.HEADCOMTECH
				.equals(user.getRoleType()))) {
			return buildFailureJson(StatusConstant.NON_ALLOW, "û��Ȩ��");
		}
		OrderBean order = orderServiceImpl.findById(orderId);
		if (null == order
				|| OrderStatusConStant.NON_VALIED.equals(order.getIsValid())) {
			return buildFailureJson(StatusConstant.NON_ALLOW, "������Ч");
		}
		if (!OrderStatusConStant.HANDLING.equals(order.getStatus())) {
			return buildFailureJson(StatusConstant.NON_ALLOW, "����״̬��Ч");
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

		// �Ƹ��ͷ�
		if (null != order.getServiceId()) {
			GroupUser serviceUser = groupUserServiceImpl.findTypeTokeById(order
					.getServiceId());
			if (null != serviceUser) {
				PushMessageUtil.pushMessages(serviceUser, "����״̬�Ѹ����� ����֤",
						new HashMap<String, String>());
				OrderInfoBean orderInfoBean = new OrderInfoBean(orderId, null,serviceUser.getId(), "����״̬�Ѹ����� ����֤",order.getOrderNumber());
				infos.add(orderInfoBean);
			}
		}
		if (RoleConstant.FILIALETECH.equals(user.getRoleType())) {
			// �Ƹ��ܹ�˾����
			if (null != order.getHeadTechId()) {
				GroupUser headUser = groupUserServiceImpl.findTypeTokeById(order.getHeadTechId());
				if (null != headUser) {
					PushMessageUtil.pushMessages(headUser, "����״̬�Ѹ����� ����֤",
							new HashMap<String, String>());
					OrderInfoBean orderInfoBean = new OrderInfoBean(orderId,
							null, headUser.getId(), "����״̬�Ѹ����� ����֤",
							order.getOrderNumber());
					infos.add(orderInfoBean);
				}
			}
		} else {
			// �Ƹ��ֹ�˾����
			if (null != order.getTscId()) {
				GroupUser tscUser = groupUserServiceImpl.findTypeTokeById(order
						.getTscId());
				if (null != tscUser) {
					PushMessageUtil.pushMessages(tscUser, "����״̬�Ѹ����� ����֤",
							new HashMap<String, String>());
					OrderInfoBean orderInfoBean = new OrderInfoBean(orderId,
							null, tscUser.getId(), "����״̬�Ѹ����� ����֤",
							order.getOrderNumber());
					infos.add(orderInfoBean);
				}
			}
		}
		if (null != order.getDecelopId()) {
			GroupUser developUser = groupUserServiceImpl.findTypeTokeById(order
					.getDecelopId());
			if (null != developUser) {
				PushMessageUtil.pushMessages(developUser, "����״̬�Ѹ����� ����֤",
						new HashMap<String, String>());
				OrderInfoBean orderInfoBean = new OrderInfoBean(orderId, null,
						developUser.getId(), "����״̬�Ѹ����� ����֤",
						order.getOrderNumber());
				infos.add(orderInfoBean);
			}
		}
		if (null != order.getUserId()) {
			UserBean usert = userServiceImpl.findById(order.getUserId());
			if (null != usert) {
				PushMessageUtil.pushMessages(usert, "����״̬�Ѹ����� ����֤",
						new HashMap<String, String>());
				OrderInfoBean orderInfoBean = new OrderInfoBean(orderId,
						usert.getId(), null, "����״̬�Ѹ����� ����֤",
						order.getOrderNumber());
				infos.add(orderInfoBean);
			}
		}
		try {
			orderInfoServiceImpl.batchAddOrderInfo(infos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "�ύ�ɹ�");
	}

	/**
	 * �û����¶���״̬�� ������ ���� ��������Ľ��
	 * 
	 * @return
	 */
	@RequestMapping("/statusToAppraise")
	@ResponseBody
	public ViewData updateStatusToOver(Integer orderId, Integer isaccept) {
		// isaccept �Ƿ���� 0 ��ʾ ���� 1 ������
		if (null == orderId) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "�ֶβ���Ϊ��");
		}
		Object obj = LoginHelper.getCurrentUser();
		if (null == obj) {
			return buildFailureJson(StatusConstant.NOTLOGIN, "���󲻴���");
		}
		if (obj instanceof GroupUser) {
			return buildFailureJson(StatusConstant.NON_ALLOW, "û��Ȩ��");
		}
		UserBean tempUser = (UserBean) obj;
		if (StatusConstant.FROZEN.equals(tempUser.getStatus())) {
			return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "�˻��ѱ�����");
		}
		OrderBean order = orderServiceImpl.findById(orderId);
		if (null == order || !order.getStatus().equals(	OrderStatusConStant.VERIFICATIONPENDING)
				|| OrderStatusConStant.NON_VALIED.equals(order.getIsValid())) {
			return buildFailureJson(StatusConstant.Fail_CODE, "����ʧ��");
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

			// �Ƹ��ͷ�
			if (null != order.getServiceId()) {
				GroupUser serviceUser = groupUserServiceImpl
						.findTypeTokeById(order.getServiceId());
				if (null != serviceUser) {
					PushMessageUtil.pushMessages(serviceUser, "�û��ܾ�����",
							new HashMap<String, String>());
					OrderInfoBean orderInfoBean = new OrderInfoBean(orderId,
							null, serviceUser.getId(), "�û��ܾ�����",
							order.getOrderNumber());
					infos.add(orderInfoBean);
				}
			}
			// �Ƹ��ܹ�˾����
			if (null != order.getHeadTechId()) {
				GroupUser headUser = groupUserServiceImpl
						.findTypeTokeById(order.getHeadTechId());
				if (null != headUser) {
					PushMessageUtil.pushMessages(headUser, "�û��ܾ�����",
							new HashMap<String, String>());
					OrderInfoBean orderInfoBean = new OrderInfoBean(orderId,
							null, headUser.getId(), "�û��ܾ�����",
							order.getOrderNumber());
					infos.add(orderInfoBean);
				}
				// �Ƹ��ֹ�˾����
				if (null != order.getTscId()) {
					GroupUser tscUser = groupUserServiceImpl
							.findTypeTokeById(order.getTscId());
					if (null != tscUser) {
						PushMessageUtil.pushMessages(tscUser, "�û��ܾ�����",
								new HashMap<String, String>());
						OrderInfoBean orderInfoBean = new OrderInfoBean(
								orderId, null, tscUser.getId(), "�û��ܾ�����",
								order.getOrderNumber());
						infos.add(orderInfoBean);
					}
				}
				if (null != order.getDecelopId()) {
					GroupUser developUser = groupUserServiceImpl
							.findTypeTokeById(order.getDecelopId());
					if (null != developUser) {
						PushMessageUtil.pushMessages(developUser, "�û��ܾ�����",
								new HashMap<String, String>());
						OrderInfoBean orderInfoBean = new OrderInfoBean(
								orderId, null, developUser.getId(), "�û��ܾ�����",
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
			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "�ύ�ɹ�");
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

		// �Ƹ��ͷ�
		if (null != order.getServiceId()) {
			GroupUser serviceUser = groupUserServiceImpl.findTypeTokeById(order
					.getServiceId());
			if (null != serviceUser) {
				PushMessageUtil.pushMessages(serviceUser, "�û���������",
						new HashMap<String, String>());
				OrderInfoBean orderInfoBean = new OrderInfoBean(orderId, null,
						serviceUser.getId(), "�û���������", order.getOrderNumber());
				infos.add(orderInfoBean);
			}
		}
		// �Ƹ��ܹ�˾����
		if (null != order.getHeadTechId()) {
			GroupUser headUser = groupUserServiceImpl.findTypeTokeById(order
					.getHeadTechId());
			if (null != headUser) {
				PushMessageUtil.pushMessages(headUser, "�û���������",
						new HashMap<String, String>());
				OrderInfoBean orderInfoBean = new OrderInfoBean(orderId, null,
						headUser.getId(), "�û���������", order.getOrderNumber());
				infos.add(orderInfoBean);
			}
			// �Ƹ��ֹ�˾����
			if (null != order.getTscId()) {
				GroupUser tscUser = groupUserServiceImpl.findTypeTokeById(order
						.getTscId());
				if (null != tscUser) {
					PushMessageUtil.pushMessages(tscUser, "�û���������",
							new HashMap<String, String>());
					OrderInfoBean orderInfoBean = new OrderInfoBean(orderId,
							null, tscUser.getId(), "�û���������",
							order.getOrderNumber());
					infos.add(orderInfoBean);
				}
			}
			if (null != order.getDecelopId()) {
				GroupUser developUser = groupUserServiceImpl
						.findTypeTokeById(order.getDecelopId());
				if (null != developUser) {
					PushMessageUtil.pushMessages(developUser, "�û���������",
							new HashMap<String, String>());
					OrderInfoBean orderInfoBean = new OrderInfoBean(orderId,
							null, developUser.getId(), "�û���������",
							order.getOrderNumber());
					infos.add(orderInfoBean);
				}
			}
			// �Ƹ�CC
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
								PushMessageUtil.pushMessages(groupUserT, "�û���������",new HashMap<String, String>());
								OrderInfoBean orderInfo = new OrderInfoBean(orderId, null, groupUserT.getId(),"�û���������", order.getOrderNumber());
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

		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "�ύ�ɹ�");
	}

	/**
	 * �û� �ܾ����ⱻ��� ���ظ��������޸�״̬Ϊ ������
	 * 
	 * @return
	 */
	// @RequestMapping("/rejectaccept")
	// @ResponseBody
	// public ViewData backStatus(Integer orderId) {
	// if (null == orderId) {
	// return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "�ֶβ���Ϊ��");
	// }
	// Object obj = LoginHelper.getCurrentUser();
	// if (null == obj) {
	// return buildFailureJson(StatusConstant.NOTLOGIN, "���󲻴���");
	// }
	// if (obj instanceof GroupUser) {
	// return buildFailureJson(StatusConstant.NON_ALLOW, "û��Ȩ��");
	// }
	// OrderBean order = orderServiceImpl.findById(orderId);
	// if (null == order
	// || !order.getStatus().equals(
	// OrderStatusConStant.VERIFICATIONPENDING)
	// || OrderStatusConStant.NON_VALIED.equals(order.getIsValid())) {
	// return buildFailureJson(StatusConstant.Fail_CODE, "����ʧ��");
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
	// return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "�ύ�ɹ�");
	// }

	/**
	 * ��ȡ ��������״̬
	 * 
	 * @return
	 */
	@RequestMapping("/getOrderTrack")
	@ResponseBody
	public ViewData getOrderTrack(Integer orderId) {

		if (null == orderId) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "�ֶβ���Ϊ��");
		}
		OrderBean order = orderServiceImpl.findById(orderId);
		if (null == order
				|| OrderStatusConStant.NON_VALIED.equals(order.getIsValid())) {
			return buildFailureJson(StatusConstant.Fail_CODE, "��Ч�Ķ���");
		}
		Object obj = LoginHelper.getCurrentUser();
		if (null == obj) {
			return buildFailureJson(StatusConstant.NOTLOGIN, "���󲻴���");
		}
		Map<String, Object> data = new HashMap<String, Object>();
		if (obj instanceof UserBean) {
			JSONArray obj1 = JSONArray.fromObject(order.getOrderData());
			data.put("lists", obj1);
		} else if (obj instanceof GroupUser) {
			data.put("lists", JSONArray.fromObject(order.getGroupOrderData()));
		}
		data.put("order", order);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�", data);
	}

	/**
	 * �ۼ��ɵ�
	 * 
	 * @return
	 */
	@RequestMapping("/countOrders")
	@ResponseBody
	public ViewData countOrder() {

		Object obj = LoginHelper.getCurrentUser();
		if (null == obj) {
			return buildFailureJson(StatusConstant.NOTLOGIN, "δ��¼");
		}
		Integer count = 0, userId = null, tscId = null, serviceId = null, headTechId = null, developId = null;
		Map<String, Object> data = new HashMap<String, Object>();
		if (obj instanceof UserBean) {
			UserBean user = (UserBean) obj;
			if (StatusConstant.FROZEN.equals(user.getStatus())) {
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "�˻��ѱ�����");
			}
			userId = user.getId();
		} else if (obj instanceof GroupUser) {
			GroupUser user = (GroupUser) obj;
			if (StatusConstant.FROZEN.equals(user.getStatus())) {
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "�˻��ѱ�����");
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
				return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�",
						data);
			}
		}
		count = orderServiceImpl.countOrders(userId, serviceId, tscId,
				headTechId, developId);
		data.put("count", count);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�", data);
	}

	/**
	 * ����ͳ�ƶ���
	 * 
	 * @return
	 */
	@RequestMapping("/countOrdersMonth")
	@ResponseBody
	public ViewData countOrderByMonth(String yearM) {
		if (null == yearM) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "�ֶβ���Ϊ��");
		}
		Object obj = LoginHelper.getCurrentUser();
		if (null == obj) {
			return buildSuccessCodeJson(StatusConstant.NOTLOGIN, "δ��¼");
		}
		OrderBean order = new OrderBean();
		if (obj instanceof UserBean) {
			UserBean user = (UserBean) obj;
			if (StatusConstant.FROZEN.equals(user.getStatus())) {
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "�˻��ѱ�����");
			}
			order.setUserId(user.getId());
		} else if (obj instanceof GroupUser) {
			GroupUser user = (GroupUser) obj;
			if (StatusConstant.FROZEN.equals(user.getStatus())) {
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "�˻��ѱ�����");
			}
			if (RoleConstant.CUSTOMER.equals(user.getRoleType())) {
				// ����ǿͷ�
				order.setServiceId(user.getId());
			} else if (RoleConstant.FILIALETECH.equals(user.getRoleType())) {
				// ����Ƿֹ�˾����
				order.setTscId(user.getId());
			} else if (RoleConstant.HEADCOMTECH.equals(user.getRoleType())) {
				// �ܲ�����
				order.setHeadTechId(user.getId());
			} else if (RoleConstant.INVENT.equals(user.getRoleType())) {
				// �з�
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
				// �����͵���
				Map<String, Integer> data = orderServiceImpl.countOrdersByCC(
						yearM, user.getId());
				return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�",
						data);
			}
		}
		Map<String, Integer> data = orderServiceImpl.countOrdersByMonth(yearM,
				order);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�", data);
	}

	/**
	 * ������������ͳ�� ��������
	 * 
	 * @return
	 */
	@RequestMapping("/countOrderCategory")
	@ResponseBody
	public ViewData countOrderByCategory(String yearM) {
		if (null == yearM) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "�ֶβ���Ϊ��");
		}
		Object obj = LoginHelper.getCurrentUser();
		if (null == obj) {
			return buildSuccessCodeJson(StatusConstant.NOTLOGIN, "δ��¼");
		}

		OrderBean order = new OrderBean();
		if (obj instanceof UserBean) {
			UserBean user = (UserBean) obj;
			if (StatusConstant.FROZEN.equals(user.getStatus())) {
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "�˻��ѱ�����");
			}
			order.setUserId(user.getId());
		} else if (obj instanceof GroupUser) {
			GroupUser user = (GroupUser) obj;
			if (StatusConstant.FROZEN.equals(user.getStatus())) {
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "�˻��ѱ�����");
			}
			if (RoleConstant.CUSTOMER.equals(user.getRoleType())) {
				// ����ǿͷ�
				order.setServiceId(user.getId());
			} else if (RoleConstant.FILIALETECH.equals(user.getRoleType())) {
				// ����Ƿֹ�˾����
				order.setTscId(user.getId());
			} else if (RoleConstant.HEADCOMTECH.equals(user.getRoleType())) {
				// �ܲ�����
				order.setHeadTechId(user.getId());
			} else if (RoleConstant.INVENT.equals(user.getRoleType())) {
				// �з�
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
				// �����͵���

				Map<String, Integer> tempData = orderServiceImpl
						.countOrderByCategoryAndCC(yearM, user.getId());
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("lists", tempData);
				return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�",
						data);
			}
		}

		Map<String, Integer> tempData = orderServiceImpl.countOrdersByCategory(
				yearM, order);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("lists", tempData);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�", data);
	}

	/**
	 * ���ݲ�ͬ��ɫͳ�Ʋ�ͬ��״̬�µĶ�������
	 * 
	 * @return
	 */
	@RequestMapping("/countOrderByRole")
	@ResponseBody
	public ViewData countOrderByRoleType() {

		Object obj = LoginHelper.getCurrentUser();
		if (null == obj) {
			return buildFailureJson(StatusConstant.NOTLOGIN, "δ��¼");
		}
		OrderStatusBean orderStatus = new OrderStatusBean();
		if (obj instanceof UserBean) {
			UserBean user = (UserBean) obj;
			if (StatusConstant.FROZEN.equals(user.getStatus())) {
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "�˻��ѱ�����");
			}
			orderStatus = orderServiceImpl.countUserOrder(user.getId());
		}
		if (obj instanceof GroupUser) {
			GroupUser groupUser = (GroupUser) obj;
			if (StatusConstant.FROZEN.equals(groupUser.getStatus())) {
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "�˻��ѱ�����");
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
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�",orderStatus);
	}

}
