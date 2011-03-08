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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sugestio.client.SugestioClient;
import com.sugestio.client.SugestioResult;
import com.sugestio.client.model.Consumption;
import com.sugestio.client.model.Item;
import com.sugestio.client.model.Recommendation;

public class Example {

	public static void main(String[] args) {
		//Example.getRecommendations();
		//Example.getSimilarItems();
		//Example.addConsumption();
		//Example.addItem();
		//Example.exportItems();
	}
	
	public static void getRecommendations() {
		
		SugestioClient client = new SugestioClient("sandbox");
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("category", "B,!C");
		
		try {
			List<Recommendation> recommendations = client.getRecommendations("1", parameters);
			Example.print(recommendations);
		} catch (Exception e) {	
			e.printStackTrace();
		}		
		
	}
	
	public static void getSimilarItems() {
		
		SugestioClient client = new SugestioClient("sandbox");
		
		try {
			List<Recommendation> recommendations = client.getSimilarItems("1", null);
			Example.print(recommendations);
		} catch (Exception e) {	
			e.printStackTrace();
		}		
		
	}
	
	public static void addConsumption() {		
		
		SugestioClient client = new SugestioClient("sandbox");
		
		Consumption consumption = new Consumption();
		consumption.setUserid("1");
		consumption.setItemid("x");
		consumption.setType("RATING");
		consumption.setDetail("STAR:5:1:3");
		consumption.setDate("NOW");
		
		SugestioResult result = client.addConsumption(consumption);		
		Example.print(result);	
	}
	
	public static void addItem() {
		
		SugestioClient client = new SugestioClient("sandbox");
		
		Item item = new Item("x");
		item.setTitle("Item X");
		item.setPermalink("http://localhost/books/x.html");
		item.addCategory("A");
		item.addCategory("B");
		
		SugestioResult result = client.addItem(item);		
		Example.print(result);
	}
	
	public static void exportItems() {
		
		SugestioClient client = new SugestioClient("sandbox");
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("start", "2");
		parameters.put("limit", "2");
		
		try {
			
			List<Item> items = client.exportItems(parameters);
			
			System.out.println(items.size() + " items:");
			
			for (Item item : items) {
				System.out.println(item.getTitle());
			}
			
		} catch (Exception e) {	
			e.printStackTrace();
		}	
	}
	
	private static void print (List<Recommendation> recommendations) {
		
		System.out.println(recommendations.size() + " recommendations:");
		
		for (Recommendation r : recommendations) {
			System.out.println(r.getItem().getTitle() + " (" + r.getScore() + ")");
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
