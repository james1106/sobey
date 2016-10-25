package com.magic.sangha.service;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.SignBean;

/**
 *  签到 业务层接口
 * @author QimouXie
 *
 */
public interface ISignService {
	/**
	 *  签到
	 * @param sign
	 * @return
	 */
	Integer addSign(SignBean sign);
	/**
	 *  查询计算积分
	 * @param userId
	 * @return
	 */
	Integer findSumSignByUserId(Integer userId,Integer groupUserId);
	/**
	 *  分页查询签到记录
	 * @param pageNO
	 * @param pageSize
	 * @param userId
	 * @return
	 */
	CutPageBean<SignBean> findSignByUser(Integer pageNO,Integer pageSize,Integer userId,Integer groupUserId);
	/**
	 *  查询用户 某天是否签到
	 * @param userId
	 * @return
	 */
	SignBean findSignByUserId(Integer userId,String yyMMdd,Integer groupUserId);
	/**
	 *  查询基础积分
	 * @param id
	 * @return
	 */
	Integer findBaseBonus(Integer id);
	/**
	 *  统计签到的天数
	 * @param userId
	 * @return
	 */
	Integer countSignByUserId(Integer userId,Integer groupUserId);
	
	

}
