package com.start.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;

import com.start.core.AppException;

public class Response {
	
	private String code;
	
	private String msg;
	
	private String responseString;
	
	private HttpResponse httpResponse;
	
	public Response(HttpResponse httpResponse) {
		this.httpResponse = httpResponse;
		//TODO:解析
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public String getResponseString() throws AppException {
		if(responseString==null){
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(this.httpResponse.getEntity().getContent()));
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

	public HttpResponse getHttpResponse() {
		return httpResponse;
	}
	
}