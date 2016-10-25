package com.magic.sangha.bean;

import java.io.Serializable;
import java.util.Date;

/**
 *  投票与用户  Bean
 * @author QimouXie
 *
 */
public class VoteUserBean implements Serializable {

	private static final long serialVersionUID = 7961788968321034502L;
	
	/**主键ID*/
	private Integer id;
	
	/**投票Id*/
	private Integer voteId;
	
	/**用户ID*/
	private Integer userId;
	
	/**用户ID 之 员工ID*/
	private Integer groupUserId;
	
	/**投票时间*/
	private Date createTime = new Date();
	
	/**该投票的描述*/
	private String voteDecribe;
	
	/**该投票选项的总投票数*/
	private Integer countVote;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVoteDecribe() {
		return voteDecribe;
	}

	public void setVoteDecribe(String voteDecribe) {
		this.voteDecribe = voteDecribe;
	}

	public Integer getCountVote() {
		return countVote;
	}

	public void setCountVote(Integer countVote) {
		this.countVote = countVote;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getVoteId() {
		return voteId;
	}

	public void setVoteId(Integer voteId) {
		this.voteId = voteId;
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
	
	
}
