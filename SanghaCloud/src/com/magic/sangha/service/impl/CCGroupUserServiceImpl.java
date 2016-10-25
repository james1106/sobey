package com.magic.sangha.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.magic.sangha.bean.CCGroupUserBean;
import com.magic.sangha.mapper.ICCGroupUserMapper;
import com.magic.sangha.service.ICCGroupUserService;
/**
 *  抄送 业务层接口
 * @author QimouXie
 *
 */
@Service
public class CCGroupUserServiceImpl implements ICCGroupUserService {

	@Resource
	private ICCGroupUserMapper cCGroupUserMapper;
	
	@Override
	public Integer batchAdd(List<CCGroupUserBean> ccs) {
		return cCGroupUserMapper.batchAdd(ccs);
	}

	@Override
	public List<CCGroupUserBean> findBygroupUserId(Integer userId) {
		return cCGroupUserMapper.findBygroupUserId(userId);
	}

	@Override
	public List<CCGroupUserBean> findByOrderId(Integer orderId) {
		return cCGroupUserMapper.findByOrderId(orderId);
	}

	@Override
	public Integer countOrders(Integer groupUserId) {
		return cCGroupUserMapper.countOrders(groupUserId);
	}

}
