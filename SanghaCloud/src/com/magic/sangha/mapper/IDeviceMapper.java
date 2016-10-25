package com.magic.sangha.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.DeviceBean;

/**
 *  �豸�б� �־ò���
 * @author QimouXie
 *
 */
public interface IDeviceMapper {
	
	/**
	 *   �������� �豸
	 * @param devices
	 * @return
	 */
	Integer batchAddDevice(@Param("devices")List<DeviceBean> devices);
	
	/**
	 *  ��ҳ��ѯ �豸�б�
	 * @param stationCode
	 * @param groupCode
	 * @param limit
	 * @param limitSize
	 * @return
	 */
	List<DeviceBean> findPageData(@Param("stationCodes")List<String> stationCodes,@Param("groupCode")String groupCode,@Param("limit")Integer limit,@Param("limitSize")Integer limitSize);
	
	/**
	 *   �豸����ͳ��
	 * @param stationCode
	 * @param groupCode
	 * @return
	 */
	Integer countDevice(@Param("stationCodes")List<String> stationCodes,@Param("groupCode")String groupCode);
	
	/**
	 *  �����ݿ��� ��ѯ �� �����б��� ���ڵ��豸
	 * @return
	 */
	List<DeviceBean> findAllDevice();

}
