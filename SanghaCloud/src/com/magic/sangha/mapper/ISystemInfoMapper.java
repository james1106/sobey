package com.magic.sangha.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.SystemInfoBean;

/**
 *  系统推送消息 持久层接口
 * @author QimouXie
 *
 */
public interface ISystemInfoMapper {
	
	/**
	 *   添加 系统消息
	 * @param system
	 * @return
	 */
	Integer addSystemInfo(@Param("system")SystemInfoBean system);
	
	/**
	 *   分页查询 系统消息数据
	 * @param group 0 用户 1 员工
	 * @param limit
	 * @param limitSize
	 * @return
	 */
	List<SystemInfoBean> queryInfoPage(@Param("group")Integer group,@Param("limit")Integer limit,@Param("limitSize")Integer limitSize);
	
	/**
	 *  统计
	 * @param group
	 * @return
	 */
	Integer countSystemInfo(@Param("group")Integer group);
	
	SystemInfoBean findById(@Param("id")Integer id);
	/**
	 *  删除
	 * @param id
	 */
	void delSystemInfo(@Param("id")Integer id);
	
	/**
	 *  更新 通知
	 * @param info
	 * @return
	 */
	Integer updateSystemInfo(@Param("info")SystemInfoBean info) ;

}
