package com.start.medical;

import java.util.HashMap;
import java.util.Map;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

import com.start.core.AppException;
import com.start.core.BaseActivity;
import com.start.core.Constant;
import com.start.service.Response;
import com.start.service.UIHelper;
import com.start.service.UIRunnable;
import com.start.utils.HttpResponse;
import com.start.utils.LogUtils;
import com.start.utils.MD5;
import com.start.utils.NetConnectManager;
import com.start.utils.StringUtils;

/**
 * 应用容器
 * @author start
 *
 */
public class AppContext extends Application {
	
  @Override
	public void onCreate() {
		super.onCreate();
        //注册App异常崩溃处理器
        Thread.setDefaultUncaughtExceptionHandler(AppException.getAppExceptionHandler());
	}
	
	/**
	 * 请求获取列表数据
	 */
	public void sendRequestRefreshList(){
		
	}
	
	/**
	 * 请求获取单条记录数据
	 */
	public void sendRequestRefreshSingle(BaseActivity activity,String url,final Map<String,String> headers,final Map<String,String> params,final UIRunnable runnable){
		if(NetConnectManager.isNetworkConnected(activity)){
			final ProgressDialog pDialog = new ProgressDialog(activity);
			pDialog.setMessage(getString(R.string.wait));
			pDialog.setIndeterminate(true);
			pDialog.setCancelable(false);
			pDialog.show();
			new Thread(new Runnable() {
				@Override
				public void run() {
					try{
						//TODO：生成请求内容
						String requestContent = "";
						//请求头内容
						Map<String,String> requestHeader=new HashMap<String,String>();
						if(headers!=null){
							requestHeader.putAll(headers);
						}
						//签名为特殊处理key为不存在时默认用ACCESSKEY签名为""用MD5否则用传入的值进行签名
						if(!requestHeader.containsKey("sign")){
							requestHeader.put("sign",StringUtils.signatureHmacSHA1(MD5.md5(requestContent),Constant.ACCESSKEY));
						}else{
							requestHeader.put("sign","".equals(requestHeader.get("sign"))?
									MD5.md5(requestContent):StringUtils.signatureHmacSHA1(MD5.md5(requestContent),requestHeader.get("sign")));
						}
						Response response=HttpResponse.resolve();
						if("10000".equals(response.getCode())){
							runnable.run();
						}else{
							
						}
					}catch(AppException e){
						LogUtils.logError(e);
						e.makeToast(AppContext.this);
					}finally{
						if (pDialog != null) {
							pDialog.dismiss();
						}
					}
				}}).start();
		}else{
			UIHelper.goSettingNetwork(activity);
		}
		
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