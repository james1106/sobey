package com.magic.sangha.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.magic.sangha.bean.ComplainBean;
import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.mapper.IComplainMapper;
import com.magic.sangha.service.IComplainService;

/**
 *  投诉 业务层接口实现类
 * @author QimouXie
 *
 */
@Service
public class ComplainServiceImpl implements IComplainService {
	
	@Resource
	private IComplainMapper complainMapper;
	
	@Override
	public Integer addComplain(ComplainBean com) {
		return complainMapper.addComplain(com);
	}

	@Override
	public ComplainBean findByOrderId(Integer orderId) {
		return complainMapper.findByOrderId(orderId);
	}

	@Override
	public CutPageBean<ComplainBean> queryPage(Integer pageNO,Integer pageSize, Date startTime, Date endTime) {
		List<ComplainBean> dataList = complainMapper.queryComplainPage(startTime, endTime, (pageNO -1) * pageSize, pageSize);
		Integer count = complainMapper.countComplainByDate(startTime, endTime);
		Integer totalPage = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
		CutPageBean<ComplainBean> page = new CutPageBean<ComplainBean>(dataList, count, totalPage);
		return page;
	}

}
