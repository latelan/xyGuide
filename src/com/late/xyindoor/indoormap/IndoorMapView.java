package com.late.xyindoor.indoormap;

import android.content.Context;
import android.location.Location;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

public class IndoorMapView extends ImageView {

	private float imageViewStartX = this.getX();
	private float imageViewStartY = this.getY();

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
		marker.setXY(event.getX(), event.getY());
		marker.invalidate();
		marker.setInMapXY(event.getX() - imageViewStartX, event.getY()
				- imageViewStartY);

		return true;
	}

	public void addMarker(MarkerView marker) {
		this.marker = marker;
	}
}
