package com.start.medical.personal.appointment;

import android.os.Bundle;

import com.start.core.BaseActivity;
import com.start.medical.R;

/**
 * 我的预约
 * @author start
 *
 */
public class MyAppointmentActivity extends BaseActivity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myappointment);
		setMainHeadTitle("我的预约");
	}
	
}