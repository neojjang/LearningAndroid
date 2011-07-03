package com.kevingomara.localservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {
	
	private static final String TAG = "MainActvity";
	
	private int counter = 1;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void doClick(View view) {
    	switch (view.getId()) {
    		case R.id.startBtn:
    			Log.v(TAG, "Starting service, counter = " + counter);
    			Intent intent = new Intent(MainActivity.this, BackgroundService.class);
    			intent.putExtra("counter", counter++);
    			startService(intent);
    			break;
    		case R.id.stopBtn:
    			stopService();
    			break;
    	}
    }
    
    private void stopService() {
    	Log.v(TAG, "Stopping service");
    	if (stopService(new Intent(MainActivity.this, BackgroundService.class))) {
    		Log.v(TAG, "stopService was successful");
    	} else {
    		Log.v(TAG, "stopService failed");
    	}
    }
    
    @Override
    public void onDestroy() {
    	stopService();
    	super.onDestroy();
    }
}