package com.magic.sangha.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.RelationTVBean;

/**
 *  TV��
 * @author QimouXie
 *
 */
public interface IRelationTVMapper {
	
	/**��������*/
	Integer addBatch(@Param("relations")List<RelationTVBean> relations);
	
	/**ɾ��Ա�������ĵ���̨*/
	Integer del(@Param("groupUserId")Integer groupUserId);
	
	/**ͨ��userId��ѯ ��������̨*/
	List<RelationTVBean> queryByUserId(@Param("userId")Integer userId);
	
	/***/
	List<RelationTVBean> findByUserId(@Param("userId")Integer userId);

}
