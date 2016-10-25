package com.magic.sangha.service;

import java.util.List;

import com.magic.sangha.bean.BannerBean;

/**
 *  Banner 业务层接口
 * @author QimouXie
 *
 */
public interface IBannerService {

	void addBanner(BannerBean banner);
	
	void delBanner(Integer id);
	
	Integer countBanner();
	
	List<BannerBean> findAll();
	
	BannerBean findById(Integer id);
	
	void updateBanner(BannerBean banner);
	
}
