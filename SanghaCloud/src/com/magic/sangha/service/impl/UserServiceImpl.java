package com.magic.sangha.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.TVBean;
import com.magic.sangha.bean.UserBean;
import com.magic.sangha.mapper.IGroupUserMapper;
import com.magic.sangha.mapper.ITVMapper;
import com.magic.sangha.mapper.IUserMapper;
import com.magic.sangha.service.IUserService;
import com.magic.sangha.util.LoginHelper;
import com.magic.sangha.util.MemcachedUtil;
import com.magic.sangha.util.StatusConstant;

@Service
public class UserServiceImpl implements IUserService {

	@Resource
	private IUserMapper userMapper;	
	@Resource
	private IGroupUserMapper groupUserMapper;
	
	@Resource
	private ITVMapper tvMapper;
	
	@Override
	public UserBean login(String mobile, String password) {
		return userMapper.login(mobile, password);
	}

	@Override
	public Integer register(UserBean user) throws Exception {
		return userMapper.register(user);
	}

	@Override
	public Integer delUser(UserBean user) {
		return userMapper.delUser(user);
	}

	@Override
	public UserBean findByToken(String token) {
		return userMapper.findByToken(token);
	}

	@Override
	public Integer updateToken(String token, Integer id) {
		return userMapper.updateToken(token, id,new Date());
	}

	@Override
	public Integer verifiMobile(String mobile) {
		return userMapper.verifiMobile(mobile);
	}

	@Override
	public Integer updateUser(UserBean user)  {
		return userMapper.updateUser(user);
	}

	@Override
	public UserBean findByMobile(String mobile) {
		// TODO Auto-generated method stub
		return userMapper.findByMobile(mobile);
	}

	@Override
	public UserBean findById(Integer id) {
		
		UserBean user = userMapper.findById(id);
		if(null != user.getTvId()){
			TVBean tv = tvMapper.findById(user.getTvId());
			user.setTvName(tv.getTvName());
		}
		return user;
	}

	@Override
	public Integer updateDeviceTypeAndToken(Integer id, Integer deviceType,
			String deviceToken) {
		return userMapper.updateDeviceTypeAndToken(id, deviceType, deviceToken);
	}

	@Override
	public List<UserBean> findByTVId(Integer tvId) {
		return userMapper.findByTVId(tvId);
	}

	@Override
	public CutPageBean<UserBean> findByAudit(Integer status, Integer pageNO,Integer pageSize,String userName) {
		
		List<UserBean> dataList = userMapper.findByAudit((pageNO - 1) * pageSize, pageSize, status,userName);
		Integer count = userMapper.countByStatus(status,userName);
		Integer totalPage = 0;
		if(count % pageSize != 0){
			totalPage = count / pageSize + 1;
		}else{
			totalPage = count / pageSize;
		}
		CutPageBean<UserBean> page = new CutPageBean<UserBean>(dataList, count, totalPage);
		
		return page;
	}

	@Override
	public Integer updateStatus(Integer id, Integer status,Integer isPUser)  throws Exception {
		
		UserBean user = userMapper.findById(id);
		if(user.getToken() != null){
			Object obj = LoginHelper.getCurrentUser(user.getToken());
			if(null != obj){
				if(obj instanceof UserBean){
					UserBean userObj = (UserBean)obj;
					userObj.setStatus(StatusConstant.FROZEN);
					userObj.setIsPUser(isPUser);
					MemcachedUtil.getInstance().replace(userObj.getToken(), userObj);
				}
			}
		}
		
		return userMapper.updateStatus(id, status,isPUser);
	}

	@Override
	public UserBean findByIdAllFields(Integer id) {
		return userMapper.findByIdAllFields(id);
	}

	@Override
	public Map<String, Integer> countUserByStatus() {
		Integer nonPass = userMapper.countByStatus(StatusConstant.NONPASS,null);
		Integer all = userMapper.countByStatus(null,null);
		Map<String, Integer> data = new HashMap<String, Integer>();
		data.put("nonPass", nonPass);
		data.put("all", all);
		return data;
	}

	@Override
	public CutPageBean<UserBean> queryPageByUser(Integer pageNO,Integer pageSize, String realName) {
		List<UserBean> dataList = userMapper.queryCountSign((pageNO - 1) * pageSize, pageSize, realName);
		Integer count = userMapper.countSign(realName);
		Integer totalPage = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
		CutPageBean<UserBean> page = new CutPageBean<UserBean>(dataList, count, totalPage);
		return page;
	}

}
