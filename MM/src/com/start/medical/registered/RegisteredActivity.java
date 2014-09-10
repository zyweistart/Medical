package com.start.medical.registered;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.start.core.BaseActivity;
import com.start.medical.R;
import com.start.medical.personal.AccountBindActivity;

/**
 * 挂号
 * @author start
 *
 */
public class RegisteredActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registered);
		setMainHeadTitle(getString(R.string.mainfunctiontxt1));
		if(getAppContext().currentUser().isLogin()){
			if(TextUtils.isEmpty(getAppContext().currentUser().getInfo().get("name"))){
				//未绑定就诊信息
				Intent intent=new Intent(this,AccountBindActivity.class);
				startActivity(intent);
				finish();
			}
		}else{
			goLogin("请重新登录");
		}
		
	}
	
}
