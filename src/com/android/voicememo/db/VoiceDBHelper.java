package com.android.voicememo.db;

import java.util.ArrayList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class VoiceDBHelper extends DBHelper{
	
	public static final String DB_NAME = "VoiceMemo.sqllite";
	public static final int DB_VERSION =1;
	
	public VoiceDBHelper(Context context, String name,CursorFactory factory, int version) {
		super(context, name, factory, version);

	}
	public VoiceDBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		
	}
	private String MEMOTABLE = 
		"CREATE TABLE MEMOTABLE (SEQ INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE ," +
				" CONTENT TEXT, CDATE DATETIME DEFAULT CURRENT_TIMESTAMP, DELYN DEFAULT N)";
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(MEMOTABLE);
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		//Log.w(TAG, "Version mismatch :"+oldVersion+"to "+newVersion );
		db.execSQL("DROP TABLE"+MEMOTABLE);
		
		onCreate(db);
	}
	
	//메모리스트 가져오기
	@SuppressWarnings("unchecked")
	public ArrayList getMemoList(){
		String sqlStr = "";
			sqlStr = "SELECT SEQ,CONTENT,DATE(CDATE) AS IDATE FROM MEMOTABLE WHERE DELYN='N' ORDER BY CDATE DESC;";
		return this.query(sqlStr , null);
	}
	
	//상세 메모
	@SuppressWarnings("unchecked")
	public ArrayList getMemoView(String seq){
		String sqlStr = "SELECT SEQ,CONTENT,CDATE FROM MEMOTABLE WHERE SEQ = "+seq;
		
		return this.query(sqlStr , null);
		
	}
	
	
	
}
