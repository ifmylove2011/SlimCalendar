package com.xter.slimcalendar.data.entity;

import com.google.gson.annotations.SerializedName;
import com.xter.support.entity.BaseModel;

/**
 * Created by XTER on 2018/4/9.
 * ShowAPI专用头
 */
public class ShowReponse<T> extends BaseModel {

	@SerializedName("showapi_res_error")
	public String resError;

	@SerializedName("showapi_res_code")
	public int resCode;

	@SerializedName("showapi_res_body")
	public ShowResponseBody<T> resBody;

	public class ShowResponseBody<K> extends BaseModel {
		@SerializedName("ret_code")
		public int code;

		@SerializedName("ret_message")
		public String message;

		@SerializedName("data")
		public K data;
	}
}
