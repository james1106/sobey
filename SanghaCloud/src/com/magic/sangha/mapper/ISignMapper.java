package com.magic.sangha.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.SignBean;

/***
 *  签到 持久层接口
 * @author QimouXie
 *
 */
public interface ISignMapper {
	
	/**
	 *  签到
	 * @param sign
	 * @return
	 */
	Integer addSign(@Param("sign")SignBean sign);
	/**
	 *  查询 指定日期是否签到
	 * @param userId
	 * @return
	 */
	SignBean findSignByUserId(@Param("userId")Integer userId,@Param("yyMMdd")String yyMMdd,@Param("groupUserId")Integer groupUserId);
	
	/**
	 *  分页查询签到记录
	 * @param limit
	 * @param limitSize
	 * @param userId
	 * @return
	 */
	List<SignBean> findByUserId(@Param("limit")Integer limit,@Param("limitSize")Integer limitSize,@Param("userId")Integer userId,@Param("groupUserId")Integer groupUserId);
	/**
	 *  统计用户签到记录的总数
	 * @param userId
	 * @return
	 */
	Integer countSignByUserId(@Param("userId")Integer userId,@Param("groupUserId")Integer groupUserId);
	/**
	 *  查询计算用户的总积分
	 * @param userId
	 * @return
	 */
	Integer sumSignByUserId(@Param("userId")Integer userId,@Param("groupUserId")Integer groupUserId);

}
