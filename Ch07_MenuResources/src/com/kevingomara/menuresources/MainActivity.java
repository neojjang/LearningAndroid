package com.kevingomara.menuresources;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.main_menu, menu);
    	
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	// show the item selected
    	this.appendMenuItemText(item);
    	
    	// handle the menu item
    	switch (item.getItemId()) {
    	case R.id.menu_clear:
    		this.emptyText();
    		break;
    	case R.id.menu_dial:
    		// TODO add an intent to dial
    		break;
    	case R.id.menu_testPick:
    		// TODO add a picker to test something
    		break;
    	case R.id.menu_testGetContent:
    		// TODO add an intent to get some content
    		break;
    	case R.id.menu_show_browser:
    		// TODO add an intent to launch web browser
    		break;
    	}
    	return true;
    }
    
    private void appendMenuItemText(MenuItem item) {
    	String title = item.getTitle().toString();
    	TextView textView = (TextView) findViewById(R.id.textViewId);
    	textView.setText(textView.getText() + "\n" + title);
    }
    
    private void emptyText() {
    	TextView textView = (TextView) findViewById(R.id.textViewId);
    	textView.setText("");
    }
}