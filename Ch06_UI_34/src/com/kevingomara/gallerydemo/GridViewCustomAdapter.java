package com.kevingomara.gallerydemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class GridViewCustomAdapter extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridviewcustom);
        
        GridView gv = (GridView)findViewById(R.id.gridview);

        ManateeAdapter adapter = new ManateeAdapter(this);

        gv.setAdapter(adapter);
    }
    
    protected static class ManateeAdapter extends BaseAdapter {
    	
    	private static final String TAG = "ManateeAdapter";
    	private static int convertViewCounter = 0;
    	private Context mContext;
    	private LayoutInflater mInflater;
    	
    	static class ViewHolder {
    		ImageView image;
    	}
    	
    	private int[] manatees = {R.drawable.manatee00, R.drawable.manatee01, R.drawable.manatee02,
    							  R.drawable.manatee03, R.drawable.manatee04, R.drawable.manatee05,
    							  R.drawable.manatee06, R.drawable.manatee07, R.drawable.manatee08,
    							  R.drawable.manatee09, R.drawable.manatee10, R.drawable.manatee11,
    							  R.drawable.manatee12, R.drawable.manatee13, R.drawable.manatee14,
    							  R.drawable.manatee15, R.drawable.manatee16, R.drawable.manatee17,
    							  R.drawable.manatee18, R.drawable.manatee19, R.drawable.manatee20,
    							  R.drawable.manatee21, R.drawable.manatee22, R.drawable.manatee23,
    							  R.drawable.manatee24, R.drawable.manatee25, R.drawable.manatee26,
    							  R.drawable.manatee27, R.drawable.manatee28, R.drawable.manatee29,
    							  R.drawable.manatee30, R.drawable.manatee31, R.drawable.manatee32,
    							  R.drawable.manatee33 };
    	
    	private Bitmap[] manateeImages = new Bitmap[manatees.length];
    	private Bitmap[] manateeThumbs = new Bitmap[manatees.length];
    	
    	public ManateeAdapter(Context context) {
    		Log.v(TAG, "Constructing ManateeAdapter");
    		
    		this.mContext = context;
    		mInflater = LayoutInflater.from(context);
    		
    		for (int i = 0; i < manatees.length; i++) {
    			manateeImages[i] = BitmapFactory.decodeResource(context.getResources(), manatees[i]);
    			manateeThumbs[i] = Bitmap.createScaledBitmap(manateeImages[i], 100, 100, false);
    		}
    	}
    	
    	@Override
    	public int getCount() {
    		return manatees.length;
    	}
    	
    	@Override
    	public int getViewTypeCount() {
    		return 1;
    	}
    	
    	@Override
    	public int getItemViewType(int position) {
    		return 0;
    	}
    	
    	@Override
    	public View getView(int position, View convertView, ViewGroup parent) {
    		ViewHolder holder;
    		
    		Log.v(TAG, "in getView for position " + position + ", convertView is " 
    				+ ((convertView == null) ? "null" : "being recycled"));
    		
    		if (convertView == null) {
    			convertView = mInflater.inflate(R.layout.gridimage, null);
    			convertViewCounter++;
    			Log.v(TAG, convertViewCounter + " convertViews have been created");
    			
    			holder = new ViewHolder();
    			holder.image = (ImageView) convertView.findViewById(R.id.gridImageView);
    			
    			convertView.setTag(holder);
    		} else {
    			holder = (ViewHolder) convertView.getTag();
    		}
    		
    		holder.image.setImageBitmap(manateeThumbs[position]);
    		
    		return convertView;
    	}
    	
    	@Override
    	public Object getItem(int position) {
    		return manateeImages[position];
    	}
    	
    	@Override 
    	public long getItemId(int position) {
    		return position;
    	}
    }
}