package com.magic.sangha.bean;

import java.io.Serializable;
/**
 *  ��������Bean
 * @author QimouXie
 *
 */
public class OrderCategoryBean implements Serializable {

	private static final long serialVersionUID = -844871850629137095L;
	
	/**����*/
	private Integer id;
	
	/**���������*/
	private String categoryName;
	
	/**�÷������һ�������Ϣ 0:����� 1:Ӳ����*/
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
