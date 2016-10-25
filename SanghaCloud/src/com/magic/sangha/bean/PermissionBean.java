package com.magic.sangha.bean;

import java.io.Serializable;
import java.util.List;

/**
 *  Ȩ��
 * @author QimouXie
 *
 */
public class PermissionBean implements Serializable {

	private static final long serialVersionUID = 9088429739249006636L;
	
	/**����*/
	private Integer id;
	
	/**����·��*/
	private String url;
	
	/**Ȩ�޵�����*/
	private String description;
	
	/**Ȩ�������*/
	private Integer sortNumber;
	
	/**��ɫID*/
	private Integer roleId;
	
	/**Ȩ�޶�Ӧ�Ľ�ɫ*/
	private List<RoleBean> roles;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSortNumber() {
		return sortNumber;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public void setSortNumber(Integer sortNumber) {
		this.sortNumber = sortNumber;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<RoleBean> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleBean> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "PermissionBean [id=" + id + ", url=" + url + ", description="
				+ description + ", roles=" + roles + "]";
	}
	
	
	

	
	
}
