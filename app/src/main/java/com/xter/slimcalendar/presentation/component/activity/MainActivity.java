package com.xter.slimcalendar.presentation.component.activity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.github.florent37.glidepalette.BitmapPalette;
import com.github.florent37.glidepalette.GlidePalette;
import com.jaeger.library.StatusBarUtil;
import com.xter.slimcalendar.R;
import com.xter.slimcalendar.data.constant.LC;
import com.xter.slimcalendar.data.entity.BingWallpaper;
import com.xter.slimcalendar.domain.usecase.GetWallpaperUseCase;
import com.xter.slimcalendar.presentation.component.app.SlimApp;
import com.xter.slimcalendar.presentation.util.BingImageSignatureKey;
import com.xter.slimcalendar.presentation.util.ColorUtil;
import com.xter.slimcalendar.presentation.util.GlideApp;
import com.xter.slimcalendar.presentation.util.InjectionUtil;
import com.xter.slimcalendar.presentation.util.SolarCalendar;
import com.xter.slimcalendar.presentation.widget.SlimWeekContentView;
import com.xter.slimcalendar.presentation.widget.SlimWeekHeaderView;
import com.xter.support.component.BaseActivity;
import com.xter.support.db.SPM;
import com.xter.support.gen.BasePresenter;
import com.xter.support.util.L;

import butterknife.BindView;
import io.reactivex.observers.DisposableObserver;

/**
 * @author XTER
 * @date 2018/3/22
 */
public class MainActivity extends BaseActivity {

	@BindView(R.id.iv_bing_wallpaper)
	ImageView ivWallpaper;

	@BindView(R.id.tv_month)
	TextView tvMonth;

	@BindView(R.id.tv_year)
	TextView tvYear;

	@BindView(R.id.toolbar)
	Toolbar toolbar;

	@BindView(R.id.slim_week_header)
	SlimWeekHeaderView headerView;

	@BindView(R.id.slim_week_content)
	SlimWeekContentView contentView;

	GetWallpaperUseCase getWallpaperUseCase;


	@Override
	protected void initView() {
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void initData() {
		setToolbar();
		confirmDate();
		loadImage();
	}

	private void setToolbar() {
		setSupportActionBar(toolbar);
	}

	private void confirmDate() {
		String month = SolarCalendar.month();
		String year = SolarCalendar.year();

		tvMonth.setText(month);
		tvYear.setText(year);

		contentView.setDate(Integer.parseInt(year), Integer.parseInt(month));
	}

	private void loadImage() {
		//图片缓存相关
		final String diskCacheDate = SPM.getStr(SlimApp.getContext(), SPM.CONSTANT, LC.BING_DATE, "");
		final String diskCacheUrl = SPM.getStr(SlimApp.getContext(), SPM.CONSTANT, LC.DEFAULT_BING_URL, "");

		//采集图片中的色彩
		final RequestListener<Drawable> requestListener = GlidePalette.with(diskCacheUrl).intoCallBack(new BitmapPalette.CallBack() {
			@Override
			public void onPaletteLoaded(@Nullable Palette palette) {
				int color1 = palette.getLightVibrantSwatch().getRgb();
				int color2 = palette.getVibrantSwatch().getRgb();
				int color3 = palette.getMutedSwatch().getRgb();
//				toolbar.setBackgroundColor(palette.getVibrantSwatch().getRgb());
//				toolbar.setBackgroundColor(Color.parseColor("#00000000"));
				headerView.setWeekendTextColor(color3);
				contentView.setSpecialTextColor(color2);
				L.d("color" + ColorUtil.reserve(color2));
//				tvMonth.setTextColor(Color.parseColor(ColorUtil.reserve(color2)));
				StatusBarUtil.setColor(MainActivity.this, color1, 0);
//				StatusBarUtil.setTranslucentForImageView(MainActivity.this,ivWallpaper);
			}
		});

		//需要更新或加载与否
		if (TextUtils.isEmpty(diskCacheDate) || !diskCacheDate.equals(SolarCalendar.today1())) {
			getWallpaperUseCase = InjectionUtil.provideGetWallpaperUseCase();
			getWallpaperUseCase.execute(new DisposableObserver<BingWallpaper>() {
				@Override
				public void onNext(BingWallpaper bingWallpaper) {
					L.d(bingWallpaper.toString());
					GlideApp.with(SlimApp.getContext()).load(bingWallpaper.img_1366).signature(new BingImageSignatureKey(bingWallpaper.date)).listener(requestListener).centerCrop().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(ivWallpaper);
					SPM.saveStr(SlimApp.getContext(), SPM.CONSTANT, LC.DEFAULT_BING_URL, bingWallpaper.img_1366);
					SPM.saveStr(SlimApp.getContext(), SPM.CONSTANT, LC.BING_DATE, bingWallpaper.date);
				}

				@Override
				public void onError(Throwable e) {
					L.e(e.getMessage());
					GlideApp.with(SlimApp.getContext()).load(diskCacheUrl).signature(new BingImageSignatureKey(diskCacheDate)).listener(requestListener).centerCrop().into(ivWallpaper);
				}

				@Override
				public void onComplete() {
					L.d("完成");
				}
			}, "");
		} else {
			GlideApp.with(SlimApp.getContext()).load(diskCacheUrl).signature(new BingImageSignatureKey(diskCacheDate)).listener(requestListener).centerCrop().into(ivWallpaper);
		}

	}

	private void getColorInImage() {
	}

	@Override
	protected BasePresenter genPresenter() {
		return null;
	}

}
