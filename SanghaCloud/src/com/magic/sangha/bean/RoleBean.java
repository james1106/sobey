package com.magic.sangha.bean;

import java.io.Serializable;
import java.util.List;

/**
 *   ��ɫ
 * @author QimouXie
 *
 */
public class RoleBean implements Serializable {

	private static final long serialVersionUID = 4863851956527133016L;
	
	/**����ID*/
	private Integer id;
	
	/**��ɫ����*/
	private String roleName;
	
	/**��ɫ����*/
	private String describe;
	
	/**�ý�ɫ��Ӧ�Ĺ�˾�û�*/
	private List<GroupUser> groupUsers;
	
	/**�ý�ɫ��Ӧ���û�*/
	private List<UserBean>  users;
	
	/** �ý�ɫ��Ӧ��Ȩ��*/
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
