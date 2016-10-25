package com.magic.sangha.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.HeadTechDevelopBean;


/**
 *  总部技术 和 总部研发 的补丁描述记录  持久层接口
 * @author QimouXie
 *
 */
public interface IHeadTechDevelopMapper {

	
	/**
	 *   添加记录
	 * @param decribe
	 * @return
	 */
	Integer addDecribe(@Param("decribe")HeadTechDevelopBean decribe);
	
	/**
	 *  根据订单ID查询记录
	 * @param orderId
	 * @return
	 */
	List<HeadTechDevelopBean> findByOrderId(@Param("orderId")Integer orderId);
	
	/**
	 *   统计 当前 订单 是否 反馈过
	 * @param orderId
	 * @param type
	 * @return
	 */
	Integer countIsFeedback(@Param("orderId")Integer orderId,@Param("type")Integer type);
	
	
	List<HeadTechDevelopBean> queryPage(@Param("limitSize")Integer limitSize,@Param("limit")Integer limit,@Param("realName")String realName,
			@Param("startTime")Date startTime,@Param("endTime")Date endTime);
	
	Integer countPage(@Param("realName")String realName,@Param("startTime")Date startTime,@Param("endTime")Date endTime);
	
	List<HeadTechDevelopBean> queryPageByDevelopId(@Param("developId")Integer developId,@Param("orderNumber")String orderNumber,@Param("limit")Integer limit,
			@Param("limitSize")Integer limitSize,@Param("startTime")Date startTime,@Param("endTime")Date endTime);
	
	Integer countPageByDevelopId(@Param("developId")Integer developId,@Param("orderNumber")String orderNumber,@Param("startTime")Date startTime,@Param("endTime")Date endTime);
	
	/**
	 *  订单联合查询
	 * @param map
	 * @return
	 */
	HeadTechDevelopBean queryHeadTech(Map<String,Object> map);
	/**
	 *  订单联合查询
	 * @param map
	 * @return
	 */
	HeadTechDevelopBean queryDevelop(Map<String,Object> map);

}














