package com.magic.sangha.service;

import java.util.List;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.GroupOfficeBean;

/**
 *  ���´� ҵ���ӿ�
 * @author QimouXie
 *
 */
public interface IGroupOfficeService {

	
	Integer addOffice(GroupOfficeBean office);
	
	/**
	 *  ���ݹ�˾ ��ѯ���´�
	 * @param companyId
	 * @return
	 */
	List<GroupOfficeBean> findByComplanyId(Integer companyId);
	
	/**
	 *  ��ҳģ����ѯ���´�
	 * @param officeName
	 * @param pageSize
	 * @param pageSizeLimit
	 * @return
	 */
	CutPageBean<GroupOfficeBean> findLikeOfficeName (String officeName,Integer pageSize,Integer pageNO);
	
	GroupOfficeBean findById(Integer id);
	/**
	 *  ������ͨ�û�ID ��ѯ���´�
	 * @param userId
	 * @return
	 */
	GroupOfficeBean findByUserId(Integer userId);
	
	Integer update(GroupOfficeBean office);
	
	/**���ݹ�˾���Ʋ�ѯ��˾*/
	Integer queryByOfficeName(String officeName);
}
