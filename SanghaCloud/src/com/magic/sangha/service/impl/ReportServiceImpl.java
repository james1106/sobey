package com.magic.sangha.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.GroupUser;
import com.magic.sangha.bean.RelationTVBean;
import com.magic.sangha.bean.ReportBean;
import com.magic.sangha.bean.TVBean;
import com.magic.sangha.bean.UserBean;
import com.magic.sangha.mapper.IRelationTVMapper;
import com.magic.sangha.mapper.IReportMapper;
import com.magic.sangha.mapper.ITVMapper;
import com.magic.sangha.service.IReportService;
import com.magic.sangha.util.RoleConstant;

/**
 *  告警信息 业务层接口实现
 * @author QimouXie
 *
 */
@Service
public class ReportServiceImpl implements IReportService {

	@Resource
	private IReportMapper reportMapper;
	@Resource
	private ITVMapper tvMapper;
	@Resource
	private IRelationTVMapper relationTVMapper;
	
	@Override
	public Integer batchAddReport(List<ReportBean> reports) throws Exception {
		if(reports.size() == 0){
			return 0;
		}
		return reportMapper.batchAddReport(reports);
	}

	@Override
	public CutPageBean<ReportBean> findReportPage(Object obj,String stationCode, String groupCode, Integer pageNO,Integer pageSize) {
		List<String> stationCodes = null;
		if(stationCode != null){
			stationCodes = new ArrayList<String>();
			stationCodes.add(stationCode);
		}else{
			if(obj instanceof UserBean){
				UserBean user = (UserBean)obj;
				TVBean tv = tvMapper.findByUserId(user.getId());
				if(null != tv){
					stationCodes = new ArrayList<String>();
					stationCodes.add(tv.getStationCode());
				}
			}else if(obj instanceof GroupUser){
				GroupUser user = (GroupUser)obj;
				Integer roleId = null;
				if(RoleConstant.CUSTOMER.equals(user.getRoleType())){
					roleId = RoleConstant.CUSTOMER;
				}else if(RoleConstant.FILIALETECH.equals(user.getRoleType())){
					roleId = RoleConstant.FILIALETECH;
				}else if(RoleConstant.HEADCOMTECH.equals(user.getRoleType())){
					roleId = RoleConstant.HEADCOMTECH;
				}else if(RoleConstant.INVENT.equals(user.getRoleType())){
					roleId = RoleConstant.INVENT;
				}
				List<TVBean> tvs = tvMapper.findByGroupUser(user.getId(), roleId);
				if(tvs.size() != 0){
					stationCodes = new ArrayList<String>();
					for (TVBean tv : tvs) {
						stationCodes.add(tv.getStationCode());
					}
				}else{
					List<RelationTVBean> relations = relationTVMapper.queryByUserId(user.getId());
					if(relations.size() != 0){
						stationCodes = new ArrayList<String>();
						for (RelationTVBean relation : relations) {
							stationCodes.add(relation.getStatiocCode());
						}
					}
				}
			}
		}
		List<ReportBean>  dataList = new ArrayList<ReportBean>();
		Integer count = 0;
		Integer totalPage = 0;
		if(stationCodes == null || stationCodes.size() == 0){
			
		}else{
//			count = reportMapper.countReport(stationCodes, groupCode);
			dataList = reportMapper.findReportByPage(stationCodes, groupCode, (pageNO - 1)* pageSize, pageSize);
			totalPage= count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
		}
		CutPageBean<ReportBean> page = new CutPageBean<ReportBean>(dataList, count, totalPage);
		return page;
	}

	@Override
	public ReportBean findNewestUpdateTime() {
		return reportMapper.findNewestUpdateTime();
	}

	@Override
	public Integer delClearReport(Date today,Integer daysBefore) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.add(Calendar.DAY_OF_MONTH, -daysBefore);
		return reportMapper.delClearReport(calendar.getTime());
	}

}
