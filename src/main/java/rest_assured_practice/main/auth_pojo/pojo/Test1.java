package rest_assured_practice.main.auth_pojo.pojo;

import static io.restassured.RestAssured.given;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class Test1 {

    public static void main(String[] args) throws InterruptedException {
        // Set the ChromeDriver property and initialize the WebDriver
        System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // Navigate to the Google OAuth 2.0 authorization page
        driver.get(
                "https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email"
                        + "&auth_url=https://accounts.google.com/o/oauth2/v2/auth"
                        + "&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com"
                        + "&response_type=code"
                        + "&redirect_uri=https://rahulshettyacademy.com/getCourse.php"
                        + "&state=verifyfjdss"
        );

        // Perform login actions
        driver.findElement(By.cssSelector("[type='email']")).sendKeys("dfsf");
        driver.findElement(By.cssSelector("[type='email']")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("[type='password']")).sendKeys("vxcvd");
        driver.findElement(By.cssSelector("[type='password']")).sendKeys(Keys.ENTER);
        Thread.sleep(5000);

        // Extract the authorization code from the URL
        String url = driver.getCurrentUrl();
        System.out.println("URL: " + url);
        String partialCode = url.split("code=")[1];
        String code = partialCode.split("&scope")[0];
        System.out.println("Authorization Code: " + code);

        // Exchange the authorization code for an access token
        String response = given()
                .urlEncodingEnabled(false)
                .queryParams("code", code)
                .queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .queryParams("grant_type", "authorization_code")
                .queryParams("state", "verifyfjdss")
                .queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")
                .queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
                .when()
                .log().all()
                .post("https://www.googleapis.com/oauth2/v4/token")
                .asString();

        // Parse the access token from the response
        JsonPath jsonPath = new JsonPath(response);
        String accessToken = jsonPath.getString("access_token");
        System.out.println("Access Token: " + accessToken);

        // Use the access token to fetch course details
        String courseResponse = given()
                .contentType("application/json")
                .queryParams("access_token", accessToken)
                .expect()
                .defaultParser(Parser.JSON)
                .when()
                .get("https://rahulshettyacademy.com/getCourse.php")
                .asString();

        System.out.println("Course Details: " + courseResponse);

        // Close the browser
        driver.quit();
    }
}

