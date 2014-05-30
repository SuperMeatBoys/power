package com.gesuper.power;

import com.gesuper.power.CustomCubic.OnScoreChanged;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainView extends LinearLayout implements OnTouchListener, OnGestureListener{
	private static String TAG = "MainView";
	private static int verticalMinDistance = 20;
	private Context context;
	private CustomCubic mCubic;
	private GestureDetector mGesture;
	private TextView score;
	private TextView highest;
	private int highestScore;

	public MainView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		inflate(context, R.layout.activity_main, this);
		this.context = context;
		this.initResources();
	}

	private void initResources() {
		// TODO Auto-generated method stub

		this.mCubic = (CustomCubic) this.findViewById(R.id.cubic);
		this.setOnTouchListener(this);
		this.mGesture = new GestureDetector(context, this);
		this.score = (TextView)this.findViewById(R.id.score_tv);
		this.highest = (TextView)this.findViewById(R.id.highest_tv);
		this.highestScore = 0;
		this.mCubic.initResources(this);
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

				if(Math.abs(dX) < verticalMinDistance 
						&& Math.abs(dY) < verticalMinDistance){
					return true;
				}

				if(Math.abs(dX) > 2*Math.abs(dY)){
					//left or right
					if(dX > 0) {
						//To Right
						mCubic.moveToRight();
					} else {
						//To Left
						mCubic.moveToLeft();
					}
				} else if(Math.abs(dY) > 2*Math.abs(dX)){
					//top or bottom
					if(dY > 0) {
						//To bottom
						mCubic.moveToBottom();
					} else {
						//To top
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

	public void refresh() {
		// TODO Auto-generated method stub
		this.mCubic.refresh();
	}
	
	public void changeScore(int score){
		this.score.setText("Score\n" + score);
		if(score >= this.highestScore){
			this.highestScore = score;
			this.highest.setText("Highest\n" + this.highestScore);
		}
	}
	
}
