package com.magic.sangha.task;

import java.util.Calendar;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.magic.sangha.service.IOrderService;

/**
 *   更新15 天还没有 待验收的订单状态为 验收通过
 * @author QimouXie
 *
 */
public class UpdateOrderStatusTask extends QuartzJobBean {

	private IOrderService orderServiceImpl;
	
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		
		try {
			SchedulerContext  skedCtx = arg0.getScheduler().getContext();
			orderServiceImpl = (IOrderService) skedCtx.get("orderServiceImpl");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DAY_OF_MONTH, -15);
			orderServiceImpl.updateAutoOrderStatus(calendar.getTime());
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		
	}

}
