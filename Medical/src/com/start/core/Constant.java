package com.start.core;

public final class Constant {

	public static final int PAGESIZE=8;
	public static final String EMPTYSTR = "";
	public static final String ENCODE = "UTF-8";
	
	/**
	 * true:测试环境 false:正式环境
	 */
	public static final boolean ISSYSTEMTEST =true;

	public static final String RESTURL = ISSYSTEMTEST ?
			"http://account.chinacloudapp.cn:83/http/HttpService":"http://account.chinacloudapp.cn:82/http/HttpService";

	public static String ACCESSID = Constant.EMPTYSTR;
	public static String ACCESSKEY = Constant.EMPTYSTR;
	public static final String ACCESSID_LOCAL = ISSYSTEMTEST ? 
			"4a069ace58ba0918a3aa11b62b472b9e": "e4e60d050f5513e086b47c4fdca35646";
	public static final String ACCESSKEY_LOCAL = ISSYSTEMTEST ? 
			"MThjMDE3ZjAwMTE3NTIzNmE4OTM2ZjQ5N2M2Y2JhNDc=": "fUETGmOIiMujnipQnFm6rJIyO2wJDZSftsK4dwmmoSZ=";
	
	/**
	 * 用户接口
	 */
	public final class URL {
		/**
		 * 校验码获取
		 */
		public static final String useracodeGet="useracodeGet";
		/**
		 * 注册
		 */
		public static final String userReg="userReg";
		/**
		 * 密码重置
		 */
		public static final String userpwdReset="userpwdReset";
		/**
		 * 登录
		 */
		public static final String userLogin="userLogin";
		/**
		 * 退出
		 */
		public static final String userLogout="userLogout";
		/**
		 * 用户信息获取
		 */
		public static final String userinfoGet="userinfoGet";
		/**
		 * 密码修改
		 */
		public static final String userpwdMod="userpwdMod";
		/**
		 * 个人资料编辑
		 */
		public static final String userinfoEdit="userinfoEdit";
		/**
		 * 就诊人列表
		 */
		public static final String userpatientList="userpatientList";
		/**
		 * 就诊人详情
		 */
		public static final String userpatientDetail="userpatientDetail";
		/**
		 * 就诊人增加
		 */
		public static final String userpatientAdd="userpatientAdd";
		/**
		 * 就诊人修改
		 */
		public static final String userpatientModify="userpatientModify";
		/**
		 * 就诊人删除
		 */
		public static final String userpatientDelete="userpatientDelete";
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