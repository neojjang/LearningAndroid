package com.kevingomara.testalarms;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class SendRepeatingAlarmTester extends SendAlarmOnceTester {
	private static final String TAG = "SendRepeatingAlarmTester";
	
	SendRepeatingAlarmTester(Context context, IReportBack target) {
		super(context, target);
	}
	
	/**
	 * An alarm can invoke a broadcast receiver starting at a specified time and frequency
	 * 
	 * Uses the same intent as SendAlarmOnceTester but a distinct request Id
	 * 
	 * Uses getDistinctPendingIntent() utility
	 */
	public void sendRepeatingAlarm() {
		Calendar calendar = Utils.getTimeAfterInSecs(30);
		// Calendar calendar = Utils.getTodayAt(11);			// set an absolute start time
		String startTime = Utils.getDateTimeString(calendar);
		this.mReportTo.reportBack(TAG, "Scheduling Repeating alarm in 5 sec interval starting at: " + startTime);
		
		// Get Intent to invoke receiver
		Intent intent = new Intent(this.mContext, TestReceiver.class);
		intent.putExtra("message", "Repeating Alarm");
		
		PendingIntent pendingIntent = this.getDistinctPendingIntent(intent, 2);
		// Schedule it!
		AlarmManager alarmMgr = (AlarmManager) this.mContext.getSystemService(Context.ALARM_SERVICE);
		
		alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 5*1000, pendingIntent);
	}
	
	protected PendingIntent getDistinctPendingIntent(Intent intent, int requestId) {
		PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, requestId, intent, 0);
		
		return pendingIntent;
	}
}
