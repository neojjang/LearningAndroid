package com.kevingomara.notificationreceiver;

import android.os.Bundle;
import android.util.Log;

public class Utils {
		
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

}
