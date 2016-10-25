package com.magic.sangha.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.magic.sangha.bean.BonusBean;
import com.magic.sangha.bean.CommentBean;
import com.magic.sangha.bean.CommentBonusBean;
import com.magic.sangha.bean.CommentCountBean;
import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.GroupUser;
import com.magic.sangha.bean.LableBean;
import com.magic.sangha.bean.UserBean;
import com.magic.sangha.mapper.IBonusMaper;
import com.magic.sangha.mapper.ICommentBonusMapper;
import com.magic.sangha.mapper.ICommentMapper;
import com.magic.sangha.mapper.ILableMapper;
import com.magic.sangha.service.ICommentService;
import com.magic.sangha.util.RoleConstant;
import com.magic.sangha.util.StatusConstant;

/**
 * 订单评价 业务层接口实现
 * 
 * @author QimouXie
 *
 */
@Service
public class CommentServiceImpl implements ICommentService {

	@Resource
	private ICommentMapper commentMapper;
	@Resource
	private IBonusMaper bounusMapper;
	@Resource
	private ICommentBonusMapper commentBonusMapper;
	@Resource
	private ILableMapper lableMapper;
	
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public Integer addComment(CommentBean comment,Object obj) throws Exception {
		commentMapper.addComment(comment);
		BonusBean  bonus = bounusMapper.findByType(StatusConstant.BONUS_TYPE_COMMENT);
		CommentBonusBean commentBonus = new CommentBonusBean();
		commentBonus.setBonus(bonus.getBonus());
		commentBonus.setCommentId(comment.getId());
		if(obj instanceof UserBean){
			UserBean user = (UserBean)obj;
			commentBonus.setUserId(user.getId());
		}else if(obj instanceof GroupUser){
			GroupUser user = (GroupUser)obj;
			commentBonus.setGroupUserId(user.getId());
		}
		commentBonusMapper.add(commentBonus);
		return comment.getId();
	}

	@Override
	public CommentBean findByOrderIdAndType(Integer roleType, Integer orderId,Integer type) {
		CommentBean com = commentMapper.findByOrderIdAndType(roleType, orderId,type);
		if(null == com){
			return null;
		}
		if (null != com.getServiceData()) {
			JSONObject jsObj = dataToJson(com.getServiceData());
			com.setServiceData(jsObj.toString());
			com.setJsServiceData(jsObj);
		}
		if (null != com.getHeadDevelopData()) {
			JSONObject jsObj = dataToJson(com.getHeadDevelopData());
			com.setHeadDevelopData(jsObj.toString());
			com.setJsHeadDevelopData(jsObj);
		}
		if (null != com.getHeadTechData()) {
			JSONObject jsObj = dataToJson(com.getHeadTechData());
			com.setHeadTechData(jsObj.toString());
			com.setJsHeadTechData(jsObj);
		}
		if (null != com.getTSCData()) {
			JSONObject jsObj = dataToJson(com.getTSCData());
			com.setTSCData(jsObj.toString());
			com.setJsTSCData(jsObj);
		}
		return com;
	}

	public JSONObject dataToJson(String data) {
		JSONObject jsObj = null;
		try {
			jsObj = JSONObject.fromObject(data);
			JSONArray jsArray = JSONArray.fromObject(jsObj.get("commentLableIds"));
			List<Integer> ids = new ArrayList<Integer>();
			for (Object obj : jsArray) {
				Integer id = (Integer) obj;
				ids.add(id);
			}
			List<LableBean> lables  = new ArrayList<LableBean>();
			if(ids.size() != 0){
				 lables = lableMapper.batchSelect(ids);
			}
			jsObj.put("lables", lables);
		} catch (Exception e) {
			log.debug(e.getMessage(), e);
		}
		return jsObj;
	}

	@Override
	public List<CommentCountBean> queryCommentCount(Integer companyId, Integer officeId,Integer roleId,Integer groupUserId,String mobile,String userName) {
		
		List<CommentCountBean> countList = new ArrayList<CommentCountBean>();
		
		if(null == companyId && null == officeId && null == roleId && null == groupUserId && null == mobile ){
			// 查询全部
			return countList;
		}else if(null != roleId && null != groupUserId && null != mobile && null != userName){
			// 查询员工
			if(!RoleConstant.CUSTOMER.equals(roleId) && !RoleConstant.FILIALETECH.equals(roleId) && !RoleConstant.HEADCOMTECH.equals(roleId) && !RoleConstant.INVENT.equals(roleId)){
				return countList;
			}
			List<CommentBean> comments = commentMapper.queryCountByGroupUser(roleId, groupUserId);
			
			for (CommentBean comment : comments) {
				CommentCountBean countConment = new CommentCountBean();
				
				countConment.setUserName(userName);
				countConment.setGroupUserId(groupUserId);
				countConment.setOrderId(comment.getOrderId());
				if(RoleConstant.CUSTOMER == roleId){
					// 客服
					if(null == comment.getServiceData()){
						continue;
					}
					Map<String,Object> serviceMap = analyticJSONObject(comment.getServiceData());
					countConment.setServiceServerAttitudeCount((Integer)(serviceMap.get("serviceAttitude")));
					countConment.setServiceDisposeSpeedCount((Integer)(serviceMap.get("disposeSpeed")));
//					analyticLablesJSON((String)(serviceMap.get("commentLableIds")),lables)
//					analysisJSONObjectData(comment.getServiceData(),roleId,countConment);
				}else if(RoleConstant.FILIALETECH == roleId){
					// 分公司技术
					if(null == comment.getTSCData()){
						continue;
					}
					Map<String,Object> tscMap = analyticJSONObject(comment.getTSCData());
					countConment.setTscServerAttitudeCount((Integer)(tscMap.get("serviceAttitude")));
					countConment.setTscDisposeSpeedCount((Integer)(tscMap.get("disposeSpeed")));
//					analysisJSONObjectData(comment.getTSCData(),roleId,countConment);
				}else if(RoleConstant.HEADCOMTECH == roleId){
					// 总部技术
					if(null == comment.getHeadTechData()){
						continue;
					}
					Map<String,Object> headTechMap = analyticJSONObject(comment.getHeadTechData());
					countConment.setHeadTechServerAttitudeCount((Integer)(headTechMap.get("serviceAttitude")));
					countConment.setHeadTechDisposeSpeedCount((Integer)(headTechMap.get("disposeSpeed")));
//					analysisJSONObjectData(comment.getHeadTechData(),roleId,countConment);
				}else if(RoleConstant.INVENT == roleId){
					// 研发
					if(null == comment.getHeadDevelopData()){
						continue;
					}
					Map<String,Object> developMap = analyticJSONObject(comment.getHeadTechData());
					countConment.setDevelopServerAttitudeCount((Integer)(developMap.get("serviceAttitude")));
					countConment.setDevelopDisposeSpeedCount((Integer)(developMap.get("disposeSpeed")));
//					analysisJSONObjectData(comment.getHeadDevelopData(),roleId,countConment);
				}else{
					continue;
				}
				countList.add(countConment);
			}
			
		}else if(null != officeId && null == mobile && null == roleId &&  null == groupUserId){
			// 查询办事处
			List<CommentBean> comments = commentMapper.queryCountByOfficeId(officeId);
			for (CommentBean comment : comments) {
				if(null == comment.getTSCData()){
					continue;
				}
				CommentCountBean countConment = new CommentCountBean();
				countConment.setOrderId(comment.getOrderId());
				
				Map<String,Object> tscMap = analyticJSONObject(comment.getTSCData());
				countConment.setTscServerAttitudeCount((Integer)(tscMap.get("serviceAttitude")));
				countConment.setTscDisposeSpeedCount((Integer)(tscMap.get("disposeSpeed")));
				
//				analysisJSONObjectData(comment.getTSCData(),roleId,countConment);
				countList.add(countConment);
			}
		}else if(null != companyId && null == officeId && null == mobile){
			// 查询公司
			List<CommentBean> comments = commentMapper.queryCountByCompanyId(companyId);
			for (CommentBean comment : comments) {
				if(null == comment.getTSCData()){
					continue;
				}
				CommentCountBean countConment = new CommentCountBean();
				countConment.setOrderId(comment.getOrderId());
				
				Map<String,Object> tscMap = analyticJSONObject(comment.getTSCData());
				countConment.setTscServerAttitudeCount((Integer)(tscMap.get("serviceAttitude")));
				countConment.setTscDisposeSpeedCount((Integer)(tscMap.get("disposeSpeed")));
				
//				analysisJSONObjectData(comment.getTSCData(),roleId,countConment);
				countList.add(countConment);
			}
		}
		return countList;
	}
	
	
	private void analysisJSONObjectData(String jsonObject,Integer roleId,CommentCountBean commentCount){
		
		if(null == jsonObject){
			return;
		}
		try {
			JSONObject json = JSONObject.fromObject(jsonObject);
			commentCount.setDisposeSpeedCount(json.getInt("disposeSpeed"));
			commentCount.setServiceAttitudeCount(json.getInt("serviceAttitude"));
		} catch (Exception e) {
			log.debug(e.getMessage(),e);
			return;
		}
	}

	@Override
	public CutPageBean<CommentCountBean> queryDetailComment(Integer pageNO,Integer pageSize, Integer companyId, Integer officeId,Integer roleId, Integer groupUserId) {
		
		List<CommentCountBean> dataList = new ArrayList<CommentCountBean>();
		Integer count = 0;
		List<LableBean> lables = lableMapper.findAll(null);
		if(null == companyId && null == officeId && null == roleId && null == groupUserId){
			// 查询全部
		}else if(null != groupUserId && null != roleId){
			
			
			// 员工查询
			if(!RoleConstant.CUSTOMER.equals(roleId) && !RoleConstant.FILIALETECH.equals(roleId) && !RoleConstant.HEADCOMTECH.equals(roleId) && !RoleConstant.INVENT.equals(roleId)){
				CutPageBean<CommentCountBean> page = new CutPageBean<CommentCountBean>(dataList, count, 1);
				return page;
			}
			 List<CommentBean> comments = commentMapper.queryPageCountByGroupUser(roleId, groupUserId, (pageNO - 1) * pageSize, pageSize);
			 count = commentMapper.countPageCountByGroupUser(roleId, groupUserId);
			 dataList = analysisJSONObjectData(comments,lables,roleId);
			 
			 
		}else if(null != officeId && null == groupUserId && null == roleId){
			// 办事处
			List<CommentBean> comments = commentMapper.queryPageCountByOfficeId(officeId, (pageNO - 1) * pageSize, pageSize);
			count = commentMapper.countPageCountByOfficeId(officeId);
			dataList = analysisJSONObjectData(comments,lables,roleId);
		}else if(null != companyId && null == officeId && null == groupUserId && null == roleId){
			// 分公司查询
			List<CommentBean> comments = commentMapper.queryPageCountByCompanyId(companyId, (pageNO - 1) * pageSize, pageSize);
			count = commentMapper.countPageCountByCompanyId(companyId);
			dataList = analysisJSONObjectData(comments,lables,roleId);
		}
		Integer totalPage = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
		CutPageBean<CommentCountBean> page = new CutPageBean<CommentCountBean>(dataList, count, totalPage);
		return page;
	}
	
	private List<CommentCountBean> analysisJSONObjectData(List<CommentBean> comments,List<LableBean> lables,Integer roleId){
		List<CommentCountBean> data = new ArrayList<CommentCountBean>();
		for (CommentBean comment : comments) {
			CommentCountBean commetCount = new CommentCountBean();
			commetCount.setOrderNumber(comment.getOrderNumber());
			commetCount.setContent(comment.getCommentContent());
			commetCount.setCreateDate(comment.getCreateTime());
			
			if(RoleConstant.CUSTOMER == roleId){
				// 客服
				if(null == comment.getServiceData()){
					continue;
				}
				analysisCountData(comment.getServiceData(),commetCount,lables);
			}else if(RoleConstant.FILIALETECH == roleId){
				// 分公司技术
				if(null == comment.getTSCData()){
					continue;
				}
				analysisCountData(comment.getTSCData(),commetCount,lables);
			}else if(RoleConstant.HEADCOMTECH == roleId){
				// 总部技术
				if(null == comment.getHeadTechData()){
					continue;
				}
				analysisCountData(comment.getHeadTechData(),commetCount,lables);
			}else if(RoleConstant.INVENT == roleId){
				// 研发
				if(null == comment.getHeadDevelopData()){
					continue;
				}
				analysisCountData(comment.getHeadDevelopData(),commetCount,lables);
			}else{
				continue;
			}
			
			data.add(commetCount);
		}
		return data;
	}

	
	private void analysisCountData(String jsonStr,CommentCountBean commetCount,List<LableBean> lables){
		if(null == jsonStr){
			return;
		}
		try {
			JSONObject json = JSONObject.fromObject(jsonStr);
			
			commetCount.setDisposeSpeedCount(json.getInt("disposeSpeed"));
			commetCount.setServiceAttitudeCount(json.getInt("serviceAttitude"));
			JSONArray lablesJSON = JSONArray.fromObject(json.getString("commentLableIds"));
			List<LableBean> dataLables = new ArrayList<LableBean>();
			for (Object obj : lablesJSON) {
				Integer lableId = (Integer)obj;
				for (LableBean lable : lables) {
					if(lableId.equals(lable.getId())){
						dataLables.add(lable);
						break;
					}
				}
			}
			commetCount.setLables(dataLables);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
	@Override
	public List<CommentCountBean> queryCountCommentByUser(Integer userId) {
		List<CommentCountBean> countList = new ArrayList<CommentCountBean>();
		List<CommentBean> comments = commentMapper.queryCountCommentByUser(userId);
		if(null == comments || comments.size() == 0){
			return countList;
		}
		for (CommentBean comment : comments) {
			CommentCountBean commentCountBean = new CommentCountBean();
			commentCountBean.setContent(comment.getCommentContent());
			commentCountBean.setUserName(comment.getUserName());
			commentCountBean.setUserId(userId);
			Map<String,Object> serviceMap = analyticJSONObject(comment.getServiceData());
			Map<String,Object> tscMap = analyticJSONObject(comment.getTSCData());
			commentCountBean.setServiceServerAttitudeCount((Integer)(serviceMap.get("serviceAttitude") == null ? 0 : serviceMap.get("serviceAttitude")));
			commentCountBean.setServiceDisposeSpeedCount((Integer)(serviceMap.get("disposeSpeed") == null ? 0 : serviceMap.get("disposeSpeed")));
			commentCountBean.setTscServerAttitudeCount((Integer)(tscMap.get("serviceAttitude") == null ?  0 : tscMap.get("serviceAttitude")));
			commentCountBean.setTscDisposeSpeedCount((Integer)(tscMap.get("disposeSpeed") == null ?  0 : tscMap.get("disposeSpeed")));
			commentCountBean.setProductCount((Integer)(tscMap.get("productComment") == null ? 0 :  tscMap.get("productComment")));
			countList.add(commentCountBean);
		}
		return countList;
	}
	
	private Map<String,Object> analyticJSONObject(String jsonStr){
		Map<String,Object> data = new HashMap<String,Object>();
		if(null == jsonStr){
			return data;
		}
		try {
			JSONObject json =  JSONObject.fromObject(jsonStr);
			if(null != json.get("disposeSpeed")){
				data.put("disposeSpeed", json.getInt("disposeSpeed"));
			}
			if(null != json.get("serviceAttitude")){
				data.put("serviceAttitude", json.getInt("serviceAttitude"));
			}
			if(null != json.get("productComment")){
				data.put("productComment", json.getInt("productComment"));
			}
			if(null != json.get("commentLableIds")){
				data.put("commentLableIds", json.getString("commentLableIds"));
			}
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			return data;
		}
		
	}

	@Override
	public CutPageBean<CommentCountBean> queryDetailUserComment(Integer pageNO,Integer pageSize, Integer userId) {
		List<CommentCountBean> dataList = new ArrayList<CommentCountBean>();
		Integer count = 0;
		List<LableBean> lables = lableMapper.findAll(null);
		List<CommentBean> comments = commentMapper.queryPageCountByUserId(userId, (pageNO - 1) * pageSize, pageSize);
		count = commentMapper.countByUserId(userId);
		dataList = analysisJSONObjectDetailUserComment(comments,lables);
		Integer totalPage = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
		CutPageBean<CommentCountBean> page = new CutPageBean<CommentCountBean>(dataList, count, totalPage);
		return page;
	}
	
	private List<CommentCountBean> analysisJSONObjectDetailUserComment(List<CommentBean> comments,List<LableBean> lables){
		List<CommentCountBean> data = new ArrayList<CommentCountBean>();
		for (CommentBean comment : comments) {
			CommentCountBean commentCountBean = new CommentCountBean();
			Map<String,Object> serviceMap = analyticJSONObject(comment.getServiceData());
			Map<String,Object> tscMap = analyticJSONObject(comment.getTSCData());
			commentCountBean.setServiceServerAttitudeCount((Integer)(serviceMap.get("serviceAttitude") == null ? 0 : serviceMap.get("serviceAttitude")));
			commentCountBean.setServiceDisposeSpeedCount((Integer)(serviceMap.get("disposeSpeed") == null ? 0 : serviceMap.get("disposeSpeed")));
			commentCountBean.setTscServerAttitudeCount((Integer)(tscMap.get("serviceAttitude") == null ?  0 : tscMap.get("serviceAttitude")));
			commentCountBean.setTscDisposeSpeedCount((Integer)(tscMap.get("disposeSpeed") == null ?  0 : tscMap.get("disposeSpeed")));
			commentCountBean.setProductCount((Integer)(tscMap.get("productComment") == null ? 0 :  tscMap.get("productComment")));
			commentCountBean.setOrderNumber(comment.getOrderNumber());
			// 标签处理
			List<LableBean> serviceLables = analyticLablesJSON((String)(serviceMap.get("commentLableIds")),lables);
			List<LableBean> tscLables = analyticLablesJSON((String)(tscMap.get("commentLableIds")),lables);
			commentCountBean.setServiceLables(serviceLables);
			commentCountBean.setTscLables(tscLables);
			data.add(commentCountBean);
		}
		return data;		
	}
	
	private List<LableBean> analyticLablesJSON(String jsonLables,List<LableBean> lables){
		
		JSONArray lablesJSON = JSONArray.fromObject(jsonLables);
		List<LableBean> serviceLables = new ArrayList<LableBean>();
		for (Object obj : lablesJSON) {
			Integer lableId = (Integer)obj;
			for (LableBean lable : lables) {
				if(lableId.equals(lable.getId())){
					serviceLables.add(lable);
					break;
				}
			}
		}
		return serviceLables;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
