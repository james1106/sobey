package com.magic.sangha.bean;

import java.io.Serializable;

/**
 *  电视台TV 绑定实体
 * @author QimouXie
 *
 */
public class RelationTVBean implements Serializable {

	private static final long serialVersionUID = 2744117074298995187L;
	
	private Integer id;
	
	private Integer groupUserId;
	
	private Integer tvId;
	
	private String tvName;
	
	private String statiocCode;
	
	private TVBean tv;

	public TVBean getTv() {
		return tv;
	}

	public void setTv(TVBean tv) {
		this.tv = tv;
	}

	public String getStatiocCode() {
		return statiocCode;
	}

	public void setStatiocCode(String statiocCode) {
		this.statiocCode = statiocCode;
	}

	public String getTvName() {
		return tvName;
	}

	public void setTvName(String tvName) {
		this.tvName = tvName;
	}

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

	public Integer getTvId() {
		return tvId;
	}

	public void setTvId(Integer tvId) {
		this.tvId = tvId;
	}
	
	

}
