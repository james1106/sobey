package com.magic.sangha.bean;

import java.io.Serializable;
import java.util.Date;

import net.sf.json.JSONArray;

/**
 *  �û�׷������  ȫ�ֿɼ�
 * @author QimouXie
 *
 */
public class OrderDecribeUser implements Serializable {

	private static final long serialVersionUID = -1050816517864123722L;
	
	/**����ID*/
	private Integer id;
	
	/**׷������������*/
	private String content;
	
	/**׷������ ʱ��*/
	private Date createTime = new Date();
	
	/**����ID*/
	private Integer orderId;
	
	/**�û�����*/
	private Integer userId;
	
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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
