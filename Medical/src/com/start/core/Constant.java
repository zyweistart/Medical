package com.start.core;

public final class Constant {
	
	/**
	 * true:测试环境 false:正式环境
	 */
	public static final boolean ISSYSTEMTEST =false;

	public static final String RESTURL = ISSYSTEMTEST ?
	 "http://192.168.0.221:8888/accore/http/HttpService"://测试地址
	"http://server.ancun.com:3391/rest/RestService";// 正式地址
	
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
	public static final String ACCESSID_LOCAL = ISSYSTEMTEST ? "b99810e661d546f14763f15a9270c28d"
			: "e4e60d050f5513e086b47c4fdca35646";
	
	/**
	 * 本地通行证密钥
	 */
	public static final String ACCESSKEY_LOCAL = ISSYSTEMTEST ? "aRxzVdMiopnzpJco3fPJOmXSDZL7rGiL3UgbqQA9YeJ="
			: "fUETGmOIiMujnipQnFm6rJIyO2wJDZSftsK4dwmmoSZ=";
	
	/**
	 * 空字符
	 */
	public static final String EMPTYSTR = "";
	
	/**
	 * 编码
	 */
	public static final String ENCODE = "UTF-8";

	public final static int LISTVIEW_DATA_MORE = 0x01;
	public final static int LISTVIEW_DATA_LOADING = 0x02;
	public final static int LISTVIEW_DATA_FULL = 0x03;
	public final static int LISTVIEW_DATA_EMPTY = 0x04;

	public final static int PAGESIZE = 8;

	/**
	 * SharedPreferences常量
	 * 
	 * @author Start
	 */
	public final class SharedPreferences {
		/**
		 * 提示网络连接信息
		 */
		public static final String SP_ALIYUN_NETWORK_MESSAGE = "SP_ALIYUN_NETWORK_MESSAGE";
	}
	
	/**
	 * Handle消息处理常量
	 * 
	 * @author start
	 */
	public final class Handle {
		
	}

	/**
	 * 用户接口
	 * 
	 * @author Start
	 */
	public final class URL {
		/**
		 * 检查手机号码是否已被注册
		 */
		public static final String v4Check = "v4Check";
	}
	
}