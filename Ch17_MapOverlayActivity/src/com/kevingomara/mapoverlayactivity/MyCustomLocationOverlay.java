package com.kevingomara.mapoverlayactivity;

import android.content.Context;
import android.location.Location;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

public class MyCustomLocationOverlay extends MyLocationOverlay {
	
	MapView mMapView = null;
	
	public MyCustomLocationOverlay(Context context, MapView mapView) {
		super(context, mapView);
		mMapView = mapView;
	}
	
	public void onLocationChanged(Location location) {
		super.onLocationChanged(location);
		
		GeoPoint point = new GeoPoint((int) (location.getLatitude() * 1E6), (int) (location.getLongitude() * 1E6));
		mMapView.getController().animateTo(point);
	}

}
