package com.magic.sangha.service;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.SignBean;

/**
 *  ǩ�� ҵ���ӿ�
 * @author QimouXie
 *
 */
public interface ISignService {
	/**
	 *  ǩ��
	 * @param sign
	 * @return
	 */
	Integer addSign(SignBean sign);
	/**
	 *  ��ѯ�������
	 * @param userId
	 * @return
	 */
	Integer findSumSignByUserId(Integer userId,Integer groupUserId);
	/**
	 *  ��ҳ��ѯǩ����¼
	 * @param pageNO
	 * @param pageSize
	 * @param userId
	 * @return
	 */
	CutPageBean<SignBean> findSignByUser(Integer pageNO,Integer pageSize,Integer userId,Integer groupUserId);
	/**
	 *  ��ѯ�û� ĳ���Ƿ�ǩ��
	 * @param userId
	 * @return
	 */
	SignBean findSignByUserId(Integer userId,String yyMMdd,Integer groupUserId);
	/**
	 *  ��ѯ��������
	 * @param id
	 * @return
	 */
	Integer findBaseBonus(Integer id);
	/**
	 *  ͳ��ǩ��������
	 * @param userId
	 * @return
	 */
	Integer countSignByUserId(Integer userId,Integer groupUserId);
	
	

}
