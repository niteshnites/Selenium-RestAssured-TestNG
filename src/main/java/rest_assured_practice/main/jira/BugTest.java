package rest_assured_practice.main.jira;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import java.io.File;

public class BugTest {

    public static void main(String[] args) {
        RestAssured.baseURI = "https://niteshnites21.atlassian.net/";
        String createIssueResponse = RestAssured.given()
                .header("Content-Type","application/json")
                .header("Authorization","Basic bml0ZXNobml0ZXMyMUBnbWFpbC5jb206QVRBVFQzeEZmR0YwZTBMc0lGcnZWME9VdW1vVHVSXzdlX2pkU1R4SlZ4OUl6aTJVVkczel8xX2FRaFYyWUwzV2xfNmFMcTg4MGN0UTJMMnF6NmpCWm5UeGlVSXlwR1c2Nm1kaFppRkZ0Wm5hZTBBUWJUcXN2R2F1R1Z0cFU2RFNVenNCQmk5R2pIYzhXZ0JtUDIwS2EzY2VtQ0hNVjlyaVJUTFRqZmhidl9GUWl3UHlBNnJnSkxJPUU3N0QzMTY4Cg==")
                .body("{\n" +
                        "  \"fields\": {\n" +
                        "    \"project\": {\n" +
                        "      \"key\": \"SCRUM\"\n" +
                        "    },\n" +
                        "    \"summary\": \"Website items are not working - automation Rest Assured\",\n" +
                        "    \"issuetype\": {\n" +
                        "      \"name\": \"Bug\"\n" +
                        "    }\n" +
                        "  }\n" +
                        "}")

//                .log().all()
                .post("rest/api/3/issue")
                .then()
                .log().all()
                .assertThat()
                .statusCode(201)
                .contentType("application/json")
                .extract()
                .response().asString();

        JsonPath js = new JsonPath(createIssueResponse);
        String issueId = js.getString("id");
        System.out.println(issueId);
        RestAssured.given().pathParam("key", issueId)
                .header("X-Atlassian-Token","no-check")
                .header("Authorization","Basic bml0ZXNobml0ZXMyMUBnbWFpbC5jb206QVRBVFQzeEZmR0YwZTBMc0lGcnZWME9VdW1vVHVSXzdlX2pkU1R4SlZ4OUl6aTJVVkczel8xX2FRaFYyWUwzV2xfNmFMcTg4MGN0UTJMMnF6NmpCWm5UeGlVSXlwR1c2Nm1kaFppRkZ0Wm5hZTBBUWJUcXN2R2F1R1Z0cFU2RFNVenNCQmk5R2pIYzhXZ0JtUDIwS2EzY2VtQ0hNVjlyaVJUTFRqZmhidl9GUWl3UHlBNnJnSkxJPUU3N0QzMTY4Cg==")
                .multiPart("file",new File(System.getProperty("user.dir") +
                        "\\src\\main\\java\\rest_assured_practice\\main\\data\\NiTes.jpeg")).log().all()
                .post("rest/api/3/issue/{key}/attachments")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }
}
