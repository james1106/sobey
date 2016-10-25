package com.magic.sangha.controller.mobile;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.magic.sangha.controller.BaseController;
/**
 *   �ͻ�������H5ҳ��ת��
 * @author QimouXie
 *
 */
@RequestMapping("/requestWeb")
@Controller
public class MobileViewContrller extends BaseController {
	
	@RequestMapping("/newsDetail")
	public String getNewsDetailPage(Integer newsId,Model model,HttpServletRequest req,String token){
//		String token = req.getHeader("token");
		model.addAttribute("newsId", newsId);
		model.addAttribute("token", token);
		return "pages/newsdetail/index";
	}
	
	@RequestMapping("/getSystemPage")
	public String getSystemPage(Integer systemId,Model model){
		model.addAttribute("systemId", systemId);
		return "pages/notice/index";
	}
	
	/**
	 *  ��������
	 * @return
	 */
	@RequestMapping("/aboutUsPage")
	public String getAboutUs(){
		return "pages/system/aboutus";
	}
	/**
	 *  ��������
	 * @return
	 */
	@RequestMapping("/clausePage")
	public String getClausePage(){
		return "pages/system/serviceagrement";
	}
	/**
	 *  �ǲ�Ʒ�û� �� ȫ��ҳ��
	 * @return
	 */
	@RequestMapping("/productPage")
	public String getProductPage(String token,Model model){
		model.addAttribute("token", token);
		return "pages/system/capetown";
	}
	/**
	 *  ���ֹ���
	 * @return
	 */
	@RequestMapping("/bounsPage")
	public String getBounsPage(){
		return "pages/system/integraldescription";
	}
	
	/**�Զ���ת*/
	@RequestMapping("/autoSkip")
	public String autoSkip(){
		return "pages/autoSkip/autoskip";
	}
	
	/**APP����ҳ��*/
	@RequestMapping("/shareApp")
	public String sharePage(){
		return "pages/shareApp/share";
	}

}
