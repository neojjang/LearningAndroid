package com.kevingomara.proximity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;

public class MainActivity extends Activity {
	
	private static final String PROX_ALERT = "com.kevingomara.intent.action.PROXIMITY_ALERT";
	private ProximityReceiver proximityReceiver = null;
	private LocationManager locationMgr 		= null;
	private PendingIntent pendingIntent1 		= null;
	private PendingIntent pendingIntent2 		= null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        locationMgr = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        
        // Set up for 5 mile radius of Jacksonville, FL
        double lat =  30.334954;				
        double lon = -81.562500;
        float radius = 5.0f * 1609.0f;			// 5 miles * 1609 meters/mile
        
        String geo = "geo:" + lat + "," + lon;
        
        Intent intent = new Intent(PROX_ALERT, Uri.parse(geo));
        intent.putExtra("message", "Jacksonville, FL");
        
        pendingIntent1 = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        
        locationMgr.addProximityAlert(lat, lon, radius, -1L, pendingIntent1);
        
        // Set up for 5 mile radius of Orlando, FL
        lat =  28.540000;				
        lon = -81.380000;
        radius = 5.0f * 1609.0f;			// 5 miles * 1609 meters/mile
        
        geo = "geo:" + lat + "," + lon;
        
        intent = new Intent(PROX_ALERT, Uri.parse(geo));
        intent.putExtra("message", "Orlando, FL");
        
        pendingIntent2 = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        
        locationMgr.addProximityAlert(lat, lon, radius, -1L, pendingIntent2);
        
        proximityReceiver = new ProximityReceiver();
        
        IntentFilter intentFilter = new IntentFilter(PROX_ALERT);
        intentFilter.addDataScheme("geo");
        
        registerReceiver(proximityReceiver, intentFilter);
    }
    
    protected void onDestroy() {
    	super.onDestroy();
    	unregisterReceiver(proximityReceiver);
    	locationMgr.removeProximityAlert(pendingIntent1);
    	locationMgr.removeProximityAlert(pendingIntent2);
    }
}