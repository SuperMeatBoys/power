package com.gesuper.power;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomCubic extends LinearLayout {
	public static final String TAG = "CustomCubic";
	private MainView mainView;
	private int BUTTON_LINE_NUMBER = 4;
	private int BUTTON_TOTAL_NUMBER = BUTTON_LINE_NUMBER*BUTTON_LINE_NUMBER;
	private List<Integer> numbers;
	private List<TextView> Btns;
	private boolean isChanged;
	private int score;
	private List<Integer> temp;
	
	public CustomCubic(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public void initResources(MainView mainView) {
		// TODO Auto-generated method stub
		this.isChanged = false;
		this.score = 0;
		this.mainView = mainView;
		numbers = new ArrayList<Integer>(BUTTON_TOTAL_NUMBER);
		Btns = new ArrayList<TextView>(BUTTON_TOTAL_NUMBER);
		temp = new ArrayList<Integer>(BUTTON_LINE_NUMBER);
		//Init numbers
		for(int i=0;i<BUTTON_TOTAL_NUMBER;i++){
			numbers.add(0);
		}
		
		//Init Btns
		Btns.add((TextView)this.findViewById(R.id.bt11));
		Btns.add((TextView)this.findViewById(R.id.bt12));
		Btns.add((TextView)this.findViewById(R.id.bt13));
		Btns.add((TextView)this.findViewById(R.id.bt14));
		
		Btns.add((TextView)this.findViewById(R.id.bt21));
		Btns.add((TextView)this.findViewById(R.id.bt22));
		Btns.add((TextView)this.findViewById(R.id.bt23));
		Btns.add((TextView)this.findViewById(R.id.bt24));
		
		Btns.add((TextView)this.findViewById(R.id.bt31));
		Btns.add((TextView)this.findViewById(R.id.bt32));
		Btns.add((TextView)this.findViewById(R.id.bt33));
		Btns.add((TextView)this.findViewById(R.id.bt34));
		
		Btns.add((TextView)this.findViewById(R.id.bt41));
		Btns.add((TextView)this.findViewById(R.id.bt42));
		Btns.add((TextView)this.findViewById(R.id.bt43));
		Btns.add((TextView)this.findViewById(R.id.bt44));
		this.add();
		this.add();
		this.add();
		updateBtns();
	}
	
	private void updateBtns(){
		//Init numbers
		for(int i=0;i<BUTTON_TOTAL_NUMBER;i++){
			if(numbers.get(i) != 0){
				Btns.get(i).setText(String.valueOf(numbers.get(i)));
			} else {
				Btns.get(i).setText(" ");
			}
		}
		this.mainView.changeScore(this.score);
	}
	
	private void add(){
		Random rand = new Random();
		
		int count = 0;
		for(int i=0;i<BUTTON_TOTAL_NUMBER;i++){
			if(numbers.get(i) == 0){
				count ++;
			}
		}
		
		int place = rand.nextInt(count) + 1;
		for(int i=0;i<=BUTTON_TOTAL_NUMBER;i++){
			if(place == 0){
				numbers.set(i-1, 2);
				break;
			}
			
			if(numbers.get(i) == 0){
				place --;
			}
		}
	}
	
	private void log(int i){
		Log.v(TAG, "" + i);
		Log.v(TAG, String.format("%3d %3d %3d %3d", numbers.get(0), numbers.get(1), numbers.get(2), numbers.get(3)));
		Log.v(TAG, String.format("%3d %3d %3d %3d", numbers.get(4), numbers.get(5), numbers.get(6), numbers.get(7)));
		Log.v(TAG, String.format("%3d %3d %3d %3d", numbers.get(8), numbers.get(9), numbers.get(10), numbers.get(11)));
		Log.v(TAG, String.format("%3d %3d %3d %3d", numbers.get(12), numbers.get(13), numbers.get(14), numbers.get(15)));
	}
	
	public void moveToLeft(){
		int count = 0, step = 0, a = 0, b = 0;
		
		for(int i=0; i<BUTTON_LINE_NUMBER; i++){
			count = 0; step = 0;
			this.temp.clear();
			for(int j=0;j<BUTTON_LINE_NUMBER;j++){
				if(numbers.get(i*BUTTON_LINE_NUMBER + j) != 0){
					this.temp.add(numbers.get(i*BUTTON_LINE_NUMBER + j));
				}
			}
			count = this.temp.size();
			if(count < BUTTON_LINE_NUMBER) this.isChanged = true;
			for(int j=0;j<BUTTON_LINE_NUMBER;j++){
				numbers.set(i*BUTTON_LINE_NUMBER + j, j<count?this.temp.get(j):0);
			}
			
			while(count > 1){
				count -= 1;
				a = numbers.get(i*BUTTON_LINE_NUMBER + step);
				b = numbers.get(i*BUTTON_LINE_NUMBER + step + 1);
				if(a == b){
					this.isChanged = true;
					numbers.set(i*BUTTON_LINE_NUMBER + step, a+b);
					this.score += a+b;
					for(int j=step+1;j<BUTTON_LINE_NUMBER-1;j++){ 
						numbers.set(i*BUTTON_LINE_NUMBER + j, numbers.get(i*BUTTON_LINE_NUMBER + j + 1));
					}
					numbers.set((i+1)*(BUTTON_LINE_NUMBER ) -1, 0);
					step += 1;
				}else{
					step += 1;
				}
			}
			this.log(i);

		}
		if(this.isChanged){
			this.add();
			this.isChanged = false;
		}
		updateBtns();
	}
	
	public void moveToRight(){
		int count = 0, step = 0, a = 0, b = 0;
		
		for(int i=0; i<BUTTON_LINE_NUMBER; i++){
			count = 0; step = BUTTON_LINE_NUMBER - 1;
			this.temp.clear();
			for(int j=0;j<BUTTON_LINE_NUMBER;j++){
				if(numbers.get(i*BUTTON_LINE_NUMBER + j) != 0){
					this.temp.add(numbers.get(i*BUTTON_LINE_NUMBER + j));
				}
			}
			count = this.temp.size();
			if(count < BUTTON_LINE_NUMBER) this.isChanged = true;
			for(int j=BUTTON_LINE_NUMBER;j>0;j--){
				numbers.set(i*BUTTON_LINE_NUMBER + j -1, j+count>BUTTON_LINE_NUMBER?this.temp.get(j+count-BUTTON_LINE_NUMBER-1):0);
			}
			
			while(count > 1){
				count -= 1;
				a = numbers.get(i*BUTTON_LINE_NUMBER + step);
				b = numbers.get(i*BUTTON_LINE_NUMBER + step - 1);
				if(0 == a){
					this.isChanged = true;
					for(int j=step;j>0;j--) 
						numbers.set(i*BUTTON_LINE_NUMBER + j, numbers.get(i*BUTTON_LINE_NUMBER + j - 1));
					numbers.set(i*BUTTON_LINE_NUMBER , 0);
				}else if(a == b){
					numbers.set(i*BUTTON_LINE_NUMBER + step, a+b);
					for(int j=step-1;j>0;j--) 
						numbers.set(i*BUTTON_LINE_NUMBER + j, numbers.get(i*BUTTON_LINE_NUMBER + j - 1));
					numbers.set(i*BUTTON_LINE_NUMBER , 0);
					step -= 1;
				}else if(a != b){
					step -= 1;
				}

			}
		}

		if(this.isChanged){
			this.add();
			this.isChanged = false;
		}
		updateBtns();
	}
	
	public void moveToTop(){
		int count = 0, step = 0, a = 0, b = 0;
		
		for(int i=0; i<BUTTON_LINE_NUMBER; i++){
			count = 0; step = 0;
			this.temp.clear();
			for(int j=0;j<BUTTON_LINE_NUMBER;j++){
				if(numbers.get(i + j*BUTTON_LINE_NUMBER) != 0){
					this.temp.add(numbers.get(i + j*BUTTON_LINE_NUMBER));
				}
			}
			count = this.temp.size();
			if(count < BUTTON_LINE_NUMBER) this.isChanged = true;
			for(int j=0;j<BUTTON_LINE_NUMBER;j++){
				numbers.set(i + j*BUTTON_LINE_NUMBER, j<count?this.temp.get(j):0);
			}
			
			while(count > 1){
				count -= 1;
				a = numbers.get(i + step*BUTTON_LINE_NUMBER);
				b = numbers.get(i + (step + 1)*BUTTON_LINE_NUMBER);
				if(a == b){
					this.isChanged = true;
					numbers.set(i + step*BUTTON_LINE_NUMBER, a+b);
					for(int j=step + 1;j<BUTTON_LINE_NUMBER-1;j++){ 
						numbers.set(i + j*BUTTON_LINE_NUMBER, numbers.get(i + (j + 1)*BUTTON_LINE_NUMBER));
					}
					numbers.set(i + BUTTON_LINE_NUMBER*(BUTTON_LINE_NUMBER - 1), 0);
					step += 1;
				}else {
					step += 1;
				}

			}
		}

		if(this.isChanged){
			this.add();
			this.isChanged = false;
		}
		updateBtns();
	}
	
	public void moveToBottom(){
		int count = 0, step = 0, a = 0, b = 0;
		
		for(int i=0; i<BUTTON_LINE_NUMBER; i++){
			count = 0; step = BUTTON_LINE_NUMBER - 1;
			this.temp.clear();
			for(int j=0;j<BUTTON_LINE_NUMBER;j++){
				if(numbers.get(i + j*BUTTON_LINE_NUMBER) != 0){
					this.temp.add(numbers.get(i + j*BUTTON_LINE_NUMBER));
				}
			}
			count = this.temp.size();
			if(count < BUTTON_LINE_NUMBER) this.isChanged = true;
			for(int j=BUTTON_LINE_NUMBER;j>0;j++){
				numbers.set(i + j*BUTTON_LINE_NUMBER, j+count>BUTTON_LINE_NUMBER?this.temp.get(j+count-BUTTON_LINE_NUMBER-1):0);
			}
			
			while(count > 1){
				count -= 1;
				a = numbers.get(i + step*BUTTON_LINE_NUMBER);
				b = numbers.get(i + (step - 1)*BUTTON_LINE_NUMBER);
				if(0 == a){
					this.isChanged = true;
					for(int j = step;j>0;j--) 
						numbers.set(i + j*BUTTON_LINE_NUMBER, numbers.get(i + (j - 1)*BUTTON_LINE_NUMBER));
					numbers.set(i, 0);
				}else if(a == b){
					this.isChanged = true;
					numbers.set(i + step*BUTTON_LINE_NUMBER, a+b);
					for(int j=step - 1;j>0;j--) 
						numbers.set(i + j*BUTTON_LINE_NUMBER, numbers.get(i + (j - 1)*BUTTON_LINE_NUMBER));
					numbers.set(i, 0);
					step -= 1;
				}else if(a != b){
					step -= 1;
				}

			}
		}

		if(this.isChanged){
			this.add();
			this.isChanged = false;
		}
		updateBtns();
	}

	public void refresh() {
		// TODO Auto-generated method stub
		for(int i=0;i<BUTTON_TOTAL_NUMBER;i++){
			numbers.set(i, 0);
		}
		this.updateBtns();
		this.score = 0;

		this.add();
		this.add();
		this.add();
	}
	
	public interface OnScoreChanged {
		public void changeScore(int score);
	}
}
