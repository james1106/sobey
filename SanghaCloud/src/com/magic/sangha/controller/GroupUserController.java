package com.magic.sangha.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.sangha.bean.GroupUser;
import com.magic.sangha.service.ICompanyService;
import com.magic.sangha.service.IGroupUserService;
import com.magic.sangha.util.LoginHelper;
import com.magic.sangha.util.StatusConstant;
import com.magic.sangha.util.ViewData;

@Controller
@RequestMapping("/group")
public class GroupUserController extends BaseController {

	@Resource
	private IGroupUserService groupUserServiceImpl;
	@Resource
	private ICompanyService companyServiceImpl;

	private Logger log = Logger.getLogger(GroupUserController.class);

	@RequestMapping("/pc/login")
	@ResponseBody
	public ViewData login(HttpServletRequest req, String mobile, String password,Model model) {
		log.debug("");
		if (null == mobile || null == password) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "�ֶβ���Ϊ��");
		}
		GroupUser user = groupUserServiceImpl.login(mobile, password);
		if (null == user) {
			return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "�û������������");
		}else if(StatusConstant.NONPASS.equals(user.getStatus()) || StatusConstant.NOTPASS.equals(user.getStatus())){
			return buildFailureJson(StatusConstant.Fail_CODE, "�ʺ��������");
		}
		req.getSession().setAttribute("user", user);
		
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��½�ɹ�", user);
	}

	@RequestMapping("/updatePassword")
	@ResponseBody
	public ViewData updatePassword(String oldpwd, String password,HttpServletRequest req) {
		if (null == password || password.trim().length() == 0) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "�ֶβ���Ϊ��");
		}
		GroupUser user = LoginHelper.getCurrentAdmin(req);
		if (null == user) {
			return buildFailureJson(StatusConstant.NOTLOGIN, "���󲻴���");
		}
		if (StatusConstant.FROZEN.equals(user.getStatus())) {
			return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "�˻��ѱ�����");
		}
		if (!oldpwd.equals(user.getPassword())) {
			return buildFailureJson(StatusConstant.Fail_CODE, "ԭʼ���벻��");
		}
		if (password.equals(user.getPassword())) {
			return buildFailureJson(StatusConstant.Fail_CODE, "������������벻����ͬ");
		}
		GroupUser temp = new GroupUser();
		temp.setId(user.getId());
		temp.setPassword(password);
		temp.setMobile(user.getMobile());

		user.setPassword(password);
		groupUserServiceImpl.updateGroupUser(temp);
		req.getSession().invalidate();
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "�޸ĳɹ�");

	}

}
