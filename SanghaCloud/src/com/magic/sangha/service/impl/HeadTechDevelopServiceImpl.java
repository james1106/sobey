package com.magic.sangha.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.HeadTechDevelopBean;
import com.magic.sangha.bean.OrderBean;
import com.magic.sangha.mapper.IHeadTechDevelopMapper;
import com.magic.sangha.service.IHeadTechDevelopService;
import com.magic.sangha.util.RoleConstant;

/**
 *  总部技术和总部研发 补丁描述  业务层接口实现类
 * @author QimouXie
 *
 */
@Service
public class HeadTechDevelopServiceImpl implements IHeadTechDevelopService {

	@Resource
	private IHeadTechDevelopMapper headTechDevelopMapper;
	private Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public Integer addOrderDecribe(HeadTechDevelopBean decribe) {
		// TODO Auto-generated method stub
		return headTechDevelopMapper.addDecribe(decribe);
	}

	@Override
	public List<HeadTechDevelopBean> findByOrderId(Integer orderId,OrderBean order) {
		
		List<HeadTechDevelopBean> decribess = headTechDevelopMapper.findByOrderId(orderId);
		HeadTechDevelopBean decribe = new HeadTechDevelopBean();
		decribe.setContent(order.getOrderDescri());
		decribe.setCreateTime(order.getCreateTime());
		decribe.setImages(order.getImages());
		decribe.setOrderId(orderId);
		decribe.setVideos(order.getVideos());
		decribe.setVoices(order.getVoices());
		if(null != order.getHeadTechId()){
			decribe.setHeadTechId(order.getHeadTechId());
		}
		if(null != order.getDecelopId()){
			decribe.setDevelopId(order.getDecelopId());
		}
		decribess.add(0,decribe);
		for (HeadTechDevelopBean orderBean : decribess) {
			// 排除第一条，第一条是订单信息
			if(!decribe.equals(orderBean)){
				if(null == orderBean.getDevelopId()){
					orderBean.setFrom(RoleConstant.HEADCOMTECH);
					orderBean.setTo(RoleConstant.INVENT);
				}else{
					orderBean.setTo(RoleConstant.HEADCOMTECH);
					orderBean.setFrom(RoleConstant.INVENT);
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
	
	@Override
	public Integer countIsFeedback(Integer orderId, Integer type) {
		return headTechDevelopMapper.countIsFeedback(orderId, type);
	}
	
	@Override
	public CutPageBean<HeadTechDevelopBean> queryPage(Integer pageNO, Integer pageSize, String realName, Date startTime, Date endTime) {
		List<HeadTechDevelopBean> dataList = headTechDevelopMapper.queryPage(pageSize, (pageNO - 1) * pageSize, realName, startTime, endTime);
		Integer count = headTechDevelopMapper.countPage(realName, startTime, endTime);
		Integer totalPage = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
		CutPageBean<HeadTechDevelopBean> page = new CutPageBean<HeadTechDevelopBean>(dataList, count, totalPage);
		return page;
	}
	
	@Override
	public CutPageBean<HeadTechDevelopBean> queryPageByDevelop(Integer developId,Integer pageNO,Integer pageSize, String orderNumber, Date startTime, Date endTime) {
		List<HeadTechDevelopBean> dataList = headTechDevelopMapper.queryPageByDevelopId(developId, orderNumber, (pageNO - 1) * pageSize, pageSize, startTime, endTime);
		Integer count = headTechDevelopMapper.countPageByDevelopId(developId, orderNumber, startTime, endTime);
		Integer totalPage = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
		CutPageBean<HeadTechDevelopBean> page = new CutPageBean<HeadTechDevelopBean>(dataList, count, totalPage);
		return page;
	}

}
