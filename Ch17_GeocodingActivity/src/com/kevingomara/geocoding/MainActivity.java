package com.kevingomara.geocoding;

import java.io.IOException;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

public class MainActivity extends MapActivity {
	
	Geocoder geocoder				= null;
	MapView mapView					= null;
	ProgressDialog progressDialog 	= null;
	List<Address> addressList		= null;
	
	@Override
	protected boolean isLocationDisplayed() {
		return false;
	}
	
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mapView = (MapView) findViewById(R.id.mapView);
        mapView.setBuiltInZoomControls(true);
        
        // default to lat/log of Jacksonville, Fl
        int lat = (int) ( 30.334954 * 1000000);
        int lon = (int) (-81.562500 * 1000000);
        GeoPoint point = new GeoPoint(lat, lon);
        mapView.getController().setZoom(10);
        mapView.getController().setCenter(point);
        
        geocoder = new Geocoder(this);
    }
    
    public void doClick(View view) {
    	EditText location = (EditText) findViewById(R.id.location);
    	String locationName = location.getText().toString();
    	
    	progressDialog = ProgressDialog.show(MainActivity.this, "Processing...", "Finding location...", true, false);
    	
    	findLocation(locationName);
    }
    
    private void findLocation(final String locationName) {
    	Thread thread = new Thread() {
    		public void run() {
    			try {
    				// do background work
    				addressList = geocoder.getFromLocationName(locationName, 5);
    				uiCallback.sendEmptyMessage(0);
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
    	};
    	thread.start();
    }
    
    private Handler uiCallback = new Handler() {
    	@Override
    	public void handleMessage(Message message) {
    		// tear down dialog
    		progressDialog.dismiss();
    		if (addressList != null && addressList.size() > 0) {
    			int lat = (int) (addressList.get(0).getLatitude()  * 1000000);
    			int lon = (int) (addressList.get(0).getLongitude() * 1000000);
    			GeoPoint point = new GeoPoint(lat, lon);
    			mapView.getController().setZoom(15);
    			mapView.getController().animateTo(point);
    		} else {
    			Dialog foundNothingDialog = new AlertDialog.Builder(MainActivity.this)
    											.setIcon(0)
    											.setTitle("Failed to Find location")
    											.setPositiveButton("Ok", null)
    											.setMessage("Location Not Found...")
    											.create();
    			foundNothingDialog.show();
    		}
    	}
    };
}