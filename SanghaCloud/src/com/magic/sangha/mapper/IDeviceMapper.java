package com.magic.sangha.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.DeviceBean;

/**
 *  设备列表 持久层框架
 * @author QimouXie
 *
 */
public interface IDeviceMapper {
	
	/**
	 *   批量新增 设备
	 * @param devices
	 * @return
	 */
	Integer batchAddDevice(@Param("devices")List<DeviceBean> devices);
	
	/**
	 *  分页查询 设备列表
	 * @param stationCode
	 * @param groupCode
	 * @param limit
	 * @param limitSize
	 * @return
	 */
	List<DeviceBean> findPageData(@Param("stationCodes")List<String> stationCodes,@Param("groupCode")String groupCode,@Param("limit")Integer limit,@Param("limitSize")Integer limitSize);
	
	/**
	 *   设备数量统计
	 * @param stationCode
	 * @param groupCode
	 * @return
	 */
	Integer countDevice(@Param("stationCodes")List<String> stationCodes,@Param("groupCode")String groupCode);
	
	/**
	 *  在数据库里 查询 在 最新列表里 存在的设备
	 * @return
	 */
	List<DeviceBean> findAllDevice();

}
