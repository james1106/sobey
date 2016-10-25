package com.magic.sangha.bean;

import java.io.Serializable;

/**
 *  ����Ա�� �� ��Ӧ��������� 
 * @author QimouXie
 *
 */
public class GroupUserCategoryBean implements Serializable {

	private static final long serialVersionUID = 1811369828379925164L;
	
	/**����ID*/
	private Integer id;
	
	/**�������ID*/
	private Integer categoryId;
	
	/**����Ա��ID*/
	private Integer groupuserId;
	
	/**��������*/
	private String categoryName;
	
	/**����Ա������*/
	private String groupUserName;
	
	/**����Ա���ֻ�*/
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
