package example;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.sugestio.client.SugestioClient;
import com.sugestio.client.SugestioResult;

public class Example {

	public static void main(String[] args) {		
		Example.addConsumption();
		Example.addItem();
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
