package com.magic.sangha.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.SuggestBean;
import com.magic.sangha.mapper.ISuggestMapper;
import com.magic.sangha.service.ISuggestService;


@Service
public class SuggestServiceImpl implements ISuggestService {

	@Resource
	private ISuggestMapper suggestMapper;
	
	@Override
	public void add(SuggestBean suggest) {
		suggestMapper.add(suggest);
	}
	
	@Override
	public CutPageBean<SuggestBean> queryPage(Integer pageNO, Integer pageSize) {
		List<SuggestBean>  dataList =  suggestMapper.queryPage((pageNO - 1) * pageSize, pageSize);
		Integer count = suggestMapper.countPage();
		Integer totalPage = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
		CutPageBean<SuggestBean> page = new CutPageBean<SuggestBean>(dataList, count, totalPage);
		return page;
	}

}
