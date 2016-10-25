package com.magic.sangha.task;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.magic.sangha.service.IReportService;

/**
 *  定时清理 告警信息数据
 *  数据有效期为一星期
 * @author QimouXie
 *
 */
@Component
public class ClearReportTask extends QuartzJobBean {

	@Resource
	private IReportService reportServiceImpl;
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		
		try {
			SchedulerContext skedCtx = arg0.getScheduler().getContext();
			reportServiceImpl = (IReportService) skedCtx.get("reportServiceImpl");
			reportServiceImpl.delClearReport(new Date(), 3);
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			e.printStackTrace();
		}
		
	}

}
