package com.start.medical.department;

import android.os.Bundle;

import com.start.core.BaseActivity;
import com.start.medical.R;

/**
 * 科室医生
 * @author start
 *
 */
public class DoctorsActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doctor);
		setMainHeadTitle(getString(R.string.mainfunctiontxt6));
	}
	
}
