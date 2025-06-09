package practice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RestApiTest {

    public static void main(String[] args) {
        RestAssured.baseURI = "https://reqres.in";

        // GET Call
        System.out.println("GET: List Users");
        given()
                .header("x-api-key", "reqres-free-v1")
                .when()
                .get("/api/users?page=2")
                .then()
                .statusCode(200)
                .body("page", equalTo(2));

        // POST Call
        System.out.println("POST: Create User");
        String postBody = "{ \"name\": \"nitesh\", \"job\": \"qa\" }";

        Response postResponse = given()
                .header("x-api-key", "reqres-free-v1")
                .contentType(ContentType.JSON)
                .body(postBody)
                .when()
                .post("/api/users");

        postResponse.then()
                .statusCode(201)
                .body("name", equalTo("nitesh"))
                .body("job", equalTo("qa"));

        // PUT Call
        System.out.println("PUT: Update User");
        String putBody = "{ \"name\": \"nitesh\", \"job\": \"lead qa\" }";

        Response putResponse = given()
                .header("x-api-key", "reqres-free-v1")
                .contentType(ContentType.JSON)
                .body(putBody)
                .when()
                .put("/api/users/2");

        putResponse.then()
                .statusCode(200)
                .body("job", equalTo("lead qa"));

        // DELETE Call
        System.out.println("DELETE: Delete User");
        given()
                .header("x-api-key", "reqres-free-v1")
                .when()
                .delete("/api/users/2")
                .then()
                .statusCode(204); // No content
    }
}
