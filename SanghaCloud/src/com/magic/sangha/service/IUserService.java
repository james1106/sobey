package com.magic.sangha.service;


import java.util.List;
import java.util.Map;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.UserBean;

public interface IUserService {

	UserBean login(String mobile, String password);

	Integer register(UserBean user) throws Exception;

	Integer delUser(UserBean user);
	
	UserBean findByToken(String token);
	
	Integer updateToken(String token,Integer id);
	
	Integer verifiMobile(String mobile);
	/**
	 *  更新修改USER信息
	 * @param user
	 * @return
	 */
	Integer updateUser(UserBean user);
	
	UserBean findByMobile(String mobile);
	
	/**
	 *  根据用户ID 查询用户名字和手机号
	 * @param id
	 * @return
	 */
	UserBean findById(Integer id);
	
	/**
	 *  更新设备token 和 设备类型
	 * @param id
	 * @param deviceType
	 * @param deviceToken
	 * @return
	 */
	Integer updateDeviceTypeAndToken(Integer id,Integer deviceType,String deviceToken);
	/**
	 *  根据电视台 查找用户
	 * @param tvId
	 * @return
	 */
	List<UserBean> findByTVId(Integer tvId);
	/**
	 *  根据审核类型 查找用户
	 * @param tvId
	 * @return
	 */
	CutPageBean<UserBean> findByAudit(Integer status,Integer pageNO,Integer pageSize,String userName);
	/**
	 *  修改用户的状态
	 * @param id
	 * @param status
	 * @return
	 */
	Integer updateStatus(Integer id,Integer status,Integer isPUser) throws Exception;
	
	/**
	 *  查询该用户所有字段
	 * @param id
	 * @return
	 */
	UserBean findByIdAllFields(Integer id);
	
	Map<String,Integer> countUserByStatus();
	
	
	/**分页查询 签到记录*/
	CutPageBean<UserBean> queryPageByUser(Integer pageNO,Integer pageSize,String realName);
	


}
