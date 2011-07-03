package com.kevingomara.downloadimage;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class MainActivity extends Activity {
	
	private DownloadImageTask downloadImageTask = null;
	
	private static final String DRAW_CHART = "http://chart.apis.google.com/chart?&cht=p&chs=460x250&chd=t:15.3,20.3,0.2,59.7,4.5&chl=Android%201.5%7CAndroid%202.1%7CAndroid%202.2&chco=c4df9b,6fad0c";
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Check to see if we're restarting with a background AsyncTask and re-establish the connection.
        // 		also, if the AsyncTask has finished, grab the image rather than re-load it.
        if ((downloadImageTask = (DownloadImageTask) getLastNonConfigurationInstance()) != null) {
        	downloadImageTask.setContext(this);
        	if (downloadImageTask.getStatus() == AsyncTask.Status.FINISHED) {
        		downloadImageTask.setImageInView();
        	}
        }
    }
    
    public void doClick(View view) {
    	
    	if (downloadImageTask != null) {
    		// a downloadImageTask is already running - let it finish!
    		AsyncTask.Status downloadStatus = downloadImageTask.getStatus();
    		Log.v("doClick", "downloadStatus is " + downloadStatus);
    		if (downloadStatus != AsyncTask.Status.FINISHED) {
    			Log.v("doClick", "... no need to start again");
    			return;
    		} else {
    			// downloadImageTask is finished, OK to fire off another
    		}
    	}
    	downloadImageTask = new DownloadImageTask(this);
    	
    	downloadImageTask.execute(DRAW_CHART);
    }
    
    @Override
    public Object onRetainNonConfigurationInstance() {
    	// called before onDestroy() - pass forward the reference to our AsyncTask
    	return downloadImageTask;
    }
}