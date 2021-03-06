package com.start.medical.personal;

import android.os.Bundle;

import com.start.core.BaseActivity;
import com.start.medical.R;

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
	}
	
}
