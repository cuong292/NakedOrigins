package com.example.admin.nakedorigins.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
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
	private Paint paintPolygon;
	private Paint paintCircle;
	private Path path;
	private Point body, acidity, aroma, bitterness;
	private int maxCoordinate;
	private int minBody, maxBody, minAcidity, maxAcidity, minAroma, maxAroma, minBitter, maxBitter;
	private int Ox, Oy;
	private float touchX, touchY;
	private float bodyP, acidityP, aromaP, bitternessP;
	private boolean isStartChange = false;

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
		Log.e("onDraw", "draw canvas");

		canvas.drawLine(Ox - maxCoordinate - 50, Oy, Ox + maxCoordinate + 50, Oy, paint);
		canvas.drawLine(Ox, Oy - maxCoordinate - 50, Ox, Oy + maxCoordinate + 50, paint);
		canvas.drawText("BODY", Ox - maxCoordinate - 150, Oy + 10, paint);
		canvas.drawText("AROMA", Ox + maxCoordinate + 70, Oy + 10, paint);
		canvas.drawText("ACIDITY", Ox - 60, Oy - maxCoordinate - 70, paint);
		canvas.drawText("BITTERNESS", Ox - 90, Oy + maxCoordinate + 100, paint);


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

			canvas.drawCircle(body.x, body.y, 25, paint);
			canvas.drawCircle(acidity.x, acidity.y, 25, paint);
			canvas.drawCircle(aroma.x, aroma.y, 25, paint);
			canvas.drawCircle(bitterness.x, bitterness.y, 25, paint);

			canvas.drawCircle(body.x, body.y, 22, paintCircle);
			canvas.drawCircle(acidity.x, acidity.y, 22, paintCircle);
			canvas.drawCircle(aroma.x, aroma.y, 22, paintCircle);
			canvas.drawCircle(bitterness.x, bitterness.y, 22, paintCircle);
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
					int bodyPercent = body.x / maxCoordinate * 100;
					int aromaPercent = aroma.x / maxCoordinate * 100;
					int acidityPercent = acidity.y / maxCoordinate * 100;
					int bitternessPercent = bitterness.y / maxCoordinate * 100;

					dataChangeListener.onChangeFinish(bodyPercent, acidityPercent, aromaPercent, bitternessPercent);
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

	public void setDataView(float bodyPercent, float acidityPercent, float aromaPercent, float bitternessPercent) {
		bodyP = bodyPercent;
		aromaP = aromaPercent;
		acidityP = acidityPercent;
		bitternessP = bitternessPercent;
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
		return ((x <= (point.x + 30)) && (x >= (point.x - 30)) && (y < (point.y + 30)) && (y >= (point.y - 30)));
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
		if (percent > 20) {
			if (percent < 80) {
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
		if (percent > 20) {
			if (percent < 80) {
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
		invalidate();
	}
}
