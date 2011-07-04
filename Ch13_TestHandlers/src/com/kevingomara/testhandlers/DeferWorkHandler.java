package com.kevingomara.testhandlers;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class DeferWorkHandler extends Handler {
	
	public static final String TAG = "DeferWorkHandler";
	
	// Keep track of how many times we sent the message
	private int count = 0;
	
	// Parent driver activity to whom we can inform status
	private TestHandlersDriverActivity parentActivity = null;
	
	/**
	 * Take in the Parent Activity
	 * 
	 * @param TestHandlersDriverActivity inParentActivity
	 */
	public DeferWorkHandler(TestHandlersDriverActivity inParentActivity) {
		parentActivity = inParentActivity;
	}
	
	@Override
	public void handleMessage(Message message) {
		String printMsg = new String("message called:" + count + ":" + message.getData().getString("message"));
		
		Log.d(TAG, printMsg);
		this.printMessage(printMsg);
		
		if (count > 5) {
			return;
		}
		count++;
		sendTestMessage(1);
	}
	
	public void sendTestMessage(long interval) {
		Message message = this.obtainMessage();
		prepareMessage(message);
		this.sendMessageDelayed(message, interval * 1000);
	}
	
	public void doDeferredWork() {
		count = 0;
		sendTestMessage(1);
	}
	
	public void prepareMessage(Message message) {
		Bundle bundle = new Bundle();
		bundle.putString("message", "Hello World");
		message.setData(bundle);
	}
	
	/**
	 * Print a message in the parentActivity text window
	 */
	private void printMessage(String string) {
		parentActivity.appendText(string);
	}
}
