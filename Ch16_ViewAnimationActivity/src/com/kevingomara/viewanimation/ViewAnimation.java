package com.kevingomara.viewanimation;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

public class ViewAnimation extends Animation {
	
	private float centerX = 0.0f;
	private float centerY = 0.0f;
	
	private Camera camera = new Camera();
	
	
	@Override
	public void initialize(int width, int height, int parentWidth, int parentHeight) {
		super.initialize(width, height, parentWidth, parentHeight);
		
		centerX = width/2.0f;
		centerY = height/2.0f;
		
		setDuration(2500);
		setFillAfter(true);
		setInterpolator(new LinearInterpolator());
	}
	
	@Override
	protected void applyTransformation(float interpolatedTime, Transformation transformation) {
		final Matrix matrix = transformation.getMatrix();
		
		// simple scaling animation
//		matrix.setScale(interpolatedTime, interpolatedTime);
//		matrix.preTranslate(-centerX, -centerY);
//		matrix.postTranslate(centerX, centerY);
		
		// Camera 3D effect animation
		camera.save();
		camera.translate(0.0f, 0.0f, (1300 - 1300.0f * interpolatedTime));
		camera.rotateY(360 * interpolatedTime);
		camera.getMatrix(matrix);
		
		matrix.preTranslate(-centerX, -centerY);
		matrix.postTranslate(centerX, centerY);
		camera.restore();
	}
}
