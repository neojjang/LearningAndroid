package com.kevingomara.bcr;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TestTimeDelayReceiver extends BroadcastReceiver {
	private static final String TAG = "TestTimeDelayReceiver";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Utils.logThreadSignature(TAG);
		Log.d(TAG, "intent = " + intent);
		Log.d(TAG, "going to sleep for 2 secs");
		Utils.sleepForInSecs(2);
		Log.d(TAG, "wake up");
		String message = intent.getStringExtra("message");
		Log.d(TAG, message);
	}
}
