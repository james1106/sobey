package com.magic.sangha.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.magic.sangha.bean.ReportBean;
import com.magic.sangha.service.IReportService;
import com.magic.sangha.util.DateUtil;
import com.magic.sangha.util.HttpGetRequest;
import com.magic.sangha.util.UrlUtils;

/**
 *  定时更新 警报信息
 * @author QimouXie
 *
 */
@Component
public class UpdateDeviceEventTask extends QuartzJobBean {
	
	@Resource
	private IReportService reportServiceImpl;
	
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		
		 try {
			SchedulerContext skedCtx = arg0.getScheduler().getContext();
			reportServiceImpl = (IReportService) skedCtx.get("reportServiceImpl");
		} catch (SchedulerException e1) {
			e1.printStackTrace();
		}  
		ReportBean tempReport = reportServiceImpl.findNewestUpdateTime();
		Map<String,String> map = new HashMap<String,String>();
		map.put("time", tempReport.getGetTime());
		map.put("count", "1000");
		String url = UrlUtils.geturl(map, UrlUtils.GET_NEWEVENT);
		
		List<ReportBean> reports = new ArrayList<ReportBean>();
		String result = HttpGetRequest.sendRequest(url);
		try {
			JSONObject resultJSON = JSONObject.fromObject(result);
			if(resultJSON.getInt("ErrorCode") != 200){
				logger.debug("数据异常"+new Date());
				return ;
			}
			JSONArray eventArrays = JSONArray.fromObject(resultJSON.getString("Events"));
			String time = resultJSON.getString("Time");
			Date createTime = new Date();
			for (Object obj : eventArrays) {
				JSONObject objJSON = JSONObject.fromObject(obj);
				ReportBean report = new ReportBean();
				report.setEventID(objJSON.getString("EventID"));
				report.setTypeID(objJSON.getString("TypeID"));
				report.setLevel(objJSON.getString("Level"));
				String resourceKey = "";
				if(objJSON.get("ResourceKey") != null){
					resourceKey = objJSON.getString("ResourceKey");
				}
				report.setResourceKey(resourceKey);
				report.setStationCode(objJSON.getString("StationCode"));
				report.setHostKey(objJSON.getString("HostKey"));
				report.setTime(DateUtil.stringToDate(objJSON.getString("Time")));
				report.setSolutionTime(objJSON.getString("SolutionTime"));
				report.setReason(objJSON.getString("Reason"));
				report.setProcessingResult(objJSON.getString("ProcessingResult"));
				report.setCreateTime(createTime);
				report.setGetTime(time);
				reports.add(report);
			}
			if(reports.size() == 0){
				return;
			}
			reportServiceImpl.batchAddReport(reports);
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}
		
	}

}
