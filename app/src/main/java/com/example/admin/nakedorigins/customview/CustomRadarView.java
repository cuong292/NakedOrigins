package com.example.admin.nakedorigins.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.admin.nakedorigins.R;

public class CustomRadarView extends View {
	public interface OnDataChangeListener {
		void onStartChange();

		void onChangeFinish(float bodyPercent, float acidityPercent, float aromaPercent, float bitternessPercent);
	}


	private OnDataChangeListener dataChangeListener;
	private Paint paint;
	private Paint dashPaint;
	private Paint paintPolygon;
	private Paint paintCircle;
	private Path path;
	private Point body, acidity, aroma, bitterness;
	private Point sBody, sAcdity, sAroma, sBitterness;
	private int maxCoordinate;
	private int minBody, maxBody, minAcidity, maxAcidity, minAroma, maxAroma, minBitter, maxBitter;
	private int Ox, Oy;
	private float touchX, touchY;
	private float bodyP, acidityP, aromaP, bitternessP;
	private boolean isStartChange = false;
	private int rPoint = 45;
	private int xSize = 10;

	private enum TypePoint {
		NONE, BODY, ACIDITY, AROMA, BITTERNESS
	}

	private TypePoint typePoint = TypePoint.NONE;

	public CustomRadarView(Context context) {
		super(context);
		init();
	}

	public CustomRadarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CustomRadarView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public CustomRadarView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//Log.e("onDraw", "draw canvas");

		canvas.drawLine(Ox - maxCoordinate - 50, Oy, Ox + maxCoordinate + 50, Oy, paint);
		canvas.drawLine(Ox, Oy - maxCoordinate - 50, Ox, Oy + maxCoordinate + 50, paint);
		canvas.drawText("BODY", Ox - maxCoordinate - 150, Oy + 10, paint);
		canvas.drawText("AROMA", Ox + maxCoordinate + 70, Oy + 10, paint);
		canvas.drawText("ACIDITY", Ox - 60, Oy - maxCoordinate - 70, paint);
		canvas.drawText("BITTERNESS", Ox - 90, Oy + maxCoordinate + 100, paint);

		canvas.drawLine(minBody, Oy - xSize, minBody, Oy + xSize, paint);
		canvas.drawLine(maxBody, Oy - xSize, maxBody, Oy + xSize, paint);
		canvas.drawLine(minAroma, Oy - xSize, minAroma, Oy + xSize, paint);
		canvas.drawLine(maxAroma, Oy - xSize, maxAroma, Oy + xSize, paint);
		canvas.drawLine(Ox - xSize, minAcidity, Ox + xSize, minAcidity, paint);
		canvas.drawLine(Ox - xSize, maxAcidity, Ox + xSize, maxAcidity, paint);
		canvas.drawLine(Ox - xSize, minBitter, Ox + xSize, minBitter, paint);
		canvas.drawLine(Ox - xSize, maxBitter, Ox + xSize, maxBitter, paint);
		canvas.drawLine(minBody - (int) ((minBody - maxBody) / 2), Oy - xSize, minBody - (int) ((minBody - maxBody) / 2), Oy + xSize, paint);
		canvas.drawLine(minAroma + (int) ((maxAroma - minAroma) / 2), Oy - xSize, minAroma + (int) ((maxAroma - minAroma) / 2), Oy + xSize, paint);
		canvas.drawLine(Ox - xSize, minAcidity - (int) ((minAcidity - maxAcidity) / 2), Ox + xSize, minAcidity - (int) ((minAcidity - maxAcidity) / 2), paint);
		canvas.drawLine(Ox - xSize, minBitter + (int) ((maxBitter - minBitter) / 2), Ox + xSize, minBitter + (int) ((maxBitter - minBitter) / 2), paint);

		//canvas.drawLine(sBody.x, sBody.y, sAcdity.x, sAcdity.y, dashPaint);


		if (body.x != Ox && acidity.y != Oy && aroma.x != Ox && bitterness.y != Oy) {
			//draw line around polygon
			canvas.drawLine(body.x, body.y, acidity.x, acidity.y, paint);
			canvas.drawLine(acidity.x, acidity.y, aroma.x, aroma.y, paint);
			canvas.drawLine(aroma.x, aroma.y, bitterness.x, bitterness.y, paint);
			canvas.drawLine(bitterness.x, bitterness.y, body.x, body.y, paint);

			//draw polygon with background opacity 30%
			path.reset();
			path.moveTo(body.x, body.y);
			path.lineTo(acidity.x, acidity.y);
			path.lineTo(aroma.x, aroma.y);
			path.lineTo(bitterness.x, bitterness.y);
			canvas.drawPath(path, paintPolygon);

			canvas.drawCircle(body.x, body.y, rPoint, paint);
			canvas.drawCircle(acidity.x, acidity.y, rPoint, paint);
			canvas.drawCircle(aroma.x, aroma.y, rPoint, paint);
			canvas.drawCircle(bitterness.x, bitterness.y, rPoint, paint);

			canvas.drawCircle(body.x, body.y, rPoint - 3, paintCircle);
			canvas.drawCircle(acidity.x, acidity.y, rPoint - 3, paintCircle);
			canvas.drawCircle(aroma.x, aroma.y, rPoint - 3, paintCircle);
			canvas.drawCircle(bitterness.x, bitterness.y, rPoint - 3, paintCircle);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
			case MotionEvent.ACTION_DOWN:
				touchX = event.getX();
				touchY = event.getY();

				typePoint = getTypePoint(touchX, touchY);
				Log.i("onTouchDown", "touchdown: " + touchX + " - " + touchY + " | " + typePoint);
				break;

			case MotionEvent.ACTION_UP:
				if (typePoint != TypePoint.NONE && dataChangeListener != null) {
					float bodyPercent = ((float) (Ox - body.x)) / maxCoordinate * 100;
					float aromaPercent = (float) (aroma.x - Ox) / maxCoordinate * 100;
					float acidityPercent = ((float) (Oy - acidity.y)) / maxCoordinate * 100;
					float bitternessPercent = ((float) (bitterness.y - Oy)) / maxCoordinate * 100;

					dataChangeListener.onChangeFinish((int) bodyPercent, (int) acidityPercent, (int) aromaPercent, (int) bitternessPercent);
				}

				typePoint = TypePoint.NONE;
				isStartChange = false;
				break;

			case MotionEvent.ACTION_POINTER_DOWN:
				break;

			case MotionEvent.ACTION_POINTER_UP:
				break;

			case MotionEvent.ACTION_MOVE:
				float currentX = event.getX();
				float currentY = event.getY();
				if (!isStartChange && dataChangeListener != null && typePoint != TypePoint.NONE) {
					isStartChange = true;
					dataChangeListener.onStartChange();
				}
				//Log.e("onTouchMove", "touchmove: " + currentX + " - " + currentY);

				switch (typePoint) {
					case BODY:
						updateXPoint(currentX, body, minBody, maxBody);
						break;

					case ACIDITY:
						updateYPoint(currentY, acidity, minAcidity, maxAcidity);
						break;

					case AROMA:
						updateXPoint(currentX, aroma, maxAroma, minAroma);
						break;

					case BITTERNESS:
						updateYPoint(currentY, bitterness, maxBitter, minBitter);
						break;
				}

				invalidate();

				break;
		}
		return true;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		Log.e("onMeasure", "set height = width = " + getMeasuredWidth());
		int size = getMeasuredWidth();
		Ox = size / 2;
		Oy = size / 2;
		maxCoordinate = Ox - 200;
		int perCoordinate = maxCoordinate / 5;

		minBody = Ox - perCoordinate;
		maxBody = Ox - maxCoordinate;
		minAcidity = Oy - perCoordinate;
		maxAcidity = Oy - maxCoordinate;
		minAroma = Ox + perCoordinate;
		maxAroma = Ox + maxCoordinate;
		minBitter = Oy + perCoordinate;
		maxBitter = Oy + maxCoordinate;

		requestViewChange();
		setMeasuredDimension(size, size);
	}

	public void setDataView(float bodyPercent, float acidityPercent, float aromaPercent, float bitternessPercent, boolean isUpdate) {
		bodyP = bodyPercent;
		aromaP = aromaPercent;
		acidityP = acidityPercent;
		bitternessP = bitternessPercent;

		if (isUpdate) {
			requestViewChange();
		}
	}

	public void setDataChangeListener(OnDataChangeListener listener) {
		dataChangeListener = listener;
	}

	private void init() {
		path = new Path();

		paintPolygon = new Paint();
		paintPolygon.setStyle(Paint.Style.FILL);
		paintPolygon.setColor(Color.WHITE);
		paintPolygon.setAntiAlias(true);
		paintPolygon.setAlpha(50);

		float[] db = {5f, 5f};
		dashPaint = new Paint();
		dashPaint.setStyle(Paint.Style.FILL);
		dashPaint.setPathEffect(new DashPathEffect(db, 10f));
		dashPaint.setColor(Color.WHITE);
		dashPaint.setAntiAlias(true);
		dashPaint.setStrokeWidth(3f);

		paint = new Paint();
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(Color.WHITE);
		paint.setAntiAlias(true);
		paint.setStrokeWidth(3f);
		paint.setTextSize(32f);

		paintCircle = new Paint();
		paintCircle.setStyle(Paint.Style.FILL);
		paintCircle.setColor(getContext().getResources().getColor(R.color.colorDiscoverBackground));
		paintCircle.setAntiAlias(true);
		paintCircle.setStrokeWidth(3f);

		body = new Point();
		acidity = new Point();
		aroma = new Point();
		bitterness = new Point();
		sBody = new Point();
		sAcdity = new Point();


		body.x = Ox;
		body.y = Oy;
		acidity.x = Ox;
		acidity.y = Oy;
		aroma.x = Ox;
		aroma.y = Oy;
		bitterness.x = Ox;
		bitterness.y = Oy;
	}

	private TypePoint getTypePoint(float touchX, float touchY) {
		if (inRange(touchX, touchY, body)) {
			Log.e("getType", "check body");
			return TypePoint.BODY;
		}

		if (inRange(touchX, touchY, acidity)) {
			Log.e("getType", "check acidity");
			return TypePoint.ACIDITY;
		}

		if (inRange(touchX, touchY, aroma)) {
			Log.e("getType", "check aroma");
			return TypePoint.AROMA;
		} else {
			//Log.e("aln", "aroma: (" + touchX + "," + touchY + "), (" + aroma.x + "," + aroma.y + ")");
		}

		if (inRange(touchX, touchY, bitterness)) {
			Log.e("getType", "check bitterness");
			return TypePoint.BITTERNESS;
		}

		return TypePoint.NONE;
	}

	private boolean inRange(float x, float y, Point point) {
		return ((x <= (point.x + 45)) && (x >= (point.x - 45)) && (y < (point.y + 45)) && (y >= (point.y - 45)));
	}

	private void updateXPoint(float currentX, Point point, int maxX, int minX) {
		float distance;
		if (currentX < touchX) {
			distance = touchX - currentX;

			if (point.x - distance >= minX) {
				point.x = (int) (point.x - distance);
			} else {
				point.x = minX;
			}

			touchX = point.x;

		} else {
			distance = currentX - touchX;

			if (point.x + distance <= maxX) {
				point.x = (int) (point.x + distance);
			} else {
				point.x = maxX;
			}

			touchX = point.x;
		}
	}

	private void updateYPoint(float currentY, Point point, int maxY, int minY) {
		float distance;

		if (currentY < touchY) {
			distance = touchY - currentY;

			if (point.y - distance >= minY) {
				point.y = (int) (point.y - distance);
			} else {
				point.y = minY;
			}

			touchY = point.y;

		} else {
			distance = currentY - touchY;

			if (point.y + distance <= maxY) {
				point.y = (int) (point.y + distance);
			} else {
				point.y = maxY;
			}

			touchY = point.y;
		}
	}

	private void setDateX(Point point, float percent, int maxX, int minX, int key) {
		if (percent > 10) {
			if (percent < 90) {
				point.x = Ox + (int) (maxCoordinate * percent / 100) * key;

			} else {
				point.x = maxX;
			}

		} else {
			point.x = minX;
		}

		point.y = Oy;
		Log.e("setX", "data:" + point.x + ", key:" + key);
	}

	private void setDateY(Point point, float percent, int maxY, int minY, int key) {
		if (percent > 10) {
			if (percent < 90) {
				point.y = Oy + (int) (maxCoordinate * percent / 100) * key;

			} else {
				point.y = maxY;
			}

		} else {
			point.y = minY;
		}

		point.x = Ox;
		Log.e("setY", "data:" + point.y + ", key:" + key);
	}

	private void requestViewChange() {
		setDateX(body, bodyP, maxBody, minBody, -1);
		setDateX(aroma, aromaP, maxAroma, minAroma, 1);
		setDateY(acidity, acidityP, maxAcidity, minAcidity, -1);
		setDateY(bitterness, bitternessP, maxBitter, minBitter, 1);

		sBody.x = body.x;
		sBody.y = body.y;
		sAcdity.x = acidity.x;
		sAcdity.y = acidity.y;

		invalidate();
	}
}
