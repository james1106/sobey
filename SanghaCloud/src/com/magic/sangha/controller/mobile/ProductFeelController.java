package com.magic.sangha.controller.mobile;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.sangha.bean.ProductFeelBean;
import com.magic.sangha.bean.UserBean;
import com.magic.sangha.controller.BaseController;
import com.magic.sangha.service.IProductFeelService;
import com.magic.sangha.util.LoginHelper;
import com.magic.sangha.util.StatusConstant;
import com.magic.sangha.util.ViewData;

/**
 *  非产品用户 体验产品 控制层
 * @author QimouXie
 *
 */
@Controller
@RequestMapping("/productFeel")
public class ProductFeelController extends BaseController {
	
	@Resource
	private IProductFeelService productFeelServiceImpl;
	
	/**
	 *  新增非产品用户体验
	 * @return
	 */
	@RequestMapping("/addFeel")
	@ResponseBody
	public ViewData productFeel(String token){
		Object obj = LoginHelper.getCurrentUser(token);
		if(null == obj){
			return buildFailureJson(StatusConstant.NOTLOGIN, "未登录");
		}
		ProductFeelBean feel = new ProductFeelBean();
		if(obj instanceof UserBean){
			UserBean user =  (UserBean)obj;
			if(StatusConstant.FROZEN.equals(user.getStatus())){
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户被冻结");
			}
			if(user.getIsPUser() == 0){
				return buildFailureJson(StatusConstant.NON_ALLOW, "没有权限");
			}
			feel.setUserId(user.getId());
			Integer count = productFeelServiceImpl.queryCheckIsFeel(user.getId());
			if(count != 0){
				return buildFailureJson(StatusConstant.Fail_CODE, "已经体验过");
			}
		}else{
			return buildFailureJson(StatusConstant.NON_ALLOW, "没有权限");
		}
		
		productFeelServiceImpl.add(feel);
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "体验成功");
	}

}
