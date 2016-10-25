package com.magic.sangha.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.VoteUserBean;

/**
 *  投票 用户 中间表  持久层接口
 * @author QimouXie
 *
 */
public interface IVoteUserMapper {

	/**
	 *  添加投票记录
	 * @param voteUser
	 */
	void addVoteUser(@Param("voteUser")VoteUserBean voteUser);
	
	/**
	 *  查询 验证 根据新闻ID 查询验证 用户有没有对该新闻下的投诉 是否投诉过
	 * @param newsId
	 * @param userId
	 * @param groupUserId
	 * @return
	 */
	VoteUserBean findVoteByUserId(@Param("newsId")Integer newsId,@Param("userId")Integer userId,@Param("groupUserId")Integer groupUserId);
	
	/**
	 *  统计 该新闻下的投票 和 投票数量
	 * @param newsId
	 * @return
	 */
	List<VoteUserBean> countVoteByNewsId(@Param("newsId")Integer newsId);
	
}
