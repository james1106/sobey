package com.magic.sangha.bean;

import java.io.Serializable;

/**
 *  评价标签 Bean
 * @author QimouXie
 *
 */
public class LableBean implements Serializable {

	private static final long serialVersionUID = -5894008446346460846L;
	
	/**主键ID*/
	private Integer id;
	
	/**标签名称*/
	private String lable;
	
	/**标签所属的角色类型*/
	private Integer roleId;

	/**标签排序*/
	private Integer number;
	
	
	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLable() {
		return lable;
	}

	public void setLable(String lable) {
		this.lable = lable;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	

}
