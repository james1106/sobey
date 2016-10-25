package com.magic.sangha.bean;

import java.io.Serializable;
import java.util.Date;

/**
 *  评论 积分 
 * @author QimouXie
 *
 */
public class CommentBonusBean implements Serializable {

	private static final long serialVersionUID = -7208164195686947661L;
	
	/**主键*/
	private Integer id;
	
	/**积分*/
	private Integer bonus;
	
	/**用户ID*/
	private Integer userId;
	
	/**员工ID*/
	private Integer groupUserId;
	
	/**评论Id*/
	private Integer commentId;
	
	/**创建时间*/
	private Date createTime = new Date();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBonus() {
		return bonus;
	}

	public void setBonus(Integer bonus) {
		this.bonus = bonus;
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

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
	

}
