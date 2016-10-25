package com.magic.sangha.mapper;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.CommentBonusBean;

public interface ICommentBonusMapper {

	
	Integer add(@Param("commentBonus")CommentBonusBean commentBonus);
	
	Integer countByUser(@Param("userId")Integer userId,@Param("groupUserId")Integer groupUserId);
}
