package com.start.medical.personal.healthrecords;

import android.os.Bundle;
import android.view.View;

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
	
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.btn_medical_recor){
			//病历
		}else if(v.getId()==R.id.btn_check){
			//检查
		}else if(v.getId()==R.id.btn_medication){
			//服药
		}else if(v.getId()==R.id.btn_discomfort_record){
			//不适记录
		}else if(v.getId()==R.id.btn_medication_reminder){
			//用药提醒
		}else if(v.getId()==R.id.btn_health_tracking){
			//健康跟踪
		}else{
			super.onClick(v);
		}
	}
	
}