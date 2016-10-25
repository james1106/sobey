package com.magic.sangha.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.DeviceBean;
import com.magic.sangha.bean.GroupUser;
import com.magic.sangha.bean.TVBean;
import com.magic.sangha.bean.UserBean;
import com.magic.sangha.mapper.IDeviceMapper;
import com.magic.sangha.mapper.ITVMapper;
import com.magic.sangha.service.IDeviceService;
import com.magic.sangha.util.RoleConstant;

/**
 *   设备管理
 * @author QimouXie
 *
 */
@Service
public class DeviceServiceImpl implements IDeviceService {

	@Resource
	private IDeviceMapper deviceMapper;
	@Resource
	private ITVMapper tvMapper;
	
	@Override
	public Integer batchAddDevice(List<DeviceBean> devices) throws Exception {
		List<DeviceBean> dataList = deviceMapper.findAllDevice();
		List<DeviceBean> newDevice = new ArrayList<DeviceBean>();
		for (DeviceBean device : devices) {
			if(!dataList.contains(device)){
				newDevice.add(device);
			}
		}
		if(newDevice.size() == 0){
			return 0;
		}
		return deviceMapper.batchAddDevice(newDevice);
	}

	@Override
	public CutPageBean<DeviceBean> findPageDevice(String staticCode,Object obj,String groupCode, Integer pageNO, Integer pageSize) {
		
		List<String> stationCodes = null;
		if(staticCode != null){
			stationCodes = new ArrayList<String>();
			stationCodes.add(staticCode);
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
				}
			}
		}
		List<DeviceBean>  dataList = deviceMapper.findPageData(stationCodes, groupCode, (pageNO - 1)* pageSize, pageSize);
		Integer count = deviceMapper.countDevice(stationCodes, groupCode);
		Integer totalPage = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
		CutPageBean<DeviceBean> page = new CutPageBean<DeviceBean>(dataList, count, totalPage);
		return page;
	}

}
