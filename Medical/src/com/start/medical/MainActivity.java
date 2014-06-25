package com.start.medical;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;

import com.start.core.BaseActivity;
import com.start.service.UIRunnable;
import com.start.utils.MD5;

/**
 * 主界面
 * @author start
 *
 */
public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		Map<String,String> requestParams=new HashMap<String,String>();
		requestParams.put("username", "18368013123");
		requestParams.put("loginsource", "9");
		requestParams.put("ip", "");
		requestParams.put("mac", "");
		Map<String,String> headerParams=new HashMap<String,String>();
		headerParams.put("sign", MD5.md5("123456@"));
		getAppContext().sendRequestRefreshSingle(this,"v4Login",requestParams,headerParams,new UIRunnable() {

			@Override
			public void run() {
				
			}
			
		});
	}
	
}
