package com.magic.sangha.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.RelationTVBean;
import com.magic.sangha.bean.TVBean;
import com.magic.sangha.mapper.IRelationTVMapper;
import com.magic.sangha.mapper.ITVMapper;
import com.magic.sangha.service.ITVService;
import com.magic.sangha.util.RoleConstant;

@Service
public class TVServiceImpl implements ITVService {
	
	@Resource
	private ITVMapper tvMapper;
	@Resource
	private IRelationTVMapper relationTVMapper;

	@Override
	public Integer addTV(TVBean tv) {
		return tvMapper.addTV(tv);
	}

	@Override
	public List<TVBean> findByOfficeId(Integer officeId) {
		return tvMapper.findByOfficeId(officeId);
	}

	@Override
	public TVBean findById(Integer id) {
		return tvMapper.findById(id);
	}


	@Override
	public List<TVBean> batchAddTV(List<TVBean> tvs) {
		
		List<TVBean> olds = tvMapper.findAll();
		List<TVBean> needAdds = new ArrayList<TVBean>();
		for (TVBean tvBean : tvs) {
			if(!olds.contains(tvBean)){
				needAdds.add(tvBean);
			}
		}
		if(needAdds.size() != 0){
			tvMapper.batchAddTV(needAdds);
		}
		return needAdds;
	}

	@Override
	public CutPageBean<TVBean> findByStatus(Integer pageNO, Integer pageSize,Integer officeStatus,String tvName) {
		
		List<TVBean> dataList = tvMapper.findTVByoffice((pageNO - 1) * pageSize, pageSize, officeStatus,tvName);
		Integer count = tvMapper.countTVByoffice(officeStatus,tvName);
		Integer totalPage = 0;
		if(count % pageSize == 0){
			totalPage = count / pageSize;
		}else{
			totalPage = count / pageSize + 1;
		}
		CutPageBean<TVBean> page = new CutPageBean<TVBean>(dataList, count, totalPage);
		return page;
	}

	@Override
	public Integer updateTVToOffice(Integer id, Integer officeId) {
		return tvMapper.updateOffice(id, officeId);
	}

	@Override
	public List<TVBean> findTVSByOffice(Integer officeId) {
		return tvMapper.findByOfficeId(officeId);
	}

	@Override
	public List<TVBean> findByUserIdAndOrder(Integer userId, Integer groupUserId,Integer roleId) {
		
		List<TVBean> tvs = null;
		
		if(null != userId){
			tvs = new ArrayList<TVBean>();
			TVBean tv = tvMapper.findByUserId(userId);
			tvs.add(tv);
		}
		if(null != groupUserId){
			tvs = tvMapper.findByGroupUser(groupUserId, roleId);
			if(null == tvs || tvs.size() == 0){
				List<RelationTVBean> relations = relationTVMapper.findByUserId(groupUserId);
				for (RelationTVBean relation : relations) {
					tvs.add(relation.getTv());
				}
			}
		}
		
		return tvs;
	}

	@Override
	public List<TVBean> dynamicGetTvs(String tvName) {
		
		return tvMapper.dynamicGetTvs(tvName);
	}

	@Override
	public Integer queryTVByTVName(String tvName) {
		// TODO Auto-generated method stub
		return tvMapper.queryTVByTVName(tvName);
	}

	@Override
	public List<TVBean> getTVByUserType(Integer roleId,Integer userId) {
		// 总部 角色组
		Integer[] headRoles = new Integer[] { RoleConstant.MANAGER,RoleConstant.CUSTOMER, RoleConstant.SALE,RoleConstant.OPERATION, RoleConstant.INVENT,RoleConstant.LEADER, RoleConstant.HEADCOMTECH };
		// 分公司 角色组
		Integer[] branchRoles = new Integer[] { RoleConstant.SALE,RoleConstant.FILIALE_HEADER, RoleConstant.FILIALE_TECH_HEADER,RoleConstant.FILIALE_SALE_HEADER };
		// 办事处 角色组
		Integer[] officeRoles = new Integer[] { RoleConstant.FILIALETECH,RoleConstant.SALE };
		Integer userType = null;
		for (int i = 0; i < headRoles.length; i++) {
			if(roleId == headRoles[i]){
				userType = 0;
				break;
			}
		}
		if(null == userType){
			for (int i = 0; i < branchRoles.length; i++) {
				if(roleId == branchRoles[i]){
					userType = 1;
					break;
				}
			}
		}
		if(null == userType){
			for (int i = 0; i < officeRoles.length; i++) {
				if(roleId == officeRoles[i]){
					userType = 2;
					break;
				}
			}
		}
		return tvMapper.getTVByUserType(userType, userId);
	}

	@Override
	public Integer updateTV(TVBean tv) {
		return tvMapper.updateTV(tv);
	}
}
