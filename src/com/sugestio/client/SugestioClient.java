package com.sugestio.client;

import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;


public class SugestioClient {
	
	private HttpClient httpClient;
	private String account;
	
	
	public SugestioClient(String account) {
		this.account = account;
		this.httpClient = new DefaultHttpClient();
	}

	private void doGet(String resource, Map parameters) {
				
	}
	
	private void doPost (String resource, Map parameters) {
		
	}
}
