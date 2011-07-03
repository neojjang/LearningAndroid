package com.kevingomara.translatedemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	
	private static final String TAG = "Translator";
	private EditText inputText = null;
	private TextView outputText = null;
	private Spinner fromLanguage = null;
	private Spinner toLanguage = null;
	private Button translateBtn = null;
	private String[] languageShortNames = null;
	private Handler mHandler = new Handler();
	
	private ITranslate mTranslateService = null;
	
	// Implement the ServiceConnection with required callbacks
	private ServiceConnection mTranslateConn = new ServiceConnection() {
		public void onServiceConnected(ComponentName name, IBinder service) {
			mTranslateService = ITranslate.Stub.asInterface(service);
			if (mTranslateService != null) {
				// all is well
				translateBtn.setEnabled(true);
			} else {
				// failed to establish service
				Log.e(TAG, "Unable to acquire TranslateService");
			}
		}
		
		public void onServiceDisconnected(ComponentName name) {
			translateBtn.setEnabled(false);
			mTranslateService = null;
		}
	};
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Get references to the layout elements
        inputText 		= (EditText) findViewById(R.id.input);
        outputText 		= (EditText) findViewById(R.id.translation);
        fromLanguage	= (Spinner)  findViewById(R.id.from);
        toLanguage		= (Spinner)  findViewById(R.id.to);
        translateBtn 	= (Button)   findViewById(R.id.translateBtn);
        // Set the onClickListener() for the translate button
        translateBtn.setOnClickListener(this);
        
        // Get the language strings from the resources
        languageShortNames = getResources().getStringArray(R.array.language_values);
        
        // Set up the from Spinner
        ArrayAdapter<?> fromAdapter = ArrayAdapter.createFromResource(this, 
        								R.array.languages, android.R.layout.simple_spinner_item);
        fromAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        fromLanguage.setAdapter(fromAdapter);
        fromLanguage.setSelection(1);				// default to English
        
        // Set up the to Spinner
        ArrayAdapter<?> toAdapter = ArrayAdapter.createFromResource(this, 
										R.array.languages, android.R.layout.simple_spinner_item);
        toAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        toLanguage.setAdapter(toAdapter);
        toLanguage.setSelection(3);					// default to German
        
        inputText.selectAll();						// select all the hint text for user convenience
        
        // Bind to the translation service
        Intent intent = new Intent(Intent.ACTION_VIEW);
        bindService(intent, mTranslateConn, Context.BIND_AUTO_CREATE);
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	unbindService(mTranslateConn);
    }
    
    public void onClick(View view) {
    	if (inputText.getText().length() > 0) {
    		doTranslate();
    	}
    }
    
    private void doTranslate() {
    	// Translation could take awhile (or fail),
    	// so fire it off in a separate thread
    	mHandler.post(new Runnable() {
    		public void run() {
    			String result = "";
    			try {
    				int fromPosition = fromLanguage.getSelectedItemPosition();
    				int toPosition	 = toLanguage.getSelectedItemPosition();
    				String input	 = inputText.getText().toString();
    				if (input.length() > 5000) {
    					// Google terms of service only allows 5000 characters max
    					input = input.substring(0, 5000);
    				}
    				Log.v(TAG, "Translating from " + languageShortNames[fromPosition] + 
    							" to " + languageShortNames[toPosition]);
    				result = mTranslateService.translate(input, languageShortNames[fromPosition], 
    							languageShortNames[toPosition]);
    				if (result == null) {
    					throw new Exception("Failed to get a translation");
    				}
    				outputText.setText(result);
    				inputText.selectAll();
    			} catch (Exception e) {
    				Log.e(TAG, "Error: " + e.getMessage());
    			}
    		}
    	});
    }
}