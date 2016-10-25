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
	 *  更新用户是否可以接受邮件通知
	 * @param id
	 * @param isEmail
	 * @return
	 */
	Integer updateGroupUserIsEmail(Integer id,Integer isEmail);
	
	GroupUser findByMobile(String mobile);
	
	GroupUser findByIdToName(Integer id);
	/**
	 *   发送审核email给指定的人 
	 *   
	 */
	void sendEmailToGroupUser(Integer officeId,UserBean user,HttpServletRequest req);
	/**
	 *  查询TSC的时候，通过订单所属的userId 查询 TSC
	 * @param userId
	 * @return
	 */
	List<GroupUser> findByUserId(Integer userId,Integer roleId);
	/**
	 *  查询总部技术支持
	 *   结果只有 头像、名字 和 ID
	 * @return
	 */
	List<GroupUser> findHeadTech(Integer roleId,Integer companyId);
	/**
	 *  更新设备token 和 设备类型
	 * @param id
	 * @param deviceType
	 * @param deviceToken
	 * @return
	 */
	Integer updateDeviceTypeAndToken(Integer id,Integer deviceType,String deviceToken);
	
	/**
	 *   通过分公司ID  查询出分公司的 普通领导 销售领导 和 技术领导
	 * @return
	 */
	List<GroupUser> findGroupUserToManyRoleId(Integer companyId);
	
	/**
	 *  根据办事处 和 角色 查询 办事处的某角色 的 用户
	 * @param officeId
	 * @param roleId
	 * @return
	 */
	List<GroupUser> findGroupUserByOfficeAndRole(Integer officeId,Integer roleId);

	/**
	 *  根据ID 查询用户 设备类型 和 设备token
	 * @param id
	 * @return
	 */
	GroupUser findTypeTokeById(Integer id);
	
	/**
	 *  查询TSC的时候，通过订单所属的userId 查询 TSC
	 * @param userId
	 * @return
	 */
	List<GroupUser> findByUserId(Integer userId);
	/**
	 *  根据审核类型 查找用户
	 * @param tvId
	 * @return
	 */
	CutPageBean<GroupUser> findByAudit(Integer roleId,Integer status,Integer pageNO,Integer pageSize,String userName);
	/**
	 *  更新用户的状态以及角色  角色可为空
	 * @param id
	 * @param status
	 * @param roleId
	 * @return
	 */
	Integer updateStatusAndRole(Integer id, Integer status, Integer roleId ) throws Exception;
	
	/**
	 *  检查 email 是否被注册
	 * @param email
	 * @return
	 */
	boolean checkEmailIsExist(String email);
	/**
	 *  查询 员工
	 * @param userId
	 * @return
	 */
	GroupUser findById(Integer userId);
	
	/**
	 *   查询 除 非编技术外的技术
	 * @return
	 */
	List<GroupUser> findAllTechNOTFeibian(Integer userId);
	
	/**
	 *  根据问题类型， 查询 非编技术
	 * @param userId
	 * @param categoryId
	 * @return
	 */
	List<GroupUser> findAllTechFeibian(Integer userId,Integer categoryId);

	/**
	 *  根据 电视台ID 查询 所辖销售
	 * @param tvId
	 * @return
	 */
	List<GroupUser> findSaleByTvId(Integer tvId);
	/**
	 *  更新 员工信息
	 * @param user
	 */
	void updateUser(GroupUser user);
	
	/**
	 *  根据TVID 查询 办事处下的销售
	 * @param tvId
	 * @return
	 */
	List<GroupUser> findOfficeSaleByTvId(Integer tvId);
	/**
	 *  批量查询
	 * @param ids
	 * @return
	 */
	List<GroupUser> batchQueryGroupUser(List<Integer> ids);
	
	/**
	 *  分页查询 员工 根据 角色  状态为正常状态
	 * @param pageNO
	 * @param pageSize
	 * @param roleId
	 * @return
	 */
	CutPageBean<GroupUser> queryPageByRole(String realName,Integer pageNO,Integer pageSize,Integer roleId);
	
	/**
	 *  查询所有用户
	 * @return
	 */
	List<GroupUser> findAllToToken();
	/**
	 *  统计 未审核 和 全部 用户数量
	 * @return
	 */
	Map<String,Integer> countByStatus();
	
	/**
	 *  查询在线的客服
	 * @return
	 */
	Integer countOnLineByService();
	
	/**
	 *   分页查询 技术员工 的 专长
	 * @param pageNO
	 * @param pageSize
	 * @param realName
	 * @param roleId
	 * @return
	 */
	CutPageBean<GroupUser> queryPageLikeNameByRole(Integer pageNO,Integer pageSize,String realName);
	
	/**分页查询 签到记录*/
	CutPageBean<GroupUser> queryPageByUser(Integer pageNO,Integer pageSize,String realName);
	
	/**查询非编研发*/
	List<GroupUser> findAllDevelopFeibian (Integer categoryId);
	
	
	void delGroupUser(Integer id);
	
	/**根据办事处 或者 根据分公司查询 企业用户*/
	List<GroupUser> queryByCompanyOrOffice(Integer companyId,Integer officeId);
}
