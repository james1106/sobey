package com.magic.sangha.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.GroupOfficeBean;

/**
 *  办事处持久层接口
 * @author QimouXie
 *
 */
public interface IGroupOfficeMapper {
	
	
	int addOffice(@Param("office")GroupOfficeBean office);
	/**
	 *  根据公司ID查询办事处
	 * @param comId
	 * @return
	 */
	List<GroupOfficeBean> findByComplanyId(@Param("comId")Integer comId);
	
	/**
	 *  根据办事处名称 模糊查询
	 * @param officeName
	 * @return
	 */
	List<GroupOfficeBean> findLikeByOfficeName(@Param("officeName")String officeName,@Param("pageSize")Integer pageSize,@Param("pageSizeLimit")Integer pageSizeLimit);

	int countOffice(@Param("officeName")String officeName);
	
	GroupOfficeBean findById(@Param("id")Integer id);
	
	/**
	 *  根据普通用户ID 查询办事处
	 * @param userId
	 * @return
	 */
	GroupOfficeBean findByUserId(@Param("userId")Integer userId);
	
	/**更新办事处*/
	Integer update(@Param("office")GroupOfficeBean office);
	
	/**根据公司名称 查询公司*/
	Integer queryByOfficeName(@Param("officeName")String officeName);
}
