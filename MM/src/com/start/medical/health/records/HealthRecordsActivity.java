package com.start.medical.health.records;

import android.os.Bundle;

import com.start.core.BaseActivity;
import com.start.medical.R;

/**
 * 健康档案
 * @author start
 *
 */
public class HealthRecordsActivity extends BaseActivity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_healthrecords);
		setMainHeadTitle("健康档案");
	}
	
}