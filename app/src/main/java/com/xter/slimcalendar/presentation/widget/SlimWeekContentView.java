package com.xter.slimcalendar.presentation.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.xter.slimcalendar.R;
import com.xter.slimcalendar.presentation.util.SolarCalendar;
import com.xter.support.util.L;

import java.util.Arrays;

/**
 * @author XTER
 * @date 2018/3/28.
 */

public class SlimWeekContentView extends View implements Runnable {
	public static final int DEFAULT_TEXT_SIZE = 18;
	public static final int DEFAULT_TEXT_COLOR = Color.parseColor("#000000");
	public static final int DEFAULT_FOCUSED_TEXT_COLOR = Color.parseColor("#FFFFFF");
	public static final int DEFAULT_COLUMNS = 7;

	private Paint mPaint;
	private int textOrder;
	private float textSize;
	private int textColor;
	private int specialTextColor;
	private int focusedTextColor;

	private int totalWidth;
	private int space;
	private float startX;

	private int offset;
	private int rows;
	private int focusedIndex;
	private int totalDays;

	private int year;
	private int month;
	private int[] days;
	private int[] specialDays;
	private Week[] weeks;

	private int currentYear;
	private int currentMonth;
	private int currentDay;

	//	private static final int[] ANIM_ARRAY = {4, 16, 36, 64, 100};
	private static final int[] ANIM_ARRAY = {0, 0, 0, 0, 1, 4, 9, 16, 25, 36, 49, 64, 81, 100};
	private boolean animOrder;
	private int animIndex;
	private float animRadius;
	private float maxRadius;

	public SlimWeekContentView(Context context) {
		this(context, null);
	}

	public SlimWeekContentView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		initAttrs(context, attrs);
	}

	public SlimWeekContentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		this(context, attrs);
	}

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	public SlimWeekContentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		this(context, attrs);
	}

	private void initAttrs(Context context, AttributeSet attrs) {
		if (attrs != null) {
			final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SlimWeekContentView);
			final int count = a.getIndexCount();
			for (int i = 0; i < count; i++) {
				int attr = a.getIndex(i);
				switch (attr) {
					case R.styleable.SlimWeekContentView_content_text_size:
						textSize = a.getDimension(attr, DEFAULT_TEXT_SIZE);
						break;
					case R.styleable.SlimWeekContentView_content_text_color:
						textColor = a.getColor(attr, DEFAULT_TEXT_COLOR);
						break;
					case R.styleable.SlimWeekContentView_content_special_text_color:
						specialTextColor = a.getColor(attr, DEFAULT_TEXT_COLOR);
						break;
					case R.styleable.SlimWeekContentView_content_text_order:
						textOrder = a.getInt(attr, 1);
						break;
					case R.styleable.SlimWeekContentView_content_focused_text_color:
						focusedTextColor = a.getInt(attr, DEFAULT_FOCUSED_TEXT_COLOR);
						break;
					default:
						break;
				}
			}
			a.recycle();
		}

		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setTextAlign(Paint.Align.CENTER);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setTextSize(textSize);
		mPaint.setColor(textColor);

		weeks = Week.values();

		confirmToday();
	}

	public void setSpecialTextColor(int specialTextColor) {
		this.specialTextColor = specialTextColor;
	}

	public void setFocusedTextColor(int focusedTextColor) {
		this.focusedTextColor = focusedTextColor;
	}

	private void setSpecialDays() {

	}

	public void setDate(int wishYear, int wishMonth) {
		year = wishYear;
		month = wishMonth;
		prepare();
	}

	private void confirmToday() {
		String today = SolarCalendar.today();
		String[] ymd = today.split("/");
		currentYear = Integer.parseInt(ymd[0]);
		currentMonth = Integer.parseInt(ymd[1]);
		currentDay = Integer.parseInt(ymd[2]);
	}

	private void prepare() {
		Week firstDay = SolarCalendar.dayForTag(year, month, 1);
		totalDays = SolarCalendar.daysInMonth(year, month);
		offset = Arrays.binarySearch(weeks, firstDay);
		focusedIndex = currentDay - 1;
		days = new int[totalDays];
		for (int i = 0; i < totalDays; i++) {
			days[i] = i + 1;
		}
		rows = (totalDays + offset) % DEFAULT_COLUMNS == 0 ? (totalDays + offset) / DEFAULT_COLUMNS : (totalDays + offset) / DEFAULT_COLUMNS + 1;

		invalidate();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		totalWidth = MeasureSpec.getSize(widthMeasureSpec);
		space = totalWidth / DEFAULT_COLUMNS;
		maxRadius = space / 2 - 5;
		setMeasuredDimension(totalWidth, space * rows);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		int index = 0;
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < DEFAULT_COLUMNS; column++) {
				//用星期表对号入座
				if (row < 1 && column < offset) {
					continue;
				}
				//不超过天数
				if (index > days.length - 1) {
					break;
				}
				mPaint.setColor(textColor);
				canvas.drawText(days[index] + "", column * space + space / 2, textSize + textSize / 2 + space * row, mPaint);
				//当天特殊处理
				if (index < days.length && index != focusedIndex && year == currentYear && month == currentMonth && days[index] == currentDay) {
					mPaint.setStyle(Paint.Style.STROKE);
					mPaint.setColor(specialTextColor);
					canvas.drawCircle(column * space + space / 2, space / 2 + space * row, space / 2 - 5, mPaint);
					canvas.drawText(days[index] + "", column * space + space / 2, textSize + textSize / 2 + space * row, mPaint);
				}
				//有焦点特殊处理
				if (index == focusedIndex) {
					mPaint.setColor(specialTextColor);
					mPaint.setStyle(Paint.Style.FILL);
					canvas.drawCircle(column * space + space / 2, space / 2 + space * row, animRadius, mPaint);
					mPaint.setColor(focusedTextColor);
					canvas.drawText(days[index] + "", column * space + space / 2, textSize + textSize / 2 + space * row, mPaint);
				}
				index++;
			}
		}
		postDelayed(this, 150);
	}

	private void changeFocusIndex(float posX, float posY) {
		int rowX = (int) (posY / space);
		int columnY = (int) (posX / space + 1);
		int tempIndex = rowX * DEFAULT_COLUMNS + columnY - offset - 1;
		if (tempIndex < totalDays)
			focusedIndex = tempIndex;
		L.d("in:" + tempIndex);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				break;
			case MotionEvent.ACTION_MOVE:
				break;
			case MotionEvent.ACTION_UP:
				changeFocusIndex(event.getX(), event.getY());
				break;
		}
		return true;
	}

	@Override
	public void run() {
		if (animIndex > ANIM_ARRAY.length - 1) {
			animIndex = ANIM_ARRAY.length - 1;
			animOrder = true;
		}
		if (animIndex < 0) {
			animIndex = 0;
			animOrder = false;
		}
		animRadius = maxRadius / 4 * 3 + maxRadius / 4 * ANIM_ARRAY[animOrder ? animIndex-- : animIndex++] / 100;
		invalidate();
	}

}
