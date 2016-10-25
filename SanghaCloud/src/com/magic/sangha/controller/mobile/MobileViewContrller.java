package com.magic.sangha.controller.mobile;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.magic.sangha.controller.BaseController;
/**
 *   客户端请求H5页面转发
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
	 *  关于我们
	 * @return
	 */
	@RequestMapping("/aboutUsPage")
	public String getAboutUs(){
		return "pages/system/aboutus";
	}
	/**
	 *  服务条款
	 * @return
	 */
	@RequestMapping("/clausePage")
	public String getClausePage(){
		return "pages/system/serviceagrement";
	}
	/**
	 *  非产品用户 的 全网页面
	 * @return
	 */
	@RequestMapping("/productPage")
	public String getProductPage(String token,Model model){
		model.addAttribute("token", token);
		return "pages/system/capetown";
	}
	/**
	 *  积分规则
	 * @return
	 */
	@RequestMapping("/bounsPage")
	public String getBounsPage(){
		return "pages/system/integraldescription";
	}
	
	/**自动跳转*/
	@RequestMapping("/autoSkip")
	public String autoSkip(){
		return "pages/autoSkip/autoskip";
	}
	
	/**APP分享页面*/
	@RequestMapping("/shareApp")
	public String sharePage(){
		return "pages/shareApp/share";
	}

}
