package com.magic.sangha.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.magic.sangha.bean.LableBean;
import com.magic.sangha.mapper.ILableMapper;
import com.magic.sangha.service.ILableService;

/**
 *  评价标签 管理 业务层实现类
 * @author QimouXie
 *
 */
@Service
public class LableServiceImpl implements ILableService {

	@Resource
	private ILableMapper lableMapper;
	
	@Override
	public Integer batchAdd(List<LableBean> lables) {
		return lableMapper.batchAdd(lables);
	}

	@Override
	public List<LableBean> findByRoleId(Integer roleId) {
		return lableMapper.findByRoleId(roleId);
	}

	@Override
	public void updateLables(List<Integer> ids, Integer type)  throws Exception {
		lableMapper.batchUpdateLables(ids, type);
		lableMapper.batchUpdateLables(ids, null);
	}

	@Override
	public List<LableBean> findAll(Integer roleId) {
		// TODO Auto-generated method stub
		return lableMapper.findAll(roleId);
	}
	@Override
	public void batchDel(List<Integer> ids) {
		if(null == ids || ids.size() == 0){
			return;
		}
		lableMapper.batchDel(ids);
	}

}
