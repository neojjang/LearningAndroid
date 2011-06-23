package com.kevingomara.com.c3_30_xmlresources;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Cs_30_XmlResources extends Activity {
    /** Called when the activity is first created. */
	
	private Activity mActivity;

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mActivity = this;
        
        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Perform action on clicks
            	try {
            		Log.d(getLocalClassName(), getEventsFromXmlFile(mActivity));
            	}
            	catch (Exception e) {
            		Log.v(getLocalClassName(), "trouble");
            	}
            }
        });    
    }
    
    private String getEventsFromXmlFile(Activity activity) throws XmlPullParserException, IOException {
    	StringBuffer sb = new StringBuffer();
    	Resources res = activity.getResources();
    	XmlResourceParser xrp = res.getXml(R.xml.test);
    	
    	xrp.next();
    	int eventType = xrp.getEventType();
    	while (eventType != XmlPullParser.END_DOCUMENT) {
    		if (eventType == XmlPullParser.START_DOCUMENT) {
    			sb.append("*******Start document");
    		} else if (eventType == XmlPullParser.START_TAG) {
    			sb.append("\nStart tag " + xrp.getName());
    		} else if (eventType == XmlPullParser.END_TAG) {
    			sb.append("\nEnd tag " + xrp.getName());
    		} else if (eventType == XmlPullParser.TEXT) {
    			sb.append("\nText " + xrp.getText());
    		}
    		eventType = xrp.next();
    	} 
    	sb.append("\n*****End document");
    	
    	return sb.toString();
    }
}