package com.kevingomara.bcr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class TestBCRActivity extends Activity {
	
	private static final String TAG = "TestBCRActivity";
	private TextView textView = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        textView = (TextView) findViewById(R.id.text_view);
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
    	appendMenuItemText(menuItem);
    	
    	switch (menuItem.getItemId()) {
    	case R.id.menu_clear:
    		textView.setText("");
    		break;
    	case R.id.menu_menu_send_broadcast:
    		this.testSendBroadcast();
    		break;
    	}
    	
    	return true;
    }
    
    private void appendMenuItemText(MenuItem menuItem) {
    	String title = menuItem.getTitle().toString();
    	textView.setText(textView.getText() + "\n" + title);
    }
    
    private void testSendBroadcast() {
    	// Print out info about the thread we're running on
    	Utils.logThreadSignature(TAG);
    	
    	// Create an intent with an action and put it in extra
    	Intent broadcastIntent = new Intent("com.kevingomara.intents.testbc");
    	broadcastIntent.putExtra("message", "Hello from TestBCRActivity");
    	
    	// Send the broadcast
    	this.sendBroadcast(broadcastIntent);
    	
    	// Log a message which should appear before the Hello above!
    	Log.d(TAG, "after send broadcast from TestBCRActivity");
    }
}