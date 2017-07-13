package com.example.sparken02.collage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;


/**
 * Card View is a Collage item
 *
 * @author Juan Carlos Moreno (jcmore2@gmail.com)
 *
 */
@SuppressLint("ResourceAsColor")
public class CardView extends android.support.v7.widget.AppCompatImageView {

	private static final int PADDING = 8;
	private static final float STROKE_WIDTH = 8.0f;
	private Context mContext;
	private Paint mBorderPaint;
	private MultiTouchListener mtl;

	public CardView(Context context) {
		this(context, null);
		init();
	}

	public CardView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		init();
		setPadding(PADDING, PADDING, PADDING, PADDING);
	}

	public CardView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
		initBorderPaint();

	}

	@SuppressLint("ClickableViewAccessibility")
	private void init() {

		this.setScaleType(ScaleType.FIT_XY);
		mtl = new MultiTouchListener();
		this.setOnTouchListener(mtl);
		mtl.setRandomPosition(this);
//


		// remove touch
//		mtl = new CollageView(this);
//		this.setOnTouchListener(mtl);
//        this.setOnLongClickListener(mtl);
//		mtl.setRandomPosition(this);

	}

//
//	public void setTouchList(boolean isTouch)
//    {
//        mtl.setTouchListener(isTouch);
//    }

//    public boolean getTouchListerner(){
//        return mtl.getTouchListener();
//    }
	private void initBorderPaint() {
		mBorderPaint = new Paint();
		mBorderPaint.setAntiAlias(true);
		mBorderPaint.setStyle(Paint.Style.STROKE);
		mBorderPaint.setColor(Color.WHITE);
		mBorderPaint.setStrokeWidth(STROKE_WIDTH);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawRect(PADDING, PADDING, getWidth() - PADDING, getHeight()
				- PADDING, mBorderPaint);

	}


	/**
	 * Function use to block movement in Card
	 */
	protected void setFixedItem() {

		mtl.isRotateEnabled = false;
		mtl.isScaleEnabled = false;
		mtl.isTranslateEnabled = false;
	}

}