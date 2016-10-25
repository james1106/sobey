package com.magic.sangha.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.LableBean;

/**
 *  ���۱�ǩ �־ò�ӿ�
 * @author QimouXie
 *
 */
public interface ILableMapper {
	
	/**
	 *  ������� ��ǩ
	 * @param lables
	 * @return
	 */
	Integer batchAdd(@Param("lables")List<LableBean> lables);
	
	
	/**
	 *  ͨ�� ��ɫ���� ��ѯ��ǩ
	 * @param roleId
	 * @return
	 */
	List<LableBean> findByRoleId(@Param("roleId")Integer roleId);
	
	/**
	 *  ������ѯ
	 * @param ids
	 * @return
	 */
	List<LableBean> batchSelect(@Param("ids")List<Integer> ids);
	
	/**
	 *  �������� 
	 * @param ids
	 * @param type
	 */
	void batchUpdateLables(@Param("ids")List<Integer> ids,@Param("type")Integer type);
	
	/**	 */
	List<LableBean> findAll(@Param("roleId")Integer roleId);
	
	/**����ɾ��*/
	void batchDel(@Param("ids")List<Integer> ids);
	
	
}
