package com.magic.sangha.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.magic.sangha.bean.DeviceBean;
import com.magic.sangha.service.IDeviceService;
import com.magic.sangha.util.HttpGetRequest;
import com.magic.sangha.util.UrlUtils;

/**
 *  定时更新 设备列表
 * @author QimouXie
 *
 */
@Component
public class UpdateDeviceListTask extends QuartzJobBean{

	@Resource
	private IDeviceService deviceServiceImpl  ;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		
		String url = UrlUtils.geturl(null, UrlUtils.GET_DEVICELIST);
		String result = HttpGetRequest.sendRequest(url);
		List<DeviceBean> devices = new ArrayList<DeviceBean>();
	
		try {
			SchedulerContext skedCtx = arg0.getScheduler().getContext();
			deviceServiceImpl = (IDeviceService) skedCtx.get("deviceServiceImpl");
			JSONObject resultJSON = JSONObject.fromObject(result);
			if(resultJSON.getInt("ErrorCode") != 200){
				logger.debug("数据请求异常"+new Date());
				return;
			}
			JSONArray hostList = JSONArray.fromObject(resultJSON.getString("HostList"));
			for (Object obj : hostList) {
				JSONObject objJSON = JSONObject.fromObject(obj);
				DeviceBean device = new DeviceBean();
				device.setHostKey(objJSON.getString("HostKey"));
				device.setHostName(objJSON.getString("HostName"));
				device.setInterIPAddress(objJSON.getString("InterIPAddress"));
				device.setStationCode(objJSON.getString("StationCode"));
				device.setGroupCode(objJSON.getString("GroupCode"));
				device.setStatus(objJSON.getString("Status"));
				String remoteIP = null;
				if(objJSON.get("RemoteIP") != null){
					remoteIP = objJSON.getString("RemoteIP");
				}
				device.setRemoteIP(remoteIP);
				String systemInfo = "";
				if(objJSON.get("SystemInfo") != null){
					systemInfo = objJSON.getString("SystemInfo");
				}
				
				device.setSystemInfo(systemInfo);
				String loadInfo ="";
				if(objJSON.get("LoadInfo") != null){
					loadInfo = objJSON.getString("LoadInfo");
				}
				device.setLoadInfo(loadInfo);
				devices.add(device);
			}
			if(devices.size() == 0){
				return;
			}
			deviceServiceImpl.batchAddDevice(devices);
		} catch (Exception e) {
			logger.debug("数据异常"+e.getMessage(), e);
		}
	}


}
