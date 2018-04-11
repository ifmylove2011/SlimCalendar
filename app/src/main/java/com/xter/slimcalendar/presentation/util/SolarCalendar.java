package com.xter.slimcalendar.presentation.util;

import com.xter.slimcalendar.presentation.widget.Week;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * @author XTER
 * @date 2018/3/23.
 * 阳历
 */

public class SolarCalendar {

	/**
	 * 某年某月一共有几天
	 *
	 * @param year  年份
	 * @param month 月份
	 * @return days
	 */
	public static int daysInMonth(int year, int month) {
		if (month < 1 || month > 12) {
			return 0;
		}
		int days = 0;
		switch (month) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				days = 31;
				break;
			case 2:
				days = isSpecialYear(year) ? 29 : 28;
				break;
			default:
				days = 30;
				break;
		}
		return days;
	}

	/**
	 * 是否为闰年
	 *
	 * @param year 年份
	 * @return 是否
	 */
	public static boolean isSpecialYear(int year) {
		if (year % 100 == 0) {
			return year % 4 == 0;
		} else {
			return year % 4 == 0;
		}
	}

	/**
	 * 一年的总天数
	 *
	 * @param year 年份
	 * @return days
	 */
	public static int daysInYear(int year) {
		return isSpecialYear(year) ? 366 : 365;
	}

	/**
	 * 这一年的第几天
	 *
	 * @param year  年份
	 * @param month 月份
	 * @param day   日子
	 * @return
	 */
	public static int daysInYear(int year, int month, int day) {
		int days = 0;
		for (int i = 0; i < month; i++) {
			days += daysInMonth(year, i);
		}
		days += day;
		return days;
	}

	/**
	 * 1601年1月1日是星期一，1701年1月1日是星期六，
	 * 1801年1月1日是星期四，1901年1月1日是星期二，
	 * 2001年1月1日是星期一
	 * 先得出本年1月1日是星期几，再根据此日在年中的天数算出星期几
	 *
	 * @param year  年份
	 * @param month 月份
	 * @param day   日子
	 * @return 星期几
	 */
	public static Week dayForTag(int year, int month, int day) {
		int term = (year - 1) % 400 / 100;
		int startIndex = 1;
		switch (term) {
			case 0:
				startIndex = 1;
				break;
			case 1:
				startIndex = 6;
				break;
			case 2:
				startIndex = 4;
				break;
			case 3:
				startIndex = 2;
				break;
			default:
				break;
		}
		int days = 0;
		int years = year % 100;
		for (int i = 1; i < years; i++) {
			days += daysInYear(i);
		}
		days += daysInYear(year, month, day);
		int offset = (days - 1) % 7;
		int finalOffset = (startIndex + offset) % 7;
		return Week.values()[finalOffset];
	}

	public static String today(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.CHINA);
		return sdf.format(System.currentTimeMillis());
	}

	public static String today1(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);
		return sdf.format(System.currentTimeMillis());
	}

	public static String year(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy", Locale.CHINA);
		return sdf.format(System.currentTimeMillis());
	}

	public static String month(){
		SimpleDateFormat sdf = new SimpleDateFormat("MM", Locale.CHINA);
		String month =  sdf.format(System.currentTimeMillis());
		if(Integer.parseInt(month)<10){
			month = month.substring(1);
		}
		return month;
	}
}
