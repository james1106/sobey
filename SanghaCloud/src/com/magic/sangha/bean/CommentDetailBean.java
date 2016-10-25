package com.magic.sangha.bean;

import java.io.Serializable;

import net.sf.json.JSONArray;

/**
 *  评论 详细 业务Bean
 * @author QimouXie
 *
 */
public class CommentDetailBean implements Serializable {

	private static final long serialVersionUID = 1727269244728865753L;
	
	/**客服态度  星星数量 0-100*/
	private Integer serviceAttitude;
	
	/**处理速度 星星数量 0-100*/
	private Integer disposeSpeed;
	
	/**产品评价 星星数量 0-100*/
	private Integer productComment;
	
	/**评价标签*/
	private JSONArray commentLableIds;

	public Integer getServiceAttitude() {
		return serviceAttitude;
	}

	public void setServiceAttitude(Integer serviceAttitude) {
		this.serviceAttitude = serviceAttitude;
	}

	public Integer getDisposeSpeed() {
		return disposeSpeed;
	}

	public void setDisposeSpeed(Integer disposeSpeed) {
		this.disposeSpeed = disposeSpeed;
	}

	public Integer getProductComment() {
		return productComment;
	}

	public void setProductComment(Integer productComment) {
		this.productComment = productComment;
	}

	public JSONArray getCommentLableIds() {
		return commentLableIds;
	}

	public void setCommentLableIds(JSONArray commentLableIds) {
		this.commentLableIds = commentLableIds;
	}
	
	
}
