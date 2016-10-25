package com.magic.sangha.bean;

import java.io.Serializable;
import java.util.Date;

/**
 *  系统消息 实体
 * @author QimouXie
 *
 */
public class SystemInfoBean implements Serializable {

	private static final long serialVersionUID = 6813911663821750275L;
	
	/**主键ID*/
	private Integer id;
	
	/**推送的人群 0 全部 1 用户 2 员工*/
	private Integer toGroup;
	
	/**Title*/
	private String title;
	
	/**内容*/
	private String content;
	
	/**创建时间*/
	private Date createTime = new Date();
	
	/**摘要*/
	private String brief;
	
	/**发布者ID*/
	private Integer publisherId;
	
	/**发布人*/
	private String publisherName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public Integer getToGroup() {
		return toGroup;
	}

	public void setToGroup(Integer toGroup) {
		this.toGroup = toGroup;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
