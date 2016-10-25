package com.magic.sangha.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.magic.sangha.bean.BonusBean;
import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.SignBean;
import com.magic.sangha.mapper.IBonusMaper;
import com.magic.sangha.mapper.IGroupUserMapper;
import com.magic.sangha.mapper.ISignMapper;
import com.magic.sangha.service.ISignService;

/**
 *  签到 业务层接口实现
 * @author QimouXie
 *
 */
@Service
public class SignServiceImpl implements ISignService {

	@Resource
	private ISignMapper signMapper;
	
	@Resource
	private IBonusMaper bonusMapper;
	@Resource
	private IGroupUserMapper groupUserMapper;
	
	@Override
	public Integer addSign(SignBean sign) {
		return signMapper.addSign(sign);
	}

	@Override
	public Integer findSumSignByUserId(Integer userId,Integer groupUserId) {
		return signMapper.sumSignByUserId(userId,groupUserId);
	}

	@Override
	public CutPageBean<SignBean> findSignByUser(Integer pageNO,Integer pageSize, Integer userId,Integer groupUserId) {
		List<SignBean> dataList = signMapper.findByUserId((pageNO - 1) * pageSize, pageSize, userId,groupUserId);
		Integer count = signMapper.countSignByUserId(userId,groupUserId);
		Integer totalPage = count % pageSize == 0 ?  count / pageSize : count / pageSize +1;
		CutPageBean<SignBean> page =  new CutPageBean<SignBean>(dataList, count, totalPage);
		return page;
	}

	@Override
	public SignBean findSignByUserId(Integer userId, String yyMMdd,Integer groupUserId) {
		return signMapper.findSignByUserId(userId, yyMMdd,groupUserId);
	}

	@Override
	public Integer findBaseBonus(Integer type) {
		BonusBean bonus = bonusMapper.findByType(type);
		if(null == bonus){
			return 15;
		}
		return bonus.getBonus() ;
	}
	@Override
	public Integer countSignByUserId(Integer userId,Integer groupUserId) {
		return signMapper.countSignByUserId(userId,groupUserId);
	}

}
