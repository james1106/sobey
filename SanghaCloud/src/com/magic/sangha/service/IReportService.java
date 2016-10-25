package com.magic.sangha.service;

import java.util.Date;
import java.util.List;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.ReportBean;

/**
 *  告警信息 业务层接口
 * @author QimouXie
 *
 */
public interface IReportService {
	
	/**
	 *  批量新增 告警信息
	 * @param reports
	 * @return
	 * @throws Exception
	 */
	Integer batchAddReport(List<ReportBean> reports) throws Exception;
	
	/**
	 *  分页获取 告警信息
	 * @param obj
	 * @param stationCode
	 * @param groupCode
	 * @param pageNO
	 * @param pageSize
	 * @return
	 */
	CutPageBean<ReportBean> findReportPage(Object obj,String stationCode,String groupCode,Integer pageNO,Integer pageSize);
	
	/**
	 *  查询最新的更新时间
	 * @return
	 */
	ReportBean findNewestUpdateTime();
	
	/**
	 *  删除多少天前的数据
	 * @param today
	 * @param daysBefore 几天前
	 * @return
	 */
	Integer delClearReport(Date today,Integer daysBefore) throws Exception;
	
}
