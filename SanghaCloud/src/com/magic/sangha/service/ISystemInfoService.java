package com.magic.sangha.service;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.SystemInfoBean;

public interface ISystemInfoService {


	/**
	 *   ��� ϵͳ��Ϣ
	 * @param system
	 * @return
	 */
	Integer addSystemInfo(SystemInfoBean system) throws Exception;
	
	/**
	 *  ��ҳ��ѯ
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
	 *  ɾ��
	 * @param id
	 * @throws Exception
	 */
	void delSystemInfo(Integer id) throws Exception;
	/**
	 *  ����ϵͳ��Ϣ
	 * @param info
	 * @return
	 */
	Integer updateSystemInfo(SystemInfoBean info) throws Exception;
}
