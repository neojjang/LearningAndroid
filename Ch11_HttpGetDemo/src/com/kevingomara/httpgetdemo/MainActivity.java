package com.kevingomara.httpgetdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        BufferedReader in = null;
        
        try {
        	HttpClient httpClient 	= new DefaultHttpClient();
        	HttpGet request 		= new HttpGet("http://code.google.com/android/");
        	HttpResponse response	= httpClient.execute(request);
        	
        	in = new BufferedReader( new InputStreamReader(response.getEntity().getContent()));
        	
        	StringBuffer sb = new StringBuffer("");
        	String line = "";
        	String NL = System.getProperty("line.separator");
        	while ((line = in.readLine()) != null) {
        		sb.append(line + NL);
        	}
        	in.close();
        	
        	String page = sb.toString();
        	System.out.println(page);
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	if (in != null) {
        		try {
        			in.close();
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
        	}
        }
    }
}