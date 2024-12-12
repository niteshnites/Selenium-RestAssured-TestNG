package concepts;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

public class GetExample {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://reqres.in/";
        RequestSpecification rs = RestAssured.given();
        Response resp = rs.request(Method.GET,"api/users?page=2");
        System.out.println(resp.prettyPrint());
        System.out.println(resp.getStatusCode());
//        System.out.println(resp.getStatusLine());

        String respBodyAsString = resp.getBody().asString();
        Assert.assertTrue(respBodyAsString.contains("George"));
//        System.out.println(respBodyAsString);
        System.out.println((String) JsonPath.from(respBodyAsString).get("data[4].first_name"));
        System.out.println((int) JsonPath.from(respBodyAsString).get("total"));


    }
}

