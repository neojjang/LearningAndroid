package com.kevingomara.frameanimationactivity;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        this.setupButton();
    }
    /**
     * Set the onClickListener for the start/stop button
     */
    private void setupButton() {
    	Button button = (Button) this.findViewById(R.id.startFABtn);
    	button.setOnClickListener( new Button.OnClickListener() {
    		public void onClick(View view) {
    			parentButtonClicked(view);
    		}
    	});
    }
    
    private void parentButtonClicked(View view) {
    	animate();
    }
    
    private void animate() {
    	ImageView imageView = (ImageView) findViewById(R.id.animationImage);
    	imageView.setVisibility(ImageView.VISIBLE);
    	imageView.setBackgroundResource(R.drawable.frame_animation);
    	
    	AnimationDrawable frameAnimation = (AnimationDrawable) imageView.getBackground();
    	
    	if (frameAnimation.isRunning()) {
    		frameAnimation.stop();
    	} else {
    		frameAnimation.stop();					// safety
    		frameAnimation.start();
    	}
    }
}