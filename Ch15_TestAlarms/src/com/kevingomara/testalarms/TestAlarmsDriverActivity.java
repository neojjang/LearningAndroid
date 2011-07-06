package com.kevingomara.testalarms;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class TestAlarmsDriverActivity extends Activity implements IReportBack {
	
	private static final String TAG = "TestAlarmsDriverActivity";
	
	private SendAlarmOnceTester alarmTester = null;
	private TextView textView = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        alarmTester = new SendAlarmOnceTester(this, this);
        textView = (TextView) findViewById(R.id.textView);
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
    	case R.id.menuClear:
    		textView.setText("");
    		break;
    	case R.id.menuAlarmOnce:
    		alarmTester.sendAlarmOnce();
    		break;
    	default:
    		Log.d(TAG, "menuItem not implemented = " + menuItem.getItemId());
    		break;
    	}
    	
    	return true;
    }
    
    public void reportBack(String tag, String message) {
    	this.appendText(tag + ":" + message);
    	Log.d(TAG, message);
    }
    
    private void appendMenuItemText(MenuItem menuItem) {
    	String title = menuItem.getTitle().toString();
    	appendText(title);
    }
    
    private void appendText(String text) {
    	textView.setText(textView.getText() + "\n" + text);
    	Log.d(TAG, text);
    }
}