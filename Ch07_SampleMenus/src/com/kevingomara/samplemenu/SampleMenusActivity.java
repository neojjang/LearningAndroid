package com.kevingomara.samplemenu;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.TextView;

public class SampleMenusActivity extends Activity {
	
	Menu myMenu = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // register the textView for a context menu
        TextView textView = (TextView) findViewById(R.id.textViewId);
        registerForContextMenu(textView);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	// invoke super to attach any system level menus
    	super.onCreateOptionsMenu(menu);
    	
    	// store a reference to the menu
    	this.myMenu = menu;
    	
    	// add regular and secondary menu items
    	addRegularMenuItems(menu);
    	addSecondaryMenuItems(menu);
    	
    	// add subMenu
    	addSubMenuItems(menu);
    	
    	// return true to cause the menu to show
    	return true;
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo) {
    	menu.setHeaderTitle("Sample Context Menu");
    	menu.add(200, 200, 200, "item 1");
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	if (item.getItemId() == 200) {
    		Log.v("contextSelection", "handling");
    		return true;
    	} else {
    		Log.v("contextSelection", "error itemId = " + item.getItemId());
    		return false;
    	}
    }
    
    private void addRegularMenuItems(Menu menu) {
    	int base = Menu.FIRST;
    	
    	menu.add(base, base, base, "append");		// set GroupId, itemId, order, and title
    	menu.add(base, base+1, base+1, "item2");
    	menu.add(base, base+2, base+2, "clear");
    	
    	menu.add(base, base+3, base+3, "hide secondary");
    	menu.add(base, base+4, base+4, "show secondary");
    	
    	menu.add(base, base+5, base+5, "enable secondary");
    	menu.add(base, base+6, base+6, "disable secondary");
    	
    	menu.add(base, base+7, base+7, "check secondary");
    	menu.add(base, base+8, base+8, "uncheck secondary");
    }
    
    private void addSecondaryMenuItems(Menu menu) {
    	int base = Menu.CATEGORY_SECONDARY;
    	
    	menu.add(base, base+1, base+1, "sec. item 1");
    	menu.add(base, base+2, base+2, "sec. item 2");
    	menu.add(base, base+3, base+3, "sec. item 3");
    	menu.add(base, base+4, base+4, "sec. item 4");
    	menu.add(base, base+5, base+5, "sec. item 5");
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	
    	switch (item.getItemId()) {
    	case 1:						// append
    		appendText("\nHello");
    		break;
    	case 2:						// item2
    		appendText("\nitem2");
    		break;
    	case 3:						// clear
    		emptyText();
    		break;
    	case 4:						// hide secondary
    		this.appendMenuItemText(item);
    		this.myMenu.setGroupVisible(Menu.CATEGORY_SECONDARY, false);
    		break;
    	case 5:						// show secondary
    		this.appendMenuItemText(item);
    		this.myMenu.setGroupVisible(Menu.CATEGORY_SECONDARY, true);
    		break;
    	case 6:						// enable secondary
    		this.appendMenuItemText(item);
    		this.myMenu.setGroupEnabled(Menu.CATEGORY_SECONDARY, true);
    		break;
    	case 7:						// disable secondary
    		this.appendMenuItemText(item);
    		this.myMenu.setGroupEnabled(Menu.CATEGORY_SECONDARY, false);
    		break;
    	case 8:						// check secondary
    		this.appendMenuItemText(item);
    		this.myMenu.setGroupCheckable(Menu.CATEGORY_SECONDARY, true, false);
    		break;
    	case 9:						// uncheck secondary
    		this.appendMenuItemText(item);
    		this.myMenu.setGroupCheckable(Menu.CATEGORY_SECONDARY, false, false);
    		break;
    	default:
    		this.appendText("\n" + item.getTitle() + " is a secondary MenuItem");
    		break;
    	}
    	
    	return true;
    }
    
    private void appendText(String text) {
    	TextView textView = (TextView) findViewById(R.id.textViewId);
    	textView.setText(textView.getText() + text);
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
    
    private void addSubMenuItems(Menu menu) {
    	int base = Menu.FIRST+50;
    	
    	SubMenu subMenu = menu.addSubMenu(base, base+1, Menu.NONE, "submenu");
    	subMenu.add(base, base+2, base+2, "sub item1");
    	subMenu.add(base, base+3, base+3, "sub item2");
    	subMenu.add(base, base+4, base+4, "sub item3");
    }
}