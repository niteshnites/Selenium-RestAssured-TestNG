package rest_assured_practice.main.auth_pojo;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import rest_assured_practice.main.auth_pojo.pojo.AddPlace;
import rest_assured_practice.main.auth_pojo.pojo.Location;


import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

public class SerializeTest {

    public static void main(String[] args) {
        // Set the base URI for RestAssured
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        // Create the AddPlace POJO object
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

        // Send the POST request
        Response response = given()
                .log().all()
                .queryParam("key", "qaclick123")
                .body(place)
                .when()
                .post("/maps/api/place/add/json")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        // Print the response
        String responseString = response.asString();
        System.out.println(responseString);
    }
}

