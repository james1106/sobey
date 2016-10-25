package com.magic.sangha.service;

import java.util.List;

import com.magic.sangha.bean.VoteUserBean;

/**
 *  用户投票 业务层接口
 * @author QimouXie
 *
 */
public interface IVoteUserService {
	
	/**
	 *  用户投票
	 * @param voteUser
	 */
	void addVoteUser(VoteUserBean voteUser);
	
	/**
	 *  检查该用户是否投票
	 * @param voteUser
	 * @param newsId
	 * @return
	 */
	VoteUserBean queryUserIsVote(VoteUserBean voteUser,Integer newsId);
	
	List<VoteUserBean> countVoteByNewsId(Integer newsId);

}
