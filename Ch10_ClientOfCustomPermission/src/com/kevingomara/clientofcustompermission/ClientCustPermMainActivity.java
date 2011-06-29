package com.kevingomara.clientofcustompermission;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ClientCustPermMainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void doClick(View view) {
    	Intent intent = new Intent();
    	intent.setClassName("com.kevingomara.custompermission", "com.kevingomara.custompermission.PrivActivity");
    	startActivity(intent);
    }
}