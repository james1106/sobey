package com.magic.sangha.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magic.sangha.bean.ReportBean;

/**
 *  �澯��Ϣ�б�  �־ò�ӿ�
 * @author QimouXie
 *
 */
public interface IReportMapper {
	
	/**
	 *  �������� �澯��Ϣ
	 * @param reports
	 * @return
	 */
	Integer batchAddReport(@Param("reports")List<ReportBean> reports);
	
	/**
	 *  ��������ҳ��ȡ �澯��Ϣ
	 * @param statiocCodes
	 * @param groupCode
	 * @param limit
	 * @param limitSize
	 * @return
	 */
	List<ReportBean> findReportByPage(@Param("stationCodes")List<String> statiocCodes,@Param("groupCode")String groupCode,@Param("limit")Integer limit,@Param("limitSize")Integer limitSize);

	/**
	 *  ������ ͳ������
	 * @param statiocCodes
	 * @param groupCode
	 * @return
	 */
	Integer countReport(@Param("stationCodes")List<String> statiocCodes,@Param("groupCode")String groupCode);
	
	/**
	 *  ��ѯ���µĸ���ʱ��
	 * @return
	 */
	ReportBean findNewestUpdateTime();
	
	/**
	 *  ���� �澯��Ϣ
	 *   �澯��Ϣ����Ч��Ϊһ��
	 * @return
	 */
	Integer delClearReport(@Param("targetDate")Date targetDate) ;
	
}
