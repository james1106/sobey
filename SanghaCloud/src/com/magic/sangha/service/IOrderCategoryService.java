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
	 *  ���� ����
	 * @param category
	 */
	void updateCategory(OrderCategoryBean category);
	
	/**��������*/
	Integer batchAdd(List<OrderCategoryBean> cateList);
}
