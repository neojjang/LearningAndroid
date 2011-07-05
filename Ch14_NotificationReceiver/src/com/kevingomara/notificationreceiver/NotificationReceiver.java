package com.kevingomara.notificationreceiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class NotificationReceiver extends BroadcastReceiver {

	private static final String TAG = "NotificationReceiver";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Utils.logThreadSignature(TAG);
		Log.d(TAG, "intent = " + intent);
		String message = intent.getStringExtra("Message");
		Log.d(TAG, message);
		this.sendNotification(context, message);
	}
	
	private void sendNotification(Context context, String message) {
		// Get the Notification manager
		String notificationService = Context.NOTIFICATION_SERVICE;
		NotificationManager notificationMgr = (NotificationManager) context.getSystemService(notificationService);
		
		// Create Notification Object
		int icon = R.drawable.robot;
		CharSequence tickerText = "Hello";
		long when = System.currentTimeMillis();
		Notification notification = new Notification(icon, tickerText, when);
		
		// Set the ContentView using setLatestEventInfo
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("http://www.google.com"));
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
		notification.setLatestEventInfo(context, "title", "text", pendingIntent);
		
		// Send notification
		notificationMgr.notify(1, notification);
	}
}
