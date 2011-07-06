package com.kevingomara.testalarms;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.os.Bundle;
import android.util.Log;

public class Utils {
	
	private static final String TAG = "Thread Utils";
	
	public static long getThreadId() {
		Thread thread = Thread.currentThread();
		return thread.getId();
	}
	
	public static String getThreadSignature() {
		Thread thread 			= Thread.currentThread();
		long threadId 			= thread.getId();
		String threadName 		= thread.getName();
		long threadPriority 	= thread.getPriority();
		String threadGroupName 	= thread.getThreadGroup().getName();
		
		return (threadName 
					+ ":(id)" + threadId 
					+ ":(priority)" + threadPriority
					+ ":(group)" + threadGroupName);
	}
	
	public static void logThreadSignature(String tag) {
		Log.d(tag, getThreadSignature());
	}
	
	public static void sleepForInSecs(int secs) {
		try {
			Thread.sleep(secs * 1000); 
		} catch (InterruptedException ex) {
			throw new RuntimeException("interrupted", ex);
		}
	}
	
	public static Bundle getStringAsABundle(String message) {
		Bundle bundle = new Bundle();
		bundle.putString("message", message);
		return bundle;
	}
	
	public static String getStringFromABundle(Bundle bundle) {
		return bundle.getString("message");
	}
	
	public static Calendar getTimeAfterInSecs(int secs) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, secs);
		
		return calendar;
	}
	
	public static Calendar getCurrentTime() {
		Calendar calendar = Calendar.getInstance();
		
		return calendar;
	}
	
	public static Calendar getTodayAt(int hours) {
		Calendar today = Calendar.getInstance();
		Calendar calendar = Calendar.getInstance();
		
		int year	= today.get(Calendar.YEAR);
		int month	= today.get(Calendar.MONTH);
		// represents the day of the month
		int day		= today.get(Calendar.DATE);
		calendar.set(year, month, day, hours, 0, 0);
		
		return calendar;
	}
	
	public static String getDateTimeString(Calendar calendar) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy hh:mm:ss");
		dateFormat.setLenient(false);
		String string = dateFormat.format(calendar.getTime());
		
		return string;
	}

}
