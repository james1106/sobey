package com.magic.sangha.mapper;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.NewsLikesBean;

/**
 *  文章点赞 持久层接口
 * @author QimouXie
 *
 */
public interface INewsLikesMapper {
	
	/**
	 *  添加点赞
	 * @param likes
	 */
	void addNewsLike(@Param("likes")NewsLikesBean likes);
	/**
	 *  查询当前用户是否点赞
	 * @param userId
	 * @param groupUserId
	 * @return
	 */
	NewsLikesBean findByUserId(@Param("userId")Integer userId,@Param("groupUserId")Integer groupUserId,@Param("newsId")Integer newsId);
	
	/**
	 *  取消点赞
	 * @param likes
	 */
	void delNewsLikes(@Param("likes")NewsLikesBean likes);
	
	Integer countLikes(@Param("newsId")Integer newsId);

}
