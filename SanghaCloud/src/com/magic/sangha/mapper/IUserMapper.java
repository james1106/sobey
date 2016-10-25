package com.magic.sangha.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.UserBean;


public interface IUserMapper {
	
	UserBean login(@Param("mobile")String mobile,@Param("password")String password);
	
	int register(@Param("user")UserBean user);
	
	@Delete("delete from t_user where id=#{user.id}")
	Integer delUser(@Param("user")UserBean user);
	
	UserBean findByToken(@Param("token")String token);
	/**
	 *  ����token
	 * @param token
	 * @return
	 */
	Integer updateToken(@Param("token")String token,@Param("id")Integer id,@Param("updatetime")Date date);
	/**
	 *  ��֤�ֻ����Ƿ����
	 * @param mobile
	 * @return
	 */
	Integer verifiMobile(@Param("mobile")String mobile);
	
	/**
	 *  �޸ĸ�������
	 * @param user
	 * @return
	 */
	Integer updateUser(@Param("user")UserBean user);
	
	/**
	 *  �����ֻ��� ��ѯ �û�
	 * @param mobile
	 * @return
	 */
	UserBean findByMobile(@Param("mobile")String mobile);
	
	/**
	 *  ����ID ��ѯ�û������ֺ͵绰����
	 * @param id
	 * @return
	 */
	UserBean findById(@Param("id")Integer id);
	
	/**
	 *  �����豸token �� �豸����
	 * @param id
	 * @param deviceType
	 * @param deviceToken
	 * @return
	 */
	Integer updateDeviceTypeAndToken(@Param("id")Integer id,@Param("deviceType")Integer deviceType,@Param("deviceToken")String deviceToken);
	
	/**
	 *  ���ݵ���̨ �����û�
	 * @param tvId
	 * @return
	 */
	List<UserBean> findByTVId(@Param("tvId")Integer tvId);
	
	/**
	 *  ����������� �����û�
	 * @param tvId
	 * @return
	 */
	List<UserBean> findByAudit(@Param("limit")Integer limit,@Param("pageLimit")Integer pageLimit,@Param("isAduit")Integer isAudit,@Param("userName")String userName);
	/**
	 *   ����״̬ͳ���û�����
	 * @param status
	 * @return
	 */
	Integer countByStatus(@Param("status")Integer status,@Param("userName")String userName);
	
	/**
	 *  �����û��� ״̬
	 * @param id
	 * @param status
	 * @return
	 */
	Integer updateStatus(@Param("id")Integer id,@Param("status")Integer status,@Param("isPUser")Integer isPUser);
	
	/**
	 *  ���� email ��ѯ�û�
	 * @param email
	 * @return
	 */
	List<UserBean> findByEmail(@Param("email")String email);
	/**
	 *  ��ѯ�û����е��ֶ�
	 * @param id
	 * @return
	 */
	UserBean findByIdAllFields(@Param("id")Integer id);
	
	
	/**
	 *  ��ѯ�����û���token
	 * @return
	 */
	List<UserBean> findAllToToken();
	
	/**��ѯͳ������ǩ������������*/
	List<UserBean> queryCountSign(@Param("limit")Integer limit,@Param("limitSize")Integer limitSize,@Param("realName")String realName);
	/**ͳ������*/
	Integer countSign(@Param("realName")String realName);
	
	
}
