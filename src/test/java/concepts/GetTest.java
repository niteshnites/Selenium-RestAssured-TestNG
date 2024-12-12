package concepts;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetTest {
    public static void main(String[] args) {

        RestAssured.baseURI = "https://demoqa.com";
        RequestSpecification requestSpecification = RestAssured.given();
        Response response = requestSpecification.request(Method.GET,"/BookStore/v1/Books");
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        System.out.println(response.prettyPrint());

        String responseBodyAsString = response.getBody().asString();
//        Assert.assertTrue(responseBodyAsString.contains("Git Pocket Guide") );
//        System.out.println(responseBodyAsString);

        System.out.println((String) JsonPath.from(responseBodyAsString).get("books[1].title"));




    }
}
