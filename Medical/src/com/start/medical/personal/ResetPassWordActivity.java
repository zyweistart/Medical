package com.start.medical.personal;

import java.util.HashMap;
import java.util.Map;

import android.view.View;

import com.start.core.Constant;
import com.start.core.Constant.Handle;
import com.start.medical.R;
import com.start.service.HttpServer;
import com.start.service.Response;
import com.start.service.UIRunnable;
import com.start.utils.MD5;
import com.start.utils.StringUtils;

/**
 * 重置密码
 * @author start
 *
 */
public class ResetPassWordActivity extends RegisterActivity {
	
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.btn_get_checksum){
			phone=String.valueOf(et_phone.getText());
			if(StringUtils.isEmpty(phone)){
				getHandlerContext().makeTextLong(getString(R.string.phoneemptytip));
				return;
			}
			getAuthCode(2);
		}else if(v.getId()==R.id.btn_re_get_checksum){
			//重发验证码
			getAuthCode(2);
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
			HttpServer hServer=new HttpServer(Constant.URL.userpwdReset, getHandlerContext());
			Map<String,String> headers=new HashMap<String,String>();
			headers.put("sign", Constant.ACCESSKEY_LOCAL);
			hServer.setHeaders(headers);
			Map<String,String> params=new HashMap<String,String>();
			params.put("accessid", Constant.ACCESSID_LOCAL);
			params.put("mobile", phone);
			params.put("pwd", MD5.md5(password));
			params.put("authcode", authcode);
			params.put("operatesource", "10");
			params.put("loginflag", "1");
			hServer.setParams(params);
			hServer.get(new UIRunnable() {
				
				@Override
				public void run(Response response) {
					getHandlerContext().getHandler().sendEmptyMessage(Handle.REGISTER_RESET_PASSWORD_STEP2);
				}
				
			});
		}else{
			super.onClick(v);
		}
	}
}