package com.magic.sangha.bean;

import java.io.Serializable;
import java.util.Date;

import net.sf.json.JSONArray;

/**
 *   分公司技术和总部技术的沟通实体
 * @author QimouXie
 *
 */
public class TSCHeadTechDecribeBean implements Serializable {

	private static final long serialVersionUID = 3111909328646768079L;
	

	/**主键ID*/
	private Integer id;
	
	/**追加描述的内容*/
	private String content;
	
	/**追加描述 时间*/
	private Date createTime = new Date();
	
	/**订单ID*/
	private Integer orderId;
	
	/**总部技术ID*/
	private Integer headTechId;
	
	/**技术ID*/
	private Integer TSCId;
	
	/**追加描述的视频*/
	private String videos;
	
	private JSONArray jsVideos;
	
	/**追加描述的音频*/
	private String voices;
	
	private JSONArray jsVoices;
	
	/**追加描述的图片*/
	private String images;

	private JSONArray jsImages;
	
	/**消息来自 某个角色*/
	private Integer from;
	
	/**消息发送 给  某个角色*/
	private Integer to;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getHeadTechId() {
		return headTechId;
	}

	public void setHeadTechId(Integer headTechId) {
		this.headTechId = headTechId;
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

	public Integer getFrom() {
		return from;
	}

	public void setFrom(Integer from) {
		this.from = from;
	}

	public Integer getTo() {
		return to;
	}

	public void setTo(Integer to) {
		this.to = to;
	}
	

}
