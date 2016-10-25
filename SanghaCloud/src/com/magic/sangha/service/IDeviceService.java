package com.magic.sangha.service;

import java.util.List;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.DeviceBean;

/**
 *  �豸���� ҵ���ӿ�
 * @author QimouXie
 *
 */
public interface IDeviceService {
	
	/**
	 *  �������� �豸�б�
	 * @param devices
	 * @return
	 */
	Integer batchAddDevice(List<DeviceBean> devices) throws Exception;
	
	/**
	 *  ��ҳ��ѯ
	 * @param stationCode
	 * @param groupCode
	 * @param pageNO
	 * @param pageSize
	 * @return
	 */
	CutPageBean<DeviceBean> findPageDevice(String staticCode,Object obj,String groupCode,Integer pageNO,Integer pageSize);
	
}
