package com.magic.sangha.bean;

import java.io.Serializable;

/**
 *  ���۱�ǩ Bean
 * @author QimouXie
 *
 */
public class LableBean implements Serializable {

	private static final long serialVersionUID = -5894008446346460846L;
	
	/**����ID*/
	private Integer id;
	
	/**��ǩ����*/
	private String lable;
	
	/**��ǩ�����Ľ�ɫ����*/
	private Integer roleId;

	/**��ǩ����*/
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
