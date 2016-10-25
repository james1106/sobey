package com.magic.sangha.service;

import java.util.Date;
import java.util.List;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.ReportBean;

/**
 *  �澯��Ϣ ҵ���ӿ�
 * @author QimouXie
 *
 */
public interface IReportService {
	
	/**
	 *  �������� �澯��Ϣ
	 * @param reports
	 * @return
	 * @throws Exception
	 */
	Integer batchAddReport(List<ReportBean> reports) throws Exception;
	
	/**
	 *  ��ҳ��ȡ �澯��Ϣ
	 * @param obj
	 * @param stationCode
	 * @param groupCode
	 * @param pageNO
	 * @param pageSize
	 * @return
	 */
	CutPageBean<ReportBean> findReportPage(Object obj,String stationCode,String groupCode,Integer pageNO,Integer pageSize);
	
	/**
	 *  ��ѯ���µĸ���ʱ��
	 * @return
	 */
	ReportBean findNewestUpdateTime();
	
	/**
	 *  ɾ��������ǰ������
	 * @param today
	 * @param daysBefore ����ǰ
	 * @return
	 */
	Integer delClearReport(Date today,Integer daysBefore) throws Exception;
	
}
