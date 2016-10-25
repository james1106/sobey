package com.magic.sangha.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.magic.sangha.bean.BannerBean;
import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.NewsBean;
import com.magic.sangha.bean.NewsLikesBean;
import com.magic.sangha.bean.VoteBean;
import com.magic.sangha.bean.VoteUserBean;
import com.magic.sangha.mapper.IBannerMapper;
import com.magic.sangha.mapper.INewsLikesMapper;
import com.magic.sangha.mapper.INewsMapper;
import com.magic.sangha.mapper.IVoteMapper;
import com.magic.sangha.mapper.IVoteUserMapper;
import com.magic.sangha.service.INewsService;
import com.magic.sangha.util.StatusConstant;

/**
 *  News ServiceImpl
 * @author QimouXie
 *
 */
@Service
public class NewsServiceImpl implements INewsService {
	
	@Resource
	private INewsMapper newsMapper;
	@Resource
	private IVoteMapper voteMapper;
	@Resource
	private INewsLikesMapper newsLikesMapper;
	
	@Resource
	private IBannerMapper bannerMapper;
	
	@Resource
	private IVoteUserMapper voteUserMapper;

	private Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public Integer addNews(NewsBean news,List<VoteBean> votes,Integer isBanner)  throws Exception {
		
		 newsMapper.addNews(news);
		if(null != votes){
			for (VoteBean voteBean : votes) {
				voteBean.setNewsId(news.getId());
			}
			voteMapper.batchAddVote(votes);
		}
		if(isBanner == 1){
			BannerBean banner = new BannerBean();
			banner.setImage(news.getImageUrl());
			banner.setIsInside(StatusConstant.INSIDE);
			banner.setNewsId(news.getId());
			bannerMapper.addBanner(banner);
		}
		return news.getId();
	}

	@Override
	public NewsBean findById(Integer id) {
		
		NewsBean news = newsMapper.findById(id);
		List<VoteUserBean> votes = voteUserMapper.countVoteByNewsId(id);
//		Integer count = newsLikesMapper.countLikes(id);
//		news.setLikes(count);
		if(null != votes){
			try {
				JSONArray jsonVote = JSONArray.fromObject(votes);
				news.setVotes(jsonVote);
			} catch (Exception e) {
				logger.debug(e.getMessage(),e);
			}
		}
		return news;
	}

	@Override
	public CutPageBean<NewsBean> findNewsByType(Integer pageNO,Integer pageSize, Integer typeId) {
		
		List<NewsBean> dataList = newsMapper.findByType(typeId, (pageNO - 1) * pageSize, pageSize);
		Integer count = newsMapper.countNewsByType(typeId);
		Integer totalPage = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
		CutPageBean<NewsBean> page = new CutPageBean<NewsBean>(dataList, count, totalPage);
		return page;
	}

	@Override
	public List<NewsBean> findNewsByType(Integer typeId) {
		return newsMapper.findByType(typeId, null, null);
	}

	@Override
	public NewsLikesBean getIsLikes(Integer userId, Integer groupUserId,Integer newsId) {
		return newsLikesMapper.findByUserId(userId, groupUserId, newsId);
	}
	@Override
	public void addIncreaseLikes(NewsLikesBean likes,NewsBean news) throws Exception {
		newsLikesMapper.addNewsLike(likes);
		NewsBean temp = new NewsBean();
		temp.setId(news.getId());
		temp.setLikes(news.getLikes() + 1);
		newsMapper.updateNews(temp);
	}
	@Override
	public void delNews(Integer newsId) throws Exception{
		bannerMapper.delBannerByNewsId(newsId);
		newsMapper.delNews(newsId);
	}

	@Override
	public void delNewsLikes(NewsLikesBean likes,NewsBean news) throws Exception {
		newsLikesMapper.delNewsLikes(likes);
		NewsBean temp = new NewsBean();
		temp.setId(news.getId());
		temp.setLikes(news.getLikes() - 1);
		newsMapper.updateNews(temp);
	}
	@Override
	public void updateNews(NewsBean news,Integer isBanner,Integer isEditImage) throws Exception {
		
		if(null != news.getIsBanner() && null != isBanner ){
			if(isBanner == 1){
				BannerBean banner = new BannerBean();
				banner.setImage(news.getImageUrl());
				banner.setIsInside(StatusConstant.INSIDE);
				banner.setNewsId(news.getId());
				bannerMapper.addBanner(banner);
			}else{
				bannerMapper.delBannerByNewsId(news.getId());
			}
		}
		if(null != isEditImage && isEditImage == 1){
			BannerBean banner = new BannerBean();
			banner.setImage(news.getImageUrl());
			banner.setNewsId(news.getId());
			bannerMapper.updateBannerByNewsId(news.getId(), banner);
		}
		newsMapper.updateNews(news);
	}
	
	
}
