package com.magic.sangha.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.magic.sangha.bean.OrderBean;
import com.magic.sangha.bean.TSCHeadTechDecribeBean;
import com.magic.sangha.mapper.ITSCHeadTechDecribeMapper;
import com.magic.sangha.service.ITSCHeadTechDecribeService;
import com.magic.sangha.util.RoleConstant;

/**
 *  分公司技术 和 总部技术 沟通记录 业务层接口实现类
 * @author QimouXie
 *
 */
@Service
public class TSCHeadTechDecribeServiceImpl implements
		ITSCHeadTechDecribeService {
	
	@Resource
	private ITSCHeadTechDecribeMapper tSCHeadTechDecribeMapper;
	private Logger log = Logger.getLogger(this.getClass());
	@Override
	public Integer addOrderDecribe(TSCHeadTechDecribeBean decribe) {
		// TODO Auto-generated method stub
		return tSCHeadTechDecribeMapper.addDecribe(decribe);
	}

	@Override
	public List<TSCHeadTechDecribeBean> findByOrderId(Integer orderId,OrderBean order) {

		List<TSCHeadTechDecribeBean> decribess = tSCHeadTechDecribeMapper.findByOrderId(orderId);
		TSCHeadTechDecribeBean decribe = new TSCHeadTechDecribeBean();
		decribe.setContent(order.getOrderDescri());
		decribe.setCreateTime(order.getCreateTime());
		decribe.setImages(order.getImages());
		decribe.setOrderId(orderId);
		decribe.setVideos(order.getVideos());
		decribe.setVoices(order.getVoices());
		if(null != order.getHeadTechId()){
			decribe.setHeadTechId(order.getHeadTechId());
		}
		if(null != order.getTscId()){
			decribe.setTSCId(order.getTscId());
		}
		decribess.add(0,decribe);
		for (TSCHeadTechDecribeBean orderBean : decribess) {
			// 排除第一条，第一条是订单信息
			if(!decribe.equals(orderBean)){
				if(null == orderBean.getHeadTechId()){
					orderBean.setFrom(RoleConstant.FILIALETECH);
					orderBean.setTo(RoleConstant.HEADCOMTECH);
				}else{
					orderBean.setTo(RoleConstant.FILIALETECH);
					orderBean.setFrom(RoleConstant.HEADCOMTECH);
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
		return decribess;
	}

}
