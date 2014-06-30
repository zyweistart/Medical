package com.start.core;

public final class Constant {

	public static final int PAGESIZE=8;
	public static final String EMPTYSTR = "";
	public static final String ENCODE = "UTF-8";
	
	/**
	 * true:测试环境 false:正式环境
	 */
	public static final boolean ISSYSTEMTEST =false;

	public static final String RESTURL = ISSYSTEMTEST ?
			"http://192.168.0.221:8888/accore/http/HttpService":"http://server.ancun.com:3391/rest/RestService";

	/**
	 * 通行证编号
	 */
	public static String ACCESSID = "";
	
	/**
	 * 通行证密钥
	 */
	public static String ACCESSKEY = "";
	
	/**
	 * 本地通行证编号
	 */
	public static final String ACCESSID_LOCAL = ISSYSTEMTEST ? 
			"b99810e661d546f14763f15a9270c28d": "e4e60d050f5513e086b47c4fdca35646";
	
	/**
	 * 本地通行证密钥
	 */
	public static final String ACCESSKEY_LOCAL = ISSYSTEMTEST ? 
			"aRxzVdMiopnzpJco3fPJOmXSDZL7rGiL3UgbqQA9YeJ=": "fUETGmOIiMujnipQnFm6rJIyO2wJDZSftsK4dwmmoSZ=";
	
	/**
	 * 用户接口
	 */
	public final class URL {
		
	}
	
	public final class Handle {
		
		/**
		 * 加载数据
		 */
		public static final int LOAD_DATA=0x1111;
		/**
		 * 初始化加载
		 */
		public static final int LOAD_INIT_DATA=0x1112;
		/**
		 * 下拉刷新开始
		 */
		public static final int LOAD_START_PULLDOWN_REFRESH_DATA=0x1113;
		/**
		 * 下拉刷新结束
		 */
		public static final int LOAD_END_PULLDOWN_REFRESH_DATA=0x1114;
		/**
		 * 加载更多开始
		 */
		public static final int LOAD_START_MORE_DATA=0x1115;
		/**
		 * 加载更多结束
		 */
		public static final int LOAD_END_MORE_DATA=0x1116;
		/**
		 * 无法加载数据时清除数据
		 */
		public static final int LOAD_DATA_FAIL_CLEAR_DATA=0x1117;
		
	}

	/**
	 * SharedPreferences
	 */
	public final class SharedPreferences {
		
		public static final String SP_ACCOUNT_CONTENT_DATA = "SP_ACCOUNT_DATA";
		public static final String SP_PASSWORD_CONTENT_DATA = "SP_PASSWORD_DATA";
		public static final String SP_AUTOLOGIN_CONTENT_DATA = "SP_AUTOLOGIN_CONTENT_DATA";
		
	}
	
}