package com.magic.sangha.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *  员工
 * @author QimouXie
 *
 */
public class GroupUser implements Serializable {

	private static final long serialVersionUID = -828252269260115502L;
	
	/**主键ID*/
	private Integer id;
	
	/**真实姓名*/
	private String realName;
	
	/**头像*/
	private String avatar;
	
	/**职位*/
	private String jobTitle;
	
	/**单位*/
	private CompanyBean  unit;
	
	/**单位ID 公司ID*/
	private Integer companyId;
	
	/**介绍 负责 xxx产品*/
	private String introduce;
	
	/**手机号 登陆名*/
	private String mobile;
	
	/**邮箱*/
	private String email;
	
	/**角色*/
	private RoleBean role;
	
	/**审核状态 0 未审核 1 审核通过 2 审核未通过*/
	private Integer status;
	
	/**登录密码*/
	private String password;
	
	/**token*/
	private String token;
	
	/**创建/注册时间*/
	private Date createDate = new Date();
	
	/**最后登录时间*/
	private Date updateDate = new Date();
	
	/**QQ*/
	private String qq;
	
	/**办事处ID*/
	private Integer officeId;
	
	/**办事处名称*/
	private String officeName;
	
	/**分公司名称*/
	private String companyName;
	
	/**办事处对象*/
	private GroupOfficeBean office;
	
	/**角色类型*/
	private Integer roleType;
	
	private Integer isEmail;
	
	/**角色ID*/
	private Integer roleId;
	
	/**技术人员的 专业ID 对应订单 问题分类*/
	private String categoryIds;
	
	/**设备类型*/
	private Integer deviceType;
	
	/**设备token*/
	private String deviceToken;
	
	/**所属公司下 所有的角色数组*/
	private List<RoleBean> roles;
	
	/**当前用户所属公司的类型 ：总公司|分公司|办事处*/
	private Integer companyType;

	/**是否上线 0 表示 在线  1 不在线*/
	private Integer isOnLine;
	
	/**技术员工 的 技能集合*/
	private List<GroupUserCategoryBean> userCates;

	/**累计签到积分*/
	private Integer countBonus;
	
	/**累计签到天数*/
	private Integer countDays;
	
	/**员工类型*/
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
