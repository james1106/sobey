package com.magic.sangha.service;

public interface IBonusService {
	
	/**获取用户总积分*/
	Integer countUserBonus(Integer userId,Integer groupUserId);

}
