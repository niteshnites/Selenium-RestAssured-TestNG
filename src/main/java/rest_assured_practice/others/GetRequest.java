package rest_assured_practice.others;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class GetRequest {
    public static void main(String[] args) {

        // Specify the base URL to the RESTFul web service
        RestAssured.baseURI = "https://bookstore.toolsqa.com/BookStore/v1/Books";
        // Get the RequestSpecification of the request to be sent to the server
        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.get("");
        ResponseBody body = response.getBody();

        // Get the status code of the request.
        //If request is successful, status code will be 200
        int statusCode = response.getStatusCode();

        System.out.println("Status Code is: " + statusCode);
        System.out.println(body.prettyPrint());
    }

    @Test
    public void queryParameter() {

        //Defining the base URI
        RestAssured.baseURI= "https://bookstore.toolsqa.com/BookStore/v1";
        RequestSpecification httpRequest = RestAssured.given();
        //Passing the resource details
        Response res = httpRequest.queryParam("ISBN","9781449325862").get("/Book");
        //Retrieving the response body using getBody() method
        ResponseBody body = res.body();
        //Converting the response body to string object
        String rbdy = body.asString();
        //Creating object of JsonPath and passing the string response body as parameter
        JsonPath jpath = new JsonPath(rbdy);
        //Storing publisher name in a string variable
        String title = jpath.getString("title");
        System.out.println("The book title is - "+title);

        System.out.println("Body: " + body.prettyPrint());

        // First get the JsonPath object instance from the Response interface
        JsonPath jsonPathEvaluator = res.jsonPath();

        // Let us print the city variable to see what we got
        System.out.println("isbn received from Response " + jsonPathEvaluator.get("isbn"));
        System.out.println("title received from Response " + jsonPathEvaluator.get("title"));
    }
}
