package com.kevingomara.mapviewactivity;

import java.util.ArrayList;
import java.util.Iterator;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;


public class MainActivity extends MapActivity {
	
	private static final String TAG = "Overlays";
	
	private MapView mapView;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mapView = (MapView) findViewById(R.id.mapView);
        mapView.setBuiltInZoomControls(true);
        
        Drawable marker = getResources().getDrawable(R.drawable.mapmarker);
        marker.setBounds((int) (-marker.getIntrinsicWidth()/2), -marker.getIntrinsicHeight(), (int) (marker.getIntrinsicWidth()/2), 0);
        Log.v(TAG, "getting locations");
        
        InterestingLocations interestingLocations = new InterestingLocations(marker);
        mapView.getOverlays().add(interestingLocations);
        
        GeoPoint geoPoint	= interestingLocations.getCenterPoint();
        int latSpanE6 		= interestingLocations.getLatSpanE6();
        int lonSpanE6		= interestingLocations.getLonSpanE6();
        Log.v(TAG, "Lat span is: " + latSpanE6);
        Log.v(TAG, "Lon span is: " + lonSpanE6);
        
        MapController mapController = mapView.getController();
        mapController.setCenter(geoPoint);
        mapController.zoomToSpan((int) (latSpanE6 * 1.5), (int) (lonSpanE6 * 1.5));
    }
    
    @Override
    protected boolean isLocationDisplayed() {
    	return false;
    }
    
    @Override
    protected boolean isRouteDisplayed() {
    	return false;
    }
    
    protected class InterestingLocations extends ItemizedOverlay {
    	private ArrayList<OverlayItem> locations = new ArrayList<OverlayItem>();
    	private GeoPoint center = null;
    	
    	protected InterestingLocations(Drawable marker) {
    		super(marker);
    		
    		// Create locations of interest
    		GeoPoint disneyMagicKingdom = new GeoPoint((int) (28.418971 * 1000000), (int) (-81.581436 * 1000000));
    		GeoPoint disneySevenLagoon	= new GeoPoint((int) (28.410067 * 1000000), (int) (-81.583699 * 1000000));
    		locations.add(new OverlayItem(disneyMagicKingdom, "Magic Kingdom",     "Magic Kindom"));
    		locations.add(new OverlayItem(disneySevenLagoon,  "Seven Seas Lagoon", "Seven Seas Lagoon"));
    		populate();
    	}
    	
    	/**
    	 * Find the middle point of the cluster of interestingLocations
    	 */
    	public GeoPoint getCenterPoint() {
    		Log.v(TAG, "in getCenterPoint()");
    		Log.v(TAG, "center = " + center);
    		
    		if (center == null) {
    			// first time called, calculate it
    			int northEdge =  -90000000;					// i.e., -90E6 microdegrees
    			int southEdge =   90000000;
    			int eastEdge  = -180000000;
    			int westEdge  =  180000000;
    			Iterator<OverlayItem> iter = locations.iterator();
    			while(iter.hasNext()) {
    				GeoPoint pt = iter.next().getPoint();
    				int latitudeE6 	= pt.getLatitudeE6();
    				int longitudeE6 = pt.getLongitudeE6();
    				if (latitudeE6 > northEdge)
    					northEdge = latitudeE6;
    				if (latitudeE6 < southEdge)
    					southEdge = latitudeE6;
    				if (longitudeE6 > eastEdge)
    					eastEdge = longitudeE6;
    				if (longitudeE6 < westEdge)
    					westEdge = longitudeE6;
    			}
    			center = new GeoPoint((int)((northEdge + southEdge) / 2), (int)((westEdge + eastEdge) / 2));
    		}
    		Log.v(TAG, "center = " + center);
    		return center;
    	}
    	
    	@Override
    	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
    		// Hide the shadow by setting shadow to false
    		super.draw(canvas, mapView, shadow);
    	}
    	
    	@Override
    	protected OverlayItem createItem(int i) {
    		return locations.get(i);
    	}
    	
    	@Override
    	public int size() {
    		return locations.size();
    	}
    }
}