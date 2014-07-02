package com.start.medical.personal;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;

import com.start.core.AppException;
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
	/**
	 * 提示信息
	 */
	public static final String BUNLE_MESSAGE="BUNLE_MESSAGE";
	/**
	 * 自动登陆标记
	 */
	public static final String BUNLE_AUTOLOGINFLAG="BUNLE_AUTOLOGINFLAG";
	
	private EditText et_login_account;
	private EditText et_login_password;
	private CheckBox cb_login_autologin;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		et_login_account=(EditText)findViewById(R.id.et_login_account);
		et_login_password=(EditText)findViewById(R.id.et_login_password);
		cb_login_autologin=(CheckBox)findViewById(R.id.cb_login_autologin);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		Bundle bundle=getIntent().getExtras();
		if(bundle!=null){
			//自动登陆
			if(bundle.getBoolean(BUNLE_AUTOLOGINFLAG,true)){
				String account=getAppContext().getSharedPreferencesUtils().getString(SharedPreferences.SP_ACCOUNT_CONTENT_DATA, Constant.EMPTYSTR);
				if(StringUtils.isEmpty(account)){
					return;
				}
				et_login_account.setText(account);
				Boolean autoLogin=getAppContext().getSharedPreferencesUtils().getBoolean(SharedPreferences.SP_AUTOLOGIN_CONTENT_DATA, false);
				cb_login_autologin.setChecked(autoLogin);
				if(autoLogin){
					String password=getAppContext().getSharedPreferencesUtils().getString(SharedPreferences.SP_PASSWORD_CONTENT_DATA, Constant.EMPTYSTR);
					if(StringUtils.isEmpty(password)){
						return;
					}
					et_login_password.setText(password);
					login(account, password, autoLogin);
				}
			}else{
				
				String message=bundle.getString(BUNLE_MESSAGE,Constant.EMPTYSTR);
				if(!StringUtils.isEmpty(message)){
					getHandlerContext().makeTextLong(message);
					return;
				}
				
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
			Boolean checked=cb_login_autologin.isChecked();
			login(account,MD5.md5(password),checked);
		}
	}
	
	
	@Override
	public void onProcessMessage(Message msg) {
		switch(msg.what){
		case 110036://签名不匹配或密码不正确
			getAppContext().currentUser().clearCachePassword();
			et_login_password.setText(Constant.EMPTYSTR);
			getHandlerContext().makeTextShort(getString(R.string.usernameorpassworderrortip));
			break;
		default:
			super.onProcessMessage(msg);
			break;
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
			public void run(Response response) throws AppException {
				getAppContext().currentUser().addCacheUser(account, password, autoLogin);
				try {
					JSONObject userinfo=response.getResponseContent().getJSONObject("userinfo");
					getAppContext().currentUser().resolveByJSON(userinfo);
					startActivity(new Intent(LoginActivity.this,MainActivity.class));
					finish();
				} catch (JSONException e) {
					throw AppException.json(e);
				}
				
			}
			
		});
	}

}