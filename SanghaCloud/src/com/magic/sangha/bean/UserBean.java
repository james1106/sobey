package com.magic.sangha.bean;

import java.io.Serializable;
import java.util.Date;

public class UserBean implements Serializable {

	private static final long serialVersionUID = -3360384983915730122L;
	
	/** ����ID */
	private Integer id;
	
	/** �ֻ��� */
	private String mobile;
	
	/** ���� */
	private String password;
	
	/** ��ʵ���� */
	private String realName;
	
	/** ���� */
	private String email;
	
	/** ������λ */
	private String unit;
	
	/** ��ϵ��ʽ֮ QQ */
	private String qq;
	
	/** �������ԽӵĹ�˾Ա��ID */
	private Integer sales;
	
	/** �������ԽӵĹ�˾Ա������ */	
	private GroupUser sale;
	
	/** ��ǰ���ԽӵĹ�˾Ա�� */
	private GroupUser preSales;
	
	/** ������Ա ������¹� */
	private GroupUser techPerson;
	
	/** �������� */
	private String area;
	
	/** ͷ�� */
	private String avatar;
	
	/** ��ɫ */
	private RoleBean role;
	
	/**��ɫID*/
	private Integer roleId;
	
	/** ���״̬ 0 δ��� 1 ���ͨ�� 2 ���δͨ�� */
	private Integer status;
	
	/**token*/
	private String token;
	
	/**����̨ID*/
	private Integer tvId;
	
	/**��������̨����*/
	private String officeName;
	
	/**��ɫ����*/
	private Integer roleType;
	
	/**����/ע��ʱ��*/
	private Date createDate = new Date();
	
	/**����¼ʱ��*/
	private Date updateDate  = new Date();
	
	
	/**�豸����*/
	private Integer deviceType;
	
	/**�豸token*/
	private String deviceToken;
	
	/**�Ƿ��ǲ�Ʒ�û� 0 �� 1 ����*/
	private Integer isPUser;
	
	/**�ۼ�ǩ������*/
	private Integer countBonus;
	
	/**�ۼ�ǩ������*/
	private Integer countDays;


	public Integer getCountBonus() {
		return countBonus;
	}

	public void setCountBonus(Integer countBonus) {
		this.countBonus = countBonus;
	}

	public Integer getCountDays() {
		return countDays;
	}

	public void setCountDays(Integer countDays) {
		this.countDays = countDays;
	}

	public Integer getIsPUser() {
		return isPUser;
	}

	public void setIsPUser(Integer isPUser) {
		this.isPUser = isPUser;
	}

	public GroupUser getSale() {
		return sale;
	}

	public void setSale(GroupUser sale) {
		this.sale = sale;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	

	public Integer getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getTvName() {
		return officeName;
	}

	public void setTvName(String officeName) {
		this.officeName = officeName;
	}

	public Integer getRoleType() {
		return roleType;
	}

	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}

	public Integer getTvId() {
		return tvId;
	}

	public void setTvId(Integer tvId) {
		this.tvId = tvId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	

	public Integer getSales() {
		return sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}

	public GroupUser getPreSales() {
		return preSales;
	}

	public void setPreSales(GroupUser preSales) {
		this.preSales = preSales;
	}

	public GroupUser getTechPerson() {
		return techPerson;
	}

	public void setTechPerson(GroupUser techPerson) {
		this.techPerson = techPerson;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public RoleBean getRole() {
		return role;
	}

	public void setRole(RoleBean role) {
		this.role = role;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "UserBean [id=" + id + ", mobile=" + mobile + ", password="
				+ password + ", realName=" + realName + ", email=" + email
				+ ", unit=" + unit + ", qq=" + qq + ", sales=" + sales
				+ ", preSales=" + preSales + ", techPerson=" + techPerson
				+ ", area=" + area + ", avatar=" + avatar + ", role=" + role
				+ ", status=" + status + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + "]";
	}
	
	
	

}
