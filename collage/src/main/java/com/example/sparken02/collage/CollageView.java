package com.example.sparken02.collage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.content.ContentValues.TAG;

/**
 *
 * Collage View is the parent view of a collage items.
 *
 * @author Juan Carlos Moreno (jcmore2@gmail.com)
 *
 */
public class CollageView extends RelativeLayout {

	private Context mContext;

	private final String BG = "#FFD4B081";

	private int collageWidth;
	private int collageHeight;

	private List<CardView> listCards = new ArrayList<CardView>();
	private boolean isViewRefresh = false;
	private boolean isCollageFixed = false;

	private final Random random = new Random();
	private static int i = 0;

	public CollageView(Context context) {
		this(context, null);
		init(context);
	}

	public CollageView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		init(context);
	}

	public CollageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	private void init(Context context) {
		mContext = context;
		this.setMotionEventSplittingEnabled(true);
		this.setBackgroundColor(Color.parseColor(BG));

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		collageWidth = MeasureSpec.getSize(widthMeasureSpec);

		collageHeight = MeasureSpec.getSize(heightMeasureSpec);
		Log.i(TAG, "onMeasure: ");

		refreshViews();
		requestLayout();
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		Log.i(TAG, "onDraw: ");
		super.onDraw(canvas);
	}

	/**
	 * Function use to add Cards to internal list
	 *
	 * @param card
	 */
	private void addViewToList(CardView card) {
		Log.i(TAG, "addViewToList: ");
		listCards.add(i,card);
		i++;
	}

	/**
	 * Function use to refresh Cards when Collage has measure
	 */
	private void refreshViews() {
		if (!listCards.isEmpty() && !isViewRefresh) {
			this.removeAllViews();
			for (CardView cardView : listCards) {
				Log.i(TAG, "refreshViews: ");
				LayoutParams params = new LayoutParams(
						cardView.getDrawable().getIntrinsicWidth(),
						cardView.getDrawable().getIntrinsicHeight());
//				int left = random.nextInt(collageWidth) - collageWidth / 4;
//				int top = random.nextInt(collageHeight) - collageHeight / 4;
//				params.leftMargin = left;
//				params.topMargin = top;
//				params.rightMargin = -left;
//				params.bottomMargin = -top;
				params.leftMargin = 100;
				params.topMargin = 100;
				params.rightMargin = 100;
				params.bottomMargin = 100;

				if (isCollageFixed)
					cardView.setFixedItem();

				this.addView(cardView, params);

			}
			isViewRefresh = true;
		}
	}

	public void setViewRefresh(boolean isCollageFixed){
		this.isViewRefresh = isCollageFixed;
		invalidate();
	}

	/**
	 * Methos use to set Collage fixed (Cards cant move)
	 *
	 * @param fixedCollage
	 */
	public void setFixedCollage(boolean fixedCollage) {
		isCollageFixed = fixedCollage;
	}

	/**
	 * Add Card from Bitmap
	 *
	 * @param bm
	 */
	public void addCard(Bitmap bm) {

		CardView card = new CardView(mContext);
		card.setImageBitmap(bm);
		Log.i(TAG, "addCard: ");
		addViewToList(card);
	}

	/**
	 * Add Card from Drawable
	 *
	 * @param drawable
	 */
	public void addCard(Drawable drawable) {

		CardView card = new CardView(mContext);
		card.setImageDrawable(drawable);
		addViewToList(card);
	}

	/**
	 * Add Card from resources
	 *
	 * @param resId
	 */
	public void addCard(int resId) {

		CardView card = new CardView(mContext);
		card.setImageResource(resId);
		addViewToList(card);
	}

	/**
	 * Create a Collage from list of Bitmaps
	 *
	 * @param bmList
	 *            List of bitmaps
	 */
	public void createCollageBitmaps(List<Bitmap> bmList) {
		for (Bitmap bm : bmList) {
			Log.i("TAG", "creaqteCollageBitmaps: ");
			addCard(bm);
		}
	}

//	public void createCollageBitmaps(Bitmap bm) {
//			addCard(bm);
//
//	}

	/**
	 * Create a Collage from list of Drawables
	 *
	 * @param drawableList
	 *            List of Drawables
	 */
	public void createCollageDrawables(List<Drawable> drawableList) {
		for (Drawable drawable : drawableList) {
			addCard(drawable);
		}
	}

	/**
	 * Create a Collage from list of Resources
	 *
	 * @param resIdList
	 *            List of resources
	 */
	public void createCollageResources(List<Integer> resIdList) {

		for (Integer res : resIdList) {
			addCard(res.intValue());
		}
	}
}