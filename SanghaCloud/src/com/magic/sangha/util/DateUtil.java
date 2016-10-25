package com.magic.sangha.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *  日期格式化工具
 * @author QimouXie
 *
 */
public class DateUtil {
	
	/**
	 *  转换 日期为 yyyyMMdd
	 * @param date
	 * @return
	 */
	public static String DateToyyyyMMdd(Date date){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		return format.format(date);
	}
	/**
	 *  转换 日期为 yyyyMM
	 * @param date
	 * @return
	 */
	public static String DateToyyyyMM(Date date){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		return format.format(date);
	}
	
	public static Date stringToDate(String yyyyMMddhhmmss){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(yyyyMMddhhmmss);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static Date getDate(String dateStr){
		Date date = null;
		if(dateStr !=null && dateStr.matches("\\d{4}-\\d{2}-\\d{2}")){
			SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date = simple.parse(dateStr);
			} catch (ParseException e) {
				return null;
			}
		}
		return date;
	}
	
	/**
	 *  获取 指定日期的 之后 之前 的几秒时间
	 * @param date
	 * @return
	 */
	public static Date getPreSecondDate(Date date,Integer second){
		if(null == date){
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.SECOND, second);
		return c.getTime();
	}
	
	public static Date getNextDay(Date date,Integer days){
		if(null == date){
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH,days);
		return c.getTime();
	}
	
}
