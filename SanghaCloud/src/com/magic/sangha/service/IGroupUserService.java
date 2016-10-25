package com.magic.sangha.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.GroupUser;
import com.magic.sangha.bean.UserBean;

public interface IGroupUserService {
	
	GroupUser login(String mobile,String password);
	
	
	Integer register(GroupUser user) throws Exception;
	
	GroupUser findByToken(String token);
	
	Integer updateToken(String token,Integer id,Date date);
	
	Integer verifiMobile(String mobile);

	Integer updateGroupUser(GroupUser user);
	
	/**
	 *  �����û��Ƿ���Խ����ʼ�֪ͨ
	 * @param id
	 * @param isEmail
	 * @return
	 */
	Integer updateGroupUserIsEmail(Integer id,Integer isEmail);
	
	GroupUser findByMobile(String mobile);
	
	GroupUser findByIdToName(Integer id);
	/**
	 *   �������email��ָ������ 
	 *   
	 */
	void sendEmailToGroupUser(Integer officeId,UserBean user,HttpServletRequest req);
	/**
	 *  ��ѯTSC��ʱ��ͨ������������userId ��ѯ TSC
	 * @param userId
	 * @return
	 */
	List<GroupUser> findByUserId(Integer userId,Integer roleId);
	/**
	 *  ��ѯ�ܲ�����֧��
	 *   ���ֻ�� ͷ������ �� ID
	 * @return
	 */
	List<GroupUser> findHeadTech(Integer roleId,Integer companyId);
	/**
	 *  �����豸token �� �豸����
	 * @param id
	 * @param deviceType
	 * @param deviceToken
	 * @return
	 */
	Integer updateDeviceTypeAndToken(Integer id,Integer deviceType,String deviceToken);
	
	/**
	 *   ͨ���ֹ�˾ID  ��ѯ���ֹ�˾�� ��ͨ�쵼 �����쵼 �� �����쵼
	 * @return
	 */
	List<GroupUser> findGroupUserToManyRoleId(Integer companyId);
	
	/**
	 *  ���ݰ��´� �� ��ɫ ��ѯ ���´���ĳ��ɫ �� �û�
	 * @param officeId
	 * @param roleId
	 * @return
	 */
	List<GroupUser> findGroupUserByOfficeAndRole(Integer officeId,Integer roleId);

	/**
	 *  ����ID ��ѯ�û� �豸���� �� �豸token
	 * @param id
	 * @return
	 */
	GroupUser findTypeTokeById(Integer id);
	
	/**
	 *  ��ѯTSC��ʱ��ͨ������������userId ��ѯ TSC
	 * @param userId
	 * @return
	 */
	List<GroupUser> findByUserId(Integer userId);
	/**
	 *  ����������� �����û�
	 * @param tvId
	 * @return
	 */
	CutPageBean<GroupUser> findByAudit(Integer roleId,Integer status,Integer pageNO,Integer pageSize,String userName);
	/**
	 *  �����û���״̬�Լ���ɫ  ��ɫ��Ϊ��
	 * @param id
	 * @param status
	 * @param roleId
	 * @return
	 */
	Integer updateStatusAndRole(Integer id, Integer status, Integer roleId ) throws Exception;
	
	/**
	 *  ��� email �Ƿ�ע��
	 * @param email
	 * @return
	 */
	boolean checkEmailIsExist(String email);
	/**
	 *  ��ѯ Ա��
	 * @param userId
	 * @return
	 */
	GroupUser findById(Integer userId);
	
	/**
	 *   ��ѯ �� �Ǳ༼����ļ���
	 * @return
	 */
	List<GroupUser> findAllTechNOTFeibian(Integer userId);
	
	/**
	 *  �����������ͣ� ��ѯ �Ǳ༼��
	 * @param userId
	 * @param categoryId
	 * @return
	 */
	List<GroupUser> findAllTechFeibian(Integer userId,Integer categoryId);

	/**
	 *  ���� ����̨ID ��ѯ ��Ͻ����
	 * @param tvId
	 * @return
	 */
	List<GroupUser> findSaleByTvId(Integer tvId);
	/**
	 *  ���� Ա����Ϣ
	 * @param user
	 */
	void updateUser(GroupUser user);
	
	/**
	 *  ����TVID ��ѯ ���´��µ�����
	 * @param tvId
	 * @return
	 */
	List<GroupUser> findOfficeSaleByTvId(Integer tvId);
	/**
	 *  ������ѯ
	 * @param ids
	 * @return
	 */
	List<GroupUser> batchQueryGroupUser(List<Integer> ids);
	
	/**
	 *  ��ҳ��ѯ Ա�� ���� ��ɫ  ״̬Ϊ����״̬
	 * @param pageNO
	 * @param pageSize
	 * @param roleId
	 * @return
	 */
	CutPageBean<GroupUser> queryPageByRole(String realName,Integer pageNO,Integer pageSize,Integer roleId);
	
	/**
	 *  ��ѯ�����û�
	 * @return
	 */
	List<GroupUser> findAllToToken();
	/**
	 *  ͳ�� δ��� �� ȫ�� �û�����
	 * @return
	 */
	Map<String,Integer> countByStatus();
	
	/**
	 *  ��ѯ���ߵĿͷ�
	 * @return
	 */
	Integer countOnLineByService();
	
	/**
	 *   ��ҳ��ѯ ����Ա�� �� ר��
	 * @param pageNO
	 * @param pageSize
	 * @param realName
	 * @param roleId
	 * @return
	 */
	CutPageBean<GroupUser> queryPageLikeNameByRole(Integer pageNO,Integer pageSize,String realName);
	
	/**��ҳ��ѯ ǩ����¼*/
	CutPageBean<GroupUser> queryPageByUser(Integer pageNO,Integer pageSize,String realName);
	
	/**��ѯ�Ǳ��з�*/
	List<GroupUser> findAllDevelopFeibian (Integer categoryId);
	
	
	void delGroupUser(Integer id);
	
	/**���ݰ��´� ���� ���ݷֹ�˾��ѯ ��ҵ�û�*/
	List<GroupUser> queryByCompanyOrOffice(Integer companyId,Integer officeId);
}
