package com.magic.sangha.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.GroupUser;
import com.magic.sangha.bean.SystemInfoBean;
import com.magic.sangha.bean.UserBean;
import com.magic.sangha.mapper.IGroupUserMapper;
import com.magic.sangha.mapper.ISystemInfoMapper;
import com.magic.sangha.mapper.IUserMapper;
import com.magic.sangha.service.ISystemInfoService;
import com.magic.sangha.util.PushMessageUtil;

/**
 *  系统消息 业务层接口实现
 * @author QimouXie
 *
 */
@Service
public class SystemInfoServiceImpl implements ISystemInfoService {

	@Resource
	private ISystemInfoMapper systemInfoMapper;
	@Resource
	private IGroupUserMapper groupUserMapper;
	@Resource
	private IUserMapper userMapper;
	
	@Override
	public Integer addSystemInfo(SystemInfoBean system) throws Exception {
		if(null == system){
			return null;
		}
		systemInfoMapper.addSystemInfo(system);
		return system.getId();
	}

	@Override
	public CutPageBean<SystemInfoBean> quertPage(Object obj, Integer pageNO,Integer pageSize) {
		
		Integer group = null;
		if(obj instanceof UserBean){
			group = 0;
		}else if(obj instanceof GroupUser){
			group = 1;
		}
		List<SystemInfoBean> dataList = systemInfoMapper.queryInfoPage(group, (pageNO - 1) * pageSize, pageSize);
		Integer count = systemInfoMapper.countSystemInfo(group);
		Integer totalPage = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
		CutPageBean<SystemInfoBean>  page  = new CutPageBean<SystemInfoBean>(dataList, count, totalPage);
		return page;
	}

	@Override
	public boolean sendNotice(SystemInfoBean system,Integer id) {
		// type  0 全部 1 用户 2员工
		Map<String,String> extendParam = new HashMap<String, String>();
		extendParam.put("type", PushMessageUtil.SYSTEM_INFO);
		if(id == null ){
			return false;
		}
		List<GroupUser> groupUsers = null;
		List<UserBean> users = null;
		if(system.getToGroup() == 0){
			PushMessageUtil.pushMessages(null, system.getBrief(), extendParam);
//			PushMessageUtil.pushMessages(obj, content, extend);
//			groupUsers = groupUserMapper.findAllToToken();
//			users = userMapper.findAllToToken();
		}else if(system.getToGroup() == 1){
			users = userMapper.findAllToToken();
		}else if(system.getToGroup() == 2){
			groupUsers = groupUserMapper.findAllToToken();
		}else{
			return false;
		}
		
		if(null != users){
			for (UserBean userBean : users) {
				PushMessageUtil.pushMessages(userBean, system.getBrief(), extendParam);
			}
		}
		if(null != groupUsers){
			for (GroupUser userBean : groupUsers) {
				PushMessageUtil.pushMessages(userBean, system.getBrief(), extendParam);
			}
		}
		return true;
	}

	@Override
	public SystemInfoBean findById(Integer id) {
		return systemInfoMapper.findById(id);
	}

	@Override
	public void delSystemInfo(Integer id) throws Exception {
		systemInfoMapper.delSystemInfo(id);
	}

	@Override
	public Integer updateSystemInfo(SystemInfoBean info) {
		return systemInfoMapper.updateSystemInfo(info);
	}
}
