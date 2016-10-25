package com.magic.sangha.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.SignBean;

/***
 *  ǩ�� �־ò�ӿ�
 * @author QimouXie
 *
 */
public interface ISignMapper {
	
	/**
	 *  ǩ��
	 * @param sign
	 * @return
	 */
	Integer addSign(@Param("sign")SignBean sign);
	/**
	 *  ��ѯ ָ�������Ƿ�ǩ��
	 * @param userId
	 * @return
	 */
	SignBean findSignByUserId(@Param("userId")Integer userId,@Param("yyMMdd")String yyMMdd,@Param("groupUserId")Integer groupUserId);
	
	/**
	 *  ��ҳ��ѯǩ����¼
	 * @param limit
	 * @param limitSize
	 * @param userId
	 * @return
	 */
	List<SignBean> findByUserId(@Param("limit")Integer limit,@Param("limitSize")Integer limitSize,@Param("userId")Integer userId,@Param("groupUserId")Integer groupUserId);
	/**
	 *  ͳ���û�ǩ����¼������
	 * @param userId
	 * @return
	 */
	Integer countSignByUserId(@Param("userId")Integer userId,@Param("groupUserId")Integer groupUserId);
	/**
	 *  ��ѯ�����û����ܻ���
	 * @param userId
	 * @return
	 */
	Integer sumSignByUserId(@Param("userId")Integer userId,@Param("groupUserId")Integer groupUserId);

}
