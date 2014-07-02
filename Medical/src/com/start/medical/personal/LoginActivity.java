package com.start.medical.personal;

import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.start.core.BaseActivity;
import com.start.core.Constant;
import com.start.core.Constant.SharedPreferences;
import com.start.medical.MainActivity;
import com.start.medical.R;
import com.start.service.HttpServer;
import com.start.service.Response;
import com.start.service.UIRunnable;
import com.start.utils.MD5;
import com.start.utils.StringUtils;

/**
 * 登陆
 * @author start
 *
 */
public class LoginActivity extends BaseActivity implements OnClickListener {
	
	private EditText et_login_account;
	private EditText et_login_password;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		et_login_account=(EditText)findViewById(R.id.et_login_account);
		et_login_password=(EditText)findViewById(R.id.et_login_password);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		Bundle bundle=getIntent().getExtras();
		if(bundle==null){
			String account=getAppContext().getSharedPreferencesUtils().getString(SharedPreferences.SP_ACCOUNT_CONTENT_DATA, Constant.EMPTYSTR);
			//TODO:显示账号
			Boolean autoLogin=getAppContext().getSharedPreferencesUtils().getBoolean(SharedPreferences.SP_AUTOLOGIN_CONTENT_DATA, false);
			if(autoLogin){
				String password=getAppContext().getSharedPreferencesUtils().getString(SharedPreferences.SP_PASSWORD_CONTENT_DATA, Constant.EMPTYSTR);
				//TODO：显示密码
				login(account, password, autoLogin);
				return;
			}
		}
	}
	
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.btn_login){
			String account=String.valueOf(et_login_account.getText());
			if(StringUtils.isEmpty(account)){
				getHandlerContext().makeTextLong(getString(R.string.phoneemptytip));
				return;
			}
			String password=String.valueOf(et_login_password.getText());
			if(StringUtils.isEmpty(password)){
				getHandlerContext().makeTextLong(getString(R.string.pwdemptytip));
				return;
			}
			login(account,MD5.md5(password),false);
		}
	}
	
	/**
	 * 登陆
	 * @param account
	 * @param password MD5加密后的密码
	 * @param autoLogin  
	 */
	public void login(final String account,final String password,final Boolean autoLogin){
		HttpServer hServer=new HttpServer(Constant.URL.userLogin, getHandlerContext());
		Map<String,String> headers=new HashMap<String,String>();
		headers.put("sign", password);
		hServer.setHeaders(headers);
		Map<String,String> params=new HashMap<String,String>();
		params.put("account", account);
		params.put("loginsource", "10");
		hServer.setParams(params);
		hServer.get(new UIRunnable() {
			
			@Override
			public void run(Response response) {
				getAppContext().getSharedPreferencesUtils().putString(SharedPreferences.SP_ACCOUNT_CONTENT_DATA, account);
				getAppContext().getSharedPreferencesUtils().putBoolean(SharedPreferences.SP_AUTOLOGIN_CONTENT_DATA, autoLogin);
				if(autoLogin){
					getAppContext().getSharedPreferencesUtils().putString(SharedPreferences.SP_PASSWORD_CONTENT_DATA, password);
				}
				
				//TODO:解析登陆数据
				Constant.ACCESSID="";
				Constant.ACCESSKEY="";
				
				startActivity(new Intent(LoginActivity.this,MainActivity.class));
				finish();
				
			}
			
		});
	}

}
