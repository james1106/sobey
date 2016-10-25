package com.magic.sangha.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.RoleBean;

public interface IRoleMapper {

	
	RoleBean findById(@Param("id")Integer id);
	
	
	List<RoleBean> findAll();
	
}
