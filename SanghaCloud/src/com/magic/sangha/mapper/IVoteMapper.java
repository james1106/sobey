package com.magic.sangha.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.VoteBean;

/**
 *  ͶƱ �־ò�
 * @author QimouXie
 *
 */
public interface IVoteMapper {
	/**
	 *  ���ͶƱ
	 * @param vote
	 * @return
	 */
	void batchAddVote(@Param("votes")List<VoteBean> votes);
	
	List<VoteBean> queryVotePage(@Param("limit")Integer limit,@Param("limitSize")Integer limitSize,@Param("title")String title);

	Integer countVotePage(@Param("title")String title);
	
	/**ͳ�Ƹ������� ���е�ͶƱ�� �� ͶƱ����*/
	List<VoteBean> queryVoteByNewsId(@Param("newsId")Integer newsId);
}
