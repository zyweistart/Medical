package com.start.core;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;

import com.start.core.HandlerContext.HandleContextListener;
import com.start.medical.AppContext;
import com.start.utils.StringUtils;

public abstract class BaseActivity extends Activity implements HandleContextListener {
	
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
	
}