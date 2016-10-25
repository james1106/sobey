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
 *  Ͷ��
 * @author QimouXie
 *
 */
@Controller
@RequestMapping("/complain")
public class ComplainController extends BaseController {
	
	@Resource
	private IComplainService complainServiceImpl;
	/**
	 *  ����Ͷ��
	 * @return
	 */
	@RequestMapping("/addcomplain")
	@ResponseBody
	public ViewData addComplain(Integer orderId,Integer type,String content){
		if(null == orderId || null == type || null == content){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "�ֶβ���Ϊ��");
		}
		Object obj = LoginHelper.getCurrentUser();
		if(null == obj){
			return buildFailureJson(StatusConstant.NOTLOGIN, "���󲻴���");
		}
		if(obj instanceof GroupUser){
			return buildFailureJson(StatusConstant.NON_ALLOW, "û��Ȩ��");
		}
		ComplainBean com = complainServiceImpl.findByOrderId(orderId);
		if(null != com){
			return buildFailureJson(StatusConstant.Fail_CODE, "�˶����Ѿ�Ͷ�߹�");
		}
		if(obj instanceof UserBean){
			UserBean user = (UserBean)obj;
			if(StatusConstant.FROZEN.equals(user.getStatus())){
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "�˻��ѱ�����");
			}
			ComplainBean temp = new ComplainBean();
			temp.setContent(content);
			temp.setOrderId(orderId);
			temp.setType(type);
			complainServiceImpl.addComplain(temp);
			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "Ͷ�߳ɹ�");
		}
		return buildFailureJson(StatusConstant.Fail_CODE, "Ͷ��ʧ��");
	}
	/**
	 *  ���˶����Ƿ�Ͷ�߹�
	 * @return
	 */
	@RequestMapping("/checkcomplain")
	@ResponseBody
	public ViewData checkIsCpmplain(Integer orderId){
		
		Object obj = LoginHelper.getCurrentUser();
		if(null == obj){
			return buildFailureJson(StatusConstant.NOTLOGIN, "δ��¼");
		}
		if(obj instanceof GroupUser){
			return buildFailureJson(StatusConstant.NON_ALLOW, "û��Ȩ��");
		}
		ComplainBean temp = complainServiceImpl.findByOrderId(orderId);
		if(null == temp){
			return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "δͶ�߹�");
		}
		return buildSuccessCodeJson(StatusConstant.Fail_CODE, "Ͷ�߹�");
	}

}
