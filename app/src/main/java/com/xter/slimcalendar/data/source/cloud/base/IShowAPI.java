package com.xter.slimcalendar.data.source.cloud.base;

import com.xter.slimcalendar.data.entity.BingWallpaper;
import com.xter.slimcalendar.data.entity.ShowReponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by XTER on 2018/4/9.
 * 必应专用
 */
public interface IShowAPI {
	@FormUrlEncoded
	@POST("1287-1")
	Observable<ShowReponse<BingWallpaper>> getBingWallpaper(@FieldMap Map<String,Object> params);
}
