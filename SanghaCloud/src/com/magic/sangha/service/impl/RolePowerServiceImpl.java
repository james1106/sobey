package com.magic.sangha.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.magic.sangha.bean.RolePowerBean;
import com.magic.sangha.mapper.IRolePowerMapper;
import com.magic.sangha.service.IRolePowerService;

@Service
public class RolePowerServiceImpl implements IRolePowerService {

	@Resource
	private IRolePowerMapper rolePowerMapper;
	
	@Override
	public void updatePower(Integer roleId, List<Integer> ids) throws Exception {
		
		rolePowerMapper.delRolePower(roleId);
		if(ids.size() == 0){
			return;
		}
		List<RolePowerBean> rolePowers = new ArrayList<RolePowerBean>();
		for (Integer powerId : ids) {
			RolePowerBean rolePower = new RolePowerBean();
			rolePower.setRoleId(roleId);
			rolePower.setPowerId(powerId);
			rolePowers.add(rolePower);
		}
		rolePowerMapper.addBatch(rolePowers);
	}

}
