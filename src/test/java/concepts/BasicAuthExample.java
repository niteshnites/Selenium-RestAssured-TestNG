package concepts;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Base64;

public class BasicAuthExample {
    public static void main(String[] args) {

        RestAssured.baseURI = "https://reqres.in/";
        RequestSpecification rs = RestAssured.given();

        // authentication credentials for access control
        String credentials = "Test1111:Test@123";

        // why we are using byte here ? and we use Base64 for encoding the credentials as a string
        byte [] encodedCredentials = Base64.getUrlEncoder().encode(credentials.getBytes());
        String encodedCredentialAsString = new String(encodedCredentials);
        System.out.println(encodedCredentialAsString);


        String payload = "{\n" +
                "  \"userId\": \"5d0e6165-ff9d-4c4a-bda0-94e5a04a6ac3\",\n" +
                "  \"collectionOfIsbns\": [\n" +
                "    {\n" +
                "      \"isbn\": \"9781449337711\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";


        // Adding authentication
        rs.header("Authentication","Basic " + encodedCredentialAsString);
        rs.header("Content-Type","application/json");
        // Requesting a post method
        Response resp = rs.body(payload).request(Method.POST,"/BookStore/v1/book1");
        System.out.println(resp.getStatusLine());
        System.out.println(resp.getStatusCode());
        System.out.println(resp.getBody().prettyPrint());
    }

}
