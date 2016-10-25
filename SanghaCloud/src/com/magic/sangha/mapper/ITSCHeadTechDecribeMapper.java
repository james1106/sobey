package com.magic.sangha.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.TSCHeadTechDecribeBean;


/**
 *  TSC和总部技术的沟通记录  持久层接口
 * @author QimouXie
 *
 */
public interface ITSCHeadTechDecribeMapper {
	
	/**
	 *   添加记录
	 * @param decribe
	 * @return
	 */
	Integer addDecribe(@Param("decribe")TSCHeadTechDecribeBean decribe);
	
	/**
	 *  根据订单ID查询记录
	 * @param orderId
	 * @return
	 */
	List<TSCHeadTechDecribeBean> findByOrderId(@Param("orderId")Integer orderId);
	
	/**
	 *  订单联合查询
	 * @param map
	 * @return
	 */
	TSCHeadTechDecribeBean queryTSC(Map<String,Object> map);
	/**
	 *  订单联合查询
	 * @param map
	 * @return
	 */
	TSCHeadTechDecribeBean queryHeadTech(Map<String,Object> map);
}
