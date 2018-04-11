package com.xter.slimcalendar.presentation.component.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.florent37.glidepalette.BitmapPalette;
import com.github.florent37.glidepalette.GlidePalette;
import com.xter.slimcalendar.R;
import com.xter.slimcalendar.data.constant.LC;
import com.xter.slimcalendar.presentation.component.app.SlimApp;
import com.xter.slimcalendar.presentation.util.BingImageSignatureKey;
import com.xter.slimcalendar.presentation.util.GlideApp;
import com.xter.support.component.BaseActivity;
import com.xter.support.db.SPM;
import com.xter.support.gen.BasePresenter;
import com.xter.support.util.L;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by XTER on 2018/4/8.
 * 测试页面
 */
public class DemoActivity extends BaseActivity {

	@BindView(R.id.iv_demo)
	ImageView ivDemo;

	@BindView(R.id.btn_demo)
	Button btnDemo;

	@BindView(R.id.tv_demo_1)
	TextView tvDemo1;
	@BindView(R.id.tv_demo_2)
	TextView tvDemo2;
	@BindView(R.id.tv_demo_3)
	TextView tvDemo3;
	@BindView(R.id.tv_demo_4)
	TextView tvDemo4;
	@BindView(R.id.tv_demo_5)
	TextView tvDemo5;
	@BindView(R.id.tv_demo_6)
	TextView tvDemo6;
	@BindView(R.id.tv_demo_7)
	TextView tvDemo7;


	@Override
	protected void initView() {
		setContentView(R.layout.activity_demo);
	}

	@Override
	protected void initData() {
		loadImage();
	}

	private void loadImage() {
		final String diskCacheDate = SPM.getStr(SlimApp.getContext(), SPM.CONSTANT, LC.BING_DATE, "");
		String diskCacheUrl = SPM.getStr(SlimApp.getContext(), SPM.CONSTANT, LC.DEFAULT_BING_URL, "");
		GlideApp.with(SlimApp.getContext()).load(diskCacheUrl).signature(new BingImageSignatureKey(diskCacheDate)).listener(GlidePalette.with(diskCacheUrl).intoCallBack(new BitmapPalette.CallBack() {
			@Override
			public void onPaletteLoaded(@Nullable Palette palette) {
				List<Palette.Swatch> list = palette.getSwatches();
				for (Palette.Swatch s : list) {
					L.i(s.toString());
				}
				tvDemo1.setBackgroundColor(palette.getDominantSwatch().getRgb());
				tvDemo2.setBackgroundColor(palette.getMutedSwatch().getRgb());
				tvDemo3.setBackgroundColor(palette.getDarkMutedSwatch().getRgb());
				tvDemo5.setBackgroundColor(palette.getVibrantSwatch().getRgb());
				tvDemo6.setBackgroundColor(palette.getDarkVibrantSwatch().getRgb());
				tvDemo7.setBackgroundColor(palette.getLightVibrantSwatch().getRgb());
					L.i("Dominant"+palette.getDominantSwatch().toString());
					L.i("Muted"+palette.getMutedSwatch().toString());
					L.i("DarkMuted"+palette.getDarkMutedSwatch().toString());
//					L.i("LightMuted"+palette.getLightMutedSwatch().toString());
					L.i("Vibrant"+palette.getVibrantSwatch().toString());
					L.i("DarkVibrant"+palette.getDarkVibrantSwatch().toString());
					L.i("LightVibrant"+palette.getLightVibrantSwatch().toString());
			}
		}).
				use(GlidePalette.Profile.MUTED).intoBackground(tvDemo2).
				use(GlidePalette.Profile.MUTED_DARK).intoBackground(tvDemo3).
				use(GlidePalette.Profile.MUTED_LIGHT).intoBackground(tvDemo4).
				use(GlidePalette.Profile.VIBRANT).intoBackground(tvDemo5).
				use(GlidePalette.Profile.VIBRANT_DARK).intoBackground(tvDemo6).
				use(GlidePalette.Profile.VIBRANT_LIGHT).intoBackground(tvDemo7))
				.centerCrop().into(ivDemo);
	}

	@Override
	protected BasePresenter genPresenter() {
		return null;
	}

	@OnClick({R.id.btn_demo})
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_demo:
				break;
		}
	}

}
