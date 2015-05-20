package com.late.xyindoor.indoormap;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

public class IndoorMapView extends ImageView {

	private int imageWidth;
	private int imageHeight;

	private MarkerView marker;

	public IndoorMapView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public IndoorMapView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public IndoorMapView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		// return super.onTouchEvent(event);
		// Log.d("imageView Location", String.valueOf(this.getX()) +", " +
		// String.valueOf(this.getY()));
		if (!(event.getX() > this.getLeft() && event.getX() < this.getRight()
				&& event.getY() > this.getTop() && event.getY() < this
				.getBottom())) {
			return super.onTouchEvent(event);
		}
		marker.setXY(event.getX(), event.getY());
		marker.invalidate();

		// imageWidth = this.getDrawable().getBounds().width();
		// imageHeight = this.getDrawable().getBounds().height();

		imageWidth = this.getWidth();
		imageHeight = this.getHeight();

		float x = (float) (Math.round((event.getX() / imageWidth) * 10000.0) / 10000.0);
		float y = (float) (Math.round((event.getY() / imageHeight) * 10000.0) / 10000.0);

		marker.setInMapXY(String.valueOf(x), String.valueOf(y));

		return true;
	}

	public void addMarker(MarkerView marker) {
		this.marker = marker;
	}

}
