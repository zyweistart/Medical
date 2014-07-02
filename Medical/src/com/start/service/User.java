package com.start.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.start.core.AppException;
import com.start.core.Constant;
import com.start.core.Constant.SharedPreferences;
import com.start.medical.AppContext;

public class User {

	private Boolean login;
	private static User mUser;
	private AppContext mContext;
	private Map<String,String> info;
	
	private User(){}
	
	public static User getInstance(AppContext mContext){
		if(mUser==null){
			mUser=new User();
			mUser.mContext=mContext;
		}
		return mUser;
	}
	
	public Boolean isLogin() {
		return login;
	}

	public void setLogin(Boolean login) {
		this.login = login;
	}

	public Map<String, String> getInfo() {
		if(info==null){
			info=new HashMap<String,String>();
		}
		return info;
	}
	
	public void addCacheUser(String account,String password,Boolean autoLogin){
		mContext.getSharedPreferencesUtils().putString(SharedPreferences.SP_ACCOUNT_CONTENT_DATA, account);
		mContext.getSharedPreferencesUtils().putBoolean(SharedPreferences.SP_AUTOLOGIN_CONTENT_DATA, autoLogin);
		if(autoLogin){
			mContext.getSharedPreferencesUtils().putString(SharedPreferences.SP_PASSWORD_CONTENT_DATA, password);
		}
	}
	
	public void clearCacheUser(){
		setLogin(false);
		mContext.getSharedPreferencesUtils().putString(SharedPreferences.SP_ACCOUNT_CONTENT_DATA, Constant.EMPTYSTR);
		mContext.getSharedPreferencesUtils().putString(SharedPreferences.SP_PASSWORD_CONTENT_DATA, Constant.EMPTYSTR);
		mContext.getSharedPreferencesUtils().putBoolean(SharedPreferences.SP_AUTOLOGIN_CONTENT_DATA, false);
	}
	
	public void clearCachePassword(){
		mContext.getSharedPreferencesUtils().putString(SharedPreferences.SP_PASSWORD_CONTENT_DATA, Constant.EMPTYSTR);
	}
	
	@SuppressWarnings("unchecked")
	public void resolveByJSON(JSONObject userinfo) throws AppException{
		try {
			setLogin(true);
			Iterator<String> i=userinfo.keys();
			while(i.hasNext()){
				String key=i.next();
				getInfo().put(key,userinfo.getString(key));
			}
			Constant.ACCESSID=getInfo().get("accessid");
			Constant.ACCESSKEY=getInfo().get("accesskey");
		} catch (JSONException e) {
			throw AppException.json(e);
		}
	}
	
}