package com.magic.sangha.bean;

import java.io.Serializable;
import java.util.Date;
/**
 *  签到
 * @author QimouXie
 *
 */
public class SignBean implements Serializable {

	private static final long serialVersionUID = 609323979430154055L;
	
	/**主键*/
	private Integer id;
	
	/**用户*/
	private Integer userId;
	
	/**员工用户*/
	private Integer groupUserId;
	
	/**签到的积分*/
	private Integer bonus;
	
	/**签到时间*/
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
