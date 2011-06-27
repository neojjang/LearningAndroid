package com.kevingomara.gridview;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.widget.GridView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview);
        
        // get a reference to our dataGrid layout
        GridView gridView = (GridView) findViewById(R.id.dataGrid);
        
        //set up a cursor into the Contacts content Provider
        Cursor cursor = managedQuery(Contacts.CONTENT_URI, null, null, null, ContactsContract.Contacts.DISPLAY_NAME);
        
        // setup the adapter and attach it to our gridView
        String[] cols = new String[] {ContactsContract.Contacts.DISPLAY_NAME};
        int[] views   = new int[] {android.R.id.text1};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, 
        								android.R.layout.simple_list_item_1,
        								cursor, cols, views);
        gridView.setAdapter(adapter);
    }
}