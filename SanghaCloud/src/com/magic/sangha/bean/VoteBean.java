package com.magic.sangha.bean;

import java.io.Serializable;
import java.util.Date;

/**
 *  资讯信息里 投票
 * @author QimouXie
 *
 */
public class VoteBean implements Serializable {

	private static final long serialVersionUID = 2880578717346992429L;
	
	/**主键ID*/
	private Integer id;
	
	/**投票 信息描述*/
	private String describe;
	
	/**资讯Id*/
	private Integer newsId;
	
	/**创建时间*/
	private Date createTime = new Date();
	
	/**发布者名字*/
	private String publisherName;
	
	/**资讯title*/
	private String title;
	
	/**资讯类型*/
	private String type;
	
	/**总票数*/
	private Integer count;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	

}
