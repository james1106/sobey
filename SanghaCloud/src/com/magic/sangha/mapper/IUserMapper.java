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
	 *  更新token
	 * @param token
	 * @return
	 */
	Integer updateToken(@Param("token")String token,@Param("id")Integer id,@Param("updatetime")Date date);
	/**
	 *  验证手机号是否存在
	 * @param mobile
	 * @return
	 */
	Integer verifiMobile(@Param("mobile")String mobile);
	
	/**
	 *  修改个人资料
	 * @param user
	 * @return
	 */
	Integer updateUser(@Param("user")UserBean user);
	
	/**
	 *  根据手机号 查询 用户
	 * @param mobile
	 * @return
	 */
	UserBean findByMobile(@Param("mobile")String mobile);
	
	/**
	 *  根据ID 查询用户的名字和电话号码
	 * @param id
	 * @return
	 */
	UserBean findById(@Param("id")Integer id);
	
	/**
	 *  更新设备token 和 设备类型
	 * @param id
	 * @param deviceType
	 * @param deviceToken
	 * @return
	 */
	Integer updateDeviceTypeAndToken(@Param("id")Integer id,@Param("deviceType")Integer deviceType,@Param("deviceToken")String deviceToken);
	
	/**
	 *  根据电视台 查找用户
	 * @param tvId
	 * @return
	 */
	List<UserBean> findByTVId(@Param("tvId")Integer tvId);
	
	/**
	 *  根据审核类型 查找用户
	 * @param tvId
	 * @return
	 */
	List<UserBean> findByAudit(@Param("limit")Integer limit,@Param("pageLimit")Integer pageLimit,@Param("isAduit")Integer isAudit,@Param("userName")String userName);
	/**
	 *   根据状态统计用户数量
	 * @param status
	 * @return
	 */
	Integer countByStatus(@Param("status")Integer status,@Param("userName")String userName);
	
	/**
	 *  更新用户的 状态
	 * @param id
	 * @param status
	 * @return
	 */
	Integer updateStatus(@Param("id")Integer id,@Param("status")Integer status,@Param("isPUser")Integer isPUser);
	
	/**
	 *  根据 email 查询用户
	 * @param email
	 * @return
	 */
	List<UserBean> findByEmail(@Param("email")String email);
	/**
	 *  查询用户所有的字段
	 * @param id
	 * @return
	 */
	UserBean findByIdAllFields(@Param("id")Integer id);
	
	
	/**
	 *  查询所有用户的token
	 * @return
	 */
	List<UserBean> findAllToToken();
	
	/**查询统计所有签到次数和天数*/
	List<UserBean> queryCountSign(@Param("limit")Integer limit,@Param("limitSize")Integer limitSize,@Param("realName")String realName);
	/**统计总数*/
	Integer countSign(@Param("realName")String realName);
	
	
}
