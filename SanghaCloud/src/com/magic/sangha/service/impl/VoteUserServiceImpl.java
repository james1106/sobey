package com.magic.sangha.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.magic.sangha.bean.VoteUserBean;
import com.magic.sangha.mapper.IVoteUserMapper;
import com.magic.sangha.service.IVoteUserService;

/**
 *  �û�ͶƱ ҵ���ӿ�
 * @author QimouXie
 *
 */
@Service
public class VoteUserServiceImpl implements IVoteUserService {

	@Resource
	private IVoteUserMapper voteUserMapper;
	
	
	@Override
	public void addVoteUser(VoteUserBean voteUser) {
		voteUserMapper.addVoteUser(voteUser);
	}

	@Override
	public VoteUserBean queryUserIsVote(VoteUserBean voteUser, Integer newsId) {
		return voteUserMapper.findVoteByUserId(newsId, voteUser.getUserId(), voteUser.getGroupUserId());
	}

	@Override
	public List<VoteUserBean> countVoteByNewsId(Integer newsId) {
		return voteUserMapper.countVoteByNewsId(newsId);
	}

}
