package com.kevingomara.viewanimation;

import android.util.Log;
import android.view.animation.Animation;

public class ViewAnimationListener implements Animation.AnimationListener {
	
	private static final String TAG = "AnimationListener";

	public ViewAnimationListener() {
		
	}
	
	public void onAnimationStart(Animation animation) {
		Log.d(TAG, "starting");
	}
	
	public void onAnimationEnd(Animation animation) {
		Log.d(TAG, "ending");
	}
	
	public void onAnimationRepeat(Animation animation) {
		Log.d(TAG, "repeating");
	}
}
