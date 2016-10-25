package com.magic.sangha.service;


import java.util.List;

import com.magic.sangha.bean.CommentBean;
import com.magic.sangha.bean.CommentCountBean;
import com.magic.sangha.bean.CutPageBean;

/**
 *  �������� ҵ���ӿ�
 * @author QimouXie
 *
 */
public interface ICommentService {

	/**
	 *  �������
	 * @param comment
	 * @return
	 */
	Integer addComment(CommentBean comment,Object obj) throws Exception;
	
	/**
	 *  ���� ��ɫ���� �������� ����ID ��ѯ��������
	 * @return
	 */
	CommentBean findByOrderIdAndType(Integer roleType,Integer orderId,Integer type);
	
	/**ͳ�� ����*/
	List<CommentCountBean> queryCommentCount(Integer companyId,Integer officeId,Integer roleId,Integer groupUserId,String mobile,String userName);
	
	/***��ҳ��ѯ ������������*/
	CutPageBean<CommentCountBean> queryDetailComment(Integer pageNO,Integer pageSize,Integer companyId,Integer officeId,Integer roleId,Integer groupUserId);
	
	List<CommentCountBean> queryCountCommentByUser(Integer userId);
	
	/**
	 *  ��ѯ�û��Ķ���ͳ������
	 * @param pageNO
	 * @param pageSize
	 * @param userId
	 * @return
	 */
	CutPageBean<CommentCountBean> queryDetailUserComment(Integer pageNO,Integer pageSize,Integer userId);
}
