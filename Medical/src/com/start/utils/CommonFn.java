package com.start.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

public class CommonFn {

	/**
	 * 获取本机号码
	 */
	public static String getPhoneNumber(Context context){ 
		//创建电话管理器
		TelephonyManager mTelephonyMgr;    
		//获取系统固定号码
		mTelephonyMgr = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
		//返回手机号码
		String phone=mTelephonyMgr.getLine1Number();
		if(phone==null){
			return "";
		}else if(phone.length()==11){
			return phone;
		}
		return "";
	}
	
}