package com.magic.sangha.service;


import java.util.List;

import com.magic.sangha.bean.CommentBean;
import com.magic.sangha.bean.CommentCountBean;
import com.magic.sangha.bean.CutPageBean;

/**
 *  订单评论 业务层接口
 * @author QimouXie
 *
 */
public interface ICommentService {

	/**
	 *  添加评论
	 * @param comment
	 * @return
	 */
	Integer addComment(CommentBean comment,Object obj) throws Exception;
	
	/**
	 *  根据 角色类型 评价类型 订单ID 查询评价内容
	 * @return
	 */
	CommentBean findByOrderIdAndType(Integer roleType,Integer orderId,Integer type);
	
	/**统计 评论*/
	List<CommentCountBean> queryCommentCount(Integer companyId,Integer officeId,Integer roleId,Integer groupUserId,String mobile,String userName);
	
	/***分页查询 订单评价详情*/
	CutPageBean<CommentCountBean> queryDetailComment(Integer pageNO,Integer pageSize,Integer companyId,Integer officeId,Integer roleId,Integer groupUserId);
	
	List<CommentCountBean> queryCountCommentByUser(Integer userId);
	
	/**
	 *  查询用户的订单统计详情
	 * @param pageNO
	 * @param pageSize
	 * @param userId
	 * @return
	 */
	CutPageBean<CommentCountBean> queryDetailUserComment(Integer pageNO,Integer pageSize,Integer userId);
}
