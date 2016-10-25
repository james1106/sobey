package com.magic.sangha.service;

import java.util.List;

/**
 *  角色对应权限 业务层接口
 * @author QimouXie
 *
 */
public interface IRolePowerService {

	/**更新角色对应的权限*/
	void updatePower(Integer roleId,List<Integer> ids) throws Exception;
	
}
