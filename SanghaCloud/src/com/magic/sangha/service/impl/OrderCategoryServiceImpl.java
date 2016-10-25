package com.magic.sangha.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.OrderCategoryBean;
import com.magic.sangha.mapper.IOrderCategoryMapper;
import com.magic.sangha.service.IOrderCategoryService;

@Service
public class OrderCategoryServiceImpl implements IOrderCategoryService {
	
	@Resource
	private IOrderCategoryMapper orderCategoryMapper;

	@Override
	public Integer addCategory(OrderCategoryBean category) {
		// TODO Auto-generated method stub
		return orderCategoryMapper.addCategory(category);
	}

	@Override
	public List<OrderCategoryBean> findByType(Integer type) {
		return orderCategoryMapper.findByType(type);
	}

	@Override
	public List<OrderCategoryBean> findAll() {
		return orderCategoryMapper.findAll();
	}

	@Override
	public CutPageBean<OrderCategoryBean> queryPage(Integer pageNO,Integer pageSize, Integer type) {
		
		List<OrderCategoryBean> dataList = orderCategoryMapper.findByTypePage((pageNO - 1) * pageSize, pageSize, type);
		Integer count = orderCategoryMapper.countByType(type);
		Integer totalPage = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
		CutPageBean<OrderCategoryBean> page = new CutPageBean<OrderCategoryBean>(dataList, count, totalPage);
		return page;
	}

	@Override
	public void updateCategory(OrderCategoryBean category) {
		orderCategoryMapper.updateCategory(category);
	}

	@Override
	public Integer batchAdd(List<OrderCategoryBean> cateList) {
		return orderCategoryMapper.batchAdd(cateList);
	}

}
