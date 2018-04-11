package com.xter.slimcalendar.presentation.util;

import com.xter.slimcalendar.data.source.SlimRepos;
import com.xter.slimcalendar.data.source.cloud.CloudRepo;
import com.xter.slimcalendar.data.source.local.LocalRepo;
import com.xter.slimcalendar.domain.usecase.GetWallpaperUseCase;
import com.xter.slimcalendar.presentation.component.app.SlimApp;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by XTER on 2018/4/9.
 * 生成各种业务实例
 */

public class InjectionUtil {
	public static SlimRepos provideSlimRepos() {
		return SlimRepos.getInstance(LocalRepo.getInstance(SlimApp.getContext()),
				CloudRepo.getInstance(SlimApp.getContext()));
	}

	public static GetWallpaperUseCase provideGetWallpaperUseCase(){
		return new GetWallpaperUseCase(Schedulers.io(), AndroidSchedulers.mainThread(),provideSlimRepos());
	}
}
