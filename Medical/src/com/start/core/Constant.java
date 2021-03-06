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
			"http://account.chinacloudapp.cn:82/hzpbws/http/HttpService":"http://account.chinacloudapp.cn:82/http/HttpService";

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
		 * 科室查询
		 */
		public static final String hisdeptQuery="hisdeptQuery";
		/**
		 * 医生查询
		 */
		public static final String hisdoctorQuery="hisdoctorQuery";
		/**
		 * 医生详情
		 */
		public static final String hisdoctorDetail="hisdoctorDetail";
		/**
		 * 健康百科-疾病查询
		 */
		public static final String htwikidisQuery="htwikidisQuery";
		/**
		 * 健康百科-疾病详情
		 */
		public static final String htwikidisDetail="htwikidisDetail";
		/**
		 * 健康资讯-新闻查询
		 */
		public static final String htinfonewsQuery="htinfonewsQuery";
		/**
		 * 健康资讯-新闻详情
		 */
		public static final String htinfonewsDetail="htinfonewsDetail";
		/**
		 * 就诊信息绑定
		 */
		public static final String hisinfoBind="hisinfoBind";
	}
	
	public final class Handler {
		
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
		 * 加载结束
		 */
		public static final int LOAD_END=0x1117;
		/**
		 * 无法加载数据时清除数据
		 */
		public static final int LOAD_DATA_FAIL_CLEAR_DATA=0x1118;
		/**
		 * 注册重置密码步骤1
		 */
		public static final int REGISTER_RESET_PASSWORD_STEP1=0x1119;
		/**
		 * 注册重置密码步骤2
		 */
		public static final int REGISTER_RESET_PASSWORD_STEP2=0x1120;
		
	}

	public final class ResultCode{
		/**
		 * 成功
		 */
		public static final int SUCCESS=100000;
		/**
		 * 用户名或密码有误
		 */
		public static final int USERNAMEORPASSWORDERROR=110036;
		/**
		 * 暂无记录
		 */
		public static final int NODATA=430;
		/**
		 * 登录信息过期，请重新登录
		 */
		public static final int NOLOGIN=414;
		
	}
	
	public final class Preferences {
		/**
		 * 用户账户
		 */
		public static final String SP_ACCOUNT_CONTENT_DATA = "SP_ACCOUNT_DATA";
		/**
		 * 用户密码
		 */
		public static final String SP_PASSWORD_CONTENT_DATA = "SP_PASSWORD_DATA";
		/**
		 * 自动登录
		 */
		public static final String SP_AUTOLOGIN_CONTENT_DATA = "SP_AUTOLOGIN_CONTENT_DATA";
		
	}
	
}