package com.magic.sangha.service;


import java.util.List;

import com.magic.sangha.bean.RoleBean;

public interface IRoleService {

	RoleBean findById(Integer id);
	
	List<RoleBean> findAll();
	
}
