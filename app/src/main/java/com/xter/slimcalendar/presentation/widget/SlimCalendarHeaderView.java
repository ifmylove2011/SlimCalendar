package com.xter.slimcalendar.presentation.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

import static android.view.View.MeasureSpec.EXACTLY;
import static android.view.View.MeasureSpec.makeMeasureSpec;

/**
 * @author XTER
 * @date 2018/3/22.
 */

public class SlimCalendarHeaderView extends FrameLayout {

	private static final String[] WEEK_NUM = {"日", "一", "二", "三", "四", "五", "六",};

	public SlimCalendarHeaderView(Context context) {
		this(context, null);
	}

	public SlimCalendarHeaderView(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SlimCalendarHeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context, attrs);
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public SlimCalendarHeaderView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		this(context, attrs, defStyleAttr);
	}

	private void init(Context context, AttributeSet attrs) {
		for (String w : WEEK_NUM) {
			TextView tv = new TextView(context);
			tv.setGravity(Gravity.CENTER);
			tv.setText(w);
			addView(tv);
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		final int totalWidth = MeasureSpec.getSize(widthMeasureSpec);
		int count = getChildCount();
		int width = makeMeasureSpec(totalWidth / 7, EXACTLY);
		for (int i = 0; i < count; i++) {
			getChildAt(i).measure(width, 100);
		}
		setMeasuredDimension(widthMeasureSpec, 100);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		int count = getChildCount();
		int space = (right - left) / 7;
		for (int i = 0; i < count; i++) {
			getChildAt(i).layout(left + space * i, top, left + space * (i + 1), bottom);
		}
	}

}
