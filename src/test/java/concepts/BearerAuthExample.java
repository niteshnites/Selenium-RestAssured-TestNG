package concepts;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BearerAuthExample {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://bookstore.toolsqa.com";
        RequestSpecification rs = RestAssured.given();

        // generating a token 
        String payloadGenerateToken = "{\n" +
                "  \"userName\": \"Test1111\",\n" +
                "  \"password\": \"Test@123\"\n" +
                "}";

        rs.header("Content-Type","application/json");
        Response respToken = rs.body(payloadGenerateToken).request(Method.POST,"/Account/v1/GenerateToken");
        String bearerToken = JsonPath.from(respToken.getBody().asString()).get("token");

        rs.header("Äuthorization","Bearer " + bearerToken);
        System.out.println(bearerToken);
        rs.header("Content-Type","application/json");

        String payload = "{\n" +
                "  \"userId\": \"5d0e6165-ff9d-4c4a-bda0-94e5a04a6ac3\",\n" +
                "  \"collectionOfIsbns\": [\n" +
                "    {\n" +
                "      \"isbn\": \"9781491950296\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        Response resp = rs.body(payload).request(Method.POST,"/BookStore/v1/book2");
        System.out.println(resp.getStatusCode());
        System.out.println(resp.getStatusLine());
        System.out.println(resp.getStatusCode());
    }
}
