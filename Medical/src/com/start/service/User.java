package com.start.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.start.core.AppException;
import com.start.core.Constant;
import com.start.core.Constant.Preferences;
import com.start.medical.AppContext;

public class User {

	public static String ACCESSID = Constant.EMPTYSTR;
	public static String ACCESSKEY = Constant.EMPTYSTR;
	public static final String ACCESSID_LOCAL = Constant.ISSYSTEMTEST ? 
			"4a069ace58ba0918a3aa11b62b472b9e": "e4e60d050f5513e086b47c4fdca35646";
	public static final String ACCESSKEY_LOCAL = Constant.ISSYSTEMTEST ? 
			"MThjMDE3ZjAwMTE3NTIzNmE4OTM2ZjQ5N2M2Y2JhNDc=": "fUETGmOIiMujnipQnFm6rJIyO2wJDZSftsK4dwmmoSZ=";
	
	/**
	 * 用户登陆标记
	 */
	private Boolean login;
	private static User mUser;
	/**
	 * 用户信息
	 */
	private Map<String,String> info;
	
	private User(){}
	
	public static User getInstance(){
		if(mUser==null){
			mUser=new User();
			mUser.setLogin(false);
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
	
	/**
	 * 用户信息加入缓存
	 */
	public void addCacheUser(String account,String password,Boolean autoLogin){
		AppContext.getSharedPreferences().putString(Preferences.SP_ACCOUNT_CONTENT_DATA, account);
		AppContext.getSharedPreferences().putBoolean(Preferences.SP_AUTOLOGIN_CONTENT_DATA, autoLogin);
		if(autoLogin){
			AppContext.getSharedPreferences().putString(Preferences.SP_PASSWORD_CONTENT_DATA, password);
		}
	}
	
	public void clearCacheUser(){
		setLogin(false);
		AppContext.getSharedPreferences().putString(Preferences.SP_ACCOUNT_CONTENT_DATA, Constant.EMPTYSTR);
		AppContext.getSharedPreferences().putString(Preferences.SP_PASSWORD_CONTENT_DATA, Constant.EMPTYSTR);
		AppContext.getSharedPreferences().putBoolean(Preferences.SP_AUTOLOGIN_CONTENT_DATA, false);
	}
	
	public void clearCachePassword(){
		AppContext.getSharedPreferences().putString(Preferences.SP_PASSWORD_CONTENT_DATA, Constant.EMPTYSTR);
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
			User.ACCESSID=getInfo().get("accessid");
			User.ACCESSKEY=getInfo().get("accesskey");
		} catch (JSONException e) {
			throw AppException.json(e);
		}
	}
	
}