package com.gesuper.power;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;

public class MainActivity extends Activity {
	public static final String TAG = "MainActivity";

	private MainView mainView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mainView = new MainView(this);
		setContentView(mainView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.action_refresh:
			this.mainView.refresh();
			break;
		case R.id.action_share:
			break;
		case R.id.action_rate_app:
			break;
		case R.id.action_undo:
			break;
		case R.id.action_help:
			break;
		case R.id.action_settings:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
}
