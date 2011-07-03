package com.kevingomara.testlibactivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class TestLibActivity extends Activity {
	
	public static final String TAG = "TestLibActivity";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
    	
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.lib_main_menu, menu);
    	
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	appendMenuItemText(item);
    	if (item.getItemId() == R.id.menu_clear) {
    		this.emptyText();
    		return true;
    	}
    	
    	return true;
    }
    
    private TextView getTextView() {
    	return (TextView) this.findViewById(R.id.text1);
    }
    
    public void appendText(String string) {
    	TextView textView = getTextView();
    	textView.setText(textView.getText() + "\n" + string);
    }
    
    private void appendMenuItemText(MenuItem menuItem) {
    	String title = menuItem.getTitle().toString();
    	TextView textView = getTextView();
    	textView.setText(textView.getText() + "\n" + title);
    }
    
    private void emptyText() {
    	TextView textView = getTextView();
    	textView.setText("");
    }
}