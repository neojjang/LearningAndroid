package com.kevingomara.intentsutils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

public class IntentUtils {
	
	public static void invokeWebBrowser(Activity activity) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("http://www.kevingomara.com"));
		activity.startActivity(intent);
	}
	
	public static void invokeWebSearch(Activity activity) {
		Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
		intent.setData(Uri.parse("http://www.google.com"));
		activity.startActivity(intent);
	}
	
	public static void dial(Activity activity) {
		Intent intent = new Intent(Intent.ACTION_DIAL);
		activity.startActivity(intent);
	}
	
	public static void call(Activity activity) {
		Intent intent = new Intent(Intent.ACTION_CALL);
		intent.setData(Uri.parse("tel://555-555-1212"));
		activity.startActivity(intent);
	}
	
	public static void showMapAtLatLong(Activity activity) {
//		Intent intent = new Intent(Intent.ACTION_VIEW);
//		// call pattern geo:lat,long?z=zoomlevel&q=question-string
//		intent.setData(Uri.parse("geo:0,0?z=4&q=business+near+city"));
//		activity.startActivity(intent);
		String uri = "geo:"+ 0 + "," + 0 /*+ "?z=4" + "?q=my+street+address"*/;
		activity.startActivity(new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri)));
	}
	
	public static void tryOneOfThese(Activity activity) {
		IntentUtils.invokeWebBrowser(activity);
	}
}
