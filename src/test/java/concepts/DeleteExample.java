package concepts;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteExample {
    public static void main(String[] args) {

        RestAssured.baseURI = "https://reqres.in/";
        RequestSpecification rs = RestAssured.given();
        Response resp = rs.request(Method.DELETE,"/api/users/2");
        System.out.println(resp.prettyPrint());
        System.out.println(resp.getStatusLine());
        System.out.println(resp.getStatusCode());
    }
}
