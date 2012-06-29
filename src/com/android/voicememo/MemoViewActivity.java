package com.android.voicememo;

import java.util.ArrayList;
import java.util.HashMap;

import com.android.voicememo.db.VoiceDBHelper;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

public class MemoViewActivity extends Activity{

	String seq;
	String content;
	String cdate;
	
	TextView cdateTv;
	
	EditText contentEdt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.memoview);
		
		Intent receviedIntent = getIntent();
		String seq = receviedIntent.getStringExtra("seq");
		
		cdateTv = (TextView)findViewById(R.id.memoCdate);
		contentEdt = (EditText)findViewById(R.id.memoContent);
		
		VoiceDBHelper voiceDBHelper = new VoiceDBHelper(this);
		ArrayList memoView = voiceDBHelper.getMemoView(seq);
		voiceDBHelper.close();
		for (int i = 0; i < memoView.size(); i++) {
			HashMap<String, String> memoMap = (HashMap<String, String>) memoView.get(i);
			seq = memoMap.get("SEQ");
			content = memoMap.get("CONTENT");
			cdate = memoMap.get("CDATE");
		
		}
		cdateTv.setText(cdate);
		contentEdt.setText(content);
	}

    /* Creates the menu items */
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
       MenuItem SendEmail = menu.add(0,1,0,"Send Mail");
       MenuItem memoDel = menu.add(0,1,0,"Memo Delete");
       Resources res = getResources();
       memoDel.setIcon(res.getDrawable(R.drawable.option_icon02));
       return true;
    }

    /* Handles item selections */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case 1:
            return true;
        case 2:
            return true;
            
        }
        return false;
    }
	
}
