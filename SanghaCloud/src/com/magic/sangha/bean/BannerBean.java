package com.magic.sangha.bean;

import java.io.Serializable;
import java.util.Date;

/**
 *  Banner 
 * @author QimouXie
 *
 */
public class BannerBean implements Serializable {

	private static final long serialVersionUID = -2604916255741930900L;
	
	/**����id*/
	private Integer id;
	
	/**banner*/
	private String image;
	
	/**banner link URL*/
	private String imgUrl;
	
	/** Create Time of Banner*/
	private Date createTime  = new Date();
	
	/**�Ƿ����ڲ����� 0 ��ʾ����  1 ��ʾ��*/
	private Integer isInside;
	
	/**������ڲ����£����ڲ����µ�Id*/
	private Integer newsId;
	
	/**banner ��Ӧ����Ѷ*/
	private NewsBean news;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	

	public NewsBean getNews() {
		return news;
	}

	public void setNews(NewsBean news) {
		this.news = news;
	}

	public Integer getIsInside() {
		return isInside;
	}

	public void setIsInside(Integer isInside) {
		this.isInside = isInside;
	}

	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	

}
