package com.magic.sangha.service;

import java.util.List;



import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.OrderCategoryBean;

public interface IOrderCategoryService {
	
	Integer addCategory(OrderCategoryBean category);
	
	List<OrderCategoryBean> findByType(Integer type);

	List<OrderCategoryBean> findAll();
	
	CutPageBean<OrderCategoryBean> queryPage(Integer pageNO,Integer pageSize,Integer type);
	
	/**
	 *  更新 名称
	 * @param category
	 */
	void updateCategory(OrderCategoryBean category);
	
	/**批量新增*/
	Integer batchAdd(List<OrderCategoryBean> cateList);
}
