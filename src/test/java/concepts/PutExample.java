package concepts;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PutExample {
    public static void main(String[] args) {
        String payloadBody = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";

        RestAssured.baseURI = "https://reqres.in/";
        RequestSpecification rs = RestAssured.given();
        rs.header("Content-type","application/json");
        Response resp = rs.body(payloadBody).request(Method.PUT,"/api/users/2");

        System.out.println(resp.prettyPrint());
        System.out.println(resp.getStatusCode());
        System.out.println(resp.getStatusLine());
    }
}
