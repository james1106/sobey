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
	 *  �����޸�USER��Ϣ
	 * @param user
	 * @return
	 */
	Integer updateUser(UserBean user);
	
	UserBean findByMobile(String mobile);
	
	/**
	 *  �����û�ID ��ѯ�û����ֺ��ֻ���
	 * @param id
	 * @return
	 */
	UserBean findById(Integer id);
	
	/**
	 *  �����豸token �� �豸����
	 * @param id
	 * @param deviceType
	 * @param deviceToken
	 * @return
	 */
	Integer updateDeviceTypeAndToken(Integer id,Integer deviceType,String deviceToken);
	/**
	 *  ���ݵ���̨ �����û�
	 * @param tvId
	 * @return
	 */
	List<UserBean> findByTVId(Integer tvId);
	/**
	 *  ����������� �����û�
	 * @param tvId
	 * @return
	 */
	CutPageBean<UserBean> findByAudit(Integer status,Integer pageNO,Integer pageSize,String userName);
	/**
	 *  �޸��û���״̬
	 * @param id
	 * @param status
	 * @return
	 */
	Integer updateStatus(Integer id,Integer status,Integer isPUser) throws Exception;
	
	/**
	 *  ��ѯ���û������ֶ�
	 * @param id
	 * @return
	 */
	UserBean findByIdAllFields(Integer id);
	
	Map<String,Integer> countUserByStatus();
	
	
	/**��ҳ��ѯ ǩ����¼*/
	CutPageBean<UserBean> queryPageByUser(Integer pageNO,Integer pageSize,String realName);
	


}
