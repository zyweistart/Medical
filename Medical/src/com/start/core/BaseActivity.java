package com.start.core;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;

import com.start.core.HandlerContext.HandleContextListener;
import com.start.medical.AppContext;
import com.start.medical.R;
import com.start.medical.personal.LoginActivity;
import com.start.utils.StringUtils;

public abstract class BaseActivity extends Activity implements HandleContextListener,OnClickListener {
	
	protected final String TAG = this.getClass().getSimpleName();
	
	private AppContext mAppContext;
	private HandlerContext mHandlerContext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 添加Activity到堆栈
		AppManager.getInstance().addActivity(this);
	}

	@Override
	public void onResume() {
		super.onResume();
		if (!Constant.ISSYSTEMTEST) {
			// 测试环境下不做统计日志提交
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		if (!Constant.ISSYSTEMTEST) {
			// 测试环境下不做统计日志提交
		}
	}
	
	@Override
	protected void onDestroy() {
		// 结束Activity&从堆栈中移除
		AppManager.getInstance().finishActivity(this);
		super.onDestroy();
	}
	
	public AppContext getAppContext() {
		if(mAppContext==null){
			mAppContext=(AppContext) getApplication();
		}
		return mAppContext;
	}
	
	public HandlerContext getHandlerContext() {
		if(mHandlerContext==null){
			mHandlerContext=new HandlerContext(this);
			mHandlerContext.setListener(this);
		}
		return mHandlerContext;
	}

	@Override
	public void onProcessMessage(Message msg) {
		switch(msg.what){
		default:
			String message=String.valueOf(msg.obj);
			if(!StringUtils.isEmpty(message)){
				getHandlerContext().makeTextShort(message);
			}
			break;
		}
	}
	
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.module_top_left){
			finish();
		}
	}
	
	public void goLogin(){
		Bundle bundle=new Bundle();
		bundle.putBoolean(LoginActivity.BUNLE_AUTOLOGINFLAG, true);
		Intent intent=new Intent(this,LoginActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);
		finish();
	}
	
	public void goLogin(String message){
		Bundle bundle=new Bundle();
		bundle.putString(LoginActivity.BUNLE_MESSAGE, message);
		Intent intent=new Intent(this,LoginActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);
		finish();
	}
	
}