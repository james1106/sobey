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
 *  �ǲ�Ʒ�û� �����Ʒ ���Ʋ�
 * @author QimouXie
 *
 */
@Controller
@RequestMapping("/productFeel")
public class ProductFeelController extends BaseController {
	
	@Resource
	private IProductFeelService productFeelServiceImpl;
	
	/**
	 *  �����ǲ�Ʒ�û�����
	 * @return
	 */
	@RequestMapping("/addFeel")
	@ResponseBody
	public ViewData productFeel(String token){
		Object obj = LoginHelper.getCurrentUser(token);
		if(null == obj){
			return buildFailureJson(StatusConstant.NOTLOGIN, "δ��¼");
		}
		ProductFeelBean feel = new ProductFeelBean();
		if(obj instanceof UserBean){
			UserBean user =  (UserBean)obj;
			if(StatusConstant.FROZEN.equals(user.getStatus())){
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "�˻�������");
			}
			if(user.getIsPUser() == 0){
				return buildFailureJson(StatusConstant.NON_ALLOW, "û��Ȩ��");
			}
			feel.setUserId(user.getId());
			Integer count = productFeelServiceImpl.queryCheckIsFeel(user.getId());
			if(count != 0){
				return buildFailureJson(StatusConstant.Fail_CODE, "�Ѿ������");
			}
		}else{
			return buildFailureJson(StatusConstant.NON_ALLOW, "û��Ȩ��");
		}
		
		productFeelServiceImpl.add(feel);
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "����ɹ�");
	}

}
