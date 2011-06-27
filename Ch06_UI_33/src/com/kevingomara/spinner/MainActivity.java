package com.kevingomara.spinner;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends Activity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spinner);
        
        // get the Spinner layout 
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, 
        										R.array.planets, 
        										android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        spinner.setAdapter(adapter);
     }
}