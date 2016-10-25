package com.magic.sangha.service;

import java.util.List;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.DeviceBean;

/**
 *  设备管理 业务层接口
 * @author QimouXie
 *
 */
public interface IDeviceService {
	
	/**
	 *  批量新增 设备列表
	 * @param devices
	 * @return
	 */
	Integer batchAddDevice(List<DeviceBean> devices) throws Exception;
	
	/**
	 *  分页查询
	 * @param stationCode
	 * @param groupCode
	 * @param pageNO
	 * @param pageSize
	 * @return
	 */
	CutPageBean<DeviceBean> findPageDevice(String staticCode,Object obj,String groupCode,Integer pageNO,Integer pageSize);
	
}
