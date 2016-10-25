package com.magic.sangha.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *  Ա��
 * @author QimouXie
 *
 */
public class GroupUser implements Serializable {

	private static final long serialVersionUID = -828252269260115502L;
	
	/**����ID*/
	private Integer id;
	
	/**��ʵ����*/
	private String realName;
	
	/**ͷ��*/
	private String avatar;
	
	/**ְλ*/
	private String jobTitle;
	
	/**��λ*/
	private CompanyBean  unit;
	
	/**��λID ��˾ID*/
	private Integer companyId;
	
	/**���� ���� xxx��Ʒ*/
	private String introduce;
	
	/**�ֻ��� ��½��*/
	private String mobile;
	
	/**����*/
	private String email;
	
	/**��ɫ*/
	private RoleBean role;
	
	/**���״̬ 0 δ��� 1 ���ͨ�� 2 ���δͨ��*/
	private Integer status;
	
	/**��¼����*/
	private String password;
	
	/**token*/
	private String token;
	
	/**����/ע��ʱ��*/
	private Date createDate = new Date();
	
	/**����¼ʱ��*/
	private Date updateDate = new Date();
	
	/**QQ*/
	private String qq;
	
	/**���´�ID*/
	private Integer officeId;
	
	/**���´�����*/
	private String officeName;
	
	/**�ֹ�˾����*/
	private String companyName;
	
	/**���´�����*/
	private GroupOfficeBean office;
	
	/**��ɫ����*/
	private Integer roleType;
	
	private Integer isEmail;
	
	/**��ɫID*/
	private Integer roleId;
	
	/**������Ա�� רҵID ��Ӧ���� �������*/
	private String categoryIds;
	
	/**�豸����*/
	private Integer deviceType;
	
	/**�豸token*/
	private String deviceToken;
	
	/**������˾�� ���еĽ�ɫ����*/
	private List<RoleBean> roles;
	
	/**��ǰ�û�������˾������ ���ܹ�˾|�ֹ�˾|���´�*/
	private Integer companyType;

	/**�Ƿ����� 0 ��ʾ ����  1 ������*/
	private Integer isOnLine;
	
	/**����Ա�� �� ���ܼ���*/
	private List<GroupUserCategoryBean> userCates;

	/**�ۼ�ǩ������*/
	private Integer countBonus;
	
	/**�ۼ�ǩ������*/
	private Integer countDays;
	
	/**Ա������*/
	private Integer userType;
	
	
	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

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

	public List<GroupUserCategoryBean> getUserCates() {
		return userCates;
	}

	public void setUserCates(List<GroupUserCategoryBean> userCates) {
		this.userCates = userCates;
	}

	public Integer getIsOnLine() {
		return isOnLine;
	}

	public void setIsOnLine(Integer isOnLine) {
		this.isOnLine = isOnLine;
	}

	public Integer getId() {
		return id;
	}

	public Integer getCompanyType() {
		return companyType;
	}

	public void setCompanyType(Integer companyType) {
		this.companyType = companyType;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	

	public GroupOfficeBean getOffice() {
		return office;
	}

	public void setOffice(GroupOfficeBean office) {
		this.office = office;
	}

	public List<RoleBean> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleBean> roles) {
		this.roles = roles;
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

	public String getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(String categoryIds) {
		this.categoryIds = categoryIds;
	}

	public Integer getIsEmail() {
		return isEmail;
	}

	public void setIsEmail(Integer isEmail) {
		this.isEmail = isEmail;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public Integer getRoleType() {
		return roleType;
	}

	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}

	public Integer getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Integer officeId) {
		this.officeId = officeId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public CompanyBean getUnit() {
		return unit;
	}

	public void setUnit(CompanyBean unit) {
		this.unit = unit;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	
	

	
	
	
	
	
	

}
