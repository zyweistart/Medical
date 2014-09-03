package com.start.medical.registered;

import android.os.Bundle;

import com.start.core.BaseActivity;
import com.start.medical.R;

/**
 * 挂号
 * @author start
 *
 */
public class RegisteredActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registered);
		setMainHeadTitle(getString(R.string.mainfunctiontxt1));
	}
	
}
