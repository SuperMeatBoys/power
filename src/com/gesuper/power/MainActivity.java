package com.gesuper.power;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;

public class MainActivity extends Activity implements OnTouchListener, OnGestureListener{
	public static final String TAG = "MainActivity";

	private static int verticalMinDistance = 20;
	private CustomCubic mCubic;
	private GestureDetector mGesture;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.mCubic = (CustomCubic) this.findViewById(R.id.cubic);
		this.mCubic.setOnTouchListener(this);
		this.mGesture = new GestureDetector(this, this);
		this.mGesture.setIsLongpressEnabled(false);
		this.mCubic.initResources(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		int dX = (int) (e2.getX() - e1.getX());
		int dY = (int) (e2.getY() - e1.getY());

		Toast.makeText(this, "onFling", Toast.LENGTH_SHORT).show();
		if(Math.abs(dX) < verticalMinDistance 
				&& Math.abs(dY) < verticalMinDistance){
			return true;
		}

		if(Math.abs(dX) > 2*Math.abs(dY) && Math.abs(dX) >= verticalMinDistance){
			//left or right
			if(dX > 0) {
				//To Right
				Log.v(TAG, "Right");
				mCubic.moveToRight();
			} else {
				//To Left
				Log.v(TAG, "Left");
				mCubic.moveToLeft();
			}
		} else if(Math.abs(dY) > 2*Math.abs(dX)){
			//top or bottom
			if(dY > 0) {
				//To bottom
				Log.v(TAG, "Bottom");
				mCubic.moveToBottom();
			} else {
				//To top
				Log.v(TAG, "Top");
				mCubic.moveToTop();
			}
		}

        return true; 
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return this.mGesture.onTouchEvent(event);
	}
}
