package com.android.voicememo;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.android.voicememo.db.VoiceDBHelper;
import com.android.voicememo.list.DataListView;
import com.android.voicememo.list.IconTextItem;
import com.android.voicememo.list.IconTextListAdapter;
import com.android.voicememo.list.OnDataSelectionListener;

public class MemoListActivity extends Activity{
	
	LinearLayout bottomLayout;
	
	LinearLayout listLayout;
	IconTextListAdapter adapter;
	DataListView list;
	VoiceDBHelper DBHelper;
	
	String curData[];
	
	Button delBtn;
	Button cencelBtn;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.listmemo);
		
		bottomLayout = (LinearLayout)findViewById(R.id.bottomLayout);
		
		list = new DataListView(this);
		list.setCacheColorHint(000000);
		listLayout = (LinearLayout)findViewById(R.id.memoList);
		adapter = new IconTextListAdapter(this);
		
		cencelBtn = (Button)findViewById(R.id.cencelBtn);
		cencelBtn.setOnClickListener(btnListener);
		delBtn = (Button)findViewById(R.id.delBtn);
		delBtn.setOnClickListener(btnListener);
		memoList();
		
		// set listener
		list.setOnDataSelectionListener(new OnDataSelectionListener() {

			@Override
			public void onDataSelected(AdapterView parent, View v,
					int position, long id) {
				IconTextItem curItem = (IconTextItem) adapter.getItem(position);
				String seq = curItem.getSeq();
				Intent intent = new Intent(getApplicationContext(),MemoViewActivity.class);
				intent.putExtra("seq", seq);
				startActivity(intent);
				
			}
			
		});

		
	}
	
    /**
     * 삭제/취소 버튼 Listener
     */
    View.OnClickListener btnListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			switch(v.getId()){
				case R.id.cencelBtn : 		// 취소 버튼 클릭 시
					bottomLayout.setVisibility(View.GONE);
				return;
					
			}
		}
	};
	private void memoList() {
		DBHelper = new VoiceDBHelper(this);
		ArrayList memoList = DBHelper.getMemoList();
		DBHelper.close();
		for (int i = 0; i < memoList.size(); i++) {
			HashMap<String, String> memoMap = (HashMap<String, String>) memoList.get(i);
			String seq = memoMap.get("SEQ");
			String content = memoMap.get("CONTENT");
			String cdate = memoMap.get("IDATE");
			
			IconTextItem item = new IconTextItem(content,cdate);
			item.setSeq(seq);
			adapter.addItem(item);	
		}
		list.setAdapter(adapter);
		listLayout.addView(list, listLayout.getLayoutParams());
	}
	
	/**
	 * 체크박스 보이기
	 */
	 private void checkVisible(){
		
		int listCnt = list.getCount();
		
		for (int i = 0; i < listCnt; i++) {
			LinearLayout LL = (LinearLayout)list.getChildAt(i);
			((CheckBox)LL.findViewById(R.id.dataCheck)).setVisibility(View.VISIBLE);
		}
		
	}
	/**
	 * 하단 보이기
	 */
	private void bottomLayoutVisible(){
		bottomLayout.setVisibility(View.VISIBLE);
	}
	
    /* Creates the menu items */
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
       MenuItem memoDel = menu.add(0,1,0,"메모삭제");
       Resources res = getResources();
       memoDel.setIcon(res.getDrawable(R.drawable.option_icon02));
       return true;
    }

    /* Handles item selections */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case 1:
        	checkVisible();
        	bottomLayoutVisible();
            return true;
            
        }
        return false;
    }
	
	
}
