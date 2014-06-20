package com.start.core;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;

import com.start.core.HandleContext.HandleContextListener;
import com.start.medical.AppContext;

public abstract class BaseActivity extends Activity implements HandleContextListener {
	
	protected final String TAG = this.getClass().getSimpleName();
	
	private AppContext mAppContext;
	private HandleContext mHandleContext;
	
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
		super.onDestroy();
		// 结束Activity&从堆栈中移除
		AppManager.getInstance().finishActivity(this);
	}
	
	public AppContext getAppContext() {
		if(mAppContext==null){
			mAppContext=(AppContext) getApplication();
		}
		return mAppContext;
	}
	
	public HandleContext getHandleContext() {
		if(mHandleContext==null){
			mHandleContext=new HandleContext(this);
		}
		return mHandleContext;
	}

	@Override
	public void onProcessMessage(Message msg) {
		//子类中如果需要处理Handle消息则必须要继承该类，并在调用前调用setListener(this);设置监听所实现的类 
	}
	
}