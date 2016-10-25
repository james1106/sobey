package com.magic.sangha.bean;

import java.io.Serializable;
import java.util.Date;

/**
 *  ������� ʵ��
 * @author QimouXie
 *
 */
public class SuggestBean implements Serializable {

	private static final long serialVersionUID = 2015372534974431761L;
	
	/**����*/
	private Integer id;
	
	/**�������*/
	private String content;
	
	/**�û�ID*/
	private Integer userId;
	
	/**��ҵ�û�ID*/
	private Integer groupUserId;
	
	/**����ʱ��*/
	private Date createDate = new Date();
	
	private String userName;
	
	private String realName;
	
	private String email;
	
	private String userEmail;
	
	private String mobile;
	
	private String userMobile;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getGroupUserId() {
		return groupUserId;
	}

	public void setGroupUserId(Integer groupUserId) {
		this.groupUserId = groupUserId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	

}
