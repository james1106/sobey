package com.magic.sangha.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.magic.sangha.bean.RoleBean;
import com.magic.sangha.mapper.IRoleMapper;
import com.magic.sangha.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {

	@Resource
	private IRoleMapper  roleMapper;
	@Override
	public RoleBean findById(Integer id) {
		return roleMapper.findById(id);
	}
	@Override
	public List<RoleBean> findAll() {
		return roleMapper.findAll();
	}

}
