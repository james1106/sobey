package com.magic.sangha.service;

import java.util.List;

import com.magic.sangha.bean.RelationTVBean;


public interface IRelationTVService {
	
	void addBatchRelationTV(List<Integer> tvIds,Integer groupUserId) throws Exception;
	
	/***/
	List<RelationTVBean> queryByUserId(Integer userId);
	
	void delDataByUser(Integer userId);

}
