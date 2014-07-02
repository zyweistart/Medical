package com.start.medical.personal;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.start.core.AppException;
import com.start.core.BaseActivity;
import com.start.core.Constant;
import com.start.core.Constant.SharedPreferences;
import com.start.medical.R;
import com.start.service.HttpServer;
import com.start.service.Response;
import com.start.service.UIRunnable;
import com.start.utils.MD5;
import com.start.utils.StringUtils;

/**
 * 更改密码
 * @author start
 *
 */
public class ChangePassWordActivity extends BaseActivity implements OnClickListener  {
	
	private EditText et_old_password;
	private EditText et_new_password;
	private EditText et_re_new_password;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_password);
		et_old_password=(EditText)findViewById(R.id.et_old_password);
		et_new_password=(EditText)findViewById(R.id.et_new_password);
		et_re_new_password=(EditText)findViewById(R.id.et_re_new_password);
	}

	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.btn_change_password){
			String oldpassword=String.valueOf(et_old_password.getText());
			if(StringUtils.isEmpty(oldpassword)){
				getHandlerContext().makeTextLong(getString(R.string.phoneemptytip));
				return;
			}
			final String newpassword=String.valueOf(et_new_password.getText());
			if(StringUtils.isEmpty(newpassword)){
				getHandlerContext().makeTextLong(getString(R.string.phoneemptytip));
				return;
			}
			String renewpassword=String.valueOf(et_re_new_password.getText());
			if(newpassword.equals(renewpassword)){
				getHandlerContext().makeTextLong(getString(R.string.phoneemptytip));
				return;
			}
			HttpServer hServer=new HttpServer(Constant.URL.userpwdMod, getHandlerContext());
			Map<String,String> headers=new HashMap<String,String>();
			headers.put("sign", MD5.md5(newpassword));
			hServer.setHeaders(headers);
			Map<String,String> params=new HashMap<String,String>();
			params.put("accessid", Constant.ACCESSID);
			params.put("pwd", MD5.md5(oldpassword));
			hServer.setParams(params);
			hServer.get(new UIRunnable() {
				
				@Override
				public void run(Response response) throws AppException {
					
					Boolean autoLogin=getAppContext().getSharedPreferencesUtils().getBoolean(SharedPreferences.SP_AUTOLOGIN_CONTENT_DATA, false);
					if(autoLogin){
						getAppContext().getSharedPreferencesUtils().putString(SharedPreferences.SP_PASSWORD_CONTENT_DATA, MD5.md5(newpassword));
					}
					getHandlerContext().makeTextLong("密码修改成功");
					finish();
				}
				
			});
		}
	}
	
}
