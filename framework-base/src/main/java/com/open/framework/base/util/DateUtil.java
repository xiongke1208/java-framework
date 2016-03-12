package com.open.framework.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DateUtil {

	private static final Log LOGGER = 
			LogFactory.getLog(DateUtil.class);
	
	private static SimpleDateFormat formateyMdHmsS 
			= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	private static SimpleDateFormat formateyMdHms 
			= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat formateyMdHm 
			= new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private static SimpleDateFormat formateyMd 
			= new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat formateHms
			= new SimpleDateFormat("HH:mm:ss");
	
	public static Date parseDateFromYMdHmss(String date) {
		if(StringUtil.isEmpty(date)) {
			return null;
		}
		
		try {
			return formateyMdHms.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * 根据生日获取年龄
	 * @param birthday
	 * @return
	 */
	public static String getAgeByBirthdate(String birthday) {
		Date date = null;
		try {
			if(birthday != null && !"".equals(birthday)) {
				date = formateyMd.parse(birthday);
			}
		} catch (Exception e) {
			LOGGER.error("根据生日计算年龄异常："+birthday,e);
		}
		return getAgeByBirthdate(date);
	}
	
	/**
	 * 根据生日获取年龄
	 * @param birthday
	 * @return
	 */
	public static String getAgeByBirthdate(Date birthday) {
		String age = "";
		if(birthday != null) {
			Date today = new Date();
			int i = today.getYear() - birthday.getYear();
			age = String.valueOf(i);
		}
		
		return age;
	}

	public static String getDateFormate(Date date, String formate) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat simpleDateFormate = new SimpleDateFormat(formate);
		return simpleDateFormate.format(date);
	}
	
	public static String get4yMdHmsS(Date date) {
		return formateyMdHmsS.format(date);
	}

	public static String get4yMdHms(Date date) {
		return formateyMdHms.format(date);
	}

	public static String get4yMdHm(Date date) {
		return formateyMdHm.format(date);
	}

	public static String get4yMd(Date date) {
		return formateyMd.format(date);
	}
	public static String get4yHh(Date date) {
		return formateHms.format(date);
	}
	
}
