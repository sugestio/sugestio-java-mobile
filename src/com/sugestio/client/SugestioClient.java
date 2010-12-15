package com.sugestio.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


public class SugestioClient {
	
	private String baseUri = "http://api.sugestio.com";
	private HttpClient httpClient;
	private String account;
	private ResponseHandler<byte[]> handler;
	
	
	/**
	 * Creates a new instance of the SugestioClient with the given access credentials.
	 * @param account your account key
	 */
	public SugestioClient(String account) {		
		this.account = account;
		this.httpClient = new DefaultHttpClient();			
	}
	
	/**
	 * Submit a consumption.
	 * @param consumption the consumption
	 * @return result
	 */
	public SugestioResult addConsumption(JsonObject consumption) {
		return this.doPost("/consumptions", consumption);
	}
	
	/**
	 * Submit item meta data.
	 * @param item the item
	 * @return result
	 */
	public SugestioResult addItem(JsonObject item) {
		return this.doPost("/items", item);
	}
	
	/**
	 * Submit user meta data.
	 * @param user the user
	 * @return result
	 */
	public SugestioResult addUser(JsonObject user) {
		return this.doPost("/users", user);
	}

	private JsonObject doGet(String resource, List<NameValuePair> parameters) {
		//URLEncodedUtils.format(null, "UTF-8");
		return null;
	}
	
	/**
	 * Performs a POST request to the given resource. Encodes given JSON object as form data.
	 * @param resource
	 * @param json JSON object with data fields
	 * @return result
	 */
	private SugestioResult doPost(String resource, JsonObject json) {
		
		List<NameValuePair> parameters = jsonToNameValuePairs(json);
		SugestioResult result = new SugestioResult();
		
		try {
			
			HttpPost httpPost = new HttpPost(getUri(resource));
			httpPost.setEntity(new UrlEncodedFormEntity(parameters, "UTF-8"));
			HttpResponse httpResponse = httpClient.execute(httpPost);            
			String message = EntityUtils.toString(httpResponse.getEntity());
			
			result.setCode(httpResponse.getStatusLine().getStatusCode());            
			result.setOk(result.getCode() == 202);            
			result.setMessage(message);            
 
		} catch (Exception e)  {
        	
			httpClient.getConnectionManager().shutdown();
			result.setOk(false);
			result.setCode(-1);
			result.setMessage(e.getMessage());            
		}
        
		return result;		
	}
	
	/**
	 * Builds the full request URI from the base URI, account and resource. 
	 * @param resource
	 * @return
	 */
	private String getUri(String resource) {
		return baseUri + "/sites/" + account + resource;
	}
	
	/**
	 * Converts a JSON object into NameValuePairs suitable which can be attached as query parameters
	 * when performing a GET or form data when performing a POST. 
	 * @param json the JSON object
	 * @return a list of NameValuePairs
	 */
	private List<NameValuePair> jsonToNameValuePairs(JsonObject json) {
	
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		
		for (Entry<String, JsonElement> entry : json.entrySet()) {
			
			if (entry.getValue().isJsonPrimitive()) {				
				pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue().getAsString()));				
			} else if (entry.getValue().isJsonArray()) {				
				JsonArray array = entry.getValue().getAsJsonArray();				
				for (int i=0; i<array.size(); i++) {
					pairs.add(new BasicNameValuePair(entry.getKey() + "[]", array.get(i).getAsString()));
				}
			}
		}
		
		return pairs;
	}
}
