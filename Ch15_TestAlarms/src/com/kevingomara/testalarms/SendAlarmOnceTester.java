package com.kevingomara.testalarms;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class SendAlarmOnceTester extends BaseTester {
	
	private static final String TAG = "SendAlarmOnceTester";
	
	SendAlarmOnceTester(Context context, IReportBack target) {
		super(context, target);
	}
	
	/**
	 * An alarm can invoke a broadcast request at a specfic time
	 * 
	 * The name of the broadcast receiver is explicitly specified in the intent.
	 */
	public void sendAlarmOnce() {
		// Get the instance in time that is 30 seconds from now
		Calendar calendar = Utils.getTimeAfterInSecs(30);
		
		// Print to the debug view that we are scheduling at a specific time
		String string = Utils.getDateTimeString(calendar);
		mReportTo.reportBack(TAG, "Scheduling alarm at: " + string);
		
		// Get an intent to invoke a receiver
		Intent intent = new Intent(mContext, TestReceiver.class);
		intent.putExtra("message", "Single Shot Alarm");
		
		PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 1, intent, PendingIntent.FLAG_ONE_SHOT);
		
		// Schedule the alarm
		AlarmManager alarmMgr = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
		alarmMgr.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
	}
}
