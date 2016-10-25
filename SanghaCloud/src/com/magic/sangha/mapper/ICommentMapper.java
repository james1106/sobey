package com.magic.sangha.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.CommentBean;

/**
 *  评价业务 持久层接口
 * @author QimouXie
 *
 */
public interface ICommentMapper {
	
	/**
	 *  添加评论
	 * @param comment
	 * @return
	 */
	Integer addComment(@Param("comment")CommentBean comment);
	
	/**
	 *  根据 角色类型 评价类型 订单ID 查询评价内容
	 * @return
	 */
	CommentBean findByOrderIdAndType(@Param("roleType")Integer roleType,@Param("orderId")Integer orderId,@Param("type")Integer type);
	
	/**查询用户的订单评论*/
	List<CommentBean> queryCountByGroupUser(@Param("roleId")Integer roleId,@Param("groupUserId")Integer groupUserId);
	
	/**查询办事处下的订单评论*/
	List<CommentBean> queryCountByOfficeId(@Param("officeId")Integer officeId);
	
	/**查询分公司下的订单评论*/
	List<CommentBean> queryCountByCompanyId(@Param("companyId")Integer companyId);
	
	
	/**分页查询用户的订单评论*/
	List<CommentBean> queryPageCountByGroupUser(@Param("roleId")Integer roleId,@Param("groupUserId")Integer groupUserId,@Param("limit")Integer limit,@Param("limitSize")Integer limitSize);
	
	Integer countPageCountByGroupUser(@Param("roleId")Integer roleId,@Param("groupUserId")Integer groupUserId);

	/**分页查询办事处下的订单评论*/
	List<CommentBean> queryPageCountByOfficeId(@Param("officeId")Integer officeId,@Param("limit")Integer limit,@Param("limitSize")Integer limitSize);
	
	Integer countPageCountByOfficeId(@Param("officeId")Integer officeId);
	
	/**分页查询分公司下的订单评论*/
	List<CommentBean> queryPageCountByCompanyId(@Param("companyId")Integer companyId,@Param("limit")Integer limit,@Param("limitSize")Integer limitSize);
	
	Integer countPageCountByCompanyId(@Param("companyId")Integer companyId);
	
	/**
	 *  查询统计用户的评论
	 * @param userId
	 * @return
	 */
	List<CommentBean> queryCountCommentByUser(@Param("userId")Integer userId);
	
	
	/**分页查询分公司下的订单评论*/
	List<CommentBean> queryPageCountByUserId(@Param("userId")Integer userId,@Param("limit")Integer limit,@Param("limitSize")Integer limitSize);
	
	Integer countByUserId(@Param("userId")Integer userId);
	
	
	
	
	
}
