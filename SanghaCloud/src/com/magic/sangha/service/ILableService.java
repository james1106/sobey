package com.magic.sangha.service;

import java.util.List;


import com.magic.sangha.bean.LableBean;

/**
 *  ���۱�ǩ  ҵ���ӿ�
 * @author QimouXie
 *
 */
public interface ILableService {
	

	/**
	 *  ������� ��ǩ
	 * @param lables
	 * @return
	 */
	Integer batchAdd(List<LableBean> lables);
	
	
	/**
	 *  ͨ�� ��ɫ���� ��ѯ��ǩ
	 * @param roleId
	 * @return
	 */
	List<LableBean> findByRoleId(Integer roleId);
	
	/**
	 *  ��������
	 * @param ids
	 * @param type
	 */
	void updateLables(List<Integer> ids,Integer type) throws Exception;
	
	List<LableBean> findAll(Integer roleId);
	
	void batchDel(List<Integer> ids);

}
