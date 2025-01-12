package rest_assured_practice.main.auth_pojo;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import rest_assured_practice.main.auth_pojo.pojo.AddPlace;
import rest_assured_practice.main.auth_pojo.pojo.Location;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

public class SpecBuilderTest {

    public static void main(String[] args) {
        // Set the base URI for RestAssured
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        // Create the Add Place POJO object
        AddPlace place = new AddPlace();
        place.setAccuracy(50);
        place.setAddress("29, side layout, cohen 09");
        place.setLanguage("French-IN");
        place.setPhone_number("(+91) 983 893 3937");
        place.setWebsite("https://rahulshettyacademy.com");
        place.setName("Frontline house");

        // Add place types to the list
        List<String> types = new ArrayList<>();
        types.add("shoe park");
        types.add("shop");
        place.setTypes(types);

        // Set location details
        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);
        place.setLocation(location);

        // Build the request specification
        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key", "qaclick123")
                .setContentType(ContentType.JSON)
                .build();

        // Build the response specification
        ResponseSpecification resSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();

        // Send the POST request and get the response
        RequestSpecification request = given().spec(reqSpec).body(place);
        Response response = request
                .when().post("/maps/api/place/add/json")
                .then().spec(resSpec)
                .extract().response();

        // Print the response string
        String responseString = response.asString();
        System.out.println(responseString);
    }
}
