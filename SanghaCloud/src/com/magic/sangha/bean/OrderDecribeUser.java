package com.magic.sangha.bean;

import java.io.Serializable;
import java.util.Date;

import net.sf.json.JSONArray;

/**
 *  用户追加描述  全局可见
 * @author QimouXie
 *
 */
public class OrderDecribeUser implements Serializable {

	private static final long serialVersionUID = -1050816517864123722L;
	
	/**主键ID*/
	private Integer id;
	
	/**追加描述的内容*/
	private String content;
	
	/**追加描述 时间*/
	private Date createTime = new Date();
	
	/**订单ID*/
	private Integer orderId;
	
	/**用户描述*/
	private Integer userId;
	
	/**追加描述的视频*/
	private String videos;
	
	private JSONArray jsVideos;
	
	/**追加描述的音频*/
	private String voices;
	
	private JSONArray jsVoices;
	
	/**追加描述的图片*/
	private String images;

	private JSONArray jsImages;
	
	/**此条消息 来自某个角色*/
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
