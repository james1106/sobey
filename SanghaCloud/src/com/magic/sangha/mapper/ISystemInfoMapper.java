package com.magic.sangha.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.SystemInfoBean;

/**
 *  ϵͳ������Ϣ �־ò�ӿ�
 * @author QimouXie
 *
 */
public interface ISystemInfoMapper {
	
	/**
	 *   ��� ϵͳ��Ϣ
	 * @param system
	 * @return
	 */
	Integer addSystemInfo(@Param("system")SystemInfoBean system);
	
	/**
	 *   ��ҳ��ѯ ϵͳ��Ϣ����
	 * @param group 0 �û� 1 Ա��
	 * @param limit
	 * @param limitSize
	 * @return
	 */
	List<SystemInfoBean> queryInfoPage(@Param("group")Integer group,@Param("limit")Integer limit,@Param("limitSize")Integer limitSize);
	
	/**
	 *  ͳ��
	 * @param group
	 * @return
	 */
	Integer countSystemInfo(@Param("group")Integer group);
	
	SystemInfoBean findById(@Param("id")Integer id);
	/**
	 *  ɾ��
	 * @param id
	 */
	void delSystemInfo(@Param("id")Integer id);
	
	/**
	 *  ���� ֪ͨ
	 * @param info
	 * @return
	 */
	Integer updateSystemInfo(@Param("info")SystemInfoBean info) ;

}
