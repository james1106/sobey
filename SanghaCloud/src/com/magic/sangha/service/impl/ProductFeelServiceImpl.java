package com.magic.sangha.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.ProductFeelBean;
import com.magic.sangha.mapper.IProductFeelMapper;
import com.magic.sangha.service.IProductFeelService;

/**
 *  用户体验产品 业务层接口实现
 * @author QimouXie
 *
 */
@Service
public class ProductFeelServiceImpl implements IProductFeelService {
	
	@Resource
	private IProductFeelMapper productFeelMapper;

	@Override
	public Integer add(ProductFeelBean feel) {
		return productFeelMapper.add(feel);
	}

	@Override
	public Integer queryCheckIsFeel(Integer userId) {
		return productFeelMapper.queryCheckIsFeel(userId);
	}

	@Override
	public CutPageBean<ProductFeelBean> quertPage(Integer pageNO,Integer pageSize) {
		
		List<ProductFeelBean> dataList = productFeelMapper.queryPage((pageNO - 1) * pageSize, pageSize);
		Integer count = productFeelMapper.countPage();
		Integer totalPage = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
		CutPageBean<ProductFeelBean> page = new CutPageBean<ProductFeelBean>(dataList, count, totalPage);
		return page;
	}

}
