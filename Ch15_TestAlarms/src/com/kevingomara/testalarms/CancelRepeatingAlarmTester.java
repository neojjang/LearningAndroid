package com.kevingomara.testalarms;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class CancelRepeatingAlarmTester extends SendRepeatingAlarmTester {
	private static final String TAG = "CancelRepeatingAlarmTester";
	
	CancelRepeatingAlarmTester(Context context, IReportBack target) {
		super(context, target);
	}
	
	/**
	 * An alarm can be stopped by cancelling the intent.
	 */
	public void cancelRepeatingAlarm() {
		// Get an intent to invoke TestReceiver class
		Intent intent = new Intent(this.mContext, TestReceiver.class);
		
		// To cancel extra is not necessary
		PendingIntent pendingIntent = this.getDistinctPendingIntent(intent, 2);
		
		// Cancel it!
		AlarmManager alarmMgr = (AlarmManager) this.mContext.getSystemService(Context.ALARM_SERVICE);
		alarmMgr.cancel(pendingIntent);
		this.mReportTo.reportBack(TAG, "You shouldn't see repeating alarms");
	}
}
