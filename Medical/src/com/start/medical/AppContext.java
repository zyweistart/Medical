package com.start.medical;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.start.core.AppException;
import com.start.core.BaseDBManageDao;
import com.start.core.Constant;
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
    private static SharedPreferencesUtils sharedPreferences; 
    private static BaseDBManageDao dbManager;
    private static  SQLiteDatabase mSQLiteDatabase;
	
    @Override
    public void onCreate() {
        super.onCreate();
        if(!Constant.ISSYSTEMTEST){
        	//注册App异常崩溃处理器
        	Thread.setDefaultUncaughtExceptionHandler(AppException.getAppExceptionHandler());
        }
    	mContext = getApplicationContext();
    	sharedPreferences=new SharedPreferencesUtils(this);
        getDBManager();
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
     * 获取数据库管理对象
     */
    public static BaseDBManageDao getDBManager(){
		if(dbManager == null){
			dbManager = new BaseDBManageDao(getContext());
		}
		return dbManager;
	}
    
    /**
     * 获取数据库操作类 
     */
    public static SQLiteDatabase getSQLiteDatabase() {
    	if(null == mSQLiteDatabase){
    		mSQLiteDatabase = getDBManager().getSQLiteDatabase();
    	}
    	return mSQLiteDatabase;
	}
    
}
