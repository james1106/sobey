package com.magic.sangha.bean;

import java.io.Serializable;
import java.util.Date;

import net.sf.json.JSONArray;

/**
 *   ����֮�������ܲ�����  �ܲ�������������ɫ֮��Ĺ�ͨ��¼
 * @author QimouXie
 *
 */
public class OrderDecribeHeadTechBean implements Serializable {

	private static final long serialVersionUID = 5857403797333448376L;
	
	/**����ID*/
	private Integer id;
	
	/**׷������������*/
	private String content;
	
	/**׷������ ʱ��*/
	private Date createTime = new Date();
	
	/**����ID*/
	private Integer orderId;
	
	/**�ܲ���������*/
	private Integer headTeah;
	
	/**�û�����*/
	private Integer user;
	
	/**׷����������Ƶ*/
	private String videos;
	
	private JSONArray jsVideos;
	
	/**׷����������Ƶ*/
	private String voices;
	
	private JSONArray jsVoices;
	
	/**׷��������ͼƬ*/
	private String images;

	private JSONArray jsImages;
	
	/**������Ϣ ����ĳ����ɫ*/
	private Integer from;
	/**������Ϣ ������ĳ����ɫ*/
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

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	
	public Integer getHeadTeah() {
		return headTeah;
	}

	public void setHeadTeah(Integer headTeah) {
		this.headTeah = headTeah;
	}

	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

	public String getVideos() {
		return videos;
	}

	public void setVideos(String videos) {
		this.videos = videos;
	}

	public JSONArray getJsVideos() {
		return jsVideos;
	}

	public void setJsVideos(JSONArray jsVideos) {
		this.jsVideos = jsVideos;
	}

	public String getVoices() {
		return voices;
	}

	public void setVoices(String voices) {
		this.voices = voices;
	}

	public JSONArray getJsVoices() {
		return jsVoices;
	}

	public void setJsVoices(JSONArray jsVoices) {
		this.jsVoices = jsVoices;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public JSONArray getJsImages() {
		return jsImages;
	}

	public void setJsImages(JSONArray jsImages) {
		this.jsImages = jsImages;
	}

	
}
