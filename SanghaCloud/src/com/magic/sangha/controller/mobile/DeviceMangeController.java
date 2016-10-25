package com.magic.sangha.controller.mobile;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.DeviceBean;
import com.magic.sangha.bean.GroupUser;
import com.magic.sangha.bean.ReportBean;
import com.magic.sangha.bean.UserBean;
import com.magic.sangha.controller.BaseController;
import com.magic.sangha.service.IDeviceService;
import com.magic.sangha.service.IReportService;
import com.magic.sangha.util.DateUtil;
import com.magic.sangha.util.HttpGetRequest;
import com.magic.sangha.util.LoginHelper;
import com.magic.sangha.util.StatusConstant;
import com.magic.sangha.util.UrlUtils;
import com.magic.sangha.util.ViewData;

@Controller
@RequestMapping("/deviceManage")
public class DeviceMangeController extends BaseController {
	
	@Resource
	private IDeviceService deviceServiceImpl;
	@Resource
	private IReportService reportServiceImpl;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 *  动态获取 设备列表接口
	 * @param stationCode
	 * @param groupCode
	 * @param pageSize
	 * @param pageNO
	 * @return
	 */
	@RequestMapping("/getDeviceList")
	@ResponseBody
	public ViewData getDeviceList(String stationCode,String groupCode,Integer pageSize,Integer pageNO) {
//		if(stationCode == null){
//			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能那个为空");
//		}
		Object obj = LoginHelper.getCurrentUser();
		if(null == obj){
			return buildFailureJson(StatusConstant.NOTLOGIN, "未登录");
		}
		if(obj instanceof UserBean){
			UserBean user = (UserBean)obj;
			if(StatusConstant.FROZEN.equals(user.getStatus())){
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户被冻结");
			}
		}else if(obj instanceof GroupUser){
			GroupUser user = (GroupUser)obj;
			if(StatusConstant.FROZEN.equals(user.getStatus())){
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户被冻结");
			}
		}
		CutPageBean<DeviceBean> page = deviceServiceImpl.findPageDevice(stationCode,obj, groupCode, pageNO, pageSize);
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("lists", page.getDataList());
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}
	
	/**
	 *  更新设备列表
	 * @return
	 */
	@RequestMapping("/updateDevice")
	@ResponseBody
	public ViewData updateDeviceList(HttpServletRequest req){
		
		GroupUser user = LoginHelper.getCurrentAdmin(req);
		if(null == user){
			return buildFailureJson(StatusConstant.NOTLOGIN, "未登录");
		}
		if(StatusConstant.FROZEN.equals(user.getStatus())){
			return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户被冻结");
		}
		Integer count = 0;
		String url = UrlUtils.geturl(null, UrlUtils.GET_DEVICELIST);
		String result = HttpGetRequest.sendRequest(url);
		List<DeviceBean> devices = new ArrayList<DeviceBean>();
		int i= 0;
		try {
			JSONObject resultJSON = JSONObject.fromObject(result);
			if(resultJSON.getInt("ErrorCode") != 200){
				return buildFailureJson(StatusConstant.Fail_CODE, "数据请求异常");
			}
			JSONArray hostList = JSONArray.fromObject(resultJSON.getString("HostList"));
			for (Object obj : hostList) {
				i++;
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
				return buildFailureJson(StatusConstant.SUCCESS_CODE, "没有数据");
			}
			count = deviceServiceImpl.batchAddDevice(devices);
		} catch (Exception e) {
			logger.debug("----------------------"+i+"----------"+e.getMessage(), e);
			return buildFailureJson(StatusConstant.Fail_CODE, "数据异常");
		}
		if(count == 0){
			return buildFailureJson(StatusConstant.SUCCESS_CODE, "没有最新数据");
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "成功更新"+count+"条数据");
	}
	
	/**
	 *  更新告警信息
	 * @return
	 */
	@RequestMapping("/updateReport")
	@ResponseBody
	public ViewData updateReportInfo(HttpServletRequest req){
		GroupUser user = LoginHelper.getCurrentAdmin(req);
		if(null == user){
			return buildFailureJson(StatusConstant.NOTLOGIN, "未登录");
		}
		if(StatusConstant.FROZEN.equals(user.getStatus())){
			return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户被冻结");
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
				logger.debug("数据获取异常....."+result);
				return buildFailureJson(StatusConstant.Fail_CODE, "获取数据异常");
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
				return buildFailureJson(StatusConstant.SUCCESS_CODE, "操作成功");
			}
			reportServiceImpl.batchAddReport(reports);
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			return buildFailureJson(StatusConstant.Fail_CODE, "数据异常");
		}
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "操作成功");
	}
	
	/**
	 *  获取 报警信息
	 * @return
	 */
	@RequestMapping("/getReport")
	@ResponseBody
	public ViewData getReport(String stationCode,String groupCode,Integer pageSize,Integer pageNO){
		Object obj = LoginHelper.getCurrentUser();
		if(null == obj){
			return buildFailureJson(StatusConstant.NOTLOGIN, "未登录");
		}
		if(obj instanceof UserBean){
			UserBean user = (UserBean)obj;
			if(StatusConstant.FROZEN.equals(user.getStatus())){
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户被冻结");
			}
		}else if(obj instanceof GroupUser){
			GroupUser user = (GroupUser)obj;
			if(StatusConstant.FROZEN.equals(user.getStatus())){
				return buildFailureJson(StatusConstant.ACCOUNT_FROZEN, "账户被冻结");
			}
		}
		CutPageBean<ReportBean> page = reportServiceImpl.findReportPage(obj, stationCode, groupCode, pageNO, pageSize);
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("lists", page.getDataList());
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}
	
	

}
