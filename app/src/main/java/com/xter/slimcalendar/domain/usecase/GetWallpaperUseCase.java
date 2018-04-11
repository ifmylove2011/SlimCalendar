package com.xter.slimcalendar.domain.usecase;

import com.xter.slimcalendar.data.entity.BingWallpaper;
import com.xter.slimcalendar.data.source.SlimRepos;
import com.xter.support.interactor.UseCase;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Created by XTER on 2018/4/9.
 * 业务--获取必应壁纸
 */
public class GetWallpaperUseCase extends UseCase<BingWallpaper,String>{

	private final SlimRepos slimRepos;

	public GetWallpaperUseCase(Scheduler observerThread, Scheduler subcriberThread, SlimRepos slimRepos) {
		super(observerThread, subcriberThread);
		this.slimRepos = slimRepos;
	}

	@Override
	protected Observable<BingWallpaper> buildUseCaseObservable(String request) {
		return slimRepos.getBingWallpaper();
	}
}
