package com.magic.sangha.service;

import java.util.List;

/**
 *  ��ɫ��ӦȨ�� ҵ���ӿ�
 * @author QimouXie
 *
 */
public interface IRolePowerService {

	/**���½�ɫ��Ӧ��Ȩ��*/
	void updatePower(Integer roleId,List<Integer> ids) throws Exception;
	
}
