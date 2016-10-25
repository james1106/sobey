package com.magic.sangha.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.GroupUser;

public interface IGroupUserMapper {
	/**
	 *  ����ID��ѯ��ҵ�û�
	 * @param id
	 * @return
	 */
	GroupUser findById(@Param("id")Integer id);
	/**
	 *  ��¼
	 * @param mobile
	 * @param password
	 * @return
	 */
	GroupUser login (@Param("mobile")String mobile,@Param("password")String password);
	
	/**
	 *  ע��
	 * @param user
	 * @return
	 */
	Integer register(@Param("user")GroupUser user);
	/**
	 *  ���ݽ�ɫ��ѯ��ҵ�û�
	 * @param roleId
	 * @return
	 */
	List<GroupUser> findByRole(@Param("roleId")Integer roleId);
	
	/**
	 *  ����token��ѯ�û�
	 * @param token
	 * @return
	 */
	GroupUser findByToken(@Param("token")String token);
	/**
	 *  ����token
	 * @param token
	 * @return
	 */
	Integer updateToken(@Param("token")String token,@Param("id")Integer id,@Param("datetime")Date date);
	/**
	 *  ��֤�ֻ����Ƿ�ע��
	 * @param mobile
	 * @return
	 */
	Integer verifiMobile(@Param("mobile")String mobile);
	
	Integer updateGroupUser(@Param("user")GroupUser user);
	
	/**
	 *  ������ҵ�û��Ƿ����յ� �ʼ�֪ͨ
	 * @param isEmail
	 * @return
	 */
	Integer updateGroupUserIsEmail(@Param("id")Integer id,@Param("isEmail")Integer isEmail);
	
	/**
	 *  ͨ���ֻ��Ų����û�
	 * @param mobile
	 * @return
	 */
	GroupUser findByMobile(@Param("mobile")String mobile);
	
	/**
	 *  ͨ��ID��ѯ�û������ֺ͵绰
	 * @param id
	 * @return
	 */
	GroupUser findByIdToName(@Param("id")Integer id);
	
	/**
	 *   ���ݰ��´�ID ��ѯԱ���û�
	 * @param id
	 * @return
	 */
	List<GroupUser> findByOfficeId(@Param("officeId")Integer officeId,@Param("isEmail")Integer isEmail);
	
	/**
	 *  ��ѯTSC��ʱ��ͨ������������userId ��ѯ TSC
	 * @param userId
	 * @return
	 */
	List<GroupUser> findByUserId(@Param("userId") Integer userId,@Param("roleId")Integer roleId);
	/**
	 *  ��ѯ�ܲ�����֧��
	 *   ���ֻ�� ͷ������ �� ID
	 * @return
	 */
	List<GroupUser> findHeadTech(@Param("roleId")Integer roleId,@Param("companyId")Integer companyId);
	
	/**
	 *  ���ݰ��´� �� ��ɫ ��ѯ ���´���ĳ��ɫ �� �û�
	 * @param officeId
	 * @param roleId
	 * @return
	 */
	List<GroupUser> findGroupUserByOfficeAndRole(@Param("officeId")Integer officeId,@Param("roleId")Integer roleId);
	
	/**
	 *  �����豸token �� �豸����
	 * @param id
	 * @param deviceType
	 * @param deviceToken
	 * @return
	 */
	Integer updateDeviceTypeAndToken(@Param("id")Integer id,@Param("deviceType")Integer deviceType,@Param("deviceToken")String deviceToken);
	
	/**
	 *   ͨ���ֹ�˾ID  ��ѯ���ֹ�˾�� ��ͨ�쵼 �����쵼 �� �����쵼
	 * @return
	 */
	List<GroupUser> findGroupUserToManyRoleId(@Param("companyId")Integer companyId);
	
	/**
	 *  ����ID ��ѯ�û� �豸���� �� �豸token
	 * @param id
	 * @return
	 */
	GroupUser findTypeTokeById(@Param("id")Integer id);
	
	List<GroupUser> findHeadTechByRoleId(@Param("roleId")Integer roleId);
	
	/**
	 *  ����������� �����û�
	 * @param tvId
	 * @return
	 */
	List<GroupUser> findByAudit(@Param("roleId")Integer roleId,@Param("limit")Integer limit,@Param("pageLimit")Integer pageLimit,@Param("isAduit")Integer isAudit,@Param("userName")String userName);
	/**
	 *   ����״̬ͳ���û�����
	 * @param status
	 * @return
	 */
	Integer countByStatus(@Param("status")Integer status,@Param("roleId")Integer roleId,@Param("userName")String userName);
	
	/**
	 *  �޸��û���״̬�Լ���ɫ����
	 * @param id
	 * @param status
	 * @param roleId
	 * @return
	 */
	Integer updateStatusAndRole(@Param("id")Integer id,@Param("status")Integer status,@Param("roleId")Integer roleId);
	/**
	 *  ���� ���´���ID �� ��ǰ���´��µ� �����ʼ�����
	 * @param officeId
	 * @param isEmail
	 * @return
	 */
	GroupUser findByOfficeAndIsEmail(@Param("officeId")Integer officeId,@Param("isEmail")Integer isEmail,@Param("roleId")Integer roleId);
	/**
	 *  ���� email ��ѯ�û�
	 * @param email
	 * @return
	 */
	List<GroupUser> findByEmail(@Param("email")String email);
	/**
	 *   ��ѯ �� �Ǳ༼����ļ���
	 * @return
	 */
	List<GroupUser> findAllTechNOTFeibian(@Param("userId")Integer userId);
	
	/**
	 *  ��ѯ�Ǳ༼��
	 * @param userId
	 * @return
	 */
	List<GroupUser> findAllTechFeibian(@Param("userId")Integer userId,@Param("categoryId")Integer categoryId);
	
	/**��ѯ�Ǳ��з�*/
	List<GroupUser> findAllDevelopFeibian(@Param("categoryId")Integer categoryId);
	
	/**
	 *  ���ݵ���̨�� ��ѯ ����(�ܲ����ۣ��ֹ�˾���ۣ����´�����)
	 * @param tvId
	 * @return
	 */
	List<GroupUser> findSaleByTVId(@Param("tvId")Integer tvId);
	
	/**
	 *  ���� user ��Ϣ PC���޸�����
	 * @param user
	 * @return
	 */
	Integer updateUser(@Param("user")GroupUser user);
	
	/**
	 *  ���ݵ���̨ ��ѯ���´��������б�
	 * @param tvId
	 * @return
	 */
	List<GroupUser> findOfficeSaleByTvId(@Param("tvId")Integer tvId);
	
	/**
	 *  ������ѯԱ��
	 * @param ids
	 * @return
	 */
	List<GroupUser> batchQueryGroupUser(@Param("ids")List<Integer> ids);
	
	/**
	 *  ��ѯ�����û���token
	 * @return
	 */
	List<GroupUser> findAllToToken();
	
	/**
	 *  ���ݽ�ɫ��ѯ ���ͨ���Լ��Ƕ���״̬�µ�Ա��
	 * @param limit
	 * @param limitSize
	 * @param roleId
	 * @return
	 */
	List<GroupUser> findPageByRole(@Param("realName")String realName,@Param("limit")Integer limit,@Param("limitSize")Integer limitSize,@Param("roleId")Integer roleId);
	
	/**
	 *  ���ݽ�ɫͳ�� ���ͨ���Լ� �Ƕ���״̬�µ�Ա������
	 * @param roleId
	 * @return
	 */
	Integer countByRole(@Param("realName")String realName,@Param("roleId")Integer roleId);
	
	/**
	 *  ͳ�ƿͷ���������
	 * @return
	 */
	Integer countOnLineByService();
	
	/**
	 *   ģ����ҳ��ѯ ����Ա�� �Լ� �Ƿ�Ǳ༼��
	 * @return
	 */
	List<GroupUser> findPageTech(@Param("limit")Integer limit,@Param("limitSize")Integer limitSize,@Param("realName")String realName);
	/**
	 *  ����ģ�� ͳ��
	 * @param realName
	 * @return
	 */
	Integer countPageTech(@Param("realName")String realName);
	
	/**��ѯͳ������ǩ������������*/
	List<GroupUser> queryCountSign(@Param("limit")Integer limit,@Param("limitSize")Integer limitSize,@Param("realName")String realName);
	/**ͳ������*/
	Integer countSign(@Param("realName")String realName);
	
	@Delete("delete from t_group_user where id=#{id}")
	Integer delGroupUser(@Param("id")Integer id);
	
	/**���ݷֹ�˾ ��ѯ �ֹ�˾�����е�Ա��*/
	List<GroupUser> queryByCompany(@Param("companyId")Integer companyId);
	
	/**��ѯ���е��û�����ȫ�ֶ�*/
	List<GroupUser> queryAll();
}
