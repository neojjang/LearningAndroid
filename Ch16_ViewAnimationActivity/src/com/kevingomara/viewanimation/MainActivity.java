package com.kevingomara.viewanimation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        setupListView();
        this.setupButton();
    }
    
    private void setupListView() {
    	String[] listItems = new String[] {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6"};
    	
    	ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
    	ListView listView = (ListView) findViewById(R.id.listView1);
    	listView.setAdapter(adapter);
    }
    
    private void setupButton() {
    	Button button = (Button) findViewById(R.id.animateBtn);
    	
    	button.setOnClickListener(new Button.OnClickListener() {
    		public void onClick(View view) {
    			animateListView();
    		}
    	});
    }
    
    private void animateListView() {
    	ListView listView = (ListView) this.findViewById(R.id.listView1);
    	ViewAnimation animation = new ViewAnimation();
    	animation.setAnimationListener(new ViewAnimationListener());
    	listView.startAnimation(animation);
    }
}