package com.start.medical.personal.report;

import android.os.Bundle;

import com.start.core.BaseActivity;
import com.start.medical.R;

/**
 * 我的报告单
 * @author start
 *
 */
public class MyReportActivity extends BaseActivity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myregistered);
		setMainHeadTitle("我的报告单");
	}
	
}