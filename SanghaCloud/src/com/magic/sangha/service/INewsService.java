package com.magic.sangha.service;

import java.util.List;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.NewsBean;
import com.magic.sangha.bean.NewsLikesBean;
import com.magic.sangha.bean.VoteBean;

/**
 *  News Service Interface
 * @author QimouXie
 *
 */
public interface INewsService {
	/**
	 *  Add News
	 * @param news
	 * @return
	 */
	Integer addNews(NewsBean news,List<VoteBean> votes,Integer isBanner) throws Exception;

	/**
	 *  Querying News By ID
	 * @param id
	 * @return
	 */
	NewsBean findById(Integer id);
	/**
	 * Paging and Querying News
	 * @param pageNO
	 * @param pageSize
	 * @param typeId
	 * @return
	 */
	CutPageBean<NewsBean> findNewsByType(Integer pageNO,Integer pageSize,Integer typeId);
	 /**
	  *  Querying News List By ID
	  * @param typeId
	  * @return
	  */
	List<NewsBean> findNewsByType(Integer typeId);
	/**
	 * 
	 * @param userId
	 * @param groupUserId
	 * @param newsId
	 * @return
	 */
	NewsLikesBean getIsLikes(Integer userId,Integer groupUserId,Integer newsId);
	/**
	 *  ����
	 * @param likes
	 */
	void addIncreaseLikes(NewsLikesBean likes,NewsBean news) throws Exception;
	/**
	 *  ȡ������
	 * @param likes
	 */
	void delNewsLikes(NewsLikesBean likes,NewsBean news)  throws Exception;
	/**
	 *  ʵ�� �������� �Ƿ���Ч״̬ ��������ɾ��
	 * @param newsId
	 */
	void delNews(Integer newsId) throws Exception;
	
	/** 
	 *  ������Ѷ
	 * @param news
	 */
	void updateNews(NewsBean news,Integer isBanner,Integer isEditImage) throws Exception;
}
