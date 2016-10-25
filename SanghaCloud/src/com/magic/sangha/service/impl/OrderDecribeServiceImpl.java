package com.magic.sangha.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.magic.sangha.bean.OrderBean;
import com.magic.sangha.bean.OrderDecribeBean;
import com.magic.sangha.mapper.IOrderDecribeMapper;
import com.magic.sangha.mapper.IOrderMapper;
import com.magic.sangha.service.IOrderDecribeService;
import com.magic.sangha.util.RoleConstant;

@Service
public class OrderDecribeServiceImpl implements IOrderDecribeService {
	
	@Resource
	private IOrderDecribeMapper orderDecribeMapper;
	
	@Resource
	private IOrderMapper orderMapper;

	private Logger log = Logger.getLogger(this.getClass());
	@Override
	public Integer addOrderDecribe(OrderDecribeBean decribe) {
		return orderDecribeMapper.addOrderDecribe(decribe);
	}

	@Override
	public List<OrderDecribeBean> findByOrderId(Integer orderId,OrderBean order) {
		List<OrderDecribeBean> decribes = orderDecribeMapper.findByOrderId(orderId);
		OrderDecribeBean decribe = new OrderDecribeBean();
		decribe.setContent(order.getOrderDescri());
		decribe.setCreateTime(order.getCreateTime());
		decribe.setImages(order.getImages());
		decribe.setOrderId(orderId);
		decribe.setVideos(order.getVideos());
		decribe.setVoices(order.getVoices());
		if(null != order.getTscId()){
			decribe.setTSCId(order.getTscId());
		}
		decribe.setUserId(order.getUserId());
		decribes.add(0,decribe);
		for (OrderDecribeBean orderBean : decribes) {
			
			if(!decribe.equals(orderBean)){
				if( null == orderBean.getTSCId()){
					orderBean.setFrom(RoleConstant.COMMON);
					orderBean.setTo(RoleConstant.FILIALETECH);
				}else{
					orderBean.setFrom(RoleConstant.FILIALETECH);
					orderBean.setTo(RoleConstant.COMMON);
				}
			}
			try{
				if(orderBean.getImages() != null){
					JSONArray js = JSONArray.fromObject(orderBean.getImages());
					orderBean.setJsImages(js);
				}
				if(orderBean.getVideos() != null){
					JSONArray js = JSONArray.fromObject(orderBean.getVideos());
					orderBean.setJsVideos(js);
				}
				if(orderBean.getVoices() != null){
					JSONArray js = JSONArray.fromObject(orderBean.getVoices());
					orderBean.setJsVoices(js);
				}
				} catch (Exception e) {
					log.debug(e.getMessage(),e);
					continue;
				}
		}
		return decribes;
	}

}
