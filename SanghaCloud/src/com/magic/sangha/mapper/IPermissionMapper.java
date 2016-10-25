package com.magic.sangha.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.PermissionBean;

public interface IPermissionMapper {

	PermissionBean findById(@Param("id")Integer id);
	
	List<PermissionBean> findAll();
	
	/***根据 角色查询该角色对应的权限*/
	List<PermissionBean> queryPowerByRole(@Param("roleId")Integer roleId);
}
