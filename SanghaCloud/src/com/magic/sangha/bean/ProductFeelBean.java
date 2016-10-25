package com.magic.sangha.bean;

import java.io.Serializable;
import java.util.Date;

/**
 *   ��Ʒ����ʵ��
 * @author QimouXie
 *
 */
public class ProductFeelBean implements Serializable {

	private static final long serialVersionUID = -2143123459261618514L;
	
	/**����*/
	private Integer id;
	
	/**�û�ID*/
	private Integer userId;
	
	/**����ʱ��*/
	private Date createTime = new Date();
	
	private String realName;
	
	private String email;
	
	private String mobile;

	private String tvName;
	
	
	public String getTvName() {
		return tvName;
	}

	public void setTvName(String tvName) {
		this.tvName = tvName;
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	

}
