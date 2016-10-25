package com.magic.sangha.controller.mobile;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.sangha.bean.BannerBean;
import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.GroupUser;
import com.magic.sangha.bean.NewsBean;
import com.magic.sangha.bean.NewsLikesBean;
import com.magic.sangha.bean.UserBean;
import com.magic.sangha.bean.VoteBean;
import com.magic.sangha.bean.VoteUserBean;
import com.magic.sangha.controller.BaseController;
import com.magic.sangha.service.IBannerService;
import com.magic.sangha.service.INewsService;
import com.magic.sangha.service.IVoteUserService;
import com.magic.sangha.util.LoginHelper;
import com.magic.sangha.util.StatusConstant;
import com.magic.sangha.util.ViewData;

/**
 *  资讯控制器
 * @author QimouXie
 *
 */
@Controller
@RequestMapping("/news")
public class NewsController extends BaseController {
	
	@Resource
	private INewsService newsServiceImpl;
	@Resource
	private IBannerService bannerServiceImpl;
	@Resource
	private IVoteUserService voreUserServiceImpl;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@RequestMapping("/getNewsList")
	@ResponseBody
	public ViewData getNewsList(Integer typeId,Integer pageNO,Integer pageSize){
		// typeId 1:互动圈 2: 产品介绍 3: 行业新闻
		CutPageBean<NewsBean> page = newsServiceImpl.findNewsByType(pageNO,pageSize,typeId);
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("lists", page.getDataList());
		data.put("totalPage", page.getTotalPage());
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}
	
	/**
	 *  新增banner
	 * @param image
	 * @param imgURL
	 * @return
	 */
	@RequestMapping("/addBanner")
	@ResponseBody
	public ViewData addBanner(String image,String imgURL){
		
		if(null == image){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		BannerBean banner = new BannerBean();
		banner.setImage(image);
		banner.setImgUrl(imgURL);
		bannerServiceImpl.addBanner(banner);
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "操作成功");
	}
	@RequestMapping("/countBanners")
	@ResponseBody
	public ViewData countBanners(){
		Integer count = bannerServiceImpl.countBanner();
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", count);
	}
	/**
	 *  获取banner
	 * @return
	 */
	@RequestMapping("/getBanners")
	@ResponseBody
	public ViewData getBanners(){
		List<BannerBean> dataList = bannerServiceImpl.findAll();
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("lists", dataList);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}
	
	/**
	 *  获取banner
	 * @return
	 */
	@RequestMapping("/getBannersById")
	@ResponseBody
	public ViewData getBannersById(Integer id){
		if(null == id || id==0){
			return buildFailureJson(StatusConstant.Fail_CODE, "字段不合法");
		}
		BannerBean data = bannerServiceImpl.findById(id);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}
	
	/**
	 *  删除Banner
	 * @param bannerId
	 * @return
	 */
	@RequestMapping("/delBanner")
	@ResponseBody
	public ViewData delBanner(Integer bannerId){
		if(null == bannerId){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		bannerServiceImpl.delBanner(bannerId);
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "操作成功");
	}
	
	@RequestMapping("/updateBanner")
	@ResponseBody
	public ViewData updateBanner(String image,String imgUrl,Integer id){
		if(id == null ){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		BannerBean banner = new BannerBean();
		banner.setId(id);
		banner.setImage(image);
		banner.setImgUrl(imgUrl);
		bannerServiceImpl.updateBanner(banner);
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "操作成功");
	}
	/**
	 *  增加阅读数
	 * @return
	 */
	@RequestMapping("/increaseReads")
	@ResponseBody
	public ViewData increaseReads(Integer newsId){
		if(null == newsId){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		NewsBean news = newsServiceImpl.findById(newsId);
		if(null == news){
			return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "对象不存在");
		}
		//  增加阅读数模块
		NewsBean tempNews = new NewsBean();
		tempNews.setId(newsId);
		tempNews.setRealReads(news.getRealReads() + 1);
		try {
			newsServiceImpl.updateNews(tempNews,null,null);
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			return buildFailureJson(StatusConstant.Fail_CODE, "操作失败");
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "阅读成功");
	}
	/**
	 *  增加点赞数
	 * @return
	 */
	@RequestMapping("/increaseLikes")
	@ResponseBody
	public ViewData increaseLikes(Integer newsId,Integer flag){
		if(null == newsId){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		Object obj = LoginHelper.getCurrentUser();
		if(null == obj){
			return buildFailureJson(StatusConstant.NOTLOGIN, "未登录");
		}
		NewsBean news = newsServiceImpl.findById(newsId);
		if(null == news){
			return buildFailureJson(StatusConstant.OBJECT_NOT_EXIST, "对象不存在");
		}
		if(flag != null && flag == 2){
			NewsLikesBean likes = new NewsLikesBean();
			if(obj instanceof UserBean){
				UserBean user = (UserBean) obj;
				likes.setUserId(user.getId());
			}else if(obj instanceof GroupUser){
				GroupUser user = (GroupUser) obj;
				likes.setGroupUserId(user.getId());
			}
			likes.setNewsId(newsId);
			try {
				newsServiceImpl.delNewsLikes(likes,news);
			} catch (Exception e) {
				logger.debug(e.getMessage(), e);
				return buildFailureJson(StatusConstant.Fail_CODE, "数据异常");
			}
		}else{
			// 增加点赞在数量模块
			NewsLikesBean likes = new NewsLikesBean();
			if(obj instanceof UserBean){
				UserBean user = (UserBean) obj;
				likes.setUserId(user.getId());
			}else if(obj instanceof GroupUser){
				GroupUser user = (GroupUser) obj;
				likes.setGroupUserId(user.getId());
			}
			likes.setNewsId(newsId);
			try {
				newsServiceImpl.addIncreaseLikes(likes,news);
			} catch (Exception e) {
				logger.debug(e.getMessage(), e);
				return buildFailureJson(StatusConstant.Fail_CODE, "数据异常");
			}
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "操作成功");
	}
	
	/**
	 *  投票接口
	 * @param voteId
	 * @return
	 */
	@RequestMapping("/increaseVotes")
	@ResponseBody
	public ViewData increaseVotes(Integer voteId,Integer newsId,String token){
		if(null == voteId || null == newsId){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		Object obj = LoginHelper.getCurrentUser(token);
		if(null == obj){
			return buildFailureJson(StatusConstant.NOTLOGIN, "未登录");
		}
		// 投票处理
		VoteUserBean voteUser = new VoteUserBean();
		if(obj instanceof UserBean){
			UserBean user = (UserBean)obj;
			if(StatusConstant.FROZEN.equals(user.getStatus())){
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户被冻结");
			}
			voteUser.setUserId(user.getId());
		}else if(obj instanceof GroupUser){
			GroupUser user = (GroupUser)obj;
			if(StatusConstant.FROZEN.equals(user.getStatus())){
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户被冻结");
			}
			voteUser.setGroupUserId(user.getId());
		}else{
			return buildFailureJson(StatusConstant.Fail_CODE, "没有权限");
		}
		
		VoteUserBean vote = voreUserServiceImpl.queryUserIsVote(voteUser, newsId);
		if(null != vote){
			return buildFailureJson(StatusConstant.Fail_CODE, "已经投过了");
		}
		voteUser.setVoteId(voteId);
		voreUserServiceImpl.addVoteUser(voteUser);
		NewsBean news = newsServiceImpl.findById(newsId);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", news);
	}
	
	/**
	 *   获取资讯 根据ID
	 * @param newsId
	 * @return
	 */
	@RequestMapping("/getNewsById")
	@ResponseBody
	public ViewData getNews(Integer newsId,String token){
		
		if(null == newsId){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		NewsBean news = newsServiceImpl.findById(newsId);
		VoteUserBean voteUser = new VoteUserBean();
//		Object obj = LoginHelper.getCurrentUser();
		Object obj = null;
		if(null != token && token.trim().length() != 0){
			obj = LoginHelper.getCurrentUser(token);
		}
		if(null != obj){
			if(obj instanceof UserBean){
				UserBean user = (UserBean)obj;
				voteUser.setUserId(user.getId());
			}else if(obj instanceof GroupUser){
				GroupUser user = (GroupUser)obj;
				voteUser.setGroupUserId(user.getId());
			}
			VoteUserBean vote = voreUserServiceImpl.queryUserIsVote(voteUser, newsId);
			news.setIsVoteUser(vote);
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", news);
	}
	
	@RequestMapping("/addNews")
	@ResponseBody
	public ViewData addNews(HttpServletRequest req){
		
		GroupUser user  = LoginHelper.getCurrentAdmin(req);
		if(null == user){
			return buildFailureJson(StatusConstant.NOTLOGIN, "未登录");
		}
		String content =  req.getParameter("content");
		String isVote = req.getParameter("isVote");
		String title = req.getParameter("title");
		String newsType = req.getParameter("type");
		String introduction = req.getParameter("introduction");
		String isShowIco = req.getParameter("isShowIco");
		String isLink = req.getParameter("isLink");
		String tagetLink = req.getParameter("tagetLink");
		String reads = req.getParameter("reads");
		// 0 否  1 是
		Integer isBanner = Integer.parseInt(req.getParameter("isBanner"));
		String[] votedecris = req.getParameterValues("votedecri");
		String filePath = req.getParameter("filePath");
		if(  null == title || title.trim().length() == 0 || null == introduction || introduction.trim().length() == 0 ){
			return buildFailureJson(StatusConstant.Fail_CODE, "选项不能为空");
		}
		if(null == filePath || filePath.trim().length() == 0){
			return buildFailureJson(StatusConstant.Fail_CODE, "图片不能为空");
		}
		if(Integer.valueOf(isLink) == 1){
			if(null == tagetLink || tagetLink.trim().length() == 0){
				return buildFailureJson(StatusConstant.Fail_CODE, "链接不合法");
			}
			content = null;
		}else{
			if(null == content || content.trim().length() == 0 ){
				return buildFailureJson(StatusConstant.Fail_CODE, "内容不能为空");
			}
			tagetLink = null;
		}
		List<VoteBean> votes = null;
		if(Integer.valueOf(isVote) == 1){
			if(null == votedecris || votedecris.length == 0){
				return buildFailureJson(StatusConstant.Fail_CODE, "没有投票");
			}else{
				votes = new ArrayList<VoteBean>();
				for(int i=0 ; i < votedecris.length ; i++){
					if(null == votedecris[i] || votedecris[i].trim().length() == 0){
						continue;
					}
					VoteBean vote = new VoteBean();
					vote.setDescribe(votedecris[i]);
					votes.add(vote);
				}
			}
		}
		
		
		NewsBean news = new NewsBean();
		
		news.setContent(content);
		news.setImageUrl(filePath);
		news.setIntroduction(introduction);
		news.setIsShowIcon(Integer.valueOf(isShowIco));
		news.setIsUrl(Integer.valueOf(isLink));
		news.setIsVote(Integer.valueOf(isVote));
		news.setLikes(0);
		news.setLinkUrl(tagetLink);
		news.setReads(Integer.valueOf(reads));
		news.setTitle(title);
		news.setTypeId(Integer.valueOf(newsType));
		news.setUpdateTime(new Date());
		news.setUserId(user.getId());
		news.setIsBanner(isBanner);
		try {
			newsServiceImpl.addNews(news, votes,isBanner);
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			return buildFailureJson(StatusConstant.Fail_CODE, "数据异常");
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "发布成功");
	}
	
	/**
	 *  查看当前用户对当前新闻是否点赞
	 * @param newsId
	 * @return
	 */
	@RequestMapping("/getIsLikes")
	@ResponseBody
	public ViewData getIsLikes(Integer newsId){
		if(null == newsId){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		Object obj = LoginHelper.getCurrentUser();
		if(null == obj){
			return buildFailureJson(StatusConstant.NOTLOGIN, "未登录");
		}
		NewsLikesBean likes = null;
		if(obj instanceof UserBean){
			UserBean user = (UserBean) obj;
			likes = newsServiceImpl.getIsLikes(user.getId(), null, newsId);
		}else if(obj instanceof GroupUser){
			GroupUser user = (GroupUser) obj;
			likes = newsServiceImpl.getIsLikes(null, user.getId(), newsId);
		}
		Integer isLikes = null;
		if(null == likes){
			// 没有点赞
			isLikes = 0;
		}else{
			// 已经点赞
			isLikes = 1;
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", isLikes);
	}
	
	@RequestMapping("/delNews")
	@ResponseBody
	public ViewData delNews(Integer newsId){
		
		if(null == newsId){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		try {
			newsServiceImpl.delNews(newsId);
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			return buildFailureJson(StatusConstant.Fail_CODE, "数据异常");
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "操作成功");
		
	}
	

}
