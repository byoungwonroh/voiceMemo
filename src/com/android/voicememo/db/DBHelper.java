package com.android.voicememo.db;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public abstract class DBHelper extends SQLiteOpenHelper {

	private SQLiteDatabase mDB;
	
	public DBHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		
	}
	

	public ArrayList query(String queryStr, String[] args ) {
		
		mDB = this.getReadableDatabase();
		ArrayList lo = null;
		HashMap eo = null;
		
		lo = new ArrayList();
		Cursor c = mDB.rawQuery(queryStr, args);
		//Log.e("-------result-------",String.valueOf(c.getCount()));
		if(c.moveToFirst()){
			do{
				eo = getRow(c);
				lo.add(eo);
			}while(c.moveToNext());
		}
		c.close();
		return lo;
	}
	
	public boolean querySelect(String queryStr, String[] args ) {
		
		boolean result = false;
		
		mDB = this.getReadableDatabase();
		
		Cursor c = mDB.rawQuery(queryStr, args);
		//Log.e("-------result-------",String.valueOf(c.getCount()));
		result = c.moveToFirst();
		Log.i("count", "result");
		c.close();
		return result;
	}
	
	
	
	public ArrayList select(String table, String[] projection, String selection,
            String[] selectionArgs, String orderBy) {
		mDB = this.getReadableDatabase();
		ArrayList lo = null;
		lo = new ArrayList();
		
		Cursor c = mDB.query(table, projection, selection, selectionArgs,
		             null, null, orderBy);
		if(c.moveToFirst()){
			do{
				String id = c.getString(0);
				lo.add(id);
			}while(c.moveToNext());
		}
		c.close();
		return lo;

	}
	
	
	

	
	public long insert(String table, ContentValues _initialValues) {
		
		long rowID=0;
		mDB = this.getWritableDatabase();
		
		try {
			rowID = mDB.insert(table, null, _initialValues);
     	
	    } catch(Exception e) {
			Log.e("Exception", e.toString());
	    } finally{
	    	mDB.close();
	    }
	    
		return rowID;
		
	}
	
	
	
	public int delete(String table, String where, String[] whereArgs) {
		int count= 0;
		mDB = this.getWritableDatabase();
		
	    try {
	    	count = mDB.delete(table, where, whereArgs);
     	
	    } catch(Exception e) {
			Log.e("Exception", e.toString());
	    } finally{
	    	mDB.close();
	    }
		return count;
	}


	public int update(String table, ContentValues values, String where, String[] whereArgs) {
	    int count=0;
	    mDB = this.getWritableDatabase();
	    
	    try {
	    	count = mDB.update(table, values, where, whereArgs);
     	
	    } catch(Exception e) {
			Log.e("Exception", e.toString());
	    } finally{
	    	mDB.close();
	    }
	    return count;
	}     


	private HashMap getRow(Cursor c) {
		
		HashMap eo = null;
		String columnType = "";
		
		
		String[] arrCol = c.getColumnNames();
		eo = new HashMap();
		for(int i=0; i< arrCol.length; i++) {
			eo.put( (String)arrCol[i], (String)c.getString(i));
		}
		return eo;
		

	}


	public long initInsert(SQLiteDatabase db, String table, ContentValues _initialValues) {
		
		long rowID=0;
		
		try {
			rowID = db.insert(table, null, _initialValues);
     	
	    } catch(Exception e) {
			Log.e("Exception", e.toString());
	    } 
	    
		return rowID;
		
	}


}
