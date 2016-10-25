package com.magic.sangha.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.PermissionBean;

public interface IPermissionMapper {

	PermissionBean findById(@Param("id")Integer id);
	
	List<PermissionBean> findAll();
	
	/***���� ��ɫ��ѯ�ý�ɫ��Ӧ��Ȩ��*/
	List<PermissionBean> queryPowerByRole(@Param("roleId")Integer roleId);
}
