package com.magic.sangha.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.NewsBean;

/**
 *  News Interface
 * @author QimouXie
 *
 */
public interface INewsMapper {
	/**
	 *  Add News Info
	 * @param news
	 * @return
	 */
	
	Integer addNews(@Param("news")NewsBean news);
	/**
	 *  Find News By News ID
	 * @param id
	 * @return
	 */
	NewsBean findById(@Param("id")Integer id);
	/**
	 *  Paging and Querying News
	 * @param typeId
	 * @param limit
	 * @param limitSize
	 * @return
	 */
	List<NewsBean> findByType(@Param("typeId")Integer typeId,@Param("limit")Integer limit,@Param("limitSize")Integer limitSize);
	/**
	 *  Count News By Type
	 * @param typeId
	 * @return
	 */
	Integer countNewsByType(@Param("typeId")Integer typeId);
	
	/**
	 *  ��������
	 * @param news
	 */
	void updateNews(@Param("news")NewsBean news);
	
	/**ʵ�� �������� �Ƿ���Ч״̬ ��������ɾ��*/
	void delNews(@Param("id")Integer id);
	
	
	

}
