package com.xter.slimcalendar.data.source;

import com.xter.slimcalendar.data.entity.BingWallpaper;
import com.xter.slimcalendar.data.source.cloud.ICloudRepo;
import com.xter.slimcalendar.data.source.local.ILocalRepo;
import com.xter.support.util.Preconditions;

import io.reactivex.Observable;

/**
 * Created by XTER on 2018/4/9.
 * 数据源
 */
public class SlimRepos implements ISlimRepo {

	private static SlimRepos INSTANCE;

	private ILocalRepo local;
	private ICloudRepo cloud;

	private SlimRepos(ILocalRepo local, ICloudRepo remote) {
		this.local = Preconditions.checkNotNull(local, "local source is null");
		this.cloud = Preconditions.checkNotNull(remote, "remote source is null");
	}

	public static SlimRepos getInstance(ILocalRepo local, ICloudRepo remote) {
		if (INSTANCE == null) {
			INSTANCE = new SlimRepos(local, remote);
		}
		return INSTANCE;
	}


	@Override
	public Observable<BingWallpaper> getBingWallpaper() {
		return cloud.getBingWallpaper();
	}
}
