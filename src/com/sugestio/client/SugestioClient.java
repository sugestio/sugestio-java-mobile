package com.sugestio.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class SugestioClient {
	
	private String baseUri = "http://api.sugestio.com";
	private HttpClient httpClient;
	private String account;
		
	
	/**
	 * Creates a new instance of the SugestioClient with the given access credentials.
	 * @param account your account key
	 */
	public SugestioClient(String account) {		
		this.account = account;
		this.httpClient = new DefaultHttpClient();			
	}
	
	/**
	 * Get personal recommendations for the given user.
	 * @param userid the user
	 * @param parameters query parameters
	 * @return recommendations
	 * @throws Exception
	 */
	public JsonArray getRecommendations(String userid, Map<String, String> parameters) throws Exception {
		
		JsonElement response = doGet("/users/" + userid + "/recommendations.json", parameters, false); 
		
		if (response != null)
			return response.getAsJsonArray();
		else
			return new JsonArray();
		
	}
	
	/**
	 * Get similar item recommendations for the given item.
	 * @param itemid the item
	 * @param parameters query parameters
	 * @return similar item recommendations
	 * @throws Exception
	 */
	public JsonArray getSimilarItems(String itemid, Map<String, String> parameters) throws Exception {
		
		JsonElement response = doGet("/users/" + itemid + "/similar.json", parameters, false); 
		
		if (response != null)
			return response.getAsJsonArray();
		else
			return new JsonArray();
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

	private JsonElement doGet(String resource, Map<String, String> parameters, boolean raise404) throws Exception {
		
		List<NameValuePair> queryParams = new ArrayList<NameValuePair>(); // todo
		//URLEncodedUtils.format(null, "UTF-8");
		
		try {
			
			HttpGet httpGet = new HttpGet(getUri(resource));
			HttpResponse httpResponse = httpClient.execute(httpGet);
			String body = EntityUtils.toString(httpResponse.getEntity());
			int code = httpResponse.getStatusLine().getStatusCode();
			
			if (code == 200) {			
				JsonParser parser = new JsonParser();				
				return parser.parse(body);
			} else if (code == 404 && !raise404) {
				return null;
			} else {
				String message = "Response code " + httpResponse.getStatusLine().getStatusCode() + ". ";
				message += body; 
				throw new Exception(message);
			}
			
		} catch (Exception e) {
			httpClient.getConnectionManager().shutdown();
			throw e;
		}		
		
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
