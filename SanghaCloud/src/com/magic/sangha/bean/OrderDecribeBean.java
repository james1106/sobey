package com.magic.sangha.bean;

import java.io.Serializable;
import java.util.Date;

import net.sf.json.JSONArray;

/**
 *  ����������׷�� �� TSC
 * @author QimouXie
 *
 */
public class OrderDecribeBean implements Serializable {

	private static final long serialVersionUID = 4293805985718184576L;
	
	/**����ID*/
	private Integer id;
	
	/**׷������������*/
	private String content;
	
	/**׷������ ʱ��*/
	private Date createTime = new Date();
	
	/**����ID*/
	private Integer orderId;
	
	/**�û�ID*/
	private Integer userId;
	
	/**����ID*/
	private Integer TSCId;
	
	/**׷����������Ƶ*/
	private String videos;
	
	private JSONArray jsVideos;
	
	/**׷����������Ƶ*/
	private String voices;
	
	private JSONArray jsVoices;
	
	/**׷��������ͼƬ*/
	private String images;

	private JSONArray jsImages;
	
	/**������Ϣ���� ĳ����ɫ*/
	private Integer from;
	
	/**������Ϣ ���͸� ĳ����ɫ*/
	private Integer to;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getTSCId() {
		return TSCId;
	}

	public void setTSCId(Integer tSCId) {
		TSCId = tSCId;
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
