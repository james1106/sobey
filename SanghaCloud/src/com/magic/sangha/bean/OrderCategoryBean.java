package com.magic.sangha.bean;

import java.io.Serializable;
/**
 *  订单分类Bean
 * @author QimouXie
 *
 */
public class OrderCategoryBean implements Serializable {

	private static final long serialVersionUID = -844871850629137095L;
	
	/**主键*/
	private Integer id;
	
	/**分类的名称*/
	private String categoryName;
	
	/**该分类的上一层分类信息 0:软件类 1:硬件类*/
	private Integer type;

	public OrderCategoryBean() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	

}
