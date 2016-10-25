package com.magic.sangha.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONArray;

/**
 *  订单实体
 * @author QimouXie
 *
 */
public class OrderBean implements Serializable {

	private static final long serialVersionUID = -7108439767925433419L;
	
	/**主键*/
	private Integer id;
	
	/**订单 问题类别*/
	private Integer orderCategory;
	
	/**订单问题类型名称*/
	private String orderCategoryName;
	
	/**订单 问题类别 名称*/
	private String categoryName;
	
	/**订单问题对象*/
	private OrderCategoryBean category;
	
	/**订单类型 顶级*/
	private Integer orderCategoryType;
	
	/**订单描述*/
	private String orderDescri	;
	
	/**订单图片集 json格式存储*/
	private String images;
	
	private JSONArray jsImages;
	
	/**订单视频集 json格式存储*/
	private String videos;
	
	private JSONArray jsVideos;
	
	/**订单音频集 json格式存储*/
	private String voices;
	
	private JSONArray jsVoices;
	
	/**客服ID 初始分配给的客服 分配规则：谁少分配给谁*/
	private Integer serviceId;
	
	/**订单状态*/
	private Integer status;
	
	/**客服是否查看 0：未查看 1：已查看*/
	private Integer serviceCheck;
	
	/**技术是否查看 0：未查看 1：已查看*/
	private Integer techCheck;
	
	/**订单状态跟踪 JSON数据*/
	private String orderData;
	
	/**员工版的 订单状态跟踪  JSON数据*/
	private String groupOrderData;
	
	/**总部技术人员*/
	private Integer headTechId;
	
	/**分公司的技术人员 术语：TSC*/
	private Integer tscId;

	/**研发人员*/
	private Integer decelopId;
	
	/**订单创建时间*/
	private Date createTime = new Date();
	
	/**发起订单的人*/
	private Integer userId;
	
	/**统计用*/
	private Integer count;
	
	/**订单编号*/
	private String orderNumber;
	
	/**订单是否有效  默认为有效 0表示有效  1表示无效*/
	private Integer isValid = 0;
	
	/** 研发是否查看 */
	private Integer developCheck;
	
	/**总部技术 是否查看*/
	private Integer headTechCheck;
	
	/**总技术是否 评论 0:未评论 1 已评论 2表示没权限评论*/
	private Integer isHeadTechComment;
	
	/**客服是否评论*/
	private Integer isServiceComment;
	
	/**TSC是否评论*/
	private Integer isTSCComment;
	
	/**研发是否评论*/
	private Integer isHeadDevelopComment;
	
	/**用户是否评价*/
	private Integer isUsercomment;
	
	/**是否 直接分配给总公司技术*/
	private Integer isHeadTech;
	
	/**TSC 是否接受任务0 没有接受 1接受  2 初始值*/
	private Integer tscIsAccept;
	
	/**总技术是否接受任务*/
	private Integer headTechIsAccept;
	
	/**研发是否接受任务*/
	private Integer developIsAccept;
	
	/**客服是否接受任务*/
	private Integer serviceIsAccept;
	
	/**发起人名字*/
	private String userName;
	
	/**客服名字*/
	private String serviceName;
	
	/**TSC名字*/
	private String tscName;
	
	/**总部技术名字*/
	private String headTechName;
	
	/**研发者名字*/
	private String developName;
	
	/**1 表示 反馈过 0 表示没有反馈 */
	private Integer isFeedback;
	
	/** 当订单状态为 待验证时，记录时间*/
	private Date updateTime;
	
	/**分技术开始处理时间*/
	private Date techStartTime;
	
	/**分技术处理完成时间*/
	private Date techEndTime;
	
	/**总部技术开始处理时间*/
	private Date headTechStartTime;
	
	/**总部技术处理完成时间*/
	private Date headTechEndTime;
	
	/**分公司技术最后反馈时间*/
	private Date techLastTime;
	
	/**总部技术最后反馈时间*/
	private Date headLastTime;
	
	/**研发 最后反馈时间*/
	private Date developTime;
	/**用户与TSC*/
	private OrderDecribeBean tscDecribe;
	/**总部技术与用户*/
	private OrderDecribeHeadTechBean headDecribe;
	/**总部技术与研发*/
	private HeadTechDevelopBean headDecribeD;
	/**总部技术与研发*/
	private HeadTechDevelopBean developDecribe;
	/**总部技术与TSC*/
	private TSCHeadTechDecribeBean tscDecribeD;
	/**总部技术与TSC*/
	private TSCHeadTechDecribeBean headDecribeT;
	/**电视台名称*/
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






























