package com.magic.sangha.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.CommentBean;

/**
 *  ����ҵ�� �־ò�ӿ�
 * @author QimouXie
 *
 */
public interface ICommentMapper {
	
	/**
	 *  �������
	 * @param comment
	 * @return
	 */
	Integer addComment(@Param("comment")CommentBean comment);
	
	/**
	 *  ���� ��ɫ���� �������� ����ID ��ѯ��������
	 * @return
	 */
	CommentBean findByOrderIdAndType(@Param("roleType")Integer roleType,@Param("orderId")Integer orderId,@Param("type")Integer type);
	
	/**��ѯ�û��Ķ�������*/
	List<CommentBean> queryCountByGroupUser(@Param("roleId")Integer roleId,@Param("groupUserId")Integer groupUserId);
	
	/**��ѯ���´��µĶ�������*/
	List<CommentBean> queryCountByOfficeId(@Param("officeId")Integer officeId);
	
	/**��ѯ�ֹ�˾�µĶ�������*/
	List<CommentBean> queryCountByCompanyId(@Param("companyId")Integer companyId);
	
	
	/**��ҳ��ѯ�û��Ķ�������*/
	List<CommentBean> queryPageCountByGroupUser(@Param("roleId")Integer roleId,@Param("groupUserId")Integer groupUserId,@Param("limit")Integer limit,@Param("limitSize")Integer limitSize);
	
	Integer countPageCountByGroupUser(@Param("roleId")Integer roleId,@Param("groupUserId")Integer groupUserId);

	/**��ҳ��ѯ���´��µĶ�������*/
	List<CommentBean> queryPageCountByOfficeId(@Param("officeId")Integer officeId,@Param("limit")Integer limit,@Param("limitSize")Integer limitSize);
	
	Integer countPageCountByOfficeId(@Param("officeId")Integer officeId);
	
	/**��ҳ��ѯ�ֹ�˾�µĶ�������*/
	List<CommentBean> queryPageCountByCompanyId(@Param("companyId")Integer companyId,@Param("limit")Integer limit,@Param("limitSize")Integer limitSize);
	
	Integer countPageCountByCompanyId(@Param("companyId")Integer companyId);
	
	/**
	 *  ��ѯͳ���û�������
	 * @param userId
	 * @return
	 */
	List<CommentBean> queryCountCommentByUser(@Param("userId")Integer userId);
	
	
	/**��ҳ��ѯ�ֹ�˾�µĶ�������*/
	List<CommentBean> queryPageCountByUserId(@Param("userId")Integer userId,@Param("limit")Integer limit,@Param("limitSize")Integer limitSize);
	
	Integer countByUserId(@Param("userId")Integer userId);
	
	
	
	
	
}
