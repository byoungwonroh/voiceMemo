package com.android.voicememo.list;

import com.android.voicememo.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

public class IconTextView extends LinearLayout {

	/**
	 * TextView 01
	 */
	private TextView mText01;
	/**
	 * TextView 02
	 */
	private TextView mText02;

	
	private CheckBox mCB;
	
	public IconTextView(Context context, IconTextItem aItem) {
		super(context);
		
		// Layout Inflation
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.listitem, this, true);	
		// Set Text 01
		mText01 = (TextView) findViewById(R.id.dataItem01);
		mText01.setText(aItem.getData(0));
		
		// Set Text 02
		mText02 = (TextView) findViewById(R.id.dataItem02);
		mText02.setText(aItem.getData(1));
		
		// Set CheckBox
		mCB = (CheckBox)findViewById(R.id.dataCheck);
	}

	/**
	 * set Text
	 * 
	 * @param index
	 * @param data
	 */
	public void setText(int index, String data) {
		if (index == 0) {
			mText01.setText(data);
		} else if (index == 1) {
			mText02.setText(data);
		} 
		
		else {
			throw new IllegalArgumentException();
		}
	}


}
