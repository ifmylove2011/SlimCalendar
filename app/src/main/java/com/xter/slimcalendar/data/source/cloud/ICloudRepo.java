package com.xter.slimcalendar.data.source.cloud;

import com.xter.slimcalendar.data.entity.BingWallpaper;

import io.reactivex.Observable;

/**
 * Created by XTER on 2018/4/9.
 * 云端
 */
public interface ICloudRepo {
	Observable<BingWallpaper> getBingWallpaper();
}
