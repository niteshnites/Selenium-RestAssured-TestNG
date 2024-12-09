package rest_assured_practice;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetParticipants {

    @Test
    public void testGetParticipant() {
        // Set the base URI for the API
        RestAssured.baseURI = "https://www.demosite.com/participants"; // Make sure to include 'https://'

        // Create a request specification
        // `given()` prepares the request (headers, query params, etc.)
        // For a simple GET request, we don't need to set any additional parameters
        Response response = given()
                .when()
                .get("/3") // GET request for the resource with ID 3
                .then()
                .statusCode(200) // Assert the status code is 200 OK
                .extract()
                .response(); // Extract the response

        // Extract the response body
        ResponseBody<?> rb = response.getBody();

        // Print the body of the response
        System.out.println("Response Body: " + rb.asString());

        // You can also perform further validations here
        // For example, validating a specific field in the response
        response.then().body("participant.name", equalTo("John Doe"));
    }

    @Test
    public void testCreateParticipant() {
        // Set the base URI for the API
        RestAssured.baseURI = "https://www.demosite.com/participants"; // Ensure to include 'https://'

        // Define the JSON data to be sent in the POST request body
        String requestBody = "{\n" +
                "    \"name\": \"Jane Doe\",\n" +
                "    \"email\": \"jane.doe@example.com\"\n" +
                "}";

        // Send a POST request with the JSON body
        Response response = given()
                .contentType(ContentType.JSON)  // Specify the content type as JSON
                .body(requestBody)              // Attach the request body
                .when()
                .post()                         // Perform the POST request
                .then()
                .statusCode(201)                // Assert status code is 201 (Created)
                .body("participant.name", equalTo("Jane Doe"))  // Validate name in response
                .body("participant.email", equalTo("jane.doe@example.com"))  // Validate email in response
                .extract()
                .response();                   // Extract the response

        // Print the full response for debugging purposes
        System.out.println("Response Body: " + response.getBody().asString());
    }
}
