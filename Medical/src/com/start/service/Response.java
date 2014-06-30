package com.start.service;

import org.json.JSONException;
import org.json.JSONObject;

public class Response {

	private String code;

	private String msg;

	public Response() {
		
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public void resolve(String JsonString) throws JSONException {
		JSONObject jsonObject = new JSONObject(JsonString);
		if (!jsonObject.isNull("code")) {
			this.code=jsonObject.getString("code");
		}
		if (!jsonObject.isNull("msg")) {
			this.code=jsonObject.getString("msg");
		}
	}

}