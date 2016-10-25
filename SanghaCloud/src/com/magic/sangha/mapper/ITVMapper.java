package com.magic.sangha.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.TVBean;

/**
 *  电视台管理持久层接口
 * @author QimouXie
 *
 */
public interface ITVMapper {

	int addTV(@Param("tv")TVBean tv);	
	
	/**
	 *  通过办事处查询 电视台
	 * @param officeId
	 * @return
	 */
	List<TVBean> findByOfficeId(@Param("officeId")Integer officeId);
	
	/**
	 *   根据 电视台名称 模糊查询 电视台列表
	 * @param tvName
	 * @return
	 */
	List<TVBean> findLikeTVName(@Param("tvName")String tvName);
	
	TVBean findById(@Param("id")Integer id);
	
	/**
	 *  根据用户ID 查询所在的电视台
	 * @param userId
	 * @return
	 */
	TVBean findByUserId(@Param("userId")Integer userId);
	/**
	 *  查询所有 电视台列表
	 * @return
	 */
	List<TVBean> findAll();
	/**
	 *  批量添加 电视台
	 * @param tvs
	 * @return
	 */
	Integer batchAddTV(@Param("tvs")List<TVBean> tvs);
	/**
	 *  根据电视台所处的办事处状态  分页查询
	 * @param limit
	 * @param limitSize
	 * @param officeStatus 0 全部   1 已分配 2 待分配
	 * @return
	 */
	List<TVBean> findTVByoffice(@Param("limit")Integer limit,@Param("limitSize")Integer limitSize,@Param("officeStatus")Integer officeStatus,@Param("tvName")String tvName);
	/**
	 *  根据电视台所处的办事处状态统计
	 * @param officeStatus
	 * @return
	 */
	Integer countTVByoffice(@Param("officeStatus")Integer officeStatus,@Param("tvName")String tvName);
	/**
	 *  修改电视台的办事处ID
	 * @param id
	 * @param officeId
	 * @return
	 */
	Integer updateOffice(@Param("id")Integer id,@Param("officeId")Integer officeId);
	
	/**
	 *  查询 员工 关联的 电视台 如果是被抄送的人，roleId 为null
	 * @param groupUserId
	 * @param roleId
	 * @return
	 */
	List<TVBean> findByGroupUser(@Param("groupUserId")Integer groupUserId,@Param("roleId")Integer roleId);

	/**
	 *  动态模糊查询
	 * @param tvName
	 * @return
	 */
	List<TVBean> dynamicGetTvs(@Param("tvName")String tvName);
	
	/***统计同名电视台 的数量*/
	Integer queryTVByTVName(@Param("tvName")String tvName);
	
	List<TVBean> getTVByUserType(@Param("userType")Integer userType,@Param("userId")Integer userId);
	/**
	 *  修改电视台名称和stationCode
	 * @param tv
	 * @return
	 */
	Integer updateTV(@Param("tv")TVBean tv);
}
