package com.magic.sangha.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.ProductFeelBean;

/**
 *  �û����� �־ò�ӿ�
 * @author QimouXie
 *
 */
public interface IProductFeelMapper {
	
	Integer add(@Param("feel")ProductFeelBean feel);
	
	Integer queryCheckIsFeel(@Param("userId")Integer userId);
	
	List<ProductFeelBean> queryPage(@Param("limit")Integer limit,@Param("limitSize")Integer limitSize);

	Integer countPage();
}
