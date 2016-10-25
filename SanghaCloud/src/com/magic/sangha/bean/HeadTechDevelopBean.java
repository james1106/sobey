package com.magic.sangha.bean;

import java.io.Serializable;
import java.util.Date;

import net.sf.json.JSONArray;

/**
 *  总部技术和总部研发 的补丁 描述
 * @author QimouXie
 *
 */
public class HeadTechDevelopBean implements Serializable {

	
	private static final long serialVersionUID = 4799941028177198873L;

	/**主键ID*/
	private Integer id;
	
	/**追加描述的内容*/
	private String content;
	
	/**追加描述 时间*/
	private Date createTime = new Date();
	
	/**订单ID*/
	private Integer orderId;
	
	/**总部研发*/
	private Integer developId;
	
	/**总部技术*/
	private Integer headTechId;
	
	/**追加描述的视频*/
	private String videos;
	
	private JSONArray jsVideos;
	
	/**追加描述的音频*/
	private String voices;
	
	private JSONArray jsVoices;
	
	/**追加描述的图片*/
	private String images;

	private JSONArray jsImages;
	
	/**消息来自某某角色*/
	private Integer from;
	
	/**消息发送至 某某角色*/
	private Integer to;
	
	/**0 表示 反馈  1 表示 补丁*/
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
