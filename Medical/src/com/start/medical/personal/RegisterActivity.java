package com.start.medical.personal;

import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.start.core.BaseActivity;
import com.start.core.Constant;
import com.start.core.Constant.Handle;
import com.start.core.Constant.SharedPreferences;
import com.start.medical.R;
import com.start.service.HttpServer;
import com.start.service.Response;
import com.start.service.UIRunnable;
import com.start.utils.MD5;
import com.start.utils.StringUtils;
import com.start.utils.TimeUtils;

/**
 * 注册
 * @author start
 *
 */
public class RegisterActivity extends BaseActivity implements OnClickListener {
	
	protected String phone;
	protected String authcode;
	protected String password;
	
	protected Button btn_re_get_checksum;
	protected EditText et_phone;
	protected EditText et_checksum;
	protected EditText et_setting_password;
	protected EditText et_setting_repassword;
	
	private LinearLayout ll_setting_phone;
	private LinearLayout ll_setting_data;
	private LinearLayout ll_setting_done;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_reset_password);
		ll_setting_phone=(LinearLayout)findViewById(R.id.ll_setting_phone);
		ll_setting_data=(LinearLayout)findViewById(R.id.ll_setting_data);
		ll_setting_done=(LinearLayout)findViewById(R.id.ll_setting_done);
		btn_re_get_checksum=(Button)findViewById(R.id.btn_re_get_checksum);
		et_phone=(EditText)findViewById(R.id.et_phone);
		et_checksum=(EditText)findViewById(R.id.et_checksum);
		et_setting_password=(EditText)findViewById(R.id.et_setting_password);
		et_setting_repassword=(EditText)findViewById(R.id.et_setting_repassword);
	}
	
	@Override
	public void onProcessMessage(Message msg) {
		switch (msg.what) {
		case Handle.REGISTER_RESET_PASSWORD_STEP1:
			ll_setting_phone.setVisibility(View.GONE);
			ll_setting_data.setVisibility(View.VISIBLE);
			ll_setting_done.setVisibility(View.GONE);
			btn_re_get_checksum.setEnabled(false);
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					int sec=60;
					while(sec>0){
						sec--;
						final int n=sec;
						runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								if(n==0){
									btn_re_get_checksum.setText(R.string.regetauthcode);
									btn_re_get_checksum.setEnabled(true);
								}else{
									btn_re_get_checksum.setText(n+"秒,后可重新获取验证码");
									btn_re_get_checksum.setEnabled(false);
								}
							}
							
						});
						
						TimeUtils.sleep(1000);
					}
					
				}
				
			}).start();
			
			break;
		case Handle.REGISTER_RESET_PASSWORD_STEP2:
			ll_setting_phone.setVisibility(View.GONE);
			ll_setting_data.setVisibility(View.GONE);
			ll_setting_done.setVisibility(View.VISIBLE);
			break;
		default:
			super.onProcessMessage(msg);
			break;
		}
	}
	
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.btn_get_checksum){
			phone=String.valueOf(et_phone.getText());
			if(StringUtils.isEmpty(phone)){
				getHandlerContext().makeTextLong(getString(R.string.phoneemptytip));
				return;
			}
			getAuthCode(1);
		}else if(v.getId()==R.id.btn_re_get_checksum){
			//重发验证码
			getAuthCode(1);
		}else if(v.getId()==R.id.btn_setting_password){
			authcode=String.valueOf(et_checksum.getText());
			if(StringUtils.isEmpty(authcode)){
				getHandlerContext().makeTextLong(getString(R.string.autocodeemptytip));
				return;
			}
			password=String.valueOf(et_setting_password.getText());
			if(StringUtils.isEmpty(password)){
				getHandlerContext().makeTextLong(getString(R.string.pwdemptytip));
				return ;
			}
			if(!password.equals(String.valueOf(et_setting_repassword.getText()))){
				getHandlerContext().makeTextLong(getString(R.string.twopwddifftip));
				return;
			}
			HttpServer hServer=new HttpServer(Constant.URL.userReg, getHandlerContext());
			Map<String,String> headers=new HashMap<String,String>();
			headers.put("sign", Constant.ACCESSKEY_LOCAL);
			hServer.setHeaders(headers);
			Map<String,String> params=new HashMap<String,String>();
			params.put("accessid", Constant.ACCESSID_LOCAL);
			params.put("mobile", phone);
			params.put("pwd", MD5.md5(password));
			params.put("authcode", authcode);
			params.put("regsource", "10");
			params.put("loginflag", "1");
			hServer.setParams(params);
			hServer.get(new UIRunnable() {
				
				@Override
				public void run(Response response) {
					getHandlerContext().getHandler().sendEmptyMessage(Handle.REGISTER_RESET_PASSWORD_STEP2);
				}
				
			});
		}else if(v.getId()==R.id.btn_done){
			getAppContext().getSharedPreferencesUtils().putString(SharedPreferences.SP_ACCOUNT_CONTENT_DATA, phone);
			getAppContext().getSharedPreferencesUtils().putString(SharedPreferences.SP_PASSWORD_CONTENT_DATA, MD5.md5(password));
			getAppContext().getSharedPreferencesUtils().putBoolean(SharedPreferences.SP_AUTOLOGIN_CONTENT_DATA, true);
			startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
			finish();
		}
	}
	
	/**
	 * 获取验证码
	 */
	public void getAuthCode(int type){
		HttpServer hServer=new HttpServer(Constant.URL.useracodeGet, getHandlerContext());
		Map<String,String> headers=new HashMap<String,String>();
		headers.put("sign", Constant.ACCESSKEY_LOCAL);
		hServer.setHeaders(headers);
		Map<String,String> params=new HashMap<String,String>();
		params.put("accessid", Constant.ACCESSID_LOCAL);
		params.put("mobile", phone);
		params.put("type", String.valueOf(type));
		hServer.setParams(params);
		hServer.get(new UIRunnable() {
			
			@Override
			public void run(Response response) {
				getHandlerContext().getHandler().sendEmptyMessage(Handle.REGISTER_RESET_PASSWORD_STEP1);
			}
			
		});
	}
	
}