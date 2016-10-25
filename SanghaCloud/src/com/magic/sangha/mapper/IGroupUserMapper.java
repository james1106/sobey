package com.magic.sangha.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.GroupUser;

public interface IGroupUserMapper {
	/**
	 *  根据ID查询企业用户
	 * @param id
	 * @return
	 */
	GroupUser findById(@Param("id")Integer id);
	/**
	 *  登录
	 * @param mobile
	 * @param password
	 * @return
	 */
	GroupUser login (@Param("mobile")String mobile,@Param("password")String password);
	
	/**
	 *  注册
	 * @param user
	 * @return
	 */
	Integer register(@Param("user")GroupUser user);
	/**
	 *  根据角色查询企业用户
	 * @param roleId
	 * @return
	 */
	List<GroupUser> findByRole(@Param("roleId")Integer roleId);
	
	/**
	 *  根据token查询用户
	 * @param token
	 * @return
	 */
	GroupUser findByToken(@Param("token")String token);
	/**
	 *  更新token
	 * @param token
	 * @return
	 */
	Integer updateToken(@Param("token")String token,@Param("id")Integer id,@Param("datetime")Date date);
	/**
	 *  验证手机号是否被注册
	 * @param mobile
	 * @return
	 */
	Integer verifiMobile(@Param("mobile")String mobile);
	
	Integer updateGroupUser(@Param("user")GroupUser user);
	
	/**
	 *  更新企业用户是否能收到 邮件通知
	 * @param isEmail
	 * @return
	 */
	Integer updateGroupUserIsEmail(@Param("id")Integer id,@Param("isEmail")Integer isEmail);
	
	/**
	 *  通过手机号查找用户
	 * @param mobile
	 * @return
	 */
	GroupUser findByMobile(@Param("mobile")String mobile);
	
	/**
	 *  通过ID查询用户的名字和电话
	 * @param id
	 * @return
	 */
	GroupUser findByIdToName(@Param("id")Integer id);
	
	/**
	 *   根据办事处ID 查询员工用户
	 * @param id
	 * @return
	 */
	List<GroupUser> findByOfficeId(@Param("officeId")Integer officeId,@Param("isEmail")Integer isEmail);
	
	/**
	 *  查询TSC的时候，通过订单所属的userId 查询 TSC
	 * @param userId
	 * @return
	 */
	List<GroupUser> findByUserId(@Param("userId") Integer userId,@Param("roleId")Integer roleId);
	/**
	 *  查询总部技术支持
	 *   结果只有 头像、名字 和 ID
	 * @return
	 */
	List<GroupUser> findHeadTech(@Param("roleId")Integer roleId,@Param("companyId")Integer companyId);
	
	/**
	 *  根据办事处 和 角色 查询 办事处的某角色 的 用户
	 * @param officeId
	 * @param roleId
	 * @return
	 */
	List<GroupUser> findGroupUserByOfficeAndRole(@Param("officeId")Integer officeId,@Param("roleId")Integer roleId);
	
	/**
	 *  更新设备token 和 设备类型
	 * @param id
	 * @param deviceType
	 * @param deviceToken
	 * @return
	 */
	Integer updateDeviceTypeAndToken(@Param("id")Integer id,@Param("deviceType")Integer deviceType,@Param("deviceToken")String deviceToken);
	
	/**
	 *   通过分公司ID  查询出分公司的 普通领导 销售领导 和 技术领导
	 * @return
	 */
	List<GroupUser> findGroupUserToManyRoleId(@Param("companyId")Integer companyId);
	
	/**
	 *  根据ID 查询用户 设备类型 和 设备token
	 * @param id
	 * @return
	 */
	GroupUser findTypeTokeById(@Param("id")Integer id);
	
	List<GroupUser> findHeadTechByRoleId(@Param("roleId")Integer roleId);
	
	/**
	 *  根据审核类型 查找用户
	 * @param tvId
	 * @return
	 */
	List<GroupUser> findByAudit(@Param("roleId")Integer roleId,@Param("limit")Integer limit,@Param("pageLimit")Integer pageLimit,@Param("isAduit")Integer isAudit,@Param("userName")String userName);
	/**
	 *   根据状态统计用户数量
	 * @param status
	 * @return
	 */
	Integer countByStatus(@Param("status")Integer status,@Param("roleId")Integer roleId,@Param("userName")String userName);
	
	/**
	 *  修改用户的状态以及角色类型
	 * @param id
	 * @param status
	 * @param roleId
	 * @return
	 */
	Integer updateStatusAndRole(@Param("id")Integer id,@Param("status")Integer status,@Param("roleId")Integer roleId);
	/**
	 *  根据 办事处的ID 和 当前办事处下的 发送邮件的人
	 * @param officeId
	 * @param isEmail
	 * @return
	 */
	GroupUser findByOfficeAndIsEmail(@Param("officeId")Integer officeId,@Param("isEmail")Integer isEmail,@Param("roleId")Integer roleId);
	/**
	 *  根据 email 查询用户
	 * @param email
	 * @return
	 */
	List<GroupUser> findByEmail(@Param("email")String email);
	/**
	 *   查询 除 非编技术外的技术
	 * @return
	 */
	List<GroupUser> findAllTechNOTFeibian(@Param("userId")Integer userId);
	
	/**
	 *  查询非编技术
	 * @param userId
	 * @return
	 */
	List<GroupUser> findAllTechFeibian(@Param("userId")Integer userId,@Param("categoryId")Integer categoryId);
	
	/**查询非编研发*/
	List<GroupUser> findAllDevelopFeibian(@Param("categoryId")Integer categoryId);
	
	/**
	 *  根据电视台， 查询 销售(总部销售，分公司销售，办事处销售)
	 * @param tvId
	 * @return
	 */
	List<GroupUser> findSaleByTVId(@Param("tvId")Integer tvId);
	
	/**
	 *  更新 user 信息 PC端修改数据
	 * @param user
	 * @return
	 */
	Integer updateUser(@Param("user")GroupUser user);
	
	/**
	 *  根据电视台 查询办事处的销售列表
	 * @param tvId
	 * @return
	 */
	List<GroupUser> findOfficeSaleByTvId(@Param("tvId")Integer tvId);
	
	/**
	 *  批量查询员工
	 * @param ids
	 * @return
	 */
	List<GroupUser> batchQueryGroupUser(@Param("ids")List<Integer> ids);
	
	/**
	 *  查询所有用户的token
	 * @return
	 */
	List<GroupUser> findAllToToken();
	
	/**
	 *  根据角色查询 审核通过以及非冻结状态下的员工
	 * @param limit
	 * @param limitSize
	 * @param roleId
	 * @return
	 */
	List<GroupUser> findPageByRole(@Param("realName")String realName,@Param("limit")Integer limit,@Param("limitSize")Integer limitSize,@Param("roleId")Integer roleId);
	
	/**
	 *  根据角色统计 审核通过以及 非冻结状态下的员工数量
	 * @param roleId
	 * @return
	 */
	Integer countByRole(@Param("realName")String realName,@Param("roleId")Integer roleId);
	
	/**
	 *  统计客服在线人数
	 * @return
	 */
	Integer countOnLineByService();
	
	/**
	 *   模糊分页查询 技术员工 以及 是否非编技术
	 * @return
	 */
	List<GroupUser> findPageTech(@Param("limit")Integer limit,@Param("limitSize")Integer limitSize,@Param("realName")String realName);
	/**
	 *  名字模糊 统计
	 * @param realName
	 * @return
	 */
	Integer countPageTech(@Param("realName")String realName);
	
	/**查询统计所有签到次数和天数*/
	List<GroupUser> queryCountSign(@Param("limit")Integer limit,@Param("limitSize")Integer limitSize,@Param("realName")String realName);
	/**统计总数*/
	Integer countSign(@Param("realName")String realName);
	
	@Delete("delete from t_group_user where id=#{id}")
	Integer delGroupUser(@Param("id")Integer id);
	
	/**根据分公司 查询 分公司下所有的员工*/
	List<GroupUser> queryByCompany(@Param("companyId")Integer companyId);
	
	/**查询所有的用户，非全字段*/
	List<GroupUser> queryAll();
}
