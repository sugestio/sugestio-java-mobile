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
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.sugestio.client;

import java.lang.reflect.Type;
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

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.sugestio.client.model.Consumption;
import com.sugestio.client.model.Item;
import com.sugestio.client.model.Recommendation;
import com.sugestio.client.model.User;


public class SugestioClient {
	
	private String baseUri = "http://api.sugestio.com";
	private HttpClient httpClient;
	private Gson gson;
	private String account;
		
	
	/**
	 * Creates a new instance of the SugestioClient with the given access credentials.
	 * @param account your account key
	 */
	public SugestioClient(String account) {		
		this.account = account;		
		this.httpClient = new DefaultHttpClient();
		this.gson = new Gson();
	}
	
	/**
	 * Get personal recommendations for the given user.
	 * @param userid the user
	 * @param parameters query parameters
	 * @return recommendations
	 * @throws Exception
	 */
	public List<Recommendation> getRecommendations(String userid, Map<String, String> parameters) throws Exception {
		
		JsonElement response = doGet("/users/" + userid + "/recommendations.json", parameters, false); 
		
		if (response != null) {			
			Type listType = new TypeToken<List<Recommendation>>() {}.getType();
			return gson.fromJson(response.getAsJsonArray(), listType);
		} else {
			return new ArrayList<Recommendation>();
		}
		
	}
	
	/**
	 * Get similar item recommendations for the given item.
	 * @param itemid the item
	 * @param parameters query parameters
	 * @return similar item recommendations
	 * @throws Exception
	 */
	public List<Recommendation> getSimilarItems(String itemid, Map<String, String> parameters) throws Exception {
		
		JsonElement response = doGet("/items/" + itemid + "/similar.json", parameters, false); 
		
		if (response != null) {			
			Type listType = new TypeToken<List<Recommendation>>() {}.getType();
			return gson.fromJson(response.getAsJsonArray(), listType);
		} else {
			return new ArrayList<Recommendation>();
		}
	} 
	
	/**
	 * Submit a consumption.
	 * @param consumption the consumption
	 * @return result
	 */
	public SugestioResult addConsumption(Consumption consumption) {
		return this.doPost("/consumptions", consumption);
	}
	
	/**
	 * Submit item meta data.
	 * @param item the item
	 * @return result
	 */
	public SugestioResult addItem(Item item) {
		return this.doPost("/items", item);
	}
	
	/**
	 * Submit user meta data.
	 * @param user the user
	 * @return result
	 */
	public SugestioResult addUser(User user) {
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
	 * @param object the object to submit
	 * @return result
	 */
	private SugestioResult doPost(String resource, Object object) {
		
		JsonObject jsonObject = gson.toJsonTree(object).getAsJsonObject();
		List<NameValuePair> parameters = jsonToNameValuePairs(jsonObject);
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
