package com.kevingomara.downloadimage;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.kevingomara.ocahttpclient.CustomHttpClient;

public class DownloadImageTask extends AsyncTask<String, Integer, Bitmap> {
	
	private Context mContext = null;
	private int progress = -1;
	private Bitmap downloadedImage = null;
	
	private static final String TAG = "DownloadImageTask";
	
	DownloadImageTask(Context context) {
		mContext = context;
	}
	
	protected void setContext(Context context) {
		mContext = context;
		if (progress >= 0) {
			publishProgress(this.progress);
		}
	}
	
	protected void onPreExecute() {
		// set the progress to 0 to indicate we've started, but nothing is complete yet
		progress = 0;
	}
	
	protected Bitmap doInBackground(String...urls) {
		// this method runs in the background
		Log.v(TAG, "downloading image");
		return downloadImage(urls);
	}
	
	protected void onProgressUpdate(Integer...progress) {
		TextView textView = (TextView) ((Activity) mContext).findViewById(R.id.text);
		textView.setText("Progress so far: " + progress[0]);
	}
	
	protected void onPostExecute(Bitmap result) {
		if (result != null) {
			// all is well
			downloadedImage = result;
			setImageInView();
		} else {
			// problem occurred - display error msg
			TextView errorMsg = (TextView) ((Activity) mContext).findViewById(R.id.errorMsg);
			errorMsg.setText("Problem downloading image");
		}
	}
	
	private Bitmap downloadImage(String...urls) {
		// this method runs in a background task (because its called from doInBackground)
		HttpClient httpClient = CustomHttpClient.getHttpClient();
		
		try {
			// set up the request and params
			HttpGet request = new HttpGet(urls[0]);
			HttpParams params = new BasicHttpParams();
			HttpConnectionParams.setSoTimeout(params, 60000);		// override timeout to 1 minute
			request.setParams(params);
			setProgress(25);									// 25% complete (step 1 of 4)
			
			// fire off the request
			HttpResponse response = httpClient.execute(request);
			setProgress(50);									// 50% complete
			
			// delay for 5 secs to give chance to rotate device
			sleepFor(5000);
			
			// get the image data from the response
			byte[] image = EntityUtils.toByteArray(response.getEntity());
			setProgress(75);									// 75% complete
			
			// create a bitmap from the byte array (image data)
			Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
			setProgress(100);									// 100% complete
			return bitmap;
		} catch (IOException e) {
			// TODO handle exceptions in a real way
			e.printStackTrace();
		}
		return null;
	}
	
	private void setProgress(int progress) {
		this.progress = progress;
		publishProgress(this.progress);
	}
	
	protected void setImageInView() {
		if (downloadedImage != null) {
			ImageView imageView = (ImageView) ((Activity) mContext).findViewById(R.id.image);
			imageView.setImageBitmap(downloadedImage);
		}
	}
	
	private void sleepFor(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			Log.v("sleep", "interrupted");
		}
	}

}
