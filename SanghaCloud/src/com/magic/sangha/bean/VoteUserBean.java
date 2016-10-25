package com.magic.sangha.bean;

import java.io.Serializable;
import java.util.Date;

/**
 *  ͶƱ���û�  Bean
 * @author QimouXie
 *
 */
public class VoteUserBean implements Serializable {

	private static final long serialVersionUID = 7961788968321034502L;
	
	/**����ID*/
	private Integer id;
	
	/**ͶƱId*/
	private Integer voteId;
	
	/**�û�ID*/
	private Integer userId;
	
	/**�û�ID ֮ Ա��ID*/
	private Integer groupUserId;
	
	/**ͶƱʱ��*/
	private Date createTime = new Date();
	
	/**��ͶƱ������*/
	private String voteDecribe;
	
	/**��ͶƱѡ�����ͶƱ��*/
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
