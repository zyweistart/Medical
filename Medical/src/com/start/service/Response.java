package com.start.service;

public class Response {
	
	private String code;
	
	private String msg;
	
	private String responseString;
	
	public Response(String responseString) {
		this.responseString = responseString;
		//TODO:解析
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public String getResponseString() {
		return responseString;
	}
	
}