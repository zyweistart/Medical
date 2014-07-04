package com.start.medical;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

import com.start.core.AppException;
import com.start.service.User;
import com.start.utils.SharedPreferencesUtils;

/**
 * 全局application
 */
public class AppContext extends Application {
    
	private User mUser;
    private static Context mContext;
    private static SharedPreferencesUtils sharedPreferences;
	
    @Override
    public void onCreate() {
        super.onCreate();
        //注册App异常崩溃处理器
        Thread.setDefaultUncaughtExceptionHandler(AppException.getAppExceptionHandler());
    	mContext = getApplicationContext();
    	sharedPreferences=new SharedPreferencesUtils(this);
    }
    
    public static Context getContext(){
        return mContext;
    }
    
    public static SharedPreferencesUtils getSharedPreferences() {
		return sharedPreferences;
	}
    
    /**
     * 获取当前用户信息
     */
    public User currentUser(){
    	if(mUser==null){
    		mUser=User.getInstance();
    	}
    	return mUser;
    }
    
    /**
	 * 获取App安装包信息
	 */
	public PackageInfo getPackageInfo() {
		PackageInfo info = null;
		try { 
			info = getPackageManager().getPackageInfo(getPackageName(), 0);
		} catch (NameNotFoundException e) {    
			e.printStackTrace(System.err);
		} 
		if(info == null) info = new PackageInfo();
		return info;
	}
    
}
