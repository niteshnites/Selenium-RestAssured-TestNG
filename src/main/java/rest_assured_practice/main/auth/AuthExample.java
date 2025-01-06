package rest_assured_practice.main.auth;

import static io.restassured.RestAssured.given;

import io.restassured.path.json.JsonPath;

public class AuthExample {

    public static void main(String[] args) throws InterruptedException {

        // Step 1: Fetch Access Token
        String response = given()
                .formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .formParams("grant_type", "client_credentials")
                .formParams("scope", "trust")
                .when()
//                .log().all()
                .post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
                .asString();

        System.out.println("Response: " + response);

        // Step 2: Extract Access Token
        JsonPath jsonPath = new JsonPath(response);
        String accessToken = jsonPath.getString("access_token");
        System.out.println("Access Token: " + accessToken);

        // Step 3: Use Access Token to Fetch Course Details
        String courseDetails = given()
                .queryParams("access_token", accessToken)
                .when()
                .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
                .asString();

        System.out.println("Course Details: " + courseDetails);
    }
}
