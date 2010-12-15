package com.sugestio.client;

public class SugestioResult {

	private boolean ok;
	private int code;
	private String message;
	
	/**
	 * Creates a new instance of SugestioResult.
	 */
	public SugestioResult() {
		
	}
	
	/**
	 * Creates a new instance of SugestioResult.
	 * @param ok request indicator
	 * @param code HTTP response code received from the server
	 * @param message textual response received from the server 
	 */
	public SugestioResult(boolean ok, int code, String message) {
		this.ok = ok;
		this.setCode(code);
		this.message = message;
	}

	/**
	 * Set the success indicator.
	 * @param ok
	 */
	public void setOk(boolean ok) {
		this.ok = ok;
	}

	/**
	 * Indicates if the request was successful. 
	 * Returns false if there was a server side or client side problem.
	 * @return success
	 */
	public boolean isOk() {
		return ok;
	}

	/**
	 * Set the textual response from the server.
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the textual response from the server (if any), 
	 * or a problem description in case of a local error.
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Set the HTTP response code.
	 * @param code
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * Gets the HTTP response code received from the server. 
	 * Returns -1 if the request never reached the server due to a client side problem, 
	 * such as a network issue.
	 * @return the code
	 */
	public int getCode() {
		return code;
	}
}
