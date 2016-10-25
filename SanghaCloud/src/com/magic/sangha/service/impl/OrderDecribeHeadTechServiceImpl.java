package com.magic.sangha.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.magic.sangha.bean.OrderBean;
import com.magic.sangha.bean.OrderDecribeHeadTechBean;
import com.magic.sangha.mapper.IOrderDecribeHeadTechMapper;
import com.magic.sangha.service.IOrderDecribeHeadTechService;
import com.magic.sangha.util.RoleConstant;

/**
 *  订单直接分配给总公司技术 之间的描述 业务层接口实现类
 * @author QimouXie
 *
 */
@Service
public class OrderDecribeHeadTechServiceImpl implements IOrderDecribeHeadTechService {

	@Resource
	private IOrderDecribeHeadTechMapper orderDecribeHeadTechMapper;
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public Integer addDecribe(OrderDecribeHeadTechBean decribe) {
		return orderDecribeHeadTechMapper.addDecribe(decribe);
	}

	@Override
	public List<OrderDecribeHeadTechBean> findByOrderId(Integer orderId,OrderBean order) {
		List<OrderDecribeHeadTechBean> decribes = orderDecribeHeadTechMapper.findByOrderId(orderId);
		OrderDecribeHeadTechBean decribe = new OrderDecribeHeadTechBean();
		decribe.setContent(order.getOrderDescri());
		decribe.setCreateTime(order.getCreateTime());
		decribe.setImages(order.getImages());
		decribe.setOrderId(orderId);
		decribe.setVideos(order.getVideos());
		decribe.setVoices(order.getVoices());
		decribe.setHeadTeah(order.getHeadTechId());
		decribe.setUser(order.getUserId());
		decribes.add(0,decribe);
		for (OrderDecribeHeadTechBean orderBean : decribes) {
			if(!decribe.equals(orderBean)){
				if(null == orderBean.getUser()){
					orderBean.setFrom(RoleConstant.HEADCOMTECH);
					orderBean.setTo(RoleConstant.COMMON);
				}else{
					orderBean.setTo(RoleConstant.HEADCOMTECH);
					orderBean.setFrom(RoleConstant.COMMON);
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
