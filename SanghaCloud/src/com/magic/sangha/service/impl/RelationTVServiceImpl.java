package com.magic.sangha.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.magic.sangha.bean.RelationTVBean;
import com.magic.sangha.mapper.IRelationTVMapper;
import com.magic.sangha.service.IRelationTVService;

@Service
public class RelationTVServiceImpl implements IRelationTVService {

	@Resource
	private IRelationTVMapper relationTVMapper;
	
	@Override
	public void addBatchRelationTV(List<Integer> tvIds, Integer groupUserId) throws Exception {
		
		if(null == tvIds || tvIds.size() == 0 || null == groupUserId){
			return;
		}
		List<RelationTVBean> relations = new ArrayList<RelationTVBean>();
		for (Integer tvId : tvIds) {
			RelationTVBean relation = new RelationTVBean();
			relation.setGroupUserId(groupUserId);
			relation.setTvId(tvId);
			relations.add(relation);
		}
		if(relations.size() == 0){
			return;
		}
		relationTVMapper.del(groupUserId);
		relationTVMapper.addBatch(relations);
	}

	@Override
	public List<RelationTVBean> queryByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return relationTVMapper.queryByUserId(userId);
	}

	@Override
	public void delDataByUser(Integer userId) {
		relationTVMapper.del(userId);
	}

}
