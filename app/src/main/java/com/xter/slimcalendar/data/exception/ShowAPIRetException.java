package com.xter.slimcalendar.data.exception;

/**
 * Created by XTER on 2018/4/9.
 * showapi二级错误，指数据、内容等错误
 */

public class ShowAPIRetException extends Exception {
	public ShowAPIRetException() {
	}

	public ShowAPIRetException(String message) {
		super(message);
	}
}
