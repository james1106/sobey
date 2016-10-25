package com.magic.sangha.service;

import java.util.List;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.TVBean;

/**
 *  ����̨���� ҵ���ӿ�
 * @author QimouXie
 *
 */
public interface ITVService {
	
	Integer addTV(TVBean tv);
	
	/**
	 *  ���� ���´� ��ѯ����̨
	 * @param officeId
	 * @return
	 */
	List<TVBean> findByOfficeId(Integer officeId);
	
	TVBean findById(Integer id);

	/**
	 *  ������� ����̨
	 * @param tvs
	 * @return
	 */
	List<TVBean> batchAddTV(List<TVBean> tvs);
	/**
	 *  ����״̬��ҳ��ѯ ����̨
	 * @param pageNO
	 * @param pageSize
	 * @param officeStatus
	 * @return
	 */
	CutPageBean<TVBean> findByStatus(Integer pageNO,Integer pageSize,Integer officeStatus,String tvName);
	
	Integer updateTVToOffice(Integer id,Integer officeId);
	/**
	 *  ͨ�����´� ��ѯ���µĵ���̨
	 * @param officeId
	 * @return
	 */
	List<TVBean> findTVSByOffice(Integer officeId);
	
	/**
	 *  ��ȡ�û� ���䶩�������������ĵ���̨
	 * @param userId
	 * @param groupUserId
	 * @return
	 */
	List<TVBean> findByUserIdAndOrder(Integer userId,Integer groupUserId,Integer roleId);
	
	/**
	 *  ��̬ ģ����ѯ����̨
	 * @param tvName
	 * @return
	 */
	List<TVBean> dynamicGetTvs(String tvName);
	/***ͳ��ͬ������̨ ������*/
	Integer queryTVByTVName(String tvName);
	
	/**�����û���������ѯ ����̨*/
	List<TVBean> getTVByUserType(Integer roleId,Integer userId);
	
	Integer updateTV(TVBean tv);
}
