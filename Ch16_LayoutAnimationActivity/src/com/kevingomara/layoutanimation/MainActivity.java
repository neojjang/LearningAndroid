package com.kevingomara.layoutanimation;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        setupListView();
    }
    
    private void setupListView() {
    	String[] listItems = new String[] {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6"};
    	
    	ArrayAdapter listItemAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
    	
    	ListView listView = (ListView) this.findViewById(R.id.listView1);
    	listView.setAdapter(listItemAdapter);
    }
}