package com.start.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.json.JSONException;
import org.json.JSONObject;

import com.start.core.AppException;
import com.start.utils.StringUtils;

public class Response {
	
	private String mCode;
	private String mMsg;
	private String responseString;
	private JSONObject mJsonObject;
	private JSONObject mResponseInfo;
	private JSONObject mResponsePage;
	private JSONObject mResponseContent;
	
	private HttpResponse mHttpResponse;

	public String getCode() throws AppException{
		if(StringUtils.isEmpty(mCode)){
			try {
				if (!mResponseInfo.isNull("code")) {
					this.mCode=mResponseInfo.getString("code");
				}
			} catch (JSONException e) {
				throw AppException.json(e);
			}
		}
		return mCode;
	}

	public String getMsg() throws AppException {
		if(StringUtils.isEmpty(mMsg)){
			try {
				if (!mResponseInfo.isNull("msg")) {
					this.mMsg=mResponseInfo.getString("msg");
				}
			} catch (JSONException e) {
				throw AppException.json(e);
			}
		}
		return mMsg;
	}
	
	public JSONObject getResponsePage() throws AppException {
		if(mResponsePage==null){
			try {
				mResponsePage=this.mJsonObject.getJSONObject("pageinfo");
			} catch (JSONException e) {
				throw AppException.json(e);
			}
		}
		return mResponsePage;
	}
	
	public JSONObject getResponseContent() throws AppException {
		if(mResponseContent==null){
			try {
				mResponseContent=this.mJsonObject.getJSONObject("content");
			} catch (JSONException e) {
				throw AppException.json(e);
			}
		}
		return mResponseContent;
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
	public String getResponseString() throws AppException {
		if(responseString==null){
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(getInputStream()));
				String line =null;
				StringBuffer buffer = new StringBuffer();
				while ((line = in.readLine()) != null) {
					buffer.append(line);
				}
				responseString=buffer.toString();
			} catch (Exception e) {
				throw AppException.http(e);
			}
		}
		return responseString;
	}
	
	/**
	 * 解析JSON
	 */
	public void resolveJson() throws AppException {
		try {
			JSONObject jo = new JSONObject(getResponseString());
			this.mJsonObject=jo.getJSONObject("response");
			mResponseInfo=this.mJsonObject.getJSONObject("info");
		} catch (JSONException e) {
			throw AppException.json(e);
		}
	}
	
}