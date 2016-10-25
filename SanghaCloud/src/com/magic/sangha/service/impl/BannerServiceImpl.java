package com.magic.sangha.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.magic.sangha.bean.BannerBean;
import com.magic.sangha.mapper.IBannerMapper;
import com.magic.sangha.service.IBannerService;

/**
 *  banner管理  业务层接口实现类
 * @author QimouXie
 *
 */
@Service
public class BannerServiceImpl implements IBannerService {

	@Resource
	private IBannerMapper bannerMapper;
	
	@Override
	public void addBanner(BannerBean banner) {
		bannerMapper.addBanner(banner);
	}

	@Override
	public void delBanner(Integer id) {
		bannerMapper.delBanner(id);
	}

	@Override
	public Integer countBanner() {
		return bannerMapper.countBanner();
	}

	@Override
	public List<BannerBean> findAll() {
		return bannerMapper.findAll();
	}
	
	@Override
	public BannerBean findById(Integer id) {
		// TODO Auto-generated method stub
		return bannerMapper.findById(id);
	}

	@Override
	public void updateBanner(BannerBean banner) {
		bannerMapper.updateBanner(banner);
	}

}
