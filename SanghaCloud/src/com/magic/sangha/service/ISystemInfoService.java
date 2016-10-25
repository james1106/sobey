package com.magic.sangha.service;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.SystemInfoBean;

public interface ISystemInfoService {


	/**
	 *   添加 系统消息
	 * @param system
	 * @return
	 */
	Integer addSystemInfo(SystemInfoBean system) throws Exception;
	
	/**
	 *  分页查询
	 * @param userId
	 * @param groupUserId
	 * @param pageNO
	 * @param pageSize
	 * @return
	 */
	CutPageBean<SystemInfoBean> quertPage(Object obj,Integer pageNO,Integer pageSize);
	
	boolean sendNotice(SystemInfoBean system,Integer id);
	
	SystemInfoBean findById(Integer id);
	/**
	 *  删除
	 * @param id
	 * @throws Exception
	 */
	void delSystemInfo(Integer id) throws Exception;
	/**
	 *  更新系统消息
	 * @param info
	 * @return
	 */
	Integer updateSystemInfo(SystemInfoBean info) throws Exception;
}
