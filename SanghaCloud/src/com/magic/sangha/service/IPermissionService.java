package com.magic.sangha.service;

import java.util.List;

import com.magic.sangha.bean.PermissionBean;

/**权限 业务层接口*/
public interface IPermissionService {

	List<PermissionBean> findAll() ;
	
	List<PermissionBean> queryPowerByRole(Integer roleId);
}
