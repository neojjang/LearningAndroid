package com.kevingomara.testalarms;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class ScheduleDistinctIntentsTester extends CancelRepeatingAlarmTester {
	private static final String TAG = "ScheduleDistinctIntentsTester";
	
	ScheduleDistinctIntentsTester(Context context, IReportBack target) {
		super(context, target);
	}
	
	/**
	 * The same Intent cannot be scheduled multiple times.
	 * If you do, only the last one will take effect.
	 */
	public void scheduleSameIntentMultipleTimes() {
		// Get multiple time instances
		Calendar calendar  = Utils.getTimeAfterInSecs(30);
		Calendar calendar2 = Utils.getTimeAfterInSecs(35);
		Calendar calendar3 = Utils.getTimeAfterInSecs(40);
		Calendar calendar4 = Utils.getTimeAfterInSecs(45);
		
		String startTime = Utils.getDateTimeString(calendar);
		mReportTo.reportBack(TAG, "Scheduling multi-alarms at: " + startTime);
		
		// Get the Intent to invoke the receiver
		Intent intent = new Intent(mContext, TestReceiver.class);
		intent.putExtra("message", "Same Intent multiple times");
		
		PendingIntent pendingIntent = this.getDistinctPendingIntent(intent, 1);
		
		// Schedule the same intent multiple times
		AlarmManager alarmMgr = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
		
		alarmMgr.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),  pendingIntent);
		alarmMgr.set(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(), pendingIntent);
		alarmMgr.set(AlarmManager.RTC_WAKEUP, calendar3.getTimeInMillis(), pendingIntent);
		alarmMgr.set(AlarmManager.RTC_WAKEUP, calendar4.getTimeInMillis(), pendingIntent);
	}
	
	/**
	 * The same Intent can be scheduled multiple times if you change the RequestId
	 */
	public void scheduleDistinctIntents() {
		// Get multiple time instances
		Calendar calendar  = Utils.getTimeAfterInSecs(30);
		Calendar calendar2 = Utils.getTimeAfterInSecs(35);
		Calendar calendar3 = Utils.getTimeAfterInSecs(40);
		Calendar calendar4 = Utils.getTimeAfterInSecs(45);
		
		String startTime = Utils.getDateTimeString(calendar);
		mReportTo.reportBack(TAG, "Scheduling multi-alarms at: " + startTime);
		
		// Get the Intent to invoke the receiver
		Intent intent = new Intent(mContext, TestReceiver.class);
		intent.putExtra("message", "Same Intent multiple times");
				
		// Schedule the same intent but with different Request Id
		AlarmManager alarmMgr = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
		
		alarmMgr.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),  this.getDistinctPendingIntent(intent, 1));
		alarmMgr.set(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(), this.getDistinctPendingIntent(intent, 2));
		alarmMgr.set(AlarmManager.RTC_WAKEUP, calendar3.getTimeInMillis(), this.getDistinctPendingIntent(intent, 3));
		alarmMgr.set(AlarmManager.RTC_WAKEUP, calendar4.getTimeInMillis(), this.getDistinctPendingIntent(intent, 4));

	}
}
