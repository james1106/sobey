package com.magic.sangha.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.magic.sangha.bean.GroupUserCategoryBean;
import com.magic.sangha.mapper.IGroupUserCategoryMapper;
import com.magic.sangha.service.IGroupUserCategoryService;

@Service
public class GroupUserCategoryServiceImpl implements IGroupUserCategoryService {
	
	@Resource
	private IGroupUserCategoryMapper groupUserCategoryMapper;

	@Override
	public Integer addGroupUserCategory(GroupUserCategoryBean gcb) {
		// TODO Auto-generated method stub
		return groupUserCategoryMapper.addGroupUserCategory(gcb);
	}

	@Override
	public List<GroupUserCategoryBean> findByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		return groupUserCategoryMapper.findByCategory(categoryId);
	}

	@Override
	public List<GroupUserCategoryBean> findAllByGroupUser() {
		// TODO Auto-generated method stub
		return groupUserCategoryMapper.findAllByGroupUser();
	}

	@Override
	public Integer delCategoryByGroupUserId(Integer groupUserId) throws Exception {
		return groupUserCategoryMapper.delCategoryByGroupUserId(groupUserId);
	}

	@Override
	public List<GroupUserCategoryBean> findCategoryAdmin(Integer groupUserId) {
		return groupUserCategoryMapper.findCategoryAdmin(groupUserId);
	}

	@Override
	public void updateGroupUserTech(List<GroupUserCategoryBean> categorys,Integer groupUserId) throws Exception {
		groupUserCategoryMapper.delCategoryByGroupUserId(groupUserId);
		groupUserCategoryMapper.batchAdd(categorys);
	}

}
