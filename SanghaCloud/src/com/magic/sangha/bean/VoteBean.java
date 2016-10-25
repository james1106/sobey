package com.magic.sangha.bean;

import java.io.Serializable;
import java.util.Date;

/**
 *  ��Ѷ��Ϣ�� ͶƱ
 * @author QimouXie
 *
 */
public class VoteBean implements Serializable {

	private static final long serialVersionUID = 2880578717346992429L;
	
	/**����ID*/
	private Integer id;
	
	/**ͶƱ ��Ϣ����*/
	private String describe;
	
	/**��ѶId*/
	private Integer newsId;
	
	/**����ʱ��*/
	private Date createTime = new Date();
	
	/**����������*/
	private String publisherName;
	
	/**��Ѷtitle*/
	private String title;
	
	/**��Ѷ����*/
	private String type;
	
	/**��Ʊ��*/
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
