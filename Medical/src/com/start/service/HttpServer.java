package com.start.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;

import com.start.core.AppException;
import com.start.core.Constant;
import com.start.medical.R;
import com.start.utils.MD5;
import com.start.utils.NetConnectManager;
import com.start.utils.StringUtils;
import com.start.utils.TimeUtils;

/**
 * @author Start   
 * @Description: 网络请求执行类
 * @ClassName: HttpServer.java   
 * @date 2014年6月27日 下午3:45:19      
 * @说明  代码版权归 杭州反盗版中心有限公司 所有
 */
@SuppressWarnings("unused")
public class HttpServer {
	
	private String mUrl;
	private Handler mHandler;
	private Context mContext;
	private Map<String,String> mHeaders;
	private Map<String,String>mParams;
	private ProgressDialog mPDialog;
	
	public HttpServer(String mUrl, Context mContext, Handler mHandler) {
		this.mUrl = mUrl;
		this.mContext = mContext;
		this.mHandler = mHandler;
	}
	
	public void setHeaders(Map<String, String> mHeaders) {
		this.mHeaders = mHeaders;
	}

	public void setParams(Map<String, String> mParams) {
		this.mParams = mParams;
	}
	
	/**
	 * 请求获取数据
	 */
	public void get(final UIRunnable runnable){
		
		if(NetConnectManager.isNetworkConnected(mContext)){
			
			showDialog();
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					
					try{
						String requestContent=buildRequestContentByStringJson();
						if(mHeaders!=null){
							if(mHeaders.containsKey("sign")){
								mHeaders.put("sign","".equals(mHeaders.get("sign"))?
										MD5.md5(requestContent):StringUtils.signatureHmacSHA1(MD5.md5(requestContent),mHeaders.get("sign")));
							}else{
								mHeaders.put("sign",StringUtils.signatureHmacSHA1(MD5.md5(requestContent),Constant.ACCESSKEY));
							}
						}
						HttpResponse httpResponse=getResponse(requestContent);
						Response response=new Response(httpResponse);
						runnable.run(response);
					}catch(AppException e){
						e.makeToast(mContext);
					}finally{
						dismissDialog();
					}
					
				}}).start();
			
		}else{
			UIHelper.goSettingNetwork(mContext);
		}
		
	}
	
	/**
	 * 获取列表数据
	 */
	public void gets(final UIRunnable runnable){
		if(NetConnectManager.isNetworkConnected(mContext)){
			
			showDialog();
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					
					try{
						String requestContent=buildRequestContentByStringJson();
						if(mHeaders!=null){
							if(mHeaders.containsKey("sign")){
								mHeaders.put("sign","".equals(mHeaders.get("sign"))?
										MD5.md5(requestContent):StringUtils.signatureHmacSHA1(MD5.md5(requestContent),mHeaders.get("sign")));
							}else{
								mHeaders.put("sign",StringUtils.signatureHmacSHA1(MD5.md5(requestContent),Constant.ACCESSKEY));
							}
						}
						HttpResponse httpResponse=getResponse(requestContent);
						Response response=new Response(httpResponse);
						response.resolveJson();
						runnable.run(response);
					}catch(AppException e){
						e.makeToast(mContext);
					}finally{
						dismissDialog();
					}
					
				}}).start();
			
		}else{
			UIHelper.goSettingNetwork(mContext);
		}
	}
	
	private void showDialog(){
		mPDialog = new ProgressDialog(mContext);
		mPDialog.setMessage(mContext.getString(R.string.wait));
		mPDialog.setIndeterminate(true);
		mPDialog.setCancelable(true);
		mPDialog.show();
	}
	
	private void dismissDialog(){
		if (mPDialog != null) {
			mPDialog.dismiss();
		}
	}
	
	/**
	 * 生成请求内容
	 */
	public String buildRequestContentByStringJson() throws AppException{
		JSONObject commonObject = new JSONObject();
		JSONObject contentObject = new JSONObject();
		JSONObject requestObject = new JSONObject();
		JSONObject mainObject = new JSONObject();
	    try {
	    	commonObject.put("action",mUrl);
	    	commonObject.put("reqtime",TimeUtils.getSysTimeLong());
	    	requestObject.put("common", commonObject);
	    	
	    	if(mParams!=null){
	    		for(String key:mParams.keySet()){
	    			contentObject.put(key,mParams.get(key));
	    		}
	    	}
	    	requestObject.put("content", contentObject);
	    	
	    	mainObject.put("request", requestObject);
			return mainObject.toString();
		} catch (JSONException e) {
			throw AppException.json(e);
		}  
	}
	
	/**
	 * 请求并获取响应的HTTP对象
	 */
	public HttpResponse getResponse(String requestContent) throws AppException {
		HttpClient client = new DefaultHttpClient();
		ProtocolVersion protocolVersion =new ProtocolVersion("HTTP", 1, 0);
		client.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, protocolVersion);
		client.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, Constant.ENCODE);
		// 设置超时时间为30秒
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,30000);
		HttpPost post = new HttpPost(Constant.RESTURL);
		try {
			post.addHeader("reqlength", StringUtils.encode(String.valueOf(requestContent.getBytes(Constant.ENCODE).length)));
			if (mHeaders != null) {
				for (String key : mHeaders.keySet()) {
					// 循环遍历添加请求头对请求头内容进行URL编码
					post.addHeader(key,StringUtils.encode(mHeaders.get(key)));
				}
			}
			post.setEntity(new ByteArrayEntity(requestContent.getBytes(Constant.ENCODE)));
			return client.execute(post);
		} catch (Exception e) {
			throw AppException.http(e);
		}
	}
	
}