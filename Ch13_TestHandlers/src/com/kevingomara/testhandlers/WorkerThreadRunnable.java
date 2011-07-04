package com.kevingomara.testhandlers;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class WorkerThreadRunnable implements Runnable {
	
	public static final String TAG = "WorkerThreadRunnable";
	/*
	 * the handler to communicate with the main thread
	 */
	Handler statusBackMainThreadHandler = null;
	
	public WorkerThreadRunnable(Handler handler) {
		statusBackMainThreadHandler = handler;
	}
	
	public void run() {
		Log.d(TAG, "Start execution");
		Utils.logThreadSignature();
		
		// Tell parent that the worker thread has started working
		informStart();
		for (int i = 1; i < 5; i++) {
			Utils.sleepForInSecs(1);
			informMiddle(i);
		}
		informFinish();
	}
	
	public void informMiddle(int count) {
		Message msg = this.statusBackMainThreadHandler.obtainMessage();
		msg.setData(Utils.getStringAsABundle("done:" + count));
		this.statusBackMainThreadHandler.sendMessage(msg);
	}
	
	public void informStart() {
		Message msg = this.statusBackMainThreadHandler.obtainMessage();
		msg.setData(Utils.getStringAsABundle("starting run"));
		this.statusBackMainThreadHandler.sendMessage(msg);		
	}
	
	public void informFinish() {
		Message msg = this.statusBackMainThreadHandler.obtainMessage();
		msg.setData(Utils.getStringAsABundle("finishing run"));
		this.statusBackMainThreadHandler.sendMessage(msg);		
	}
}
