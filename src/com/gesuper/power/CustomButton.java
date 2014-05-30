package com.gesuper.power;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class CustomButton extends TextView{

	private static final String TAG = "CustomButton";

	public CustomButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
//		int number = 0;
//		try{
//			number = Integer.valueOf(text.toString());
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		switch(number){
//		case 2:
//			this.setBackgroundResource(R.color.cell_2);
//			break;
//		case 4:
//			this.setBackgroundResource(R.color.cell_4);
//			break;
//		case 8:
//			this.setBackgroundResource(R.color.cell_8);
//			break;
//		case 16:
//			this.setBackgroundResource(R.color.cell_16);
//			break;
//		case 32:
//			this.setBackgroundResource(R.color.cell_32);
//			break;
//		case 64:
//			this.setBackgroundResource(R.color.cell_64);
//			break;
//		case 128:
//			this.setBackgroundResource(R.color.cell_128);
//			break;
//		case 256:
//			this.setBackgroundResource(R.color.cell_256);
//			break;
//		case 512:
//			this.setBackgroundResource(R.color.cell_512);
//			break;
//		case 1024:
//			this.setBackgroundResource(R.color.cell_1024);
//			break;
//		case 2048:
//			this.setBackgroundResource(R.color.cell_2048);
//			break;
//		default:
//			this.setBackgroundResource(R.color.overlay_bg);
//			break;
//		}
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
		 setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));
		 int childWidthSize = getMeasuredWidth();
		 //高度和宽度一样
		 heightMeasureSpec = widthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthSize, MeasureSpec.EXACTLY);

		 super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
}
