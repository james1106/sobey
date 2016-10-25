package com.magic.sangha.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.ReportBean;

/**
 *  告警信息列表  持久层接口
 * @author QimouXie
 *
 */
public interface IReportMapper {
	
	/**
	 *  批量新增 告警信息
	 * @param reports
	 * @return
	 */
	Integer batchAddReport(@Param("reports")List<ReportBean> reports);
	
	/**
	 *  按条件分页获取 告警信息
	 * @param statiocCodes
	 * @param groupCode
	 * @param limit
	 * @param limitSize
	 * @return
	 */
	List<ReportBean> findReportByPage(@Param("stationCodes")List<String> statiocCodes,@Param("groupCode")String groupCode,@Param("limit")Integer limit,@Param("limitSize")Integer limitSize);

	/**
	 *  按条件 统计数量
	 * @param statiocCodes
	 * @param groupCode
	 * @return
	 */
	Integer countReport(@Param("stationCodes")List<String> statiocCodes,@Param("groupCode")String groupCode);
	
	/**
	 *  查询最新的更新时间
	 * @return
	 */
	ReportBean findNewestUpdateTime();
	
	/**
	 *  清理 告警信息
	 *   告警信息的有效期为一周
	 * @return
	 */
	Integer delClearReport(@Param("targetDate")Date targetDate) ;
	
}
