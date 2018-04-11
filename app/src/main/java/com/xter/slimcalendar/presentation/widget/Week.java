package com.xter.slimcalendar.presentation.widget;

/**
 * @author XTER
 * @date 2018/3/26.
 */

public enum Week {
	SUNDAY("日"),
	MONDAY("一"),
	TUESDAY("二"),
	WEDNESDAY("三"),
	THURSDAY("四"),
	FRIDAY("五"),
	SATURDAY("六");

	public String tag;

	Week(String tag) {
		this.tag = tag;
	}
}