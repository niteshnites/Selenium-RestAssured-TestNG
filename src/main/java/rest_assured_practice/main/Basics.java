package rest_assured_practice.main;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import rest_assured_practice.main.files.ReUsableMethods;
import rest_assured_practice.main.files.Payload;


public class Basics {

	public static void main(String[] args) {

		// validate if Add Place API is working as expected
		// add place-> Update Place with New Address -> Get Place to validate if New address is present in response
		
		// given - all input details
		// when - Submit the API -resource,http method
		// then - validate the response
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		String response = RestAssured.given()
//				.log().all()
				.queryParam("key", "qaclick123")
				.header("Content-Type","application/json")
				.body(Payload.AddPlace())
				.when()
				.post("maps/api/place/add/json")
				.then()
				.assertThat()
				.statusCode(200)
				.body("scope", equalTo("APP"))
				.header("server", "Apache/2.4.52 (Ubuntu)")
				.extract()
				.response()
				.asString();
		
		System.out.println(response);
		JsonPath jsonPath = new JsonPath(response); //for parsing Json
		String placeId = jsonPath.getString("place_id");
		System.out.println("Place ID: " + placeId);


		// Update Place
		String newAddress = "Summer Walk, Africa";
		
		RestAssured.given()
//				.log().all()
				.queryParam("key", "qaclick123")
				.header("Content-Type","application/json")
				.body("{\r\n" +
						"\"place_id\":\""+placeId+"\",\r\n" +
						"\"address\":\""+newAddress+"\",\r\n" +
						"\"key\":\"qaclick123\"\r\n" +
						"}")
				.when()
				.put("maps/api/place/update/json")
				.then()
				.assertThat()
				.log().all()
				.statusCode(200)
				.body("msg", equalTo("Address successfully updated"));


		// Get Place
		String getPlaceResponse = RestAssured.given()
//				.log().all()
				.queryParam("key", "qaclick123")
				.queryParam("place_id",placeId)
				.when()
				.get("maps/api/place/get/json")
				.then()
				.assertThat()
				.log().all()
				.statusCode(200)
				.extract()
				.response()
				.asString();

		JsonPath jsonPath1 = ReUsableMethods.rawToJson(getPlaceResponse);
		String actualAddress = jsonPath1.getString("address");
		System.out.println("Updated Address: " + actualAddress);
		Assert.assertEquals(actualAddress, "Pacific ocean");

	}
}
