package com.xter.slimcalendar.data.source;

import com.xter.slimcalendar.data.entity.BingWallpaper;

import io.reactivex.Observable;

/**
 * Created by XTER on 2018/4/9.
 * 数据源
 */
public interface ISlimRepo {
	Observable<BingWallpaper> getBingWallpaper();
}
