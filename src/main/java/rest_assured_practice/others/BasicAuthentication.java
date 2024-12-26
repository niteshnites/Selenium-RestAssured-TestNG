package rest_assured_practice.others;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Base64;

public class BasicAuthentication {
    public static void main(String[] args) {

        RestAssured.baseURI = "https://bookstore.toolsqa.com";
        RequestSpecification rs = RestAssured.given();

        String credentials = "Test1111:Test@123";
        byte [] encodedCredentials = Base64.getEncoder().encode(credentials.getBytes());
        String encodedCredentialsAsString = new String(encodedCredentials);
        System.out.println(encodedCredentialsAsString);

        String payload = "{\n" +
                "  \"userId\": \"5d0e6165-ff9d-4c4a-bda0-94e5a04a6ac3\",\n" +
                "  \"collectionOfIsbns\": [\n" +
                "    {\n" +
                "      \"isbn\": \"9781449337711\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        rs.header("Authorization", "Basic " + encodedCredentialsAsString);
        rs.header("Content-Type", "application/json");
        Response response = rs.body(payload).request(Method.POST, "/BookStore/v1/book1");
        System.out.println(response.getStatusLine());
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().prettyPrint());

    }
}
