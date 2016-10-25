package com.magic.sangha.service;

import java.util.List;

import com.magic.sangha.bean.VoteUserBean;

/**
 *  �û�ͶƱ ҵ���ӿ�
 * @author QimouXie
 *
 */
public interface IVoteUserService {
	
	/**
	 *  �û�ͶƱ
	 * @param voteUser
	 */
	void addVoteUser(VoteUserBean voteUser);
	
	/**
	 *  �����û��Ƿ�ͶƱ
	 * @param voteUser
	 * @param newsId
	 * @return
	 */
	VoteUserBean queryUserIsVote(VoteUserBean voteUser,Integer newsId);
	
	List<VoteUserBean> countVoteByNewsId(Integer newsId);

}
