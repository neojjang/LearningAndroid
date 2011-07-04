package com.kevingomara.testhandlers;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class TestHandlersDriverActivity extends Activity {
	
	private static final String TAG = "TestHandlersDriverActivity";
	private TextView textView = null;
    private DeferWorkHandler deferWorkHandler = null;
    private Handler statusBackHandler = null;
    private Thread workerThread = null;
    	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        textView = (TextView) findViewById(R.id.text1);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
    	
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.main_menu, menu);
    	
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
    	appendText(menuItem.getTitle().toString());
    	
    	switch (menuItem.getItemId()) {
    	case R.id.menu_clear:
    		textView.setText("");
    		return true;
    	case R.id.menu_test_thread:
    		this.testThread();
    		return true;
    	case R.id.menu_test_defered_handler:
    		this.testDeferedHandler();
    		return true;
    	default:
    		Log.e(TAG, "Error, unknown menuItem: " + menuItem.getItemId());
    		break;
    	}
    	return false;
    }
    
    public void appendText(String string) {
    	textView.setText(textView.getText() + "\n" + string);
    }
    
    private void testDeferedHandler() {
    	if (deferWorkHandler == null) {
    		deferWorkHandler = new DeferWorkHandler(this);
    		appendText("Creating a new handler");
    	}
    	appendText("Starting to do deferred work by sending messages");
    	deferWorkHandler.doDeferredWork();
    }
    
    private void testThread() {
    	if (statusBackHandler == null) {
    		statusBackHandler = new ReportStatusHandler(this);
    		workerThread = new Thread(new WorkerThreadRunnable(statusBackHandler));
    		workerThread.start();
    	}
    	
    	if (workerThread.getState() != Thread.State.TERMINATED) {
    		Log.d(TAG, "Thread is new or alive, but not terminated");
    	} else {
    		Log.d(TAG, "Thread is likely dead, starting anew");
    		workerThread = new Thread(new WorkerThreadRunnable(statusBackHandler));
    		workerThread.start();
    	}
    }
}