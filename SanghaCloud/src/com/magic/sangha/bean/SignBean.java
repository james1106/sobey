package com.magic.sangha.bean;

import java.io.Serializable;
import java.util.Date;
/**
 *  ǩ��
 * @author QimouXie
 *
 */
public class SignBean implements Serializable {

	private static final long serialVersionUID = 609323979430154055L;
	
	/**����*/
	private Integer id;
	
	/**�û�*/
	private Integer userId;
	
	/**Ա���û�*/
	private Integer groupUserId;
	
	/**ǩ���Ļ���*/
	private Integer bonus;
	
	/**ǩ��ʱ��*/
	private Date createTime = new Date();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGroupUserId() {
		return groupUserId;
	}

	public void setGroupUserId(Integer groupUserId) {
		this.groupUserId = groupUserId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getBonus() {
		return bonus;
	}

	public void setBonus(Integer bonus) {
		this.bonus = bonus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	

}
