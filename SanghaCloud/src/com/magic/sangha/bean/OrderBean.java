package com.magic.sangha.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONArray;

/**
 *  ����ʵ��
 * @author QimouXie
 *
 */
public class OrderBean implements Serializable {

	private static final long serialVersionUID = -7108439767925433419L;
	
	/**����*/
	private Integer id;
	
	/**���� �������*/
	private Integer orderCategory;
	
	/**����������������*/
	private String orderCategoryName;
	
	/**���� ������� ����*/
	private String categoryName;
	
	/**�����������*/
	private OrderCategoryBean category;
	
	/**�������� ����*/
	private Integer orderCategoryType;
	
	/**��������*/
	private String orderDescri	;
	
	/**����ͼƬ�� json��ʽ�洢*/
	private String images;
	
	private JSONArray jsImages;
	
	/**������Ƶ�� json��ʽ�洢*/
	private String videos;
	
	private JSONArray jsVideos;
	
	/**������Ƶ�� json��ʽ�洢*/
	private String voices;
	
	private JSONArray jsVoices;
	
	/**�ͷ�ID ��ʼ������Ŀͷ� �������˭�ٷ����˭*/
	private Integer serviceId;
	
	/**����״̬*/
	private Integer status;
	
	/**�ͷ��Ƿ�鿴 0��δ�鿴 1���Ѳ鿴*/
	private Integer serviceCheck;
	
	/**�����Ƿ�鿴 0��δ�鿴 1���Ѳ鿴*/
	private Integer techCheck;
	
	/**����״̬���� JSON����*/
	private String orderData;
	
	/**Ա����� ����״̬����  JSON����*/
	private String groupOrderData;
	
	/**�ܲ�������Ա*/
	private Integer headTechId;
	
	/**�ֹ�˾�ļ�����Ա ���TSC*/
	private Integer tscId;

	/**�з���Ա*/
	private Integer decelopId;
	
	/**��������ʱ��*/
	private Date createTime = new Date();
	
	/**���𶩵�����*/
	private Integer userId;
	
	/**ͳ����*/
	private Integer count;
	
	/**�������*/
	private String orderNumber;
	
	/**�����Ƿ���Ч  Ĭ��Ϊ��Ч 0��ʾ��Ч  1��ʾ��Ч*/
	private Integer isValid = 0;
	
	/** �з��Ƿ�鿴 */
	private Integer developCheck;
	
	/**�ܲ����� �Ƿ�鿴*/
	private Integer headTechCheck;
	
	/**�ܼ����Ƿ� ���� 0:δ���� 1 ������ 2��ʾûȨ������*/
	private Integer isHeadTechComment;
	
	/**�ͷ��Ƿ�����*/
	private Integer isServiceComment;
	
	/**TSC�Ƿ�����*/
	private Integer isTSCComment;
	
	/**�з��Ƿ�����*/
	private Integer isHeadDevelopComment;
	
	/**�û��Ƿ�����*/
	private Integer isUsercomment;
	
	/**�Ƿ� ֱ�ӷ�����ܹ�˾����*/
	private Integer isHeadTech;
	
	/**TSC �Ƿ��������0 û�н��� 1����  2 ��ʼֵ*/
	private Integer tscIsAccept;
	
	/**�ܼ����Ƿ��������*/
	private Integer headTechIsAccept;
	
	/**�з��Ƿ��������*/
	private Integer developIsAccept;
	
	/**�ͷ��Ƿ��������*/
	private Integer serviceIsAccept;
	
	/**����������*/
	private String userName;
	
	/**�ͷ�����*/
	private String serviceName;
	
	/**TSC����*/
	private String tscName;
	
	/**�ܲ���������*/
	private String headTechName;
	
	/**�з�������*/
	private String developName;
	
	/**1 ��ʾ ������ 0 ��ʾû�з��� */
	private Integer isFeedback;
	
	/** ������״̬Ϊ ����֤ʱ����¼ʱ��*/
	private Date updateTime;
	
	/**�ּ�����ʼ����ʱ��*/
	private Date techStartTime;
	
	/**�ּ����������ʱ��*/
	private Date techEndTime;
	
	/**�ܲ�������ʼ����ʱ��*/
	private Date headTechStartTime;
	
	/**�ܲ������������ʱ��*/
	private Date headTechEndTime;
	
	/**�ֹ�˾���������ʱ��*/
	private Date techLastTime;
	
	/**�ܲ����������ʱ��*/
	private Date headLastTime;
	
	/**�з� �����ʱ��*/
	private Date developTime;
	/**�û���TSC*/
	private OrderDecribeBean tscDecribe;
	/**�ܲ��������û�*/
	private OrderDecribeHeadTechBean headDecribe;
	/**�ܲ��������з�*/
	private HeadTechDevelopBean headDecribeD;
	/**�ܲ��������з�*/
	private HeadTechDevelopBean developDecribe;
	/**�ܲ�������TSC*/
	private TSCHeadTechDecribeBean tscDecribeD;
	/**�ܲ�������TSC*/
	private TSCHeadTechDecribeBean headDecribeT;
	/**����̨����*/
	private String tvName;
	
	public OrderBean() {
		super();
	}

	public String getTvName() {
		return tvName;
	}

	public void setTvName(String tvName) {
		this.tvName = tvName;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public OrderDecribeBean getTscDecribe() {
		return tscDecribe;
	}

	public void setTscDecribe(OrderDecribeBean tscDecribe) {
		this.tscDecribe = tscDecribe;
	}

	public OrderDecribeHeadTechBean getHeadDecribe() {
		return headDecribe;
	}

	public void setHeadDecribe(OrderDecribeHeadTechBean headDecribe) {
		this.headDecribe = headDecribe;
	}

	public HeadTechDevelopBean getHeadDecribeD() {
		return headDecribeD;
	}

	public void setHeadDecribeD(HeadTechDevelopBean headDecribeD) {
		this.headDecribeD = headDecribeD;
	}

	public HeadTechDevelopBean getDevelopDecribe() {
		return developDecribe;
	}

	public void setDevelopDecribe(HeadTechDevelopBean developDecribe) {
		this.developDecribe = developDecribe;
	}

	public TSCHeadTechDecribeBean getTscDecribeD() {
		return tscDecribeD;
	}

	public void setTscDecribeD(TSCHeadTechDecribeBean tscDecribeD) {
		this.tscDecribeD = tscDecribeD;
	}

	public TSCHeadTechDecribeBean getHeadDecribeT() {
		return headDecribeT;
	}

	public void setHeadDecribeT(TSCHeadTechDecribeBean headDecribeT) {
		this.headDecribeT = headDecribeT;
	}

	public Date getTechLastTime() {
		return techLastTime;
	}

	public void setTechLastTime(Date techLastTime) {
		this.techLastTime = techLastTime;
	}

	public Date getHeadLastTime() {
		return headLastTime;
	}

	public void setHeadLastTime(Date headLastTime) {
		this.headLastTime = headLastTime;
	}

	public Date getDevelopTime() {
		return developTime;
	}

	public void setDevelopTime(Date developTime) {
		this.developTime = developTime;
	}

	public Date getTechStartTime() {
		return techStartTime;
	}

	public void setTechStartTime(Date techStartTime) {
		this.techStartTime = techStartTime;
	}

	public Date getTechEndTime() {
		return techEndTime;
	}

	public void setTechEndTime(Date techEndTime) {
		this.techEndTime = techEndTime;
	}

	public Date getHeadTechStartTime() {
		return headTechStartTime;
	}

	public void setHeadTechStartTime(Date headTechStartTime) {
		this.headTechStartTime = headTechStartTime;
	}

	public Date getHeadTechEndTime() {
		return headTechEndTime;
	}

	public void setHeadTechEndTime(Date headTechEndTime) {
		this.headTechEndTime = headTechEndTime;
	}

	public Integer getIsFeedback() {
		return isFeedback;
	}

	public void setIsFeedback(Integer isFeedback) {
		this.isFeedback = isFeedback;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getTscName() {
		return tscName;
	}

	public void setTscName(String tscName) {
		this.tscName = tscName;
	}

	public String getHeadTechName() {
		return headTechName;
	}

	public void setHeadTechName(String headTechName) {
		this.headTechName = headTechName;
	}

	public String getDevelopName() {
		return developName;
	}

	public void setDevelopName(String developName) {
		this.developName = developName;
	}

	public Integer getId() {
		return id;
	}

	public Integer getIsUsercomment() {
		return isUsercomment;
	}

	public void setIsUsercomment(Integer isUsercomment) {
		this.isUsercomment = isUsercomment;
	}

	public Integer getServiceIsAccept() {
		return serviceIsAccept;
	}

	public void setServiceIsAccept(Integer serviceIsAccept) {
		this.serviceIsAccept = serviceIsAccept;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public Integer getIsHeadTech() {
		return isHeadTech;
	}

	public void setIsHeadTech(Integer isHeadTech) {
		this.isHeadTech = isHeadTech;
	}

	public Integer getIsHeadTechComment() {
		return isHeadTechComment;
	}

	public void setIsHeadTechComment(Integer isHeadTechComment) {
		this.isHeadTechComment = isHeadTechComment;
	}

	public Integer getIsServiceComment() {
		return isServiceComment;
	}

	public void setIsServiceComment(Integer isServiceComment) {
		this.isServiceComment = isServiceComment;
	}

	public Integer getIsTSCComment() {
		return isTSCComment;
	}

	public void setIsTSCComment(Integer isTSCComment) {
		this.isTSCComment = isTSCComment;
	}

	public Integer getIsHeadDevelopComment() {
		return isHeadDevelopComment;
	}

	public void setIsHeadDevelopComment(Integer isHeadDevelopComment) {
		this.isHeadDevelopComment = isHeadDevelopComment;
	}

	public Integer getOrderCategoryType() {
		return orderCategoryType;
	}

	public String getGroupOrderData() {
		return groupOrderData;
	}

	public void setGroupOrderData(String groupOrderData) {
		this.groupOrderData = groupOrderData;
	}

	public void setOrderCategoryType(Integer orderCategoryType) {
		this.orderCategoryType = orderCategoryType;
	}

	public JSONArray getJsVideos() {
		if(jsVideos == null){
			return null;
		}
		return jsVideos;
	}

	public void setJsVideos(JSONArray jsVideos) {
		this.jsVideos = jsVideos;
	}

	public JSONArray getJsVoices() {
		if(jsVoices == null){
			return null;
		}
		return jsVoices;
	}

	public void setJsVoices(JSONArray jsVoices) {
		this.jsVoices = jsVoices;
	}

	public JSONArray getJsImages() {
		if(jsImages == null){
			return null;
		}
		return jsImages;
	}

	public void setJsImages(JSONArray jsImages) {
		this.jsImages = jsImages;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDevelopCheck() {
		return developCheck;
	}

	public void setDevelopCheck(Integer developCheck) {
		this.developCheck = developCheck;
	}

	public Integer getHeadTechCheck() {
		return headTechCheck;
	}

	public void setHeadTechCheck(Integer headTechCheck) {
		this.headTechCheck = headTechCheck;
	}

	public Integer getOrderCategory() {
		return orderCategory;
	}

	public void setOrderCategory(Integer orderCategory) {
		this.orderCategory = orderCategory;
	}

	public String getOrderCategoryName() {
		return orderCategoryName;
	}

	public void setOrderCategoryName(String orderCategoryName) {
		this.orderCategoryName = orderCategoryName;
	}

	public String getOrderDescri() {
		return orderDescri;
	}

	public void setOrderDescri(String orderDescri) {
		this.orderDescri = orderDescri;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
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

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getServiceCheck() {
		return serviceCheck;
	}

	public void setServiceCheck(Integer serviceCheck) {
		this.serviceCheck = serviceCheck;
	}

	public Integer getTechCheck() {
		return techCheck;
	}

	public void setTechCheck(Integer techCheck) {
		this.techCheck = techCheck;
	}

	public String getOrderData() {
		return orderData;
	}

	public void setOrderData(String orderData) {
		this.orderData = orderData;
	}

	public Integer getHeadTechId() {
		return headTechId;
	}

	public void setHeadTechId(Integer headTechId) {
		this.headTechId = headTechId;
	}

	public Integer getTscId() {
		return tscId;
	}

	public void setTscId(Integer tscId) {
		this.tscId = tscId;
	}

	public Integer getDecelopId() {
		return decelopId;
	}

	public void setDecelopId(Integer decelopId) {
		this.decelopId = decelopId;
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
	
	
	public Integer getTscIsAccept() {
		return tscIsAccept;
	}

	public void setTscIsAccept(Integer tscIsAccept) {
		this.tscIsAccept = tscIsAccept;
	}

	public Integer getHeadTechIsAccept() {
		return headTechIsAccept;
	}

	public void setHeadTechIsAccept(Integer headTechIsAccept) {
		this.headTechIsAccept = headTechIsAccept;
	}

	public Integer getDevelopIsAccept() {
		return developIsAccept;
	}

	public void setDevelopIsAccept(Integer developIsAccept) {
		this.developIsAccept = developIsAccept;
	}

	public OrderCategoryBean getCategory() {
		return category;
	}

	public void setCategory(OrderCategoryBean category) {
		this.category = category;
	}

	public String buildOrderNumber(){
		SimpleDateFormat format = new SimpleDateFormat("yyMMddhhmmssSSSS");
		return format.format(new Date());
	}
	
	public static void main(String[] args) {
		System.out.println(new OrderBean().buildOrderNumber());
	}
	
}






























