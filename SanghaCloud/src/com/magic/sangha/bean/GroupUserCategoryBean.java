package com.magic.sangha.bean;

import java.io.Serializable;

/**
 *  技术员工 和 对应的问题分类 
 * @author QimouXie
 *
 */
public class GroupUserCategoryBean implements Serializable {

	private static final long serialVersionUID = 1811369828379925164L;
	
	/**主键ID*/
	private Integer id;
	
	/**问题分类ID*/
	private Integer categoryId;
	
	/**技术员工ID*/
	private Integer groupuserId;
	
	/**分类名称*/
	private String categoryName;
	
	/**技术员工名字*/
	private String groupUserName;
	
	/**技术员工手机*/
	private String mobile;

	public String getGroupUserName() {
		return groupUserName;
	}

	public void setGroupUserName(String groupUserName) {
		this.groupUserName = groupUserName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getGroupuserId() {
		return groupuserId;
	}

	public void setGroupuserId(Integer groupuserId) {
		this.groupuserId = groupuserId;
	}
	
	
	
	

}
