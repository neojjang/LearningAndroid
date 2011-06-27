package com.kevingomara.listview;

//TODO update to new Contacts API

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends ListActivity  {
	
	private final static String TAG = "ListViewActivity";
	private ListView listView = null;
	private Cursor cursor = null;
	private int idCol = -1;
	private int nameCol = -1;
	private int notesCol = -1;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // set the contentView to use the ListView we defined
        setContentView(R.layout.list);
        
        // get a reference to the default ListView
        listView = getListView();
        
        // get a cursor to the list of contacts on the device, sorted by NAME
        cursor = managedQuery(People.CONTENT_URI, null, null, null, People.NAME);
        
        // set up the arrays of columns (just NAME) and their corresponding views
        String[] cols = new String[] {People.NAME};
        idCol = cursor.getColumnIndex(People._ID);
        nameCol = cursor.getColumnIndex(People.NAME);
        notesCol = cursor.getColumnIndex(People.NOTES);
        
        int views[] = new int[] {android.R.id.text1};
        
        // set up a cursor adapter and attach it to the default ListLiew
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, 
        								android.R.layout.simple_list_item_multiple_choice, 
        								cursor, cols, views);
        this.setListAdapter(adapter);
        
        // set the OnClick Listener for the default ListView
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }
    
    public void doClick(View view) {
    	
    	int count = listView.getCount();
    	
    	SparseBooleanArray viewItems = listView.getCheckedItemPositions();
    	
    	for (int i = 0; i < count; i++) {
    		if (viewItems.get(i)) {
    			cursor.moveToPosition(i);
    			long id = cursor.getLong(idCol);
    			String name = cursor.getString(nameCol);
    			String notes = cursor.getString(notesCol);
    			Log.v(TAG, name + "is checked. Notes: " + notes + ". Position = " + i + ". Id = " + id);
    		}
    	}
    }
}