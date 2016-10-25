package com.magic.sangha.service;

import java.util.List;


import com.magic.sangha.bean.GroupUserCategoryBean;

/**
 *  ����Ա�������רҵ ҵ���ӿ�
 * @author QimouXie
 *
 */
public interface IGroupUserCategoryService {
	/**
	 *  ��Ӽ�¼
	 * @param gcb
	 * @return
	 */
	Integer addGroupUserCategory(GroupUserCategoryBean gcb);
	
	/**
	 *  �������ID ��ѯ����Ӧ�ļ�����Ա
	 * @param categoryId
	 * @return
	 */
	List<GroupUserCategoryBean> findByCategory(Integer categoryId);
	
	/**
	 *  ����ֻ�� groupUser�ֶ�
	 * @return
	 */
	List<GroupUserCategoryBean> findAllByGroupUser();
	

	/**
	 *  ��� ָ��Ա����ָ������
	 * @param groupUserId
	 * @return
	 */
	Integer delCategoryByGroupUserId(Integer groupUserId) throws Exception;
	
	/**
	 *   ��ѯ�����е� �������⣬������Ա����ӵ�е� ���༼��
	 * @param groupUserId
	 * @return
	 */
	List<GroupUserCategoryBean> findCategoryAdmin(Integer groupUserId);
	
	void updateGroupUserTech(List<GroupUserCategoryBean> categorys,Integer groupUserId) throws Exception;

}
