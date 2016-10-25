package com.magic.sangha.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.VoteBean;

/**
 *  投票 持久层
 * @author QimouXie
 *
 */
public interface IVoteMapper {
	/**
	 *  添加投票
	 * @param vote
	 * @return
	 */
	void batchAddVote(@Param("votes")List<VoteBean> votes);
	
	List<VoteBean> queryVotePage(@Param("limit")Integer limit,@Param("limitSize")Integer limitSize,@Param("title")String title);

	Integer countVotePage(@Param("title")String title);
	
	/**统计该新闻下 所有的投票列 和 投票总数*/
	List<VoteBean> queryVoteByNewsId(@Param("newsId")Integer newsId);
}
