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
import com.xter.support.util.L;

/**
 * @author XTER
 * @date 2018/3/23.
 */
public class SlimWeekHeaderView extends View {

	public static final int DEFAULT_TEXT_SIZE = 16;
	public static final int DEFAULT_TEXT_COLOR = Color.parseColor("#000000");
	public static final int DEFAULT_COLUMNS = 7;


	private Paint mPaint;

	private int textOrder;
	private float textSize;
	private int textColor;
	private int weekendTextColor;

	private int totalWidth;
	private int space;
	private float startX;
	private int today;
	private Week[] weeks;

	public SlimWeekHeaderView(Context context) {
		this(context, null);
	}

	public SlimWeekHeaderView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		initAttrs(context, attrs);
	}

	public SlimWeekHeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		this(context, attrs);
	}

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	public SlimWeekHeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		this(context, attrs, defStyleAttr);
	}

	private void initAttrs(Context context, AttributeSet attrs) {
		if (attrs != null) {
			final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SlimWeekHeaderView);
			final int count = a.getIndexCount();
			for (int i = 0; i < count; i++) {
				int attr = a.getIndex(i);
				switch (attr) {
					case R.styleable.SlimWeekHeaderView_header_text_size:
						textSize = a.getDimension(attr, DEFAULT_TEXT_SIZE);
						break;
					case R.styleable.SlimWeekHeaderView_header_text_color:
						textColor = a.getColor(attr, DEFAULT_TEXT_COLOR);
						break;
					case R.styleable.SlimWeekHeaderView_header_weekend_text_color:
						weekendTextColor = a.getColor(attr, DEFAULT_TEXT_COLOR);
						break;
					case R.styleable.SlimWeekHeaderView_header_text_order:
						textOrder = a.getInt(attr, 1);
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
		mPaint.setTextSize(textSize);
		mPaint.setColor(textColor);


		weeks = Week.values();
	}



	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		totalWidth = MeasureSpec.getSize(widthMeasureSpec);
		space = totalWidth / DEFAULT_COLUMNS;
		int h = (int) (textSize + getPaddingTop() + getPaddingBottom());
		setMeasuredDimension(totalWidth, h * 2);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		for (int i = 0; i < DEFAULT_COLUMNS; i++) {
			//周六|周日可能会有所不同
			if (weekendTextColor != DEFAULT_TEXT_COLOR) {
				if (weeks[i] == Week.SUNDAY || weeks[i] == Week.SATURDAY) {
					mPaint.setColor(weekendTextColor);
				} else {
					mPaint.setColor(textColor);
				}
			}
			canvas.drawText(weeks[i].tag, i * space + space / 2, textSize + textSize / 2, mPaint);
		}
	}

	public void setTextSize(float textSize) {
		this.textSize = textSize;
		invalidate();
	}

	public void setTextColor(int textColor) {
		this.textColor = textColor;
		invalidate();
	}

	public void setWeekendTextColor(int weekendTextColor) {
		this.weekendTextColor = weekendTextColor;
		invalidate();
	}

	public void setTextOrder(int textOrder) {
		this.textOrder = textOrder;
		invalidate();
	}

	private void changeOrder(Week[] weekArray, boolean order) {
		int length = weekArray.length;
		if (order) {
			Week first = weekArray[0];
			System.arraycopy(weekArray, 1, weekArray, 0, length - 1);
			weekArray[length - 1] = first;
		} else {
			Week last = weekArray[length - 1];
			System.arraycopy(weekArray, 0, weekArray, 1, length - 1);
			weekArray[0] = last;
		}
	}

//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//		switch (event.getAction()) {
//			case MotionEvent.ACTION_DOWN:
//				startX = event.getRawX();
//				break;
//			case MotionEvent.ACTION_MOVE:
//				if (textOrder == 3) {
//					float currentX = event.getRawX();
//					if (Math.abs(currentX - startX) >= space / 2) {
//						changeOrder(weeks, currentX - startX < 0);
//						startX = currentX;
//						invalidate();
//					}
//				}
//				break;
//			case MotionEvent.ACTION_UP:
//				if (textOrder != 3) {
//					if (textOrder == 1) {
//						textOrder = 2;
//						changeOrder(weeks, false);
//					} else {
//						textOrder = 1;
//						changeOrder(weeks, true);
//					}
//					invalidate();
//				}
//				break;
//			default:
//				break;
//		}
//		return true;
//	}

}
