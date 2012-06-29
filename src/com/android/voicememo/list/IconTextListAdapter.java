package com.android.voicememo.list;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

public class IconTextListAdapter extends BaseAdapter {

	private Context mContext;

	private CheckBox mCheckBox; 
	
	private List<IconTextItem> mItems = new ArrayList<IconTextItem>();

	public IconTextListAdapter(Context context) {
		mContext = context;
	}

	public void addItem(IconTextItem it) {
		mItems.add(it);
	}

	public void setListItems(List<IconTextItem> lit) {
		mItems = lit;
	}

	public int getCount() {
		return mItems.size();
	}

	public Object getItem(int position) {
		return mItems.get(position);
	}

	public boolean areAllItemsSelectable() {
		return false;
	}

	public boolean isSelectable(int position) {
		try {
			return mItems.get(position).isSelectable();
		} catch (IndexOutOfBoundsException ex) {
			return false;
		}
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		IconTextView itemView;
		if (convertView == null) {
			itemView = new IconTextView(mContext, mItems.get(position));
		} else {
			
			itemView = (IconTextView) convertView;
			
			itemView.setText(0, mItems.get(position).getData(0));
			itemView.setText(1, mItems.get(position).getData(1));
			
			itemView = new IconTextView(mContext, mItems.get(position));
			
		}

		return itemView;
	}
	

}
