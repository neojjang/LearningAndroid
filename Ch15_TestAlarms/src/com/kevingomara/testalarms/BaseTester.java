package com.kevingomara.testalarms;

import android.content.Context;

public class BaseTester {

	protected IReportBack mReportTo;
	protected Context mContext;
	
	public BaseTester(Context context, IReportBack target) {
		mReportTo = target;
		mContext  = context;
	}
}
