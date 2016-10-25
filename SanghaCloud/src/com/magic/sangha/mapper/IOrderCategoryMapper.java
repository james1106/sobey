package com.magic.sangha.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.OrderCategoryBean;

/**
 *  订单分类 的持久层接口
 * @author QimouXie
 *
 */
public interface IOrderCategoryMapper {
	
	@Insert("insert into t_order_category (category_name,category_type) values(#{category.categoryName},#{category.type})")
	Integer addCategory(@Param("category")OrderCategoryBean category);
	
	List<OrderCategoryBean> findByType(@Param("type")Integer type);
	
	OrderCategoryBean findById(@Param("id")Integer id);
	
	List<OrderCategoryBean> findAll();
	
	List<OrderCategoryBean> findByTypePage(@Param("limit")Integer limit,@Param("limitSize")Integer limitSize,@Param("type")Integer type);
	
	Integer countByType(@Param("type")Integer type);
	
	void updateCategory(@Param("category")OrderCategoryBean category);
	
	/**
	 *  批量新增
	 * @param cateList
	 * @return
	 */
	Integer batchAdd(@Param("cateList")List<OrderCategoryBean> cateList);

}
