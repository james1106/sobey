package com.magic.sangha.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.sangha.bean.NewsBean;
import com.magic.sangha.service.INewsService;
import com.magic.sangha.util.StatusConstant;
import com.magic.sangha.util.ViewData;

/**��Ѷ����*/
@Controller
@RequestMapping("/admin/news")
public class AdminNewsController extends BaseController {
	
	@Resource
	private INewsService newsServiceImpl;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@RequestMapping("/edit")
	@ResponseBody
	public ViewData editNews(HttpServletRequest req){
		
		String newsStrId = req.getParameter("newsId");
		if(null == newsStrId){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "�ֶβ���Ϊ��");
		}
		Integer newsId = Integer.parseInt(newsStrId);
		String content =  req.getParameter("content");
		// �Ƿ�ر�ͶƱ
		String isCloseVote = req.getParameter("isCloseVote");
		String title = req.getParameter("title");
		String newsType = req.getParameter("type");
		String introduction = req.getParameter("introduction");
		String isShowIco = req.getParameter("isShowIco");
		String isLink = req.getParameter("isLink");
		String tagetLink = req.getParameter("tagetLink");
		String reads = req.getParameter("reads");
		// 0 ��  1 ��
		Integer isBanner = Integer.parseInt(req.getParameter("isBanner"));
		Integer oldIsBanner = Integer.parseInt(req.getParameter("oldIsBanner"));
		Integer isEditImage = Integer.parseInt(req.getParameter("isEditImage"));
		String filePath = req.getParameter("filePath");
		String oldImgUrl = req.getParameter("oldImgUrl");
		
		if(  null == title || title.trim().length() == 0 || null == introduction || introduction.trim().length() == 0 ){
			return buildFailureJson(StatusConstant.Fail_CODE, "ѡ���Ϊ��");
		}
		
		if(Integer.parseInt(isLink) == 1){
			if(null == tagetLink || tagetLink.trim().length() == 0){
				return buildFailureJson(StatusConstant.Fail_CODE, "���������Ӳ���Ϊ��");
			}
			if(tagetLink.indexOf("http") == -1){
				return buildFailureJson(StatusConstant.Fail_CODE, "���������Ӳ��Ϸ�");
			}
		}else{
			tagetLink = null;
		}
		if(isEditImage == 0){
			filePath = oldImgUrl;
		}else{
			if(null == filePath || filePath.trim().length() == 0 || filePath.indexOf("upload") == -1){
				return buildFailureJson(StatusConstant.Fail_CODE, "ͼƬ����Ϊ��");
			}
		}
		
		
		NewsBean news = new NewsBean();
		
		news.setId(newsId);
		news.setContent(content);
		news.setImageUrl(filePath);
		news.setIntroduction(introduction);
		news.setIsShowIcon(Integer.valueOf(isShowIco));
		news.setIsUrl(Integer.valueOf(isLink));
		if(Integer.parseInt(isCloseVote) == 1){
			news.setIsVote(0);
		}
		news.setLinkUrl(tagetLink);
		news.setReads(Integer.valueOf(reads));
		news.setTitle(title);
		news.setTypeId(Integer.valueOf(newsType));
		news.setUpdateTime(new Date());
		if(oldIsBanner != isBanner){
			news.setIsBanner(isBanner);
		}
		try {
			newsServiceImpl.updateNews(news,isBanner,isEditImage);
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			return buildFailureJson(StatusConstant.Fail_CODE, "�����쳣");
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "�޸ĳɹ�");
	}

}
