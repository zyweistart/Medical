package com.start.medical.personal.appointment;

import android.os.Bundle;

import com.start.core.BaseActivity;
import com.start.medical.R;

/**
 * 预约详情
 * @author start
 *
 */
public class MyAppointmentDetailActivity extends BaseActivity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myappointment_detail);
		setMainHeadTitle("预约详情");
	}
	
}