package com.magic.sangha.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.magic.sangha.bean.PermissionBean;
import com.magic.sangha.mapper.IPermissionMapper;
import com.magic.sangha.service.IPermissionService;

@Service
public class PermissionServiceImpl implements IPermissionService {

	@Resource
	private IPermissionMapper permissionMapper;
	
	@Override
	public List<PermissionBean> findAll() {
		return permissionMapper.findAll();
	}

	@Override
	public List<PermissionBean> queryPowerByRole(Integer roleId) {
		return permissionMapper.queryPowerByRole(roleId);
	}

}
