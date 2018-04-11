package com.xter.slimcalendar.presentation.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author XTER
 * @date 2018/3/22.
 */
public class SlimCalendarMonthView extends ViewGroup {
	public SlimCalendarMonthView(Context context) {
		this(context, null);
	}

	public SlimCalendarMonthView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SlimCalendarMonthView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initAttrs(context, attrs);
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public SlimCalendarMonthView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	private void initAttrs(Context context, AttributeSet attrs) {

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		final int childCount = getChildCount();
		for (int i = 0; i < childCount; i++) {
			View childView = getChildAt(i);
			childView.layout(l + i * 50, t+ i * 50, r, b);
		}
	}
}
