package com.magic.sangha.service;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.ProductFeelBean;

public interface IProductFeelService {

	Integer add(ProductFeelBean feel);

	Integer queryCheckIsFeel(Integer userId);
	
	CutPageBean<ProductFeelBean> quertPage(Integer pageNO,Integer pageSize);

}
