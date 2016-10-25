package com.magic.sangha.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.GroupUserCategoryBean;

public interface IGroupUserCategoryMapper {
	/**
	 *  ��Ӽ�¼
	 * @param gcb
	 * @return
	 */
	Integer addGroupUserCategory(@Param("gcb")GroupUserCategoryBean gcb);
	
	/**
	 *  �������ID ��ѯ����Ӧ�ļ�����Ա
	 * @param categoryId
	 * @return
	 */
	List<GroupUserCategoryBean> findByCategory(@Param("categoryId")Integer categoryId);
	
	/**
	 *  ��ѯ���еķǱ༼��
	 * @return
	 */
	List<GroupUserCategoryBean> findAllByGroupUser();
	
	/**
	 *   ���ݼ���Ա��ID ��ѯ����Ӧ�� ���ܷ���
	 * @param groupUserId
	 * @return
	 */
	List<GroupUserCategoryBean> findCategoryByGroupUserId(@Param("groupUserId")Integer groupUserId);
	
	/**
	 *  ��� ָ��Ա����ָ������
	 * @param groupUserId
	 * @return
	 */
	Integer delCategoryByGroupUserId(@Param("groupUserId")Integer groupUserId);
	
	/**
	 *   ��ѯ�����е� �������⣬������Ա����ӵ�е� ���༼��
	 * @param groupUserId
	 * @return
	 */
	List<GroupUserCategoryBean> findCategoryAdmin(@Param("groupUserId")Integer groupUserId);
	
	/**
	 *  ��������
	 * @return
	 */
	Integer batchAdd(@Param("categorys")List<GroupUserCategoryBean> categorys);

}
