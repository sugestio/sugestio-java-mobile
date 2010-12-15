/**
 * The MIT License
 * 
 * Copyright (c) 2010 Sugestio
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:

 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
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
