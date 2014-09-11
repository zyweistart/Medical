package com.start.medical.more;

import android.os.Bundle;

import com.start.core.BaseActivity;
import com.start.medical.R;

/**
 * 意见反馈
 * @author start
 *
 */
public class FeedbackActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_navigation);
		setMainHeadTitle(getString(R.string.mainfunctiontxt5));
	}
	
}
