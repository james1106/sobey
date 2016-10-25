package com.magic.sangha.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.BannerBean;

/**
 *  Banner �־ò�
 * @author QimouXie
 *
 */
public interface IBannerMapper {
	
	/**
	 *  ���banner
	 * @param banner
	 */
	void addBanner(@Param("banner")BannerBean banner);
	
	/**
	 *  ɾ��banner
	 * @param id
	 */
	void delBanner(@Param("id")Integer id);
	
	/**
	 *  ͳ��banner����
	 * @return
	 */
	Integer countBanner();
	
	/**
	 *  ��ѯ����banner
	 * @return
	 */
	List<BannerBean> findAll();
	
	BannerBean findById(@Param("id")Integer id);
	
	void updateBanner(@Param("banner")BannerBean banner);
	
	void delBannerByNewsId(@Param("newsId")Integer newsId);
	
	void updateBannerByNewsId(@Param("newsId")Integer newsId,@Param("banner")BannerBean banner);
}
