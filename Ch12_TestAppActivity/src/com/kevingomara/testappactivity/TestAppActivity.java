package com.kevingomara.testappactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.kevingomara.testlibactivity.TestLibActivity;

public class TestAppActivity extends Activity {
	
	private static final String TAG = "TestAppActivity";
	
	private TextView mTextView;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mTextView = (TextView) findViewById(R.id.text1);
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
    		this.mTextView.setText("");
    		return true;
    	case R.id.menu_library_activity:
    		this.invokeLibraryActivity(menuItem.getItemId());
    		return true;
    	default:
    		// should not happen!
    		Log.e(TAG, "Error in menu, id = " + menuItem.getItemId());
    		break;
    	}
    	return false;
    }
    
    private void invokeLibraryActivity(int mid) {
    	Intent intent = new Intent(this, TestLibActivity.class);
    	intent.putExtra("com.ai.menuid", mid);
    	startActivity(intent);
    }
    
    private void appendMenuItemText(MenuItem menuItem) {
    	String title = menuItem.getTitle().toString();
    	mTextView.setText(mTextView.getText() + "\n" + title);
    }
}