package com.magic.sangha.mapper;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.NewsLikesBean;

/**
 *  ���µ��� �־ò�ӿ�
 * @author QimouXie
 *
 */
public interface INewsLikesMapper {
	
	/**
	 *  ��ӵ���
	 * @param likes
	 */
	void addNewsLike(@Param("likes")NewsLikesBean likes);
	/**
	 *  ��ѯ��ǰ�û��Ƿ����
	 * @param userId
	 * @param groupUserId
	 * @return
	 */
	NewsLikesBean findByUserId(@Param("userId")Integer userId,@Param("groupUserId")Integer groupUserId,@Param("newsId")Integer newsId);
	
	/**
	 *  ȡ������
	 * @param likes
	 */
	void delNewsLikes(@Param("likes")NewsLikesBean likes);
	
	Integer countLikes(@Param("newsId")Integer newsId);

}
