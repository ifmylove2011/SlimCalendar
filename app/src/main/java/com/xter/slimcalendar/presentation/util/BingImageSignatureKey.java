package com.xter.slimcalendar.presentation.util;

import android.support.annotation.NonNull;

import com.bumptech.glide.load.Key;

import java.security.MessageDigest;

/**
 * Created by XTER on 2018/4/10.
 * 区别glide缓存，利用当天时间区别
 */

public class BingImageSignatureKey implements Key {

	private String date;

	public BingImageSignatureKey(String date) {
		this.date = date;
	}

	@Override
	public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
		messageDigest.update(date.getBytes());
	}
}
