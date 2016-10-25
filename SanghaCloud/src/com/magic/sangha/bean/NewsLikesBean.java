package com.magic.sangha.bean;

import java.io.Serializable;

/**
 *  µãÔÞ
 * @author QimouXie
 *
 */
public class NewsLikesBean implements Serializable {

	
	private static final long serialVersionUID = 7190486394501720187L;
	
	private Integer id;
	
	private Integer userId;
	
	private Integer groupUserId;
	
	private Integer newsId;

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

	public Integer getGroupUserId() {
		return groupUserId;
	}

	public void setGroupUserId(Integer groupUserId) {
		this.groupUserId = groupUserId;
	}

	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}
	
	

}
