package com.start.core;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.start.core.Constant.ResultCode;
import com.start.core.HandlerContext.HandleContextListener;
import com.start.medical.AppContext;
import com.start.medical.R;
import com.start.medical.personal.LoginActivity;
import com.umeng.analytics.MobclickAgent;

public abstract class BaseActivity extends Activity implements OnClickListener,HandleContextListener {
	
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
	protected void onResume() {
		super.onResume();
		if (!Constant.ISSYSTEMTEST) {
			MobclickAgent.onResume(this);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (!Constant.ISSYSTEMTEST) {
			MobclickAgent.onPause(this);
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
	public void onClick(View v) {
	
	}
	
	@Override
	public void onProcessMessage(Message msg) throws AppException {
		switch(msg.what){
		case ResultCode.NOLOGIN:
			goLogin(String.valueOf(msg.obj));
			break;
		default:
			if(msg.obj!=null){
				getHandlerContext().makeTextShort(String.valueOf(msg.obj));
			}
			break;
		}
	}
	
	/**
	 * 设置导航标题
	 */
	public void setMainHeadTitle(String title){
		TextView tvTitle=(TextView)findViewById(R.id.main_head_title);
		if(tvTitle!=null){
			tvTitle.setText(title);
		}
	}
	
	public void goLogin(Boolean autoLogin){
		Bundle bundle=new Bundle();
		bundle.putBoolean(LoginActivity.BUNLE_AUTOLOGINFLAG, autoLogin);
		Intent intent=new Intent(this,LoginActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);
		finish();
	}
	
	public void goLogin(String message){
		getAppContext().currentUser().clearCachePassword();
		Bundle bundle=new Bundle();
		bundle.putString(LoginActivity.BUNLE_MESSAGE, message);
		Intent intent=new Intent(this,LoginActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);
		finish();
	}
	
}