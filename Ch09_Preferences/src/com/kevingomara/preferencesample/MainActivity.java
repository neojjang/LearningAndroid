package com.kevingomara.preferencesample;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private TextView textView = null;
	private Resources resources;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        resources = this.getResources();
        
        textView = (TextView) findViewById(R.id.text1);
        setOptionText();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.mainmenu, menu);
    	
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	
    	if (item.getItemId() == R.id.menu_prefs) {
    		// Launch the preferences screen
    		Intent intent = new Intent().setClass(this, 
    							com.kevingomara.preferencesample.FlightPreferenceActivity.class);
    		this.startActivityForResult(intent, 0);
    	}
    	
    	return true;
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	setOptionText();
    }
    
    private void setOptionText() {
    	SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
    	
    	String option = prefs.getString(resources.getString(R.string.selected_flight_sort_option), 
    									resources.getString(R.string.flight_sort_option_default_value));
    	String[] optionText = resources.getStringArray(R.array.flight_sort_options);
    	
    	textView.setText("option value is " + option + " (" + optionText[Integer.parseInt(option)] + ")");
    }
}