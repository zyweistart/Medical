package com.start.medical.personal;

import android.os.Bundle;

import com.start.core.BaseActivity;
import com.start.medical.R;

/**
 * 编辑个人信息
 * @author start
 *
 */
public class EditPersonalActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(!getAppContext().currentUser().isLogin()){
			goLogin(getString(R.string.not_login_message));
			return;
		}
		setContentView(R.layout.activity_edit_personal);
	}
	
}
