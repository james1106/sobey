package com.magic.sangha.bean;

import java.io.Serializable;
import java.util.List;

/**
 *   角色
 * @author QimouXie
 *
 */
public class RoleBean implements Serializable {

	private static final long serialVersionUID = 4863851956527133016L;
	
	/**主键ID*/
	private Integer id;
	
	/**角色名称*/
	private String roleName;
	
	/**角色描述*/
	private String describe;
	
	/**该角色对应的公司用户*/
	private List<GroupUser> groupUsers;
	
	/**该角色对应的用户*/
	private List<UserBean>  users;
	
	/** 该角色对应的权限*/
	private List<RolePowerBean> powers;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public List<GroupUser> getGroupUsers() {
		return groupUsers;
	}

	public void setGroupUsers(List<GroupUser> groupUsers) {
		this.groupUsers = groupUsers;
	}

	public List<UserBean> getUsers() {
		return users;
	}

	public void setUsers(List<UserBean> users) {
		this.users = users;
	}

	public List<RolePowerBean> getPowers() {
		return powers;
	}

	public void setPowers(List<RolePowerBean> powers) {
		this.powers = powers;
	}

	@Override
	public String toString() {
		return "RoleBean [id=" + id + ", roleName=" + roleName + ", describe="
				+ describe + ", groupUsers=" + groupUsers + ", users=" + users
				+ ", powers=" + powers + "]";
	}
	
	
	
	
	
}
