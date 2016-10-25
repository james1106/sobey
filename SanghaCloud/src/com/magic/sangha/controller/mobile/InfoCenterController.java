package com.magic.sangha.controller.mobile;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.GroupUser;
import com.magic.sangha.bean.OrderInfoBean;
import com.magic.sangha.bean.SystemInfoBean;
import com.magic.sangha.bean.UserBean;
import com.magic.sangha.controller.BaseController;
import com.magic.sangha.service.IOrderInfoService;
import com.magic.sangha.service.ISystemInfoService;
import com.magic.sangha.util.LoginHelper;
import com.magic.sangha.util.StatusConstant;
import com.magic.sangha.util.ViewData;

/**
 *  ��Ϣ���� �ӿ�
 * @author QimouXie
 *
 */
@Controller
@RequestMapping("/msgCenter")
public class InfoCenterController extends BaseController {

	@Resource
	private ISystemInfoService systemInfoServiceImpl;
	
	@Resource
	private IOrderInfoService orderInfoServiceImpl;
	
	/**
	 *  ��ȡ��Ϣ
	 * @return
	 */
	@RequestMapping("/getMsg")
	@ResponseBody
	public ViewData getMessage(Integer type,Integer pageNO,Integer pageSize){
		
		if(null == type){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "�ֶβ���Ϊ��");
		}
		Object obj = LoginHelper.getCurrentUser();
		if(null == obj){
			return buildFailureJson(StatusConstant.NOTLOGIN, "δ��¼");
		}
		Integer userId = null;
		Integer groupUserId = null;
		if(obj instanceof UserBean){
			UserBean user = (UserBean)obj;
			userId = user.getId();
			if(StatusConstant.FROZEN.equals(user.getStatus())){
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "�˻��ѱ�����");
			}
		}else if(obj instanceof GroupUser){
			GroupUser user = (GroupUser)obj;
			groupUserId = user.getId();
			if(StatusConstant.FROZEN.equals(user.getStatus())){
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "�˻��ѱ�����");
			}
		}
		Map<String, Object> data = new HashMap<String, Object>();
		if(StatusConstant.SYSTEM_INFO.equals(type)){
			CutPageBean<SystemInfoBean> page = systemInfoServiceImpl.quertPage(obj, pageNO, pageSize);
			data.put("lists", page.getDataList());
		}else if(StatusConstant.ORDER_INFO.equals(type)){
			CutPageBean<OrderInfoBean> page = orderInfoServiceImpl.quertPage(userId, groupUserId, pageNO, pageSize);
			data.put("lists", page.getDataList());
		}else{
			return buildFailureJson(StatusConstant.Fail_CODE, "��ȡʧ��");
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�", data);
	}
	
	@RequestMapping("/sendNotice")
	@ResponseBody
	public ViewData addNotice(Integer type,String title,String content,String brief,HttpServletRequest req){
		// type  0 ȫ�� 1 �û� 2Ա��
		if(null == type || title == null || brief == null || content == null){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "�ֶβ���Ϊ��");
		}
		GroupUser user = LoginHelper.getCurrentAdmin(req);
		Integer id = null;
		boolean isOk = false;
		SystemInfoBean info = new SystemInfoBean();
		info.setToGroup(type);
		info.setContent(content);
		info.setTitle(title);
		info.setBrief(brief);
		info.setPublisherId(user.getId());
		info.setPublisherName(user.getRealName());
		try {
			id = systemInfoServiceImpl.addSystemInfo(info);
			isOk= systemInfoServiceImpl.sendNotice(info,id);
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			return buildFailureJson(StatusConstant.Fail_CODE, "����ʧ��");
		}
		if(isOk){
			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "���ͳɹ�");
		}
		return buildFailureJson(StatusConstant.Fail_CODE, "����ʧ��");
	}
	
	/**
	 *  ��ȡ ϵͳ��Ϣ֪ͨ
	 * @return
	 */
	@RequestMapping("/adminGetInfo")
	@ResponseBody
	public ViewData getSystemInfoList(Integer pageNO,Integer pageSize){
		CutPageBean<SystemInfoBean> page = systemInfoServiceImpl.quertPage(null, pageNO, pageSize);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�", page);
	}
	
	@RequestMapping("/getMsgById")
	@ResponseBody
	public ViewData getMsgById(Integer systemId){
		if(null == systemId){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "�ֶβ���Ϊ��");
		}
		SystemInfoBean info = systemInfoServiceImpl.findById(systemId);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�", info);
	}
}
