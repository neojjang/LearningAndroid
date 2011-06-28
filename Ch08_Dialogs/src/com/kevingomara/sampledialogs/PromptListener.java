package com.kevingomara.sampledialogs;

import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;

public class PromptListener implements android.content.DialogInterface.OnClickListener {
	
	private String promptReply = null;				// used to return prompt reply value
	private View promptDialogView = null;			// used to retrieve the prompt value
	
	// get the View in the constructor
	public PromptListener(View inDialogView) {
		promptDialogView = inDialogView;
	}
	
	// callback method from dialogs
	public void onClick(DialogInterface view, int buttonId) {
		if (buttonId == DialogInterface.BUTTON_POSITIVE) {
			// it's the OK button
			promptReply = getPromptText();
		} else {
			// must be the Cancel button
			promptReply = null;
		}
	}
	
	// get the text from the edit box
	private String getPromptText() {
		EditText editText = (EditText) promptDialogView.findViewById(R.id.editText_prompt);
		String prompt = editText.getText().toString();
		if (prompt == null) {
			return "";
		} else {
			return prompt;
		}
	}
	
	public String getPromptReply() {
		return promptReply;
	}
}
