package com.ylw.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.StringUtils;

public class DateConvertUtils {
	/**
	 * 日期型格式化成字符型
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String format(Date date, String format) {
		if(date != null && format != null){
			SimpleDateFormat formatter = null;
			if (StringUtils.isEmpty(format) || format.length() < 5) {
				formatter = new SimpleDateFormat("yyyy-MM-dd");
			} else {
				formatter = new SimpleDateFormat(format);
			}
			return formatter.format(date);
		}else{
			return "";
		}
	}

	/**
	 * 字符型格式化成日期型
	 * 
	 * @param datetimestr
	 * @param format
	 * @return
	 */
	public static Date parse(String datetimestr, String format) {
		return parse(datetimestr, format, java.util.Date.class);
	}

	@SuppressWarnings("unchecked")
	public static <T extends java.util.Date> T parse(String dateString,
			String dateFormat, Class<T> targetResultType) {
		if (StringUtils.isEmpty(dateString))
			return null;
		DateFormat df = new SimpleDateFormat(dateFormat);
		try {
			long time = df.parse(dateString).getTime();
			java.util.Date t = targetResultType.getConstructor(long.class)
					.newInstance(time);
			return (T) t;
		} catch (ParseException e) {
			String errorInfo = "cannot use dateformat:" + dateFormat
					+ " parse datestring:" + dateString;
			throw new IllegalArgumentException(errorInfo, e);
		} catch (Exception e) {
			throw new IllegalArgumentException("error targetResultType:"
					+ targetResultType.getName(), e);
		}
	}
	
	public static Date firstDate(String sdate) {
		Date date = parse(sdate, "yyyy-MM-dd");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return formatter.parse(formatter.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date endDate(String sdate) {
		Date date = parse(sdate, "yyyy-MM-dd");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return parse(formatter.format(date) + " 23:59:59",
				"yyyy-MM-dd HH:mm:ss");
	}


	/**
	 * 格式化成一天的第一分钟
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date firstDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return formatter.parse(formatter.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 格式化成一天的最后1分钟
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date endDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return parse(formatter.format(date) + " 23:59:59",
				"yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 取当前时间
	 * 
	 * @return
	 */
	public static Date getNow() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * 根据当前月取得任何一个月时间
	 * 
	 * @param arg
	 *            -1：上个月 / 1:下个月
	 * @return
	 */
	public static Date getAnyMonthDate(int arg) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.add(2, arg);
		return calendar.getTime();
	}

	/**
	 * 根据当天取得任何前几天时间
	 * 
	 * @return
	 */
	public static Date getAnyDayDate(int arg) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -arg);
		return calendar.getTime();
	}
	
	/**
	 * 获得上周开始时间
	 * 
	 * @return
	 */
	public static Date getLastWeekStart() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.WEEK_OF_MONTH, -1);
		cal.set(Calendar.DAY_OF_WEEK, 2);
		return firstDate(cal.getTime());
	}

	/**
	 * 获取周一时间 -2:上上周 -1：上周 0：本周 1：下周
	 * 
	 * @param arg
	 * @return
	 */
	public static Date getLastWeekStart(int arg) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.WEEK_OF_MONTH, arg);
		cal.set(Calendar.DAY_OF_WEEK, 2);
		return firstDate(cal.getTime());
	}

	/**
	 * 获得上周结束时间
	 * 
	 * @return
	 */
	public static Date getLastWeekEnd() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, 1);
		return endDate(cal.getTime());
	}

	public static Date getLastWeekEnd(int arg) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.WEEK_OF_MONTH, arg);
		cal.set(Calendar.DAY_OF_WEEK, 1);
		return endDate(cal.getTime());
	}

	/**
	 * 计算时间差[日期]
	 * 
	 * @param now
	 * @param returnDate
	 * @return
	 */
	public static int daysBetween(Date now, Date returnDate) {
		Calendar cNow = Calendar.getInstance();
		Calendar cReturnDate = Calendar.getInstance();
		cNow.setTime(now);
		cReturnDate.setTime(returnDate);
		setTimeToMidnight(cNow);
		setTimeToMidnight(cReturnDate);
		long todayMs = cNow.getTimeInMillis();
		long returnMs = cReturnDate.getTimeInMillis();
		long intervalMs = todayMs - returnMs;
		return millisecondsToDays(intervalMs);
	}

	/**
	 * 计算时间差[时间]
	 * 
	 * @param now
	 * @param returnDate
	 * @return
	 */
	public static int hoursBetween(Date now, Date returnDate) {
		Calendar cNow = Calendar.getInstance();
		Calendar cReturnDate = Calendar.getInstance();
		cNow.setTime(now);
		cReturnDate.setTime(returnDate);
		long todayMs = cNow.getTimeInMillis();
		long returnMs = cReturnDate.getTimeInMillis();
		long intervalMs = todayMs - returnMs;
		return millisecondsToHours(intervalMs);
	}
	/**
	 * 计算时间差[分钟]
	 * 
	 * @param now
	 * @param returnDate
	 * @return
	 */
	public static int minBetween(Date now, Date returnDate) {
		Calendar cNow = Calendar.getInstance();
		Calendar cReturnDate = Calendar.getInstance();
		cNow.setTime(now);
		cReturnDate.setTime(returnDate);
		long todayMs = cNow.getTimeInMillis();
		long returnMs = cReturnDate.getTimeInMillis();
		long intervalMs = todayMs - returnMs;
		return millisecondsTomin(intervalMs);
	}

	/**
	 * 计算小时数
	 * 
	 * @param intervalMs
	 * @return
	 */
	private static int millisecondsToHours(long intervalMs) {
		return (int) (intervalMs / (1000 * 3600));
	}

	/**
	 * 计算天数
	 * 
	 * @param intervalMs
	 * @return
	 */
	private static int millisecondsToDays(long intervalMs) {
		return (int) (intervalMs / (1000 * 86400));
	}
	
	/**
	 * 分钟数
	 * @param date
	 * @param now
	 * @return
	 */
	public static int millisecondsTomin(long intervalMs) {
		return (int) (intervalMs / (1000 * 60));
	}

	private static void setTimeToMidnight(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
	}

}
