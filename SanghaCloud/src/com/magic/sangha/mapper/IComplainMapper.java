package com.magic.sangha.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.ComplainBean;

/**
 *  投诉 持久层接口
 * @author QimouXie
 *
 */
public interface IComplainMapper {
	
	/**
	 *  新增投诉
	 * @param com
	 * @return
	 */
	@Insert("insert into t_complaint(type,order_id,content,createtime) values(#{coms.type},#{coms.orderId},#{coms.content},#{coms.createTime})")
	Integer addComplain(@Param("coms")ComplainBean com);
	
	/**
	 *  查看订单是否被投诉
	 * @param orderId
	 * @return
	 */
	ComplainBean findByOrderId(@Param("orderId")Integer orderId);
	
	/**
	 *  按时间查询 时间内的投诉
	 * @param startdate
	 * @param limit
	 * @param limitSize
	 * @return
	 */
	List<ComplainBean> queryComplainPage(@Param("startTime")Date startTime,@Param("endTime")Date endTime,@Param("limit")Integer limit,@Param("limitSize")Integer limitSize);
	/**
	 * 按时间统计 时间内的投诉
	 * @param startdate
	 * @return
	 */
	Integer countComplainByDate(@Param("startTime")Date startTime,@Param("endTime")Date endTime);
	
}
