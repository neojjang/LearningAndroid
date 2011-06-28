package com.kevingomara.sampledialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

public class Alerts {
	public static String prompt(String message, Context context) {
        // inflate the dialog resource
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.prompt_layout, null);
        
        // build the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(message);
        builder.setView(view);
        
        // set the buttons and callback
        PromptListener promptListener = new PromptListener(view);
        builder.setPositiveButton("OK", promptListener);
        builder.setNegativeButton("Cancel", promptListener);
        
        // create and show the dialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        
        return promptListener.getPromptReply();
	}
}
