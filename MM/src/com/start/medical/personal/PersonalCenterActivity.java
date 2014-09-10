package com.start.medical.personal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.start.core.BaseActivity;
import com.start.medical.R;
import com.start.medical.health.records.HealthRecordsActivity;

/**
 * 个人中心
 * @author start
 *
 */
public class PersonalCenterActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(!getAppContext().currentUser().isLogin()){
			goLogin(getString(R.string.not_login_message));
			return;
		}
		setContentView(R.layout.activity_personal_center);
		setMainHeadTitle("个人中心");
	}
	
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.btn_edit_personal){
			//编辑个人中心
		}else if(v.getId()==R.id.btn_health_records){
			//健康档案
			startActivity(new Intent(this,HealthRecordsActivity.class));
		}else if(v.getId()==R.id.btn_my_registered){
			//我的挂号
		}else if(v.getId()==R.id.btn_my_appointment){
			//我的预约
		}else if(v.getId()==R.id.btn_my_takereport){
			//我的报告单
		}else if(v.getId()==R.id.btn_my_consultation){
			//我的咨询
		}else if(v.getId()==R.id.btn_exit_login){
			//退出登录
			finish();
		}
	}
	
}
