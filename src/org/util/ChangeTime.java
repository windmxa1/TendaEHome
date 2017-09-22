package org.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class ChangeTime {
	/**
	 * 日期格式字符串转换成时间戳
	 * 
	 * @param date
	 *            字符串日期
	 * @param format
	 *            如：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String date2TimeStamp(String date_str, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return String.valueOf(sdf.parse(date_str).getTime() / 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 取得当天的指定时间的时间戳
	 */
	public static long hourTimeStamp(Integer hour,Long time) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time*1000);
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, 0);//分  
		c.set(Calendar.SECOND, 0);//秒 
		return c.getTimeInMillis()/1000;
	}

	/**
	 * 取得当前时间戳（精确到秒）
	 * 
	 * @return
	 */
	public static String timeStamp() {
		long time = System.currentTimeMillis();
		String t = String.valueOf(time / 1000);
		return t;
	}

	/**
	 * 取得当前月份
	 */
	public static Integer currentMon() {
		return Integer.parseInt(new SimpleDateFormat("MM").format(new Date()));
	}

	/**
	 * 取得当前年份
	 */
	public static Integer currentYear() {
		return Integer
				.parseInt(new SimpleDateFormat("YYYY").format(new Date()));
	}

	/**
	 * 取得当前年月日
	 */
	public static Integer currentDate() {
		return Integer.parseInt(new SimpleDateFormat("YYYYMMdd")
				.format(new Date()));
	}

	/**
	 * 取得前十二个月份的数组
	 */
	public static List<String> current() {
		List<String> list = new ArrayList<>();
		if (currentMon() - 12 == 0) {
			for (int i = 1; i <= 12; i++) {
				if (i < 10) {
					list.add(currentYear() + "-0" + i);
				} else {
					list.add(currentYear() + "-" + i);
				}
			}
		} else {
			for (int i = currentMon() + 1; i <= 12; i++) {
				if (i < 10) {
					list.add(currentYear() - 1 + "-0" + i);
				} else {
					list.add(currentYear() - 1 + "-" + i);
				}
			}
			for (int i = 1; i <= currentMon(); i++) {
				if (i < 10) {
					list.add(currentYear() + "-0" + i);
				} else {
					list.add(currentYear() + "-" + i);
				}
			}
		}
		return list;

	}

	// 输出结果：
	// timeStamp=1417792627
	// date=2014-12-05 23:17:07
	// 1417792627
	public static String TimeStamp2Date(String timestampString, String formats) {
		Long timestamp = Long.parseLong(timestampString) * 1000;
		String date = new java.text.SimpleDateFormat(formats)
				.format(new java.util.Date(timestamp));
		return date;
	}
}
