package com.late.xyindoor.indoormap;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewDebug.ExportedProperty;
import android.widget.ImageView;

public class MarkerView extends View {

	private float currentX = 0;
	private float currentY = 0;
	
	// marker 在IndoorMapView中的坐标
	private float inMapX;		
	private float inMapY;
	
	private Paint p = new Paint();
	
	public MarkerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		p.setColor(Color.RED);
		canvas.drawCircle(currentX, currentY, 15, p);
	}

//	public boolean onTouchEvent(MotionEvent event) {
//		// TODO Auto-generated method stub
//		this.currentX = event.getX();
//		this.currentY = event.getY();
//		
//		this.invalidate();
//		
//		return true;
//	}

	@Override
	public float getX() {
		// TODO Auto-generated method stub
		return this.currentX;
	}

	@Override
	public float getY() {
		// TODO Auto-generated method stub
		return this.currentY;
	}
	
	public void setXY(float x, float y) {
		this.currentX = x;
		this.currentY = y;
	}
	
	public float getInMapX() {
		return this.inMapX;
	}
	
	public float getInMapY() {
		return this.inMapY;
	}
	
	public void setInMapXY(float x, float y) {
		this.inMapX = x;
		this.inMapY = y;
	}
}
