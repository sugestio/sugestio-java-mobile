package example;

import com.google.gson.JsonObject;
import com.sugestio.client.SugestioClient;
import com.sugestio.client.SugestioResult;

public class Example {

	public static void main(String[] args) {
		
		SugestioClient client = new SugestioClient("sandbox");
		
		JsonObject consumption = new JsonObject();
		consumption.addProperty("userid", "1");
		consumption.addProperty("itemid", "x");
		
		SugestioResult result = client.addConsumption(consumption);
		
		if (result.isOk()) {
			System.out.println("API call succeeded.");
			System.out.println(result.getMessage());
		} else {
			System.err.println("API call failed with response code " + result.getCode());
			System.err.println(result.getMessage());
		}
		
	}
}
