package com.kevingomara.testalarms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TestReceiver extends BroadcastReceiver {
	
	private static final String TAG = "TestReceiver";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d(TAG, "intent = " + intent);
		String message = intent.getStringExtra("message");
		Log.d(TAG, message);
	}
}
