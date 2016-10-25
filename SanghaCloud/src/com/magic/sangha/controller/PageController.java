package com.magic.sangha.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.magic.sangha.bean.CompanyBean;
import com.magic.sangha.bean.GroupUser;
import com.magic.sangha.bean.OrderCategoryBean;
import com.magic.sangha.bean.RelationTVBean;
import com.magic.sangha.bean.TVBean;
import com.magic.sangha.service.ICompanyService;
import com.magic.sangha.service.IGroupUserService;
import com.magic.sangha.service.IOrderCategoryService;
import com.magic.sangha.service.IRelationTVService;
import com.magic.sangha.service.ITVService;
import com.magic.sangha.util.LoginHelper;
import com.magic.sangha.util.StatusConstant;

/**
 *  请求页面控制器
 * @author QimouXie
 *
 */
@Controller
@RequestMapping("/admin/requestpage")
public class PageController {

	@Resource
	private ITVService tvServiceImpl;
	@Resource
	private ICompanyService companyServiceImpl;
	@Resource
	private IOrderCategoryService orderCategoryServiceImpl;
	@Resource
	private IGroupUserService groupUserServiceImpl;
	@Resource
	private IRelationTVService relationServiceImpl;
	/**
	 *  登录成功后， 跳转到主页
	 * @param req
	 * @return
	 */
	@RequestMapping("/index")
	public String requestIndex(Integer roleId,Model model,HttpServletResponse resp,HttpServletRequest req,RedirectAttributes red){
		req.getSession().setAttribute("roleId", roleId);
		return "redirect:/admin/requestpage/admin";

		
	}
	
	@RequestMapping("/admin")
	public String rediectPage(Model model,HttpServletRequest req,RedirectAttributes red){
		Integer roleId = (Integer)req.getSession().getAttribute("roleId");
		model.addAttribute("roleId", roleId);
		return "index";
	}
	
	/**默认页面*/
	@RequestMapping("/defaultPage/index")
	public String defaultPage(){
		return "pages/index/index";
	}
	
	@RequestMapping("/count/list")
	public String requestcountList(){
		return "pages/count/list";
	}
	
	@RequestMapping("/loginout")
	public String loginOut(HttpServletRequest req){
		req.getSession().invalidate();
		return "redirect:/"+LoginHelper.LOGIN_PATH;
	}
	
	
	/**
	 *  请求用户审核页面
	 * @return
	 */
	@RequestMapping("/shenhe")
	public String shenhePage(){
		return "pages/shenhe/list";
	}
	@RequestMapping("/shenhe/detail")
	public String shenheDetail(){
		return "pages/shenhe/detail";
	}
	
	/**
	 *  请求电视台管理 列表页面
	 * @return
	 */
	@RequestMapping("/tv")
	public String tvPage(Model model){
		
		return "pages/tv/list";
	}
	/**
	 *  请求新增电视台 页面
	 * @return
	 */
	@RequestMapping("/tv/list")
	public String addTvPage(){
		return "pages/tv/add";
	}
	/**
	 *  请求电视台修改页面
	 * @return
	 */
	@RequestMapping("/tv/edit")
	public String editTvPage(Integer id,String tvName,String stationCode,Model model){
		model.addAttribute("id", id);
		model.addAttribute("tvName", tvName);
		model.addAttribute("stationCode", stationCode);
		return "pages/tv/edit";
	}
	/**
	 *  请求新闻列表页
	 * @return
	 */
	@RequestMapping("/news")
	public String newsPage(){
		return "pages/news/list";
	}
	/**
	 *  请求新闻修改页
	 * @return
	 */
	@RequestMapping("/news/edit")
	public String editNewsPage(Integer newsId,Model model){
		model.addAttribute("newsId", newsId);
		return "pages/news/edit";
	}
	
	/**
	 *  请求添加资讯页面
	 * @return
	 */
	@RequestMapping("/news/add")
	public String addAdsPage(){
		return "pages/news/add";
	}
	/**
	 *  请求资讯详细页面
	 * @return
	 */
	@RequestMapping("/news/detail")
	public String detailPage(Integer newsId,Model model){
		model.addAttribute("newsId", newsId);
		return "pages/news/detail";
	}
	
	/**
	 *  请求广告管理列表
	 * @return
	 */
	@RequestMapping("/ads")
	public String adsPage(){
		return "pages/ads/list";
	}
	/**
	 *  请求banner 添加页
	 * @return
	 */
	@RequestMapping("/ads/add")
	public String adsAddPage(){
		return "pages/ads/add";
	}
	/**
	 *  请求banner 修改页
	 * @return
	 */
	@RequestMapping("/ads/edit")
	public String adsEditPage(Integer bannerId,ModelMap model){
		model.addAttribute("bannerId", bannerId);
		return "pages/ads/edit";
	}
	
	/**
	 *  请求用户管理 页面
	 * @return
	 */
	@RequestMapping("/user/list")
	public String userPage(){
		return "pages/user/list";
	}
	/**
	 *  请求用户添加页面
	 * @return
	 */
	@RequestMapping("/user/add")
	public String addUserPage(){
		return "pages/user/add";
	}
	/**
	 *  请求用户详细页面
	 * @return
	 */
	@RequestMapping("/user/detail")
	public String userDetailPage(Integer userId,Model model,Integer type){
		model.addAttribute("userId", userId);
		model.addAttribute("type", type);
		return "pages/user/detail";
	}
	 /**
		 *  请求用户修改页面
		 * @return
		 */
	@RequestMapping("/user/edit")
	public String userEditPage(Integer userId,Model model,Integer type){
		model.addAttribute("userId", userId);
		model.addAttribute("type", type);
		return "pages/user/edit";
	}
	
	/**
	 *  请求 删除 用户 页面
	 * @return
	 */
	@RequestMapping("/delUser")
	public String delUserPage(){
		return "pages/delUser/list";
	}
	
	/**
	 *  请求设备管理页面
	 * @return
	 */
	@RequestMapping("/device")
	public String devicePage(){
		return "pages/device/updateDevice";
	}
	
	/**
	 *  系统公告 页面
	 * @return
	 */
	@RequestMapping("/notice")
	public String noticePage(){
		return "pages/notice/list";
	}
	/**
	 *  请求添加通知页面
	 * @return
	 */
	@RequestMapping("/notice/add")
	public String addNoticePage(){
		return "pages/notice/add";
	}
	
//	@RequestMapping("/match/list")
//	public String getMathch(){
//		return "pages/match/list";
//	}
	
	@RequestMapping("/notice/edit")
	public String editNoticPage(Integer systemId,Model model){
		model.addAttribute("systemId", systemId);
		return "pages/notice/edit";
	}
	
	@RequestMapping("/notice/detail")
	public String detailNoticPage(Integer systemId,Model model){
		model.addAttribute("systemId", systemId);
		return "pages/notice/detail";
	}
	
	@RequestMapping("/servicemanage")
	public String listServiceManage(){
		return "pages/servicemanage/list";
	}
	
	/**
	 *  订单管理 页面
	 * @return
	 */
	@RequestMapping("/order")
	public String listOrder(Model model){
		List<TVBean> data = tvServiceImpl.dynamicGetTvs(null);
//		TVBean tv = new TVBean();
//		tv.setId(0);
//		tv.setTvName("请选择");
		model.addAttribute("tvs", data);
		return "pages/order/list";
	}
	
	/**
	 *  统计 页面
	 * @return
	 */
	@RequestMapping("/count/countmanage")
	public String countManage(){
		return "pages/countmanage/list";
	}
	
	/**
	 *  统计 页面
	 * @return
	 */
	@RequestMapping("/count/detailcount")
	public String countDetailManage(Model model){
		List<OrderCategoryBean> data = orderCategoryServiceImpl.findAll();
		List<GroupUser> users = groupUserServiceImpl.findAllToToken();
		model.addAttribute("categorys", data);
		model.addAttribute("users", users);
		return "pages/countmanage/detailcount";
	}
	
	/**
	 *  技术管理页面
	 * @return
	 */
	@RequestMapping("/techmanage")
	public String listTechManage(){
		return "pages/techmanage/list";
	}
	/**
	 *  技术管理 修改页面
	 * @return
	 */
	@RequestMapping("/techmanage/edit")
	public String editTechManage(Integer id,Model model,String mobile,String realName){
		model.addAttribute("id", id);
		model.addAttribute("mobile", mobile);
		model.addAttribute("realName", realName);
		return "pages/techmanage/edit";
	}
	
	/**
	 *  请求 评价 统计 列表页面
	 * @return
	 */
	@RequestMapping("/commentcount")
	public String commentCountListPage(Model model){
		List<CompanyBean> coms = companyServiceImpl.findAllByType(StatusConstant.FILIALE);
		model.addAttribute("coms", coms);
		return "pages/commentcount/list";
	}
	/**
	 *  请求 评价 统计 列表页面
	 * @return
	 */
	@RequestMapping("/commentcount/detail")
	public String commentDetailCountListPage(Integer type,String data,Model model,String name){
		
//		Integer id = null;
//		if(type == 0 || type == 1){
//			id = Integer.parseInt(data);
//		}else if(type == 2) {
//			if(null != data && data.trim().length() != 0){
//				String[] userFileds = data.split("\\|");
//				id =  Integer.parseInt(userFileds[1]);
//			}
//		}
		model.addAttribute("data", data);
		model.addAttribute("type", type);
		model.addAttribute("name", name);
		return "pages/commentcount/detail-list";
	}
	@RequestMapping("/commentcount/userDetail")
	public String commentUserCountDetail(Integer userId,Model model,String userName){
		model.addAttribute("userId", userId);
		model.addAttribute("userName", userName);
		return "pages/commentcount/detail-user-list";
	}
	
	/**投诉统计*/
	@RequestMapping("/complaincount")
	public String complainCountPage(){
		return "pages/complaincount/list";
	}
	
	/**
	 *  签到统计
	 * @return
	 */
	@RequestMapping("/sign")
	public String signPage(){
		return "pages/sign/list";
	}
	/**
	 *  签到统计 详细页面
	 * @return
	 */
	@RequestMapping("/sign/detail")
	public String signDetailPage(Integer type,Integer id,Model model,String realName,String mobile){
		model.addAttribute("type", type);
		model.addAttribute("id", id);
		model.addAttribute("realName", realName);
		model.addAttribute("mobile", mobile);
		return "pages/sign/detail-list";
	}
	
	/**
	 *  标签管理 页面
	 * @return
	 */
	@RequestMapping("/lablemanage")
	public String lablePage(){
		return "pages/lablemanage/list";
	}
	/**
	 *  添加标签管理 页面
	 * @return
	 */
	@RequestMapping("/lablemanage/add")
	public String addLablePage(){
		return "pages/lablemanage/add";
	}
	
	/**
	 *  设置标签管理 页面
	 * @return
	 */
	@RequestMapping("/lablemanage/edit")
	public String editLablePage(){
		return "pages/lablemanage/edit";
	}
	
	@RequestMapping("/ordercategorymanage")
	public String ordercategoryPage(){
		return "pages/ordercategorymanage/list";
	}
	
	@RequestMapping("/ordercategorymanage/edit")
	public String editOrderCategoryPage(Integer id,Model model,String categoryName,String upName){
		model.addAttribute("id", id);
		model.addAttribute("categoryName", categoryName);
		model.addAttribute("upName", upName);
		return "pages/ordercategorymanage/edit";
	}
	
	@RequestMapping("/ordercategorymanage/add")
	public String addOrderCategoryPage(){
		return "pages/ordercategorymanage/add";
	}
	
	/**产品体验申请列表页*/
	@RequestMapping("/productfeel")
	public String productfeelPage(){
		return "pages/productfeel/list";
	}
	
	/**
	 *  意见反馈
	 * @return
	 */
	@RequestMapping("/suggest")
	public String suggestPage(){
		return "pages/suggest/list";
	}
	
	/**补丁统计页面*/
	@RequestMapping("/patch")
	public String patchPage(){
		return "pages/patch/list";
	}
	
	@RequestMapping("/patch/detail")
	public String patchDetailPage(Integer groupUserId,Model model,String realName,String mobile,String email){
		model.addAttribute("email", email);
		model.addAttribute("mobile", mobile);
		model.addAttribute("realName", realName);
		model.addAttribute("groupUserId", groupUserId);
		return "pages/patch/detail-list";
	}
	
	@RequestMapping("/selfCenter/order")
	public String selfCenterOrder(){
		return "pages/selfcenter/ordermanage/list";
	}
	@RequestMapping("/selfCenter/editPwd")
	public String selfCenterEditPWD(){
		return "pages/selfcenter/editpassword/edit";
	}
	
	/** 权限管理 页*/
	@RequestMapping("/powermanage")
	public String powermanagePage(){
		return "pages/powermanage/list";
	}
	/** 权限管理 页*/
	@RequestMapping("/powermanage/edit")
	public String powermanageEditPage(){
		return "pages/powermanage/edit";
	}
	
	/**公司管理*/
	@RequestMapping("/companymanage")
	public String companymanagePage(){
		return "pages/companymanage/list";
	}
	
	/**添加公司*/
	@RequestMapping("/companymanage/add")
	public String addCompanyPage(){
		return "pages/companymanage/add";
	}
	
	/**修改公司*/
	@RequestMapping("/companymanage/edit")
	public String editCompanyPage(Integer id,String upName,String company,Integer type,Model model){
		model.addAttribute("id", id);
		model.addAttribute("upName", upName);
		model.addAttribute("company", company);
		model.addAttribute("type", type);
		return "pages/companymanage/edit";
	}
	
	/**投票统计*/
	@RequestMapping("/votecount")
	public String voteCountPage(){
		return "pages/votecount/list";
	}
	
	/**投票统计 详细*/
	@RequestMapping("/votecount/detail")
	public String voteDetailCountPage(Integer newsId,Model model){
		model.addAttribute("newsId", newsId);
		return "pages/votecount/detail-list";
	}
	
	
	
	/**关联TV 页面*/
	@RequestMapping("/relationTV")
	public String setDefaultTVPage(){
		return "pages/RelationTV/list";
	}
	
	/**设置关联TV 页面*/
	@RequestMapping("/relationTV/edit")
	public String editRelationTV(Integer userId,Model model,String realName,Integer roleId){
		model.addAttribute("userId", userId);
		model.addAttribute("realName", realName);
		model.addAttribute("roleId", roleId);
		List<TVBean> tvs = tvServiceImpl.getTVByUserType(roleId, userId);
		List<RelationTVBean> relations = relationServiceImpl.queryByUserId(userId);
		model.addAttribute("tvs", tvs);
		model.addAttribute("relations", relations);
		return "pages/RelationTV/edit";
	}
	/**关联TV 页面*/
	@RequestMapping("/relationTV/detail")
	public String detailRelationTVPage(Integer userId,Model model,String realName){
		model.addAttribute("userId", userId);
		model.addAttribute("realName", realName);
		return "pages/RelationTV/detail";
	}
	
}
