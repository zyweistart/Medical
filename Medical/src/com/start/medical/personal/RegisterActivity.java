package com.start.medical.personal;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.start.core.AppException;
import com.start.core.BaseActivity;
import com.start.core.Constant;
import com.start.medical.R;
import com.start.service.HttpServer;
import com.start.service.Response;
import com.start.service.UIRunnable;
import com.start.utils.StringUtils;

/**
 * 注册
 * @author start
 *
 */
public class RegisterActivity extends BaseActivity implements OnClickListener {
	
	private LinearLayout ll_setting_phone;
	private LinearLayout ll_setting_checksum;
	private LinearLayout ll_setting_password;
	private LinearLayout ll_setting_done;
	
	private EditText et_phone;
	private EditText et_checksum;
	private EditText et_setting_password;
	private EditText et_setting_repassword;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		ll_setting_phone=(LinearLayout)findViewById(R.id.ll_setting_phone);
		ll_setting_checksum=(LinearLayout)findViewById(R.id.ll_setting_checksum);
		ll_setting_password=(LinearLayout)findViewById(R.id.ll_setting_password);
		ll_setting_done=(LinearLayout)findViewById(R.id.ll_setting_done);
		et_phone=(EditText)findViewById(R.id.et_phone);
		et_checksum=(EditText)findViewById(R.id.et_checksum);
		et_setting_password=(EditText)findViewById(R.id.et_setting_password);
		et_setting_repassword=(EditText)findViewById(R.id.et_setting_repassword);
		getHandleContext().getHandler().sendEmptyMessage(REGISTER_RESET_PASSWORD_STEP1);
	}
	
	public static final int REGISTER_RESET_PASSWORD_STEP1=11;
	public static final int REGISTER_RESET_PASSWORD_STEP2=22;
	public static final int REGISTER_RESET_PASSWORD_STEP3=33;
	public static final int REGISTER_RESET_PASSWORD_STEP4=44;
	
	@Override
	public void onProcessMessage(Message msg) {
		super.onProcessMessage(msg);
		switch (msg.what) {
		case REGISTER_RESET_PASSWORD_STEP1:
			ll_setting_phone.setVisibility(View.VISIBLE);
			ll_setting_checksum.setVisibility(View.GONE);
			ll_setting_password.setVisibility(View.GONE);
			ll_setting_done.setVisibility(View.GONE);
			break;
		case REGISTER_RESET_PASSWORD_STEP2:
			ll_setting_phone.setVisibility(View.GONE);
			ll_setting_checksum.setVisibility(View.VISIBLE);
			ll_setting_password.setVisibility(View.GONE);
			ll_setting_done.setVisibility(View.GONE);
			break;
		case REGISTER_RESET_PASSWORD_STEP3:
			ll_setting_phone.setVisibility(View.GONE);
			ll_setting_checksum.setVisibility(View.GONE);
			ll_setting_password.setVisibility(View.VISIBLE);
			ll_setting_done.setVisibility(View.GONE);
			break;
		case REGISTER_RESET_PASSWORD_STEP4:
			ll_setting_phone.setVisibility(View.GONE);
			ll_setting_checksum.setVisibility(View.GONE);
			ll_setting_password.setVisibility(View.GONE);
			ll_setting_done.setVisibility(View.VISIBLE);
			break;
		}
	}
	
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.btn_get_checksum){
			String phone=String.valueOf(et_phone.getText());
			if(!StringUtils.isEmpty(phone)){
				HttpServer httpServer=new HttpServer(Constant.URL.useracodeGet, getHandleContext());
				Map<String,String> params=new HashMap<String,String>();
				Map<String,String> headers=new HashMap<String,String>();
				headers.put("sign", Constant.ACCESSKEY_LOCAL);
				httpServer.setHeaders(headers);
				params.put("accessid", Constant.ACCESSID_LOCAL);
				params.put("mobile", phone);
				params.put("type", "1");
				httpServer.setParams(params);
				httpServer.get(new UIRunnable() {
					
					@Override
					public void run(Response response) {
						try {
							getHandleContext().makeTextLong(response.getResponseByString());
						} catch (AppException e) {
							e.printStackTrace();
						}
						getHandleContext().getHandler().sendEmptyMessage(REGISTER_RESET_PASSWORD_STEP2);
					}
					
				});
			}else{
				getHandleContext().makeTextLong("请输入手机号码");
			}
		}else if(v.getId()==R.id.btn_confirm_checksum){
			String checksum=String.valueOf(et_checksum.getText());
			if(!TextUtils.isEmpty(checksum)){
				getHandleContext().getHandler().sendEmptyMessage(REGISTER_RESET_PASSWORD_STEP2);
			}
		}else if(v.getId()==R.id.btn_setting_password){
			String password=String.valueOf(et_setting_password.getText());
			String repassword=String.valueOf(et_setting_repassword.getText());
			if(!StringUtils.isEmpty(password)){
				if(!password.equals(repassword)){
					
				}else{
					getHandleContext().makeTextLong("二次密码输入不一致");
				}
			}else{
				getHandleContext().makeTextLong("密码不能为空");
			}
		}else if(v.getId()==R.id.btn_done){
			finish();
		}
	}
	
}