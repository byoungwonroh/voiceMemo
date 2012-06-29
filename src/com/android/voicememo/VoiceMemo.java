package com.android.voicememo;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

import com.android.voicememo.db.VoiceDBHelper;
import com.android.voicememo.utils.Util;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class VoiceMemo extends Activity implements View.OnClickListener{
    
	Button memoListBtn;
	Button voiceMemoBtn;
	Button saveMemoBtn;
	
	EditText memoEdt;
	
	private static final int REQUEST_CODE = 1234;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voicememo);
        
        memoListBtn = (Button)findViewById(R.id.memoListBtn);
        voiceMemoBtn = (Button)findViewById(R.id.voiceMemo);
        saveMemoBtn = (Button)findViewById(R.id.saveMemo);
        
        saveMemoBtn.setOnClickListener(this);
        voiceMemoBtn.setOnClickListener(this);
        memoListBtn.setOnClickListener(this);
        
        memoEdt = (EditText)findViewById(R.id.memoEdt);
        
    }

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.saveMemo:
			saveMemoGo();
			break;
		case R.id.voiceMemo:
			startVoiceRecognitionActivity();
			break;
		case R.id.memoListBtn:
			Intent intent = new Intent(getApplicationContext(),MemoListActivity.class);
			startActivity(intent);
			break;	

		}
		
		
	}

	/*
	 * 음성 메모
	 * */
	private void startVoiceRecognitionActivity() {
		// TODO Auto-generated method stub
		try {
			Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
			intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
			intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "메모내용을 말씀하세요");
			
			startActivityForResult(intent, REQUEST_CODE);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		
		if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
			ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			memoEdt.setText(matches.toString());
			
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	private void saveMemoGo() {
		// TODO Auto-generated method stub
		VoiceDBHelper DBHelper = new VoiceDBHelper(getApplicationContext());
		
		String content = StringUtils.defaultString(memoEdt.getText().toString());
		
		if(!content.equals("")){
			ContentValues values = new ContentValues();
			
			values.put("CONTENT", content);
			DBHelper.insert("MEMOTABLE", values);
			DBHelper.close();
			
			Util.commonToast(getApplicationContext(), "메모 등록되었습니다.");
			
			Intent intent = new Intent(getApplicationContext(),MemoListActivity.class);
			startActivity(intent);
		}else{
			Util.commonToast(getApplicationContext(), "메모를 입력 하세요");
		}
		
	}
	
    /* Creates the menu items */
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
       MenuItem fontSize = menu.add(0,1,0,"글자크기");
       MenuItem memoType = menu.add(0,1,0,"메모지");
       //Resources res = getResources();
       //item.setIcon(res.getDrawable(R.drawable.option_icon04));
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