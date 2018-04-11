package com.xter.slimcalendar.data.exception;

/**
 * Created by XTER on 2018/4/9.
 * showapi一级错误，指签名、key等错误
 */

public class ShowAPIResException extends Exception {
	public ShowAPIResException() {
	}

	public ShowAPIResException(String message) {
		super(message);
	}
}
