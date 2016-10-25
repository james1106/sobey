package com.magic.sangha.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.ComplainBean;

/**
 *  Ͷ�� �־ò�ӿ�
 * @author QimouXie
 *
 */
public interface IComplainMapper {
	
	/**
	 *  ����Ͷ��
	 * @param com
	 * @return
	 */
	@Insert("insert into t_complaint(type,order_id,content,createtime) values(#{coms.type},#{coms.orderId},#{coms.content},#{coms.createTime})")
	Integer addComplain(@Param("coms")ComplainBean com);
	
	/**
	 *  �鿴�����Ƿ�Ͷ��
	 * @param orderId
	 * @return
	 */
	ComplainBean findByOrderId(@Param("orderId")Integer orderId);
	
	/**
	 *  ��ʱ���ѯ ʱ���ڵ�Ͷ��
	 * @param startdate
	 * @param limit
	 * @param limitSize
	 * @return
	 */
	List<ComplainBean> queryComplainPage(@Param("startTime")Date startTime,@Param("endTime")Date endTime,@Param("limit")Integer limit,@Param("limitSize")Integer limitSize);
	/**
	 * ��ʱ��ͳ�� ʱ���ڵ�Ͷ��
	 * @param startdate
	 * @return
	 */
	Integer countComplainByDate(@Param("startTime")Date startTime,@Param("endTime")Date endTime);
	
}
