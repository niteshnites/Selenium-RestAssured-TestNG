package rest_assured_practice.main.demo;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;
import rest_assured_practice.main.pojo.Api;
import rest_assured_practice.main.pojo.GetCourse;
import rest_assured_practice.main.pojo.WebAutomation;

public class O2AuthTest {

	public static void main(String[] args) {

		// Define expected course titles
		String[] courseTitles = {"Selenium Webdriver Java", "Cypress", "Protractor"};

		// Step 1: Obtain OAuth token
		String response = given()
				.formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.formParams("grant_type", "client_credentials")
				.formParams("scope", "trust")
				.when().log().all()
				.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
				.asString();

		System.out.println("OAuth Response: " + response);

		JsonPath jsonPath = new JsonPath(response);
		String accessToken = jsonPath.getString("access_token");

		// Step 2: Get course details using the access token
		GetCourse gc = given()
				.queryParams("access_token", accessToken)
				.when().log().all()
				.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
				.as(GetCourse.class);

		System.out.println("LinkedIn: " + gc.getLinkedIn());
		System.out.println("Instructor: " + gc.getInstructor());
		System.out.println("Second API Course Title: " + gc.getCourses().getApi().get(1).getCourseTitle());

		// Step 3: Print the price of the "SoapUI Webservices testing" course
		List<Api> apiCourses = gc.getCourses().getApi();
		for (Api apiCourse : apiCourses) {
			if (apiCourse.getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
				System.out.println("Price of SoapUI Webservices testing: " + apiCourse.getPrice());
			}
		}

		// Step 4: Verify WebAutomation course names
		ArrayList<String> actualCourseTitles = new ArrayList<>();
		List<WebAutomation> webAutomationCourses = gc.getCourses().getWebAutomation();

		for (WebAutomation webCourse : webAutomationCourses) {
			actualCourseTitles.add(webCourse.getCourseTitle());
		}

		List<String> expectedCourseTitles = Arrays.asList(courseTitles);

		Assert.assertTrue(actualCourseTitles.equals(expectedCourseTitles),
				"WebAutomation course titles do not match the expected list.");
	}
}
