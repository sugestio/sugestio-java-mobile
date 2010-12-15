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
package example;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.sugestio.client.SugestioClient;
import com.sugestio.client.SugestioResult;

public class Example {

	public static void main(String[] args) {
		Example.getRecommendations();
		//Example.addConsumption();
		//Example.addItem();
	}
	
	private static void getRecommendations() {
		
		SugestioClient client = new SugestioClient("sandbox");
		
		try {
			JsonArray recommendations = client.getRecommendations("1", null);
			Example.print(recommendations);
		} catch (Exception e) {			
			e.printStackTrace();
		}		
		
	}
	
	private static void addConsumption() {		
		
		SugestioClient client = new SugestioClient("sandbox");
		
		JsonObject consumption = new JsonObject();
		consumption.addProperty("userid", "1");
		consumption.addProperty("itemid", "x");
		
		SugestioResult result = client.addConsumption(consumption);
		Example.print(result);	
	}
	
	private static void addItem() {
		
		SugestioClient client = new SugestioClient("sandbox");
		
		JsonObject item = new JsonObject();
		item.addProperty("id", "x");
		item.addProperty("title", "Item X");
		item.addProperty("permalink", "http://localhost/books/x.html");
		
		JsonArray categories = new JsonArray();
		categories.add(new JsonPrimitive("A"));
		categories.add(new JsonPrimitive("B"));		
		item.add("category", categories);
		
		SugestioResult result = client.addItem(item);		
		Example.print(result);
	}
	
	private static void print (JsonArray recommendations) {
		
		System.out.println(recommendations.size() + " recommendations.");
		
		for (int i=0; i<recommendations.size(); i++) {
			
			JsonObject recommendation = recommendations.get(i).getAsJsonObject();
			String itemid = recommendation.get("itemid").getAsString();
			String score = recommendation.get("score").getAsString();
			
			if (recommendation.has("item")) {			
				JsonObject item = recommendation.get("item").getAsJsonObject();				
				System.out.println(item.get("title").getAsString() + " (" + score + ")");
			} else {
				System.out.println(itemid + " (" + score + ")");
			}
			
			
		}
	}
	
	private static void print(SugestioResult result) {		
		if (result.isOk()) {
			System.out.println("API call succeeded with response code " + result.getCode() + ". Response body:");
			System.out.println(result.getMessage());
		} else {
			System.err.println("API call failed with response code " + result.getCode() + ". Response body:");
			System.err.println(result.getMessage());
		}
	}
}
