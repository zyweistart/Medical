package com.start.service;




public abstract class UIRunnable{
	
	private Response response;

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}
	
	public abstract void run();
	
}
