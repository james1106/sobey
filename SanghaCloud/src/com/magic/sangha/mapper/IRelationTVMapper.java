package com.magic.sangha.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.RelationTVBean;

/**
 *  TV绑定
 * @author QimouXie
 *
 */
public interface IRelationTVMapper {
	
	/**批量新增*/
	Integer addBatch(@Param("relations")List<RelationTVBean> relations);
	
	/**删除员工关联的电视台*/
	Integer del(@Param("groupUserId")Integer groupUserId);
	
	/**通过userId查询 关联电视台*/
	List<RelationTVBean> queryByUserId(@Param("userId")Integer userId);
	
	/***/
	List<RelationTVBean> findByUserId(@Param("userId")Integer userId);

}
