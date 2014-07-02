package com.start.service;

import java.util.HashMap;
import java.util.Map;

public class User {
	
	private User(){
		
	}
	
	private static User mUser;
	
	public User getInstance(){
		if(mUser==null){
			mUser=new User();
		}
		return mUser;
	}
	
	private Map<String,String> info;

	public Map<String, String> getInfo() {
		if(info==null){
			info=new HashMap<String,String>();
		}
		return info;
	}
	
	public void addCache(String account,String password,String flag){
		
	}
	
	public void clear(){
		
	}
	
	
}
