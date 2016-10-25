package com.magic.sangha.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.GroupOfficeBean;

/**
 *  ���´��־ò�ӿ�
 * @author QimouXie
 *
 */
public interface IGroupOfficeMapper {
	
	
	int addOffice(@Param("office")GroupOfficeBean office);
	/**
	 *  ���ݹ�˾ID��ѯ���´�
	 * @param comId
	 * @return
	 */
	List<GroupOfficeBean> findByComplanyId(@Param("comId")Integer comId);
	
	/**
	 *  ���ݰ��´����� ģ����ѯ
	 * @param officeName
	 * @return
	 */
	List<GroupOfficeBean> findLikeByOfficeName(@Param("officeName")String officeName,@Param("pageSize")Integer pageSize,@Param("pageSizeLimit")Integer pageSizeLimit);

	int countOffice(@Param("officeName")String officeName);
	
	GroupOfficeBean findById(@Param("id")Integer id);
	
	/**
	 *  ������ͨ�û�ID ��ѯ���´�
	 * @param userId
	 * @return
	 */
	GroupOfficeBean findByUserId(@Param("userId")Integer userId);
	
	/**���°��´�*/
	Integer update(@Param("office")GroupOfficeBean office);
	
	/**���ݹ�˾���� ��ѯ��˾*/
	Integer queryByOfficeName(@Param("officeName")String officeName);
}
