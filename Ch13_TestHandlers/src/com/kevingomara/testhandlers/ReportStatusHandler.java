package com.kevingomara.testhandlers;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class ReportStatusHandler extends Handler {

	private static final String TAG = "ReportStatusHandler";
	
	/**
	 * Holder for the parentDriverActivity
	 */
	private TestHandlersDriverActivity parentTestHandlersDriverActivity = null;
	
	public ReportStatusHandler(TestHandlersDriverActivity inParentActivity) {
		parentTestHandlersDriverActivity = inParentActivity;
	}
	
	@Override
	public void handleMessage(Message msg) {
		// Get string data from the message
		String parentMsg = Utils.getStringFromABundle(msg.getData());
		Log.d(TAG, parentMsg);
		
		// Tell the parent activity that something happened
		this.printMessage(parentMsg);
		
		// Assert this runs on the main Thread
		Utils.logThreadSignature();
	}
	
	private void printMessage(String string) {
		parentTestHandlersDriverActivity.appendText(string);
	}
}
