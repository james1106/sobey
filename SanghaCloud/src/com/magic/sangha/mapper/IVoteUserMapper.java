package com.magic.sangha.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.VoteUserBean;

/**
 *  ͶƱ �û� �м��  �־ò�ӿ�
 * @author QimouXie
 *
 */
public interface IVoteUserMapper {

	/**
	 *  ���ͶƱ��¼
	 * @param voteUser
	 */
	void addVoteUser(@Param("voteUser")VoteUserBean voteUser);
	
	/**
	 *  ��ѯ ��֤ ��������ID ��ѯ��֤ �û���û�жԸ������µ�Ͷ�� �Ƿ�Ͷ�߹�
	 * @param newsId
	 * @param userId
	 * @param groupUserId
	 * @return
	 */
	VoteUserBean findVoteByUserId(@Param("newsId")Integer newsId,@Param("userId")Integer userId,@Param("groupUserId")Integer groupUserId);
	
	/**
	 *  ͳ�� �������µ�ͶƱ �� ͶƱ����
	 * @param newsId
	 * @return
	 */
	List<VoteUserBean> countVoteByNewsId(@Param("newsId")Integer newsId);
	
}
