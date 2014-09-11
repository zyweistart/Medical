package com.start.medical.personal.registered;

import android.os.Bundle;

import com.start.core.BaseActivity;
import com.start.medical.R;

/**
 * 我的挂号
 * @author start
 *
 */
public class MyRegisteredActivity extends BaseActivity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myregistered);
		setMainHeadTitle("我的挂号");
	}
	
}