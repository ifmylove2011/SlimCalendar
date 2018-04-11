package com.xter.slimcalendar.presentation.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.xter.support.util.L;

/**
 * Created by XTER on 2018/4/2.
 * 日历容器
 */
public class SlimCalendarContainer extends ViewGroup {
	public SlimCalendarContainer(Context context) {
		this(context, null);
	}

	public SlimCalendarContainer(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SlimCalendarContainer(Context context, AttributeSet attrs, int defStyleAttr) {
		this(context, attrs);
		initAttrs(context, attrs);
	}

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	public SlimCalendarContainer(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		this(context, attrs);
	}

	private void initAttrs(Context context, AttributeSet attrs) {

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int w = MeasureSpec.getSize(widthMeasureSpec);
		int h = MeasureSpec.getSize(heightMeasureSpec);
		L.d(w + "," + h);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int count = getChildCount();
		for (int i = 0; i < count; i++) {
			View view = getChildAt(i);
			L.d("child" + i + "," + view.getClass().getSimpleName());
			if (view instanceof SlimWeekContentView) {
				L.d("content height"+view.getMeasuredHeight());
				view.layout(l, b - view.getHeight(), r, b);
			}
		}
	}
}
