package com.xter.slimcalendar.data.source.local;

import android.content.Context;

import com.litesuits.orm.LiteOrm;
import com.xter.slimcalendar.data.constant.LC;

/**
 * Created by XTER on 2018/4/9.
 * 本地
 */

public class LocalRepo implements ILocalRepo {
	private LiteOrm liteOrm;

	private LocalRepo(Context context) {
		liteOrm = LiteOrm.newCascadeInstance(context, LC.DB_DEFAULT_NAME);
	}

	private static LocalRepo INSTANCE;

	public static LocalRepo getInstance(Context context) {
		if (INSTANCE == null)
			INSTANCE = new LocalRepo(context);
		return INSTANCE;
	}
}
