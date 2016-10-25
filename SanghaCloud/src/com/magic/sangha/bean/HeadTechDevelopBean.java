package com.magic.sangha.bean;

import java.io.Serializable;
import java.util.Date;

import net.sf.json.JSONArray;

/**
 *  �ܲ��������ܲ��з� �Ĳ��� ����
 * @author QimouXie
 *
 */
public class HeadTechDevelopBean implements Serializable {

	
	private static final long serialVersionUID = 4799941028177198873L;

	/**����ID*/
	private Integer id;
	
	/**׷������������*/
	private String content;
	
	/**׷������ ʱ��*/
	private Date createTime = new Date();
	
	/**����ID*/
	private Integer orderId;
	
	/**�ܲ��з�*/
	private Integer developId;
	
	/**�ܲ�����*/
	private Integer headTechId;
	
	/**׷����������Ƶ*/
	private String videos;
	
	private JSONArray jsVideos;
	
	/**׷����������Ƶ*/
	private String voices;
	
	private JSONArray jsVoices;
	
	/**׷��������ͼƬ*/
	private String images;

	private JSONArray jsImages;
	
	/**��Ϣ����ĳĳ��ɫ*/
	private Integer from;
	
	/**��Ϣ������ ĳĳ��ɫ*/
	private Integer to;
	
	/**0 ��ʾ ����  1 ��ʾ ����*/
	private Integer type;
	
	private String realName;
	
	private String email;
	
	private String mobile;
	
	private Integer count;
	
	private String orderNumber;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getTo() {
		return to;
	}

	public void setTo(Integer to) {
		this.to = to;
	}

	public Integer getFrom() {
		return from;
	}

	public void setFrom(Integer from) {
		this.from = from;
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

	public Integer getDevelopId() {
		return developId;
	}

	public void setDevelopId(Integer developId) {
		this.developId = developId;
	}

	public Integer getHeadTechId() {
		return headTechId;
	}

	public void setHeadTechId(Integer headTechId) {
		this.headTechId = headTechId;
	}

	public String getVideos() {
		return videos;
	}

	public void setVideos(String videos) {
		this.videos = videos;
	}

	public String getVoices() {
		return voices;
	}

	public void setVoices(String voices) {
		this.voices = voices;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public JSONArray getJsVideos() {
		return jsVideos;
	}

	public void setJsVideos(JSONArray jsVideos) {
		this.jsVideos = jsVideos;
	}

	public JSONArray getJsVoices() {
		return jsVoices;
	}

	public void setJsVoices(JSONArray jsVoices) {
		this.jsVoices = jsVoices;
	}

	public JSONArray getJsImages() {
		return jsImages;
	}

	public void setJsImages(JSONArray jsImages) {
		this.jsImages = jsImages;
	}
}
