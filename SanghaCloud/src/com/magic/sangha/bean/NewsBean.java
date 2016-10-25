package com.magic.sangha.bean;

import java.io.Serializable;
import java.util.Date;

import net.sf.json.JSONArray;

/**
 *  ������Ѷ
 * @author QimouXie
 *
 */
public class NewsBean implements Serializable {

	private static final long serialVersionUID = 2917249242002855292L;
	
	/**����*/
	private Integer id;
	
	/**���ű���*/
	private String title;
	
	/**����*/
	private String content;
	
	/**���ݼ��*/
	private String introduction;
	
	/**ͼƬ��ַ*/
	private String imageUrl;
	
	/**��������*/
	private Integer typeId;
	
	/**��������*/
	private Integer likes;
	
	/**�Ķ���*/
	private Integer reads;
	
	/***��ʵ�Ķ���*/
	private Integer realReads;
	
	/**�Ƿ����� 0��ʾ ����  1 ��ʾ��*/
	private Integer isUrl;
	
	/**������ַ*/
	private String linkUrl;
	
	/**�Ƿ�ͶƱ 0 û��ͶƱ  1 ��ͶƱ*/
	private Integer isVote;
	
	/**ͶƱID*/
	private Integer voteId;
	
	/**��ǰ�û��Ƿ�ͶƱ δͶƱΪ null*/
	private VoteUserBean isVoteUser;
	
	private JSONArray votes;
	
	/**�Ƿ���ʾ �ٷ�ͼ��*/
	private Integer isShowIcon;
	
	/**���´���ʱ��*/
	private Date createTime = new Date();
	
	/**�޸�ʱ��*/
	private Date updateTime = new Date();
	
	/**���ŷ�����*/
	private Integer userId;
	
	/**����������*/
	private String userName;
	
	/**banner�Ƿ��ö�*/
	private Integer isBanner;
	
	/**�����Ƿ���Ч 0 ��ʾ��Ч  1 ��ʾ��Ч*/
	private Integer isValid;
	
	public Integer getRealReads() {
		return realReads;
	}


	public void setRealReads(Integer realReads) {
		this.realReads = realReads;
	}


	public NewsBean() {
		super();
	}
	
	
	public NewsBean(String title, String content,
			String introduction, String imageUrl, Integer typeId,
			Integer likes, Integer reads, Integer isUrl, Integer isVote,
			Integer voteId, Integer isShowIcon, Date createTime, Date updateTime) {
		super();
		this.title = title;
		this.content = content;
		this.introduction = introduction;
		this.imageUrl = imageUrl;
		this.typeId = typeId;
		this.likes = likes;
		this.reads = reads;
		this.isUrl = isUrl;
		this.isVote = isVote;
		this.voteId = voteId;
		this.isShowIcon = isShowIcon;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}


	public Integer getIsBanner() {
		return isBanner;
	}


	public void setIsBanner(Integer isBanner) {
		this.isBanner = isBanner;
	}


	public Integer getIsValid() {
		return isValid;
	}


	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}


	public VoteUserBean getIsVoteUser() {
		return isVoteUser;
	}


	public void setIsVoteUser(VoteUserBean isVoteUser) {
		this.isVoteUser = isVoteUser;
	}


	public JSONArray getVotes() {
		return votes;
	}


	public void setVotes(JSONArray votes) {
		this.votes = votes;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public String getLinkUrl() {
		return linkUrl;
	}


	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Integer getIsVote() {
		return isVote;
	}

	public void setIsVote(Integer isVote) {
		this.isVote = isVote;
	}

	public Integer getVoteId() {
		return voteId;
	}

	public void setVoteId(Integer voteId) {
		this.voteId = voteId;
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public Integer getReads() {
		return reads;
	}

	public void setReads(Integer reads) {
		this.reads = reads;
	}

	public Integer getIsUrl() {
		return isUrl;
	}

	public void setIsUrl(Integer isUrl) {
		this.isUrl = isUrl;
	}

	public Integer getIsShowIcon() {
		return isShowIcon;
	}

	public void setIsShowIcon(Integer isShowIcon) {
		this.isShowIcon = isShowIcon;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
