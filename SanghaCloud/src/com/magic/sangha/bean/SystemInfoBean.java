package com.magic.sangha.bean;

import java.io.Serializable;
import java.util.Date;

/**
 *  ϵͳ��Ϣ ʵ��
 * @author QimouXie
 *
 */
public class SystemInfoBean implements Serializable {

	private static final long serialVersionUID = 6813911663821750275L;
	
	/**����ID*/
	private Integer id;
	
	/**���͵���Ⱥ 0 ȫ�� 1 �û� 2 Ա��*/
	private Integer toGroup;
	
	/**Title*/
	private String title;
	
	/**����*/
	private String content;
	
	/**����ʱ��*/
	private Date createTime = new Date();
	
	/**ժҪ*/
	private String brief;
	
	/**������ID*/
	private Integer publisherId;
	
	/**������*/
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
