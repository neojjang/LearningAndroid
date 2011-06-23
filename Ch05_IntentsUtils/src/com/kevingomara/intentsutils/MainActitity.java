package com.kevingomara.intentsutils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActitity extends Activity {
	
	private final static String tag = "MainActivity";
	
	//Initialize this in onCreateOptions

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        
        TextView textView = new TextView(this);
        textView.setText("Use the Menu button to start a test");
        setContentView(R.layout.main);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	
    	super.onCreateOptionsMenu(menu);

    	MenuInflater menuInflater = this.getMenuInflater();
    	menuInflater.inflate(R.menu.main_menu,menu);
    	
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	
    	try {
    		handleMenus(item);
    	}
    	catch(Throwable t) {
    		Log.d(tag,t.getMessage(),t);
    		throw new RuntimeException("error",t);
    	}
    	
    	return true;
    }
    
    private void handleMenus(MenuItem item) {
    	
		this.appendMenuItemText(item);
		
		if (item.getItemId() == R.id.menu_clear) {
			this.emptyText();
		} else if (item.getItemId() == R.id.menu_show_browser) {
			IntentUtils.invokeWebBrowser(this);
		} else if (item.getItemId() == R.id.menu_dial) {
			IntentUtils.dial(this);
		} else if (item.getItemId() == R.id.menu_call) {
			IntentUtils.call(this);
		} else if (item.getItemId() == R.id.menu_map) {
			IntentUtils.showMapAtLatLong(this);
		} 
    }
    
    public void appendMenuItemText(MenuItem menuItem) {
    	
    	String title = menuItem.getTitle().toString();
       	TextView textView = (TextView)this.findViewById(R.id.textViewId);
       	textView.setText(textView.getText() + "\n" + title + ":" + menuItem.getItemId());
    }

    
    private void emptyText() {
    	
       	TextView textView = 
       		(TextView)this.findViewById(R.id.textViewId);
       	textView.setText("");
    }

}