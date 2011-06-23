package com.kevingomara.ch06_ui_03;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private static final String TAG = "RadGrp";

	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        setNameAndAddress();
        setLanguageChooser();
        setMealChooser();
    }
    
    private void setNameAndAddress() {
        TextView nv = (TextView) findViewById(R.id.nameValue);
        nv.setText("John Doe");
        TextView av = (TextView) findViewById(R.id.addrValue);
        av.setText("123 Main Street");
    }
    
    private void setLanguageChooser() {
        AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.actv);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,
        								new String[] {"English", "Hebrew", "Hindi", "Spanish", "German", "Greek"});
        actv.setAdapter(adapter);
    }
    
    private void setMealChooser() {
    	    	
    	RadioGroup radGrp = (RadioGroup) findViewById(R.id.radGrp);
    	    	
    	radGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch(checkedId) {
				case -1:					// -1 returned if nothing checked
					Log.v(TAG, "Choices cleared!");
					break;
				case R.id.chRBtn:
					Log.v(TAG, "Chose Chicken");
					break;
				case R.id.fishRBtn:
					Log.v(TAG, "Chose Fish");
					break;
				case R.id.stkRBtn:
					Log.v(TAG, "Chose Steak");
					break;
				default:
					Log.v(TAG, "Error! Invalid checkedId returned.");
					break;
				}
			}
		});
    }
}