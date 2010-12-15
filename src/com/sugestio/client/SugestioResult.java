package com.sugestio.client;

public class SugestioResult {

	private boolean ok;
	private int code;
	private String message;
	
	public SugestioResult() {
		
	}
	
	public SugestioResult(boolean ok, int code, String message) {
		this.ok = ok;
		this.setCode(code);
		this.message = message;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public boolean isOk() {
		return ok;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
