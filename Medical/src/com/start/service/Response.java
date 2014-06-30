package com.start.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.json.JSONException;
import org.json.JSONObject;

import com.start.core.AppException;

public class Response {
	
	private String mCode;
	private String mMsg;
	private JSONObject mJsonObject;
	private HttpResponse mHttpResponse;

	public String getCode(){
		return mCode;
	}

	public String getMsg() {
		return mMsg;
	}
	
	public Response(HttpResponse httpResponse) {
		this.mHttpResponse=httpResponse;
	}
	
	/**
	 * 请求并获取响应的流对象
	 */
	public InputStream getInputStream() throws AppException{
		try {
			return mHttpResponse.getEntity().getContent();
		} catch (Exception e) {
			throw AppException.http(e);
		}
	}
	
	/**
	 * 请求并获取响应的字符串数据
	 */
	public String getResponseByString() throws AppException {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(getInputStream()));
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
	 * 解析XML
	 */
	public void resolveXML() throws AppException {
		
	}
	
	/**
	 * 解析JSON
	 */
	public void resolveJson() throws AppException {
		try {
			this.mJsonObject = new JSONObject(getResponseByString());
			if (!mJsonObject.isNull("code")) {
				this.mCode=mJsonObject.getString("code");
			}
			if (!mJsonObject.isNull("msg")) {
				this.mMsg=mJsonObject.getString("msg");
			}
		} catch (JSONException e) {
			throw AppException.json(e);
		}
	}
	
	/**
	 * 文件下载
	 * @param path 下载文件存储的路径
	 */
	public void downloadFile(String path) throws AppException{
		
	}
	
}