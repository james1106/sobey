package com.magic.sangha.service;

import java.util.List;

import com.magic.sangha.bean.PermissionBean;

/**Ȩ�� ҵ���ӿ�*/
public interface IPermissionService {

	List<PermissionBean> findAll() ;
	
	List<PermissionBean> queryPowerByRole(Integer roleId);
}
