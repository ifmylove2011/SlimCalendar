package com.xter.slimcalendar.data.source.cloud.request;

import com.xter.slimcalendar.data.constant.NC;
import com.xter.slimcalendar.data.source.cloud.base.IShowRequest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by XTER on 2018/4/9.
 * ShowAPI请求头
 */
public class ShowBingWallpaperRequest implements IShowRequest {

	/**
	 * 得到当前时间
	 *
	 * @return time
	 */
	protected String getTime() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
		return sdf.format(d);
	}

	@Override
	public Map<String, Object> getParams() {
		Map<String, Object> params = new HashMap<>();
		params.put("showapi_appid", NC.APP_ID_SHOWAPI);
		params.put("showapi_sign", NC.APP_KEY_SHOWAPI);
//		params.put("showapi_timestamp", getTime());
//		params.put("showapi_sign_method", "md5");
//		params.put("showapi_res_gzip", "1");
		return params;
	}
}
