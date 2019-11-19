/**
 * Util.java
 * creater:徐强
 */
package com.ep.yc.yctcpanalysis.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

	private static final Log log = LogFactory.getLog(DateUtil.class);
	private static final String secondLabel = "秒";

	private static final String minuteLabel = "分";

	private static final String hourLabel = "时";

	private static final String dayLabel = "天";
	/**
	 * "yyyyMMdd HH:mm"
	 */
	public static final String DEFAULT_SHORT_DATE_TIME_FORMAT = "yyyyMMdd HH:mm";

	/**
	 * "yyyyMM"
	 */
	public static final String DEFAULT_SHORT_YEAR_MONTH_FORMAT = "yyyyMM";
	/**
	 * "yyyyMMdd"
	 */
	public static final String DEFAULT_SHORT_DATE_FORMAT = "yyyyMMdd";
	/**
	 * "yyyy-MM-dd"
	 */
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	/**
	 * "yyyy-MM-dd HH:mm"
	 */
	public static final String DEFAULT_DATETIME_FORMAT_EX = "yyyy-MM-dd HH:mm";
	/**
	 * "yyyy-MM-dd HH:mm:ss"
	 */
	public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * "yyyy-MM-dd HH:mm:ss"
	 */
	public static final String DEFAULT_INTEGER_FORMAT = "yyyyMMddHHmmss";
	/**
	 * "yyyy-MM-dd HH:mm:ss"
	 */
	public static final String DEFAULT_INTEGER_FORMATSSS = "yyyyMMddHHmmssSSS";
	/**
	 * "HH:mm:ss"
	 */
	public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

	public static SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);

	/**
	 * 获取当前系统时间字符串（当前时间）
	 * 
	 * @param format
	 * @return
	 */
	public static String getCurDateStr(String format) {
		return dateToString(new Date(), format);
	}

	public static String getCurDate() {
		return dateToString(new Date(), DEFAULT_DATETIME_FORMAT);
	}
	public static String getAbbDate() {
		return dateToString(new Date(), DEFAULT_INTEGER_FORMAT);
	}
	
	/**
	 * 转换日期对象到日期字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToString(Date date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}

	/**
	 * 转换日期字符串到日期对象
	 * 
	 * @param dateString
	 * @param format
	 *            日期格式
	 * @return
	 */
	public static Date stringToDate(String dateString, String format) {
		Date date = null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			date = formatter.parse(dateString);
		} catch (ParseException e) {
			log.error("stringToDate fail", e);
		}
		return date;
	}

	/**
	 * 自动匹配模式-转换日期字符串到日期对象
	 * 
	 * @param dateString
	 * @return
	 */
	public static Date autoStringToDate(String dateString) {
		String format = DEFAULT_DATE_FORMAT;
		if (dateString.matches("\\d{2,4}\\d{1,2}")) {
			format = DEFAULT_SHORT_YEAR_MONTH_FORMAT;
		} else if (dateString.matches("\\d{2,4}\\d{1,2}\\d{1,2}")) {
			format = DEFAULT_SHORT_DATE_FORMAT;
		} else if (dateString
				.matches("\\d{2,4}\\-\\d{1,2}\\-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}")) {
			format = DEFAULT_DATETIME_FORMAT;
		} else if (dateString
				.matches("\\d{2,4}\\-\\d{1,2}\\-\\d{1,2} \\d{1,2}:\\d{1,2}")) {
			format = DEFAULT_DATETIME_FORMAT_EX;
		} else if (dateString.matches("\\d{1,2}:\\d{1,2}:\\d{1,2}")) {
			format = DEFAULT_TIME_FORMAT;
		}
		return stringToDate(dateString, format);
	}

	/**
	 * 格式化日期字符串
	 * 
	 * @param dateStr
	 *            日期字符串
	 * @param format
	 * @return
	 */
	public static String formatDateStr(String dateStr, String format)
			throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date date = formatter.parse(dateStr);
		SimpleDateFormat formatter2 = new SimpleDateFormat(format);
		System.out.println(formatter2.format(date));
		return formatter2.format(date);
	}

	/**
	 * 将 <code>java.util.Date</code> 转换为 <code>java.sql.Date</code>
	 * 
	 * @param date
	 * @return
	 */
	public static java.sql.Date toSqlDate(Date date) {
		if (date != null) {
			return new java.sql.Date(date.getTime());
		}
		return null;
	}

	/**
	 * 将 <code>java.util.Date</code> 转换为 <code>java.sql.Date</code>
	 * 
	 * @param date
	 * @return
	 */
	public static Timestamp toTimestamp(Date date) {
		if (date != null) {
			return new Timestamp(date.getTime());
		}

		return null;
	}

	/**
	 * 比较s1和s2转换成日期后比较的结果， s1 和 s2 相同返回 0 ， s1 在 s2 之后返回 1, 反之返回 -1
	 *
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static int compareDate(String s1, String s2) {
		try {
			Date d1 = autoStringToDate(s1);
			Date d2 = autoStringToDate(s2);
			return d1.compareTo(d2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public static int compareDate(Date d1, Date d2) {
		try {
			return d1.compareTo(d2);
		} catch (Exception ex) {

		}
		return -1;
	}

	private static Calendar getCalendar(long time) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		return cal;
	}

	/**
	 * 获取年
	 *
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		Calendar cal = getCalendar(date.getTime());
		return cal.get(Calendar.YEAR);
	}

	/**
	 * 获取月
	 *
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		Calendar cal = getCalendar(date.getTime());
		return cal.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取天
	 *
	 * @param date
	 * @return
	 */
	public static int getDate(Date date) {
		Calendar cal = getCalendar(date.getTime());
		return cal.get(Calendar.DATE);
	}

	/**
	 * 获取小时
	 *
	 * @param date
	 * @return
	 */
	public static int getHour(Date date) {
		Calendar cal = getCalendar(date.getTime());
		return cal.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 获取分钟
	 *
	 * @param date
	 * @return
	 */
	public static int getMinute(Date date) {
		Calendar cal = getCalendar(date.getTime());
		return cal.get(Calendar.MINUTE);
	}

	/**
	 * 获取秒
	 *
	 * @param date
	 * @return
	 */
	public static int getSecond(Date date) {
		Calendar cal = getCalendar(date.getTime());
		return cal.get(Calendar.SECOND);
	}

	/**
	 * 根据时间获得随机数
	 */
	public static String getRnd() {
		Calendar tCal = Calendar.getInstance();
		Timestamp ts = new Timestamp(tCal.getTime().getTime());
		Date date = new Date(ts.getTime());
		SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		String tmpStr = formatter.format(date)
				+ Math.round(Math.random() * 1000 + 1);
		return (tmpStr);
	}

	/**
	 * 获取两个日期时间的数值差值
	 *
	 * @param firstTime
	 * @param secondTime
	 * @return
	 */
	public static long getDateSCDiff(String firstTime, String secondTime) {
		Date firstDate = autoStringToDate(firstTime);// 服务器
		Date secondDate = autoStringToDate(secondTime);// 客户端
		// 日期相减得到相差的日期
		return firstDate.getTime() - secondDate.getTime();
	}

	/**
	 * 日期相减获取具体的时分秒
	 *
	 * @param dateOne
	 * @param dateTwo
	 * @return
	 */
	public static String dateDiffByDateStr(String dateOne, String dateTwo) {
		Date date1 = autoStringToDate(dateOne);
		Date date2 = autoStringToDate(dateTwo);
		// long diffDate = date1.getTime()-date2.getTime()>0 ?
		// date1.getTime()-date2.getTime():
		// date2.getTime()-date1.getTime();
		// 日期相减得到相差的日期
		long second = (date1.getTime() - date2.getTime()) / (1000) > 0 ? (date1
				.getTime() - date2.getTime()) / (1000)
				: (date2.getTime() - date1.getTime()) / (1000);
		String castDay = String.valueOf(second / (60 * 60 * 24));// 除一天的秒钟数
		String castHours = String
				.valueOf((second % (60 * 60 * 24)) / (60 * 60));// 模一天的秒数再除一小时的秒钟
		String castMinute = String
				.valueOf(((second % (60 * 60 * 24)) % (60 * 60)) / 60);// 模一天的秒钟数再模一小时的秒钟
		// ,
		// 然后再除一分钟的秒
		String castSecond = String
				.valueOf(((second % (60 * 60 * 24)) % (60 * 60)) % 60);// 模一天的秒钟数再模一小时的秒钟
		// ,
		StringBuilder cn = new StringBuilder(); // 然后再模一分钟的秒
		if (!"0".equals(castDay)) {
			cn.append(castDay).append(dayLabel);
		}
		if (!"0".equals(castHours)) {
			cn.append(castHours).append(hourLabel);
		}
		if (!"0".equals(castMinute)) {
			cn.append(castMinute).append(minuteLabel);
		}
		if (!"0".equals(castSecond)) {
			cn.append(castSecond).append(secondLabel);
		}
		return cn.toString();
		// return
		// castDay.concat(dayLabel).concat(castHours).concat(hourLabel).concat(
		// castMinute).concat(minuteLabel).concat(castSecond).concat(secondLabel);
	}

	/**
	 * 日期相减获取具体的时分秒
	 *
	 * @param times
	 *            秒
	 * @return
	 */
	public static String dateDiffByDateStr(long times) {
		long second = times;
		String castDay = String.valueOf(second / (60 * 60 * 24));// 除一天的秒钟数
		String castHours = String
				.valueOf((second % (60 * 60 * 24)) / (60 * 60));// 模一天的秒数再除一小时的秒钟
		String castMinute = String
				.valueOf(((second % (60 * 60 * 24)) % (60 * 60)) / 60);// 模一天的秒钟数再模一小时的秒钟
		// ,
		// 然后再除一分钟的秒
		String castSecond = String
				.valueOf(((second % (60 * 60 * 24)) % (60 * 60)) % 60);// 模一天的秒钟数再模一小时的秒钟
		// ,
		// 然后再模一分钟的秒

		StringBuilder cn = new StringBuilder();
		if (!castDay.equals("0")) {
			cn.append(castDay).append(dayLabel);
		}
		if (!castHours.equals("0")) {
			cn.append(castHours).append(hourLabel);
		}
		if (!castMinute.equals("0")) {
			cn.append(castMinute).append(minuteLabel);
		}
		if (!castSecond.equals("0")) {
			cn.append(castSecond).append(secondLabel);
		}
		return cn.toString();
	}

	// 获取工作年限
	public static String getWorkYearByDateDiff(String beginDate,
			String endDate, boolean showAllMonthFlag) throws Exception {
		Date date1 = autoStringToDate(beginDate);
		Date date2 = autoStringToDate(endDate);
		// 日期相减得到相差的日期
		long second = (date1.getTime() - date2.getTime()) / (1000) > 0 ? (date1
				.getTime() - date2.getTime()) / (1000)
				: (date2.getTime() - date1.getTime()) / (1000);
		String castYear = String.valueOf(second / (60 * 60 * 24 * 365));
		String castMonth = String.valueOf((second % (60 * 60 * 24 * 365))
				/ (60 * 60 * 24 * 30));
		String castTotalMonth = String.valueOf(second / (60 * 60 * 24 * 30));// 共多少个月
		if (showAllMonthFlag) {
			return castYear.concat("年").concat(castMonth).concat("月").concat(
					",共").concat(castTotalMonth).concat("月");
		} else {
			return castYear.concat("年").concat(castMonth).concat("月");
		}
	}

	/**
	 * 验证时间的差值（以天为计算）(<diffValue返回true);
	 *
	 * @param validateTime1
	 * @param validateTime2
	 * @param diffValue
	 * @return
	 * @throws Exception
	 */
	public static boolean validateDayByDiffValue(String validateTime1,
			String validateTime2, long diffValue) throws Exception {
		return validateTimeByDiffValue(validateTime1, validateTime2,
				diffValue * 24 * 60);
	}

	/**
	 * 设置验证时间的差值(以分钟计算).(<diffValue返回true)
	 *
	 * @param validateTime1
	 * @param validateTime2
	 * @param diffValue
	 * @return
	 * @throws Exception
	 */
	public static boolean validateTimeByDiffValue(String validateTime1,
			String validateTime2, long diffValue) throws Exception {
		Date date1 = autoStringToDate(validateTime1);
		Date date2 = autoStringToDate(validateTime2);
		// 日期相减得到相差的日期
		long second = (date1.getTime() - date2.getTime()) / (1000 * 60) > 0 ? (date1
				.getTime() - date2.getTime())
				/ (1000 * 60)
				: (date2.getTime() - date1.getTime()) / (1000 * 60);
		if (second >= diffValue) {
			return false;
		}
		return true;
	}

	/**
	 * 设置验证时间的差值(以秒钟计算)(<diffValue返回true)
	 *
	 * @param validateTime1
	 * @param validateTime2
	 * @param diffValue
	 * @return
	 * @throws Exception
	 */
	public static boolean validateSecondByDiffValue(String validateTime1,
			String validateTime2, long diffValue) throws Exception {
		Date date1 = autoStringToDate(validateTime1);
		Date date2 = autoStringToDate(validateTime2);
		// 日期相减得到相差的秒钟
		long second = (date1.getTime() - date2.getTime()) / (1000) > 0 ? (date1
				.getTime() - date2.getTime()) / (1000)
				: (date2.getTime() - date1.getTime()) / (1000);
		if (second >= diffValue) {
			return false;
		}
		return true;
	}

	/**
	 * 获得本年第一天的日期
	 *
	 * @return
	 */
	public static String getFirstDayOfYear() {
		int yearPlus = getYearPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, yearPlus);
		Date yearDay = currentDate.getTime();
		SimpleDateFormat df = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		String preYearDay = df.format(yearDay);
		return preYearDay;
	}

	private static int getYearPlus() {
		Calendar cd = Calendar.getInstance();
		int yearOfNumber = cd.get(Calendar.DAY_OF_YEAR);// 获得当天是一年中的第几天
		cd.set(Calendar.DAY_OF_YEAR, 1);// 把日期设为当年第一天
		cd.roll(Calendar.DAY_OF_YEAR, -1);// 把日期回滚一天。
		int MaxYear = cd.get(Calendar.DAY_OF_YEAR);
		if (yearOfNumber == 1) {
			return -MaxYear;
		} else {
			return 1 - yearOfNumber;
		}
	}

	/**
	 * 获取当月第一天
	 *
	 * @return
	 */
	public static String getFirstDayOfMonth() {
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		return sdf.format(lastDate.getTime());
	}

	// 获得当前日期与本周日相差的天数 (我们计算根据中国周一开始算起)
	private static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK); // 外国周末是第1天
		if (dayOfWeek == 1) {// 周末时候
			return dayOfWeek - 7;
		} else {
			return 2 - dayOfWeek;
		}
	}

	/**
	 * 获取本周第一天(周一日期)
	 *
	 * @return
	 */
	public static String getFirstDayOfWeek() {
		SimpleDateFormat tempDate = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		int mondayPlus = getMondayPlus();
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DATE, mondayPlus);
		return tempDate.format(now.getTime());
	}

	/**
	 * 获取当前日期(yyyyMMdd)
	 *
	 * @return
	 */
	public static String getNowDate() {
		SimpleDateFormat tempDate = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		return tempDate.format(new Date());
	}

	/**
	 * 根据参数获取当天前后的日期 LST num为正:当前日期后num天是返回值 num为负:当前日期前num天是返回值
	 * 返回的日期的格式:yyyy-MM-dd
	 */
	public static String getTheDay(int num) {
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		GregorianCalendar gc = new GregorianCalendar();
		gc.add(GregorianCalendar.DATE, num);
		Date theday = gc.getTime();
		return sdf.format(theday);
	}

	/**
	 * 获得某日期的下一天
	 *
	 * @param dateStr
	 * @return
	 */
	public static String getNextDayByDataStr(String dateStr) {
		if (dateStr == null || dateStr.trim().length() == 0) {
			return "";
		}
		SimpleDateFormat f = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(f.parse(dateStr));
		} catch (ParseException ex) {
			log.error("getNextDay fail", ex);
		}
		calendar.add(5, 1);
		return f.format(calendar.getTime());
	}

	/**
	 * 获得某日期的下N天
	 *
	 * @param dateStr
	 * @return
	 */
	public static String getNextDayByDataStr(String dateStr, int n) {
		if (dateStr == null || dateStr.trim().length() == 0) {
			return "";
		}
		SimpleDateFormat f = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(f.parse(dateStr));
		} catch (ParseException ex) {
			log.error("getNextDay fail", ex);
		}
		calendar.add(5, n);
		return f.format(calendar.getTime());
	}

	/**
	 * 根据YYYYMMDD格式获取该日期当月第一天
	 *
	 * @param yyyyMMDD
	 * @return
	 */
	public static String getMonthFirstDayByYYYYMMDD(String yyyyMMDD) {
		SimpleDateFormat tempDate = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		try {
			Calendar now = Calendar.getInstance();
			String[] yearAndMonthStr = new String[2];
			yearAndMonthStr[0] = yyyyMMDD.substring(0, 4);
			yearAndMonthStr[1] = yyyyMMDD.substring(4, 6);
			now.set(Integer.valueOf(yearAndMonthStr[0]), Integer.valueOf(
					yearAndMonthStr[1]).intValue() - 1, 1);
			return tempDate.format(now.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getMonthFirstDayByYYYYMMDD fail", e);
		}
		return tempDate.format(new Date());
	}

	/**
	 * 根据YYYYMMDD格式获取该日期当月最后一天
	 *
	 * @param yyyyMMDD
	 * @return
	 */
	public static String getMonthLastDayByYYYYMMDD(String yyyyMMDD) {
		SimpleDateFormat tempDate = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		try {
			Calendar now = Calendar.getInstance();
			String[] yearAndMonthStr = new String[2];
			yearAndMonthStr[0] = yyyyMMDD.substring(0, 4);
			yearAndMonthStr[1] = yyyyMMDD.substring(4, 6);
			now.set(Integer.valueOf(yearAndMonthStr[0]), Integer.valueOf(
					yearAndMonthStr[1]).intValue() - 1, 1);
			now.add(Calendar.MONTH, 1);
			now.add(Calendar.DATE, -1);
			return tempDate.format(now.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getMonthLastDayByYYYYMMDD fail", e);
		}
		return tempDate.format(new Date());
	}

	/**
	 * 返回两个日期相差的天数
	 * 
	 * @param dateOne
	 * @param dateTwo
	 * @return
	 * @throws ParseException
	 */
	public static int getDistDates(String dateOne, String dateTwo) {
		int totalDate = 0;
		Date startDate = autoStringToDate(dateOne);
		Date endDate = autoStringToDate(dateTwo);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		long timestart = calendar.getTimeInMillis();
		calendar.setTime(endDate);
		long timeend = calendar.getTimeInMillis();
		totalDate = Long.valueOf(
				Math.abs((timeend - timestart)) / (1000 * 60 * 60 * 24))
				.intValue();
		return totalDate;
	}

	/**
	 * 某日期是否在两日期之间的时间
	 * 
	 * @param sourceDate
	 * @param beginDate
	 * @param endDate
	 * @return
	 * @throws Exception
	 */
	public static boolean betweenTwoDate(String sourceDate, String beginDate,
			String endDate) throws Exception {
		boolean flag = false;
		Date sourceDateD = autoStringToDate(sourceDate);
		Date beginDateD = autoStringToDate(beginDate);
		Date endDateD = autoStringToDate(endDate);
		if (sourceDateD.getTime() >= beginDateD.getTime()
				&& sourceDateD.getTime() <= endDateD.getTime()) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 格式化时间 前台已经补0 把传入的时间格式化成为历史轨迹的时间格式 传入时间格式为yyyy-MM-dd HH:mm:ss
	 * 转化后的格式为yyyyMMddHHmmss
	 * 
	 * @param time
	 * @return
	 */
	public static String fomartTimeToHistoryMode(String time) {
		return time.replaceAll("[-| |:]", "");
	}

	/**
	 * （开始）字符串日期转成数字日期(例如“2011-01-01”变为“20110101000000”)
	 * 
	 * @param date
	 * @return
	 */
	public static String dateStrToBeginDateNumber(String date) {
		date = date.replaceAll("[-| |:]", "");
		while (date.length() < 14) {
			date += "0";
		}
		return date;
	}

	/**
	 * （结束）字符串日期转成数字日期(例如“2011-01-01”变为“20110101235959”)
	 * 
	 * @param date
	 * @return
	 */
	public static String dateStrToEndDateNumber(String date) {
		date = date.replaceAll("[-| |:]", "");
		if (date.length() == 8) {
			date = date + "235959";
		} else if (date.length() == 12) {
			date = date + "59";
		} else if (date.length() == 10) {
			date = date + "5959";
		}
		return date;
	}

	/**
	 * 数字日期转成字符串日期(例如“20110101235959”变为“2011-01-01 23:59:59”)
	 * 
	 * @param date
	 * @return
	 */
	public static String dateNumberToDateStr(String date) {
		if (date.length() == 14)
			return new StringBuilder(date).insert(12, ":").insert(10, ":")
					.insert(8, " ").insert(6, "-").insert(4, "-").toString();
		return date;
	}
	/**
	 * 两个时间获取分钟数差
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static long dateNumberToDateStr(Date d1,Date d2){
		Calendar dateOne=Calendar.getInstance(),
				dateTwo=Calendar.getInstance();
		dateOne.setTime(d1);	//设置为当前系统时间 
		dateTwo.setTime(d2); 
		long timeOne=dateOne.getTimeInMillis();
		long timeTwo=dateTwo.getTimeInMillis();
		long minute=(timeOne-timeTwo)/(1000*60);//转化minute
		return minute;
	} 
	/**
	 * 时间字符串转换为时间
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date dateStrToDate(String date,String format){  
	SimpleDateFormat f=new SimpleDateFormat(format);
     try {
		return f.parse(date);
	} catch (ParseException e) { 
		e.printStackTrace();
	}
	return null; 
}
	/** 
     *  
     * 描述:获取下一个月. 
     *  
     * @return 
     */  
    public static String getPreMonth() {  
        Calendar cal = Calendar.getInstance();  
        cal.add(cal.MONTH, 1);  
        SimpleDateFormat dft = new SimpleDateFormat(DateUtil.DEFAULT_DATETIME_FORMAT);  
        String preMonth = dft.format(cal.getTime());  
        return preMonth;  
    }  
    public static String dateFormat(String date,int yuefen) {
     	  SimpleDateFormat format = new SimpleDateFormat( DateUtil.DEFAULT_DATETIME_FORMAT);
     	  Calendar cal = Calendar.getInstance();
     	  try {
			cal.setTime(format.parse(date));
		} catch (ParseException e) { 
			e.printStackTrace();
		} 
     	  //下面的就是把当前日期加一个月
     	  cal.add(Calendar.MONTH, yuefen); 
     	  return format.format(cal.getTime());
    }
    public static Date dateHour(Date date,int hour) {
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.set(Calendar.HOUR,calendar.get(Calendar.HOUR) + hour);// 让日期加1
    	return calendar.getTime();
    //	System.out.println(calendar.get(Calendar.DATE));// 加1之后的日期Top
		//System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS").format(calendar.getTime()));
    }
    public static Date dateMinute(Date date,int minute) {
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.set(Calendar.MINUTE,calendar.get(Calendar.MINUTE) + minute);// 让分钟加1
    	return calendar.getTime();
    //	System.out.println(calendar.get(Calendar.DATE));// 加1之后的日期Top
		//System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS").format(calendar.getTime()));
    }
}
