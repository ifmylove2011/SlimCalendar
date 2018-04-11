package com.xter.slimcalendar.presentation.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by XTER on 2018/4/2.
 * 方便判断
 */

public class SlimWeekBanner extends RelativeLayout {
	public SlimWeekBanner(Context context) {
		super(context);
	}

	public SlimWeekBanner(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SlimWeekBanner(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	public SlimWeekBanner(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}
}
