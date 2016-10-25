package com.magic.sangha.controller.mobile;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.sangha.bean.GroupUser;
import com.magic.sangha.bean.SuggestBean;
import com.magic.sangha.bean.UserBean;
import com.magic.sangha.controller.BaseController;
import com.magic.sangha.service.ISuggestService;
import com.magic.sangha.util.LoginHelper;
import com.magic.sangha.util.StatusConstant;
import com.magic.sangha.util.ViewData;


/**
 *  意见反馈 控制层
 * @author QimouXie
 *
 */
@Controller
@RequestMapping("/suggest")
public class SuggestController extends BaseController {

	@Resource
	private ISuggestService suggestServiceImpl;
	
	@RequestMapping("/add")
	@ResponseBody
	public ViewData addSuggest(String content){
		
		Object obj = LoginHelper.getCurrentUser();
		if(null == obj){
			return buildFailureJson(StatusConstant.NOTLOGIN, "未登录");
		}
		if(null == content || content.trim().length() == 0){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		SuggestBean suggest = new SuggestBean();
		if(obj instanceof UserBean){
			UserBean user = (UserBean)obj;
			if(user.getStatus().equals(StatusConstant.FROZEN)){
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "帐号被冻结");
			}
			suggest.setUserId(user.getId());
		}else if(obj instanceof GroupUser){
			GroupUser user = (GroupUser)obj;
			if(user.getStatus().equals(StatusConstant.FROZEN)){
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "帐号被冻结");
			}
			suggest.setGroupUserId(user.getId());
		}else{
			return buildFailureJson(StatusConstant.Fail_CODE, "无权限");
		}
		suggest.setContent(content);
		suggestServiceImpl.add(suggest);
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "操作成功");
	}
	
}
