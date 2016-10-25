package com.magic.sangha.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.SuggestBean;

public interface ISuggestMapper {
	
	
	Integer add(@Param("suggest")SuggestBean suggest);
	
	/**��ҳ��ѯ*/
	List<SuggestBean> queryPage(@Param("limit")Integer limit,@Param("limitSize")Integer limitSize);
	
	Integer countPage();
}
