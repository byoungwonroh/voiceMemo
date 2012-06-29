package com.android.voicememo.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.Toast;


public class Util {
	
	/**
	 * NULL Check ??공백 문자 리턴
	 * @param str	문자??
	 * @return
	 */
	public static String nullCheck(String str) {
		
		if(str == null) {
			return "";
		} else {
			return str;
		}
	}
	public static String nullCheck(Object obj) {
		
		if(obj == null) {
			return "";
		} else {
			return String.valueOf(obj);
		}
	}
	
	/**
	 * Null 체크(Null이거나 값이 없으면 대체 값으로 변경)
	 * @param obj 체크할 Object
	 * @param defaultValue 대체 값
	 * @return STring
	 */
	public static String nullCheck(Object obj, String defaultValue) {
		String returnData = "";
		if(obj == null) {
			returnData = defaultValue;
		} else if(String.valueOf(obj).equals("")){
			returnData = defaultValue;
		} else {
			returnData = String.valueOf(obj);
		}
		
		return returnData;
	}
	
	/**
	 * NULL Check ??0  리턴
	 * @param str			
	 * @return
	 */
	public static int numCheck(String str) {
		
		if(str == null) {
			return 0;
		} else {
			return Integer.parseInt(str);
		}
	}	
	public static int numCheck(Object obj) {
		
		if(obj == null || obj.equals("")) {
			return 0;
		} else {
			return Integer.parseInt((String)obj);
		}
	}
	
	
	/**
	 * 공백 Check ??defaultValue�?리턴
	 * @param str			?�본 문자??
	 * @param defaultValue	??�� 문자??
	 * @return
	 */
	public static String nullCheck(String str, String defaultValue) {
		
		if(str == null || str.equals("")) {
			return defaultValue;
		} else {
			return str;
		}
		
	}
	
	/**
	 * ?�늘 ?�짜 반환 (yyyy.MM.dd)
	 * 
	 * @return
	 */
	public static String getToday() {

		String format="yyyyMMdd";
		SimpleDateFormat formatter = new SimpleDateFormat(format,
				java.util.Locale.KOREA);
		java.util.Date currentTime = new java.util.Date();
		String today = formatter.format(currentTime);

		return today;
	}
	

	/**
	 * ArrayList 출력
	 * 
	 */
	public static void printArrayList(ArrayList listData){
		Log.d("------------","printArrayList - Start");
		if(listData !=null && listData.size()>0){
			HashMap mapData;
			for(int i=0; i<listData.size(); i++) {
				mapData = (HashMap)listData.get(i);
				printHashMap(mapData);
				
			}
		}
		Log.d("------------","printArrayList - end");
	}
	/**
	 * HashMap 출력
	 * 
	 */
	public static void printHashMap(HashMap map){
		Set keySet = null;
		Iterator iter = null;
		
		keySet = map.keySet();
		iter = keySet.iterator();
		
		String keyParam = null;
		while(iter.hasNext()) {
			keyParam = (String)iter.next();
			Log.d("<----- " + keyParam + ": -----> ",  nullCheck(map.get(keyParam)));
		}		
		
	}

	/*
	 * 토스트 중앙으로 메제지 띠우기
	 *  * 
	 * */
	public static void commonToast(Context context,String message){
		
		   Toast toast = Toast.makeText(context,message,800);
		   int offsetX = 0;
		   int offsetY = 0;
		   toast.setGravity(Gravity.CENTER, offsetX, offsetY);
		   toast.show(); 
		
	}
	
	
	/**
	 * Toast 띄우기
	 * @param context Context
	 * @param message 메세지
	 * @param isLong 긴시간 띄울지 값
	 */
	public static void commonToast(Context context,String message, boolean isLong){
		int showTime = -1;
		if(isLong){
			showTime = Toast.LENGTH_SHORT;
		}
		Toast toast = Toast.makeText(context, message, showTime);
		//int offsetX = 0;
		//int offsetY = 0;
		//toast.setGravity(Gravity.CENTER, offsetX, offsetY);
		toast.show(); 
	}
	
	
	/*
	 * 현재 시간 구하기
	 *  * 
	 * */
	public static String getCurrentTime(){
		String time = "";
		java.util.Calendar cal = java.util.Calendar.getInstance();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		time = StringUtils.defaultString(sdf.format(cal.getTime()));
		
		return time;
	}
	
	/**
	 * Dip 변환 함수
	 * @param number 변환해야하는 Int 값
	 * @param context Context
	 * @return int
	 */
	public static int dipToInt(float number, Context context){
		int num = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, number, context.getResources().getDisplayMetrics());
		
		return num;
	}
	
	
	/**
	 * AlertDialog
	 * @param String 메세지
	 * @param context Context
	 * @return 
	 */
	public static void alertDialog(Context context,String msg) {
		AlertDialog.Builder alt_bld = new AlertDialog.Builder(context);
		alt_bld.setMessage(msg)
				.setCancelable(false).setPositiveButton("예",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								
							}
						});
		AlertDialog alert = alt_bld.create();
		alert.show();
	}
	
	
}