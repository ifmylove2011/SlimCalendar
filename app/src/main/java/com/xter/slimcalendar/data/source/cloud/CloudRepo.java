package com.xter.slimcalendar.data.source.cloud;

import android.annotation.SuppressLint;
import android.content.Context;

import com.xter.slimcalendar.data.entity.BingWallpaper;
import com.xter.slimcalendar.data.entity.ShowReponse;
import com.xter.slimcalendar.data.exception.ShowAPIResException;
import com.xter.slimcalendar.data.exception.ShowAPIRetException;
import com.xter.slimcalendar.data.source.cloud.base.ShowAPI;
import com.xter.slimcalendar.data.source.cloud.request.ShowBingWallpaperRequest;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by XTER on 2018/4/9.
 * 云端
 */

public class CloudRepo implements ICloudRepo {
	private Context context;

	@SuppressLint("StaticFieldLeak")
	private static CloudRepo INSTANCE;

	public static CloudRepo getInstance(Context context) {
		if (INSTANCE == null)
			INSTANCE = new CloudRepo(context);
		return INSTANCE;
	}

	private CloudRepo(Context context){
		this.context = context;
	}

	@Override
	public Observable<BingWallpaper> getBingWallpaper() {
		return Observable.create(new ObservableOnSubscribe<BingWallpaper>() {
			@Override
			public void subscribe(final ObservableEmitter<BingWallpaper> e) throws Exception {
				ShowAPI.build().geJiSuAPI().getBingWallpaper(new ShowBingWallpaperRequest().getParams()).subscribe(new Observer<ShowReponse<BingWallpaper>>() {

					Disposable disposable;

					@Override
					public void onSubscribe(Disposable d) {
						disposable = d;
					}

					@Override
					public void onNext(ShowReponse<BingWallpaper> bingWallpaperShowReponse) {
						if(bingWallpaperShowReponse.resCode==0){
							if(bingWallpaperShowReponse.resBody.code==0){
								e.onNext(bingWallpaperShowReponse.resBody.data);
								onComplete();
							}else{
								onError(new ShowAPIRetException(bingWallpaperShowReponse.resBody.message));
							}
						}else{
							onError(new ShowAPIResException(bingWallpaperShowReponse.resError));
						}
					}

					@Override
					public void onError(Throwable ex) {
						e.onError(ex);
					}

					@Override
					public void onComplete() {
						e.onComplete();
					}
				});
			}
		});
	}
}
