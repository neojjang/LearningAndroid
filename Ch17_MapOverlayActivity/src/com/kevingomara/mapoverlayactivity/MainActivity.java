package com.kevingomara.mapoverlayactivity;

import android.os.Bundle;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

public class MainActivity extends MapActivity {
	
	MapView mapView 					= null;
	MapController mapController 		= null;
	MyLocationOverlay myLocationOverlay = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Setup & initialize up the mapView
        mapView = (MapView) findViewById(R.id.mapView);
        mapView.setBuiltInZoomControls(true);
        
        // Setup & initialize the mapController
        mapController = mapView.getController();
        mapController.setZoom(18);
        
        // Setup & initialize the myLocationOverlay
        myLocationOverlay = new MyCustomLocationOverlay(this, mapView);
        mapView.getOverlays().add(myLocationOverlay);
        mapView.postInvalidate();
    }
    
    @Override
    protected boolean isLocationDisplayed() {
    	return myLocationOverlay.isMyLocationEnabled();
    }
    
    @Override
    protected boolean isRouteDisplayed() {
    	return false;
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	
    	myLocationOverlay.enableMyLocation();
    	myLocationOverlay.runOnFirstFix(new Runnable() {
    		public void run() {
    			mapController.setCenter(myLocationOverlay.getMyLocation());
    		}
    	});
    }
    
    @Override
    public void onPause() {
    	super.onPause();
    	
    	myLocationOverlay.disableMyLocation();
    }
}