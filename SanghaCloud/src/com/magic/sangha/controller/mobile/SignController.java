package com.magic.sangha.controller.mobile;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.GroupUser;
import com.magic.sangha.bean.SignBean;
import com.magic.sangha.bean.UserBean;
import com.magic.sangha.controller.BaseController;
import com.magic.sangha.service.IBonusService;
import com.magic.sangha.service.ISignService;
import com.magic.sangha.util.DateUtil;
import com.magic.sangha.util.LoginHelper;
import com.magic.sangha.util.StatusConstant;
import com.magic.sangha.util.ViewData;
/**
 *  ǩ����¼ �ӿ�
 * @author QimouXie
 *
 */
@RequestMapping("/sign")
@Controller
public class SignController extends BaseController {
	
	@Resource
	private ISignService signServiceImpl;
	@Resource
	private IBonusService bonusServiceImpl;
	
	
	@RequestMapping("/addSign")
	@ResponseBody
	public ViewData sign(){
		
		Object obj = LoginHelper.getCurrentUser();
		if(null == obj){
			return buildFailureJson(StatusConstant.NOTLOGIN, "δ��¼");
		}
		SignBean sign = new SignBean();
		sign.setBonus(signServiceImpl.findBaseBonus(StatusConstant.BONUS_TYPE_SIGN));
		if(obj instanceof UserBean){
			UserBean user = (UserBean)obj;
			if(StatusConstant.FROZEN.equals(user.getStatus())){
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "�˻��ѱ�����");
			}
			sign.setUserId(user.getId());
		}else if(obj instanceof GroupUser){
			GroupUser user = (GroupUser)obj;
			if(StatusConstant.FROZEN.equals(user.getStatus())){
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "�˻��ѱ�����");
			}
			sign.setGroupUserId(user.getId());
		}else{
			return buildFailureJson(StatusConstant.Fail_CODE, "ǩ��ʧ��");
		}
		SignBean temp = signServiceImpl.findSignByUserId(sign.getUserId(), DateUtil.DateToyyyyMMdd(new Date()),sign.getGroupUserId());
		if(null != temp){
			return buildFailureJson(StatusConstant.Fail_CODE, "������ǩ��");
		}
		signServiceImpl.addSign(sign);
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("bonus", sign.getBonus());
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "ǩ���ɹ�" ,data);
	}
	/**
	 *  ��ȡǩ����Ϣ
	 * @return
	 */
	@RequestMapping("/getSignInfo")
	@ResponseBody
	public ViewData getSignInfo(){
		Object obj = LoginHelper.getCurrentUser();
		if(null == obj){
			return buildFailureJson(StatusConstant.NOTLOGIN, "δ��¼");
		}
		Map<String,Object> data = new HashMap<String,Object>();
		Integer isSign = null;
		Integer bonus = null;
		Integer signs = null;
		SignBean temp = null;
		Integer tempBonus = null;
		Integer days = null;
		Integer userId = null;
		Integer groupUserId = null;
		if(obj instanceof UserBean){
			UserBean user = (UserBean)obj;
			if(StatusConstant.FROZEN.equals(user.getStatus())){
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "�˻��ѱ�����");
			}
			userId = user.getId();
			temp = signServiceImpl.findSignByUserId(user.getId(), DateUtil.DateToyyyyMMdd(new Date()),null);
			tempBonus= signServiceImpl.findSumSignByUserId(user.getId(),null);
			days = signServiceImpl.countSignByUserId(user.getId(),null);
		}else if(obj instanceof GroupUser){
			GroupUser user = (GroupUser)obj;
			if(StatusConstant.FROZEN.equals(user.getStatus())){
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "�˻��ѱ�����");
			}
			groupUserId = user.getId();
			temp = signServiceImpl.findSignByUserId(null, DateUtil.DateToyyyyMMdd(new Date()),user.getId());
			tempBonus= signServiceImpl.findSumSignByUserId(null,user.getId());
			days = signServiceImpl.countSignByUserId(null,user.getId());
		}else{
			return buildFailureJson(StatusConstant.Fail_CODE, "��ȡʧ��");
		}
		Integer countBonus = bonusServiceImpl.countUserBonus(userId, groupUserId);
		isSign = temp == null ? StatusConstant.NON_SIGNED : StatusConstant.SIGNED;
		bonus = tempBonus == null ? 0 : tempBonus;
		signs = days == null ? 0 : days;
		
		data.put("isSign", isSign);
		data.put("bonus", bonus);
		data.put("signs", signs);
		data.put("countBonus", countBonus);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�", data);
	}
	
	@RequestMapping("/signPageInfo")
	@ResponseBody
	public ViewData getSignPageInfo(Integer pageNO,Integer pageSize){
		if(null == pageNO || null == pageSize){
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
			if(StatusConstant.FROZEN.equals(user.getStatus())){
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "�˻��ѱ�����");
			}
			userId = user.getId();
		}else if(obj instanceof GroupUser){
			GroupUser user = (GroupUser)obj;
			if(StatusConstant.FROZEN.equals(user.getStatus())){
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "�˻��ѱ�����");
			}
			groupUserId = user.getId();
		}else{
			return buildFailureJson(StatusConstant.Fail_CODE, "��ȡʧ��");
		}
		if(null == userId && groupUserId == null){
			return buildFailureJson(StatusConstant.Fail_CODE, "��ȡʧ��");
		}
		CutPageBean<SignBean> page = signServiceImpl.findSignByUser(pageNO, pageSize, userId,groupUserId);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("lists", page.getDataList());
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�", data);
	}

}
