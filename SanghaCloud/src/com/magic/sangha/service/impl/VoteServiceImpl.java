package com.magic.sangha.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.VoteBean;
import com.magic.sangha.mapper.IVoteMapper;
import com.magic.sangha.service.IVoteService;


@Service
public class VoteServiceImpl implements IVoteService {

	@Resource
	private IVoteMapper voteMapper;
	
	@Override
	public CutPageBean<VoteBean> queryPage(Integer pageNO, Integer pageSize,String title) {
		
		List<VoteBean> dataList = voteMapper.queryVotePage((pageNO -  1) * pageSize, pageSize, title);
		Integer count = voteMapper.countVotePage(title);
		Integer totalPage = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
		CutPageBean<VoteBean> page = new CutPageBean<VoteBean>(dataList, count, totalPage);
		return page;
	}
	@Override
	public List<VoteBean> queryVoteByNewsId(Integer newsId) {
		// TODO Auto-generated method stub
		return voteMapper.queryVoteByNewsId(newsId);
	}

}
