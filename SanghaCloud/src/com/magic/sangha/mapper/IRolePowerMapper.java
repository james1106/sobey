package com.magic.sangha.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.RoleBean;
import com.magic.sangha.bean.RolePowerBean;

public interface IRolePowerMapper {
	
	/**
	 *  ���� Ȩ�� ��ѯ��ɫ
	 * @param powerId
	 * @return
	 */
	List<RoleBean> findByPowerId(@Param("powerId")Integer powerId);
	
	/**
	 *  ���� ��ɫ ��ѯ Ȩ��
	 * @param roleId
	 * @return
	 */
	List<RolePowerBean> findByRoleId(@Param("roleId")Integer roleId);
	
	/**
	 *  ����ɫ����Ȩ��
	 * @param roleId
	 * @param powerId
	 * @return
	 */
	Integer updateRolePower(Integer roleId,Integer powerId);

	/**���ݽ�ɫ ɾ��Ȩ��*/
	Integer delRolePower(@Param("roleId")Integer roleId);
	
	/**��������*/
	Integer addBatch(@Param("rolePowers")List<RolePowerBean> rolePowers);
	
	
}
