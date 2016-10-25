package com.magic.sangha.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.magic.sangha.mapper.ICommentBonusMapper;
import com.magic.sangha.mapper.ISignMapper;
import com.magic.sangha.service.IBonusService;

/**
 *  积分管理 业务层接口实现
 * @author QimouXie
 *
 */
@Service
public class BonusServiceImpl implements IBonusService {

	@Resource
	private ICommentBonusMapper commentBonusMapper;
	@Resource
	private ISignMapper signMapper;
	
	@Override
	public Integer countUserBonus(Integer userId, Integer groupUserId) {
		Integer commentBonus = commentBonusMapper.countByUser(userId, groupUserId);
		Integer signBonus = signMapper.sumSignByUserId(userId, groupUserId);
		if(null ==commentBonus ){
			commentBonus = 0;
		}
		if(null == signBonus){
			signBonus = 0;
		}
		return commentBonus + signBonus;
	}

}
