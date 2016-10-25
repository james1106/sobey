package com.magic.sangha.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.magic.sangha.bean.OrderDecribeUser;
import com.magic.sangha.mapper.IOrderDecribeUserMapper;
import com.magic.sangha.service.IOrderDecribeUserService;
import com.magic.sangha.util.RoleConstant;

/**
 *  用户追加描述 业务层接口  实现类
 * @author QimouXie
 *
 */
@Service
public class OrderDecribeUserServiceImpl implements IOrderDecribeUserService {
	
	@Resource
	private IOrderDecribeUserMapper orderDecribeUserMapper;
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public Integer addOrderDecribe(OrderDecribeUser decribe) {
		return orderDecribeUserMapper.addOrderDecribe(decribe);
	}

	@Override
	public List<OrderDecribeUser> findByOrderId(Integer orderId) {
		
		
		List<OrderDecribeUser> decribes = orderDecribeUserMapper.findByOrderId(orderId);
		
		for (OrderDecribeUser orderBean : decribes) {
			
			if(null != orderBean.getUserId()){
				orderBean.setFrom(RoleConstant.COMMON);
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
