package concepts;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

public class PostTest {
    public static void main(String[] args) {

        String payload = "{\n" +
                "  \"userName\": \"Test111\",\n" +
                "  \"password\": \"Test@123\"\n" +
                "}";

        RestAssured.baseURI = "https://bookstore.toolsqa.com";
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-type", "application/json");
        Response response = httpRequest.body(payload).request(Method.POST, "/Account/v1/User");
        Assert.assertEquals(201, response.getStatusCode());
        System.out.println(response.getStatusLine());
    }
}




