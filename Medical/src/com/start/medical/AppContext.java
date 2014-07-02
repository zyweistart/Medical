package com.start.medical;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

import com.start.service.User;
import com.start.utils.SharedPreferencesUtils;

/**
 * 
 * @author Start   
 * @Description: 全局application 
 * @ClassName: AppContext.java   
 * @date 2014年6月30日 上午9:08:15      
 * @说明  代码版权归 杭州反盗版中心有限公司 所有
 */
public class AppContext extends Application {
    
	private User mUser;
    private static Context mContext;
    private SharedPreferencesUtils sharedPreferences;
	
    @Override
    public void onCreate() {
    	mContext = getApplicationContext();
        super.onCreate();
    }
    
    public static Context getContext(){
        return mContext;
    }
    
    public User currentUser(){
    	if(mUser==null){
    		mUser=User.getInstance(this);
    	}
    	return mUser;
    }
    
    public SharedPreferencesUtils getSharedPreferencesUtils() {
		if(sharedPreferences==null){
			sharedPreferences=new SharedPreferencesUtils(this);
		}
		return sharedPreferences;
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
