package com.magic.sangha.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.TVBean;

/**
 *  ����̨����־ò�ӿ�
 * @author QimouXie
 *
 */
public interface ITVMapper {

	int addTV(@Param("tv")TVBean tv);	
	
	/**
	 *  ͨ�����´���ѯ ����̨
	 * @param officeId
	 * @return
	 */
	List<TVBean> findByOfficeId(@Param("officeId")Integer officeId);
	
	/**
	 *   ���� ����̨���� ģ����ѯ ����̨�б�
	 * @param tvName
	 * @return
	 */
	List<TVBean> findLikeTVName(@Param("tvName")String tvName);
	
	TVBean findById(@Param("id")Integer id);
	
	/**
	 *  �����û�ID ��ѯ���ڵĵ���̨
	 * @param userId
	 * @return
	 */
	TVBean findByUserId(@Param("userId")Integer userId);
	/**
	 *  ��ѯ���� ����̨�б�
	 * @return
	 */
	List<TVBean> findAll();
	/**
	 *  ������� ����̨
	 * @param tvs
	 * @return
	 */
	Integer batchAddTV(@Param("tvs")List<TVBean> tvs);
	/**
	 *  ���ݵ���̨�����İ��´�״̬  ��ҳ��ѯ
	 * @param limit
	 * @param limitSize
	 * @param officeStatus 0 ȫ��   1 �ѷ��� 2 ������
	 * @return
	 */
	List<TVBean> findTVByoffice(@Param("limit")Integer limit,@Param("limitSize")Integer limitSize,@Param("officeStatus")Integer officeStatus,@Param("tvName")String tvName);
	/**
	 *  ���ݵ���̨�����İ��´�״̬ͳ��
	 * @param officeStatus
	 * @return
	 */
	Integer countTVByoffice(@Param("officeStatus")Integer officeStatus,@Param("tvName")String tvName);
	/**
	 *  �޸ĵ���̨�İ��´�ID
	 * @param id
	 * @param officeId
	 * @return
	 */
	Integer updateOffice(@Param("id")Integer id,@Param("officeId")Integer officeId);
	
	/**
	 *  ��ѯ Ա�� ������ ����̨ ����Ǳ����͵��ˣ�roleId Ϊnull
	 * @param groupUserId
	 * @param roleId
	 * @return
	 */
	List<TVBean> findByGroupUser(@Param("groupUserId")Integer groupUserId,@Param("roleId")Integer roleId);

	/**
	 *  ��̬ģ����ѯ
	 * @param tvName
	 * @return
	 */
	List<TVBean> dynamicGetTvs(@Param("tvName")String tvName);
	
	/***ͳ��ͬ������̨ ������*/
	Integer queryTVByTVName(@Param("tvName")String tvName);
	
	List<TVBean> getTVByUserType(@Param("userType")Integer userType,@Param("userId")Integer userId);
	/**
	 *  �޸ĵ���̨���ƺ�stationCode
	 * @param tv
	 * @return
	 */
	Integer updateTV(@Param("tv")TVBean tv);
}
