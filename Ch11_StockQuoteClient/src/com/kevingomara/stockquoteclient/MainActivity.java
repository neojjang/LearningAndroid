package com.kevingomara.stockquoteclient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.kevingomara.stockquoteservice.IStockQuoteService;

public class MainActivity extends Activity {
	
	private static final String TAG = "StockQuoteClient";
	private IStockQuoteService stockService = null;
	private ToggleButton bindBtn;
	private Button callBtn;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // save references to the buttons as they are manipulated throughout the code
        bindBtn = (ToggleButton) findViewById(R.id.bindBtn);
        callBtn = (Button) findViewById(R.id.callBtn);
    }
    
    public void doClick(View view) {
    	switch (view.getId()) {
    	case R.id.bindBtn:
    		if (((ToggleButton) view).isChecked()) {
    			// ready to bind to the service - do it!
    			bindService(new Intent(IStockQuoteService.class.getName()), serConn, Context.BIND_AUTO_CREATE);
    		} else {
    			// need to unbind from the service
    			unbindService(serConn);
    			callBtn.setEnabled(false);
    		}
    		break;
    	case R.id.callBtn:
    		callService();
    		break;
    	}
    }
    
    private void callService() {
    	try {
    		double value = stockService.getQuote("Android");
    		Toast.makeText(MainActivity.this, "Value from service is " + value, Toast.LENGTH_SHORT).show();
    	} catch (RemoteException ee) {
    		Log.e("MainActivity", ee.getMessage(), ee);
    	}
    }
    
    private ServiceConnection serConn = new ServiceConnection() {
    	@Override
    	public void onServiceConnected(ComponentName name, IBinder service) {
    		Log.v(TAG, "onServiceConnected() called");
    		stockService = IStockQuoteService.Stub.asInterface(service);
    		bindBtn.setChecked(true);
    		callBtn.setEnabled(true);
    	}
    	
    	@Override
    	public void onServiceDisconnected(ComponentName name) {
    		// This callback is NOT called when we unbind from the service.  It is called when a disconnect
    		// happens for some reason - e.g., the service crashed.  If Android restarts the service,
    		// onServiceConnected() will be called and we're off and running again.
    		Log.v(TAG, "onServiceDisconnected() called");
    		bindBtn.setChecked(false);
    		callBtn.setEnabled(false);
    		stockService = null;
    	}
    };
    
    protected void onDestroy() {
    	Log.v(TAG, "onDestroy() called");
    	if (callBtn.isEnabled()) {
    		unbindService(serConn);
    	}
    	super.onDestroy();
    }
}