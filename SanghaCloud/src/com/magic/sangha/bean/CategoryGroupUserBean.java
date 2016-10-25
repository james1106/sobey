package com.magic.sangha.bean;

import java.io.Serializable;

/**
 *    总公司员工 和 专业 技能表 关系
 * @author QimouXie
 *
 */

public class CategoryGroupUserBean implements Serializable {
	
	private static final long serialVersionUID = -965197492903529439L;
	
	private Integer categoryId;
	
	private String categoryName;
	
	private Integer groupUserId;
	
	private String groupUserName;
	
	private String mobile;

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getGroupUserId() {
		return groupUserId;
	}

	public void setGroupUserId(Integer groupUserId) {
		this.groupUserId = groupUserId;
	}

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
	
	

}
