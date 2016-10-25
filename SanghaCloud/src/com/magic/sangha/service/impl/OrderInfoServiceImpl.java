package com.magic.sangha.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.OrderInfoBean;
import com.magic.sangha.mapper.IOrderInfoMapper;
import com.magic.sangha.service.IOrderInfoService;

/**
 *  ����������Ϣ ҵ���ӿ�ʵ��
 * @author QimouXie
 *
 */
@Service
public class OrderInfoServiceImpl implements IOrderInfoService {

	@Resource
	private IOrderInfoMapper orderInfoMapper;
	@Override
	public void batchAddOrderInfo(List<OrderInfoBean> infos) throws Exception {
		if(null == infos || infos.size() == 0){
			return;
		}
		orderInfoMapper.batchAddOrderInfo(infos);
	}

	@Override
	public CutPageBean<OrderInfoBean> quertPage(Integer userId,Integer groupUserId, Integer pageNO, Integer pageSize) {
		List<OrderInfoBean> dataList = orderInfoMapper.queryInfoList(userId, groupUserId, (pageNO - 1)*pageSize, pageSize);
		Integer count = orderInfoMapper.countInfo(userId, groupUserId);
		Integer totalPage = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
		CutPageBean<OrderInfoBean> page = new CutPageBean<OrderInfoBean>(dataList, count, totalPage);
		return page;
	}

}
