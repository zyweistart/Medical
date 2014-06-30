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

import com.start.core.AppException;
import com.start.core.Constant;
import com.start.medical.R;
import com.start.utils.NetConnectManager;
import com.start.utils.StringUtils;
import com.start.utils.TimeUtils;

/**
 * @author weizy   
 * @Description: 网络请求执行类
 * @ClassName: HttpServer.java   
 * @date 2014年6月27日 下午3:45:19      
 * @说明  代码版权归 杭州反盗版中心有限公司 所有
 */
@SuppressWarnings("unused")
public class HttpServer {
	
	private String mUrl;
	private Map<String,String> mHeaders;
	private Map<String,String>mParams;
	private Handler mHandler;
	private Context mContext;
	
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
			
			final ProgressDialog mPDialog = new ProgressDialog(mContext);
			mPDialog.setMessage(mContext.getString(R.string.wait));
			mPDialog.setIndeterminate(true);
			mPDialog.setCancelable(true);
			mPDialog.show();
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					
					try{
						String requestContent=buildRequestContentByString();
						//TODO:执行网络请求
						Response response=new Response();
						//TODO:解析并生成响应对象
						runnable.run(response);
					}catch(AppException e){
						e.makeToast(mContext);
					}finally{
						if (mPDialog != null) {
							mPDialog.dismiss();
						}
					}
					
				}}).start();
			
		}else{
			UIHelper.goSettingNetwork(mContext);
		}
		
	}
	
	/**
	 * 获取列表数据
	 */
	public void gets(UIRunnable runnable){
		Response response=new Response();
		//更新UI
		runnable.run(response);
	}
	
	/**
	 * 生成请求内容
	 */
	public String buildRequestContentByString() throws AppException{
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
	 * 请求并获取响应的字符串数据
	 */
	public String getResponseByString(String requestContent) throws AppException {
		try {
			HttpResponse response = getResponse(requestContent);
			BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line =null;
			StringBuffer buffer = new StringBuffer();
			while ((line = in.readLine()) != null) {
				buffer.append(line);
			}
			return buffer.toString();
		} catch (Exception e) {
			throw AppException.http(e);
		}
	}

	/**
	 * 请求并获取响应的流对象
	 */
	public InputStream getResponseByInputStream(String requestContent) throws AppException {
		try {
			HttpResponse response = getResponse(requestContent);
			return response.getEntity().getContent();
		} catch (Exception e) {
			throw AppException.http(e);
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