package rest_assured_practice.main.files;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DynamicJson {

    @Test(dataProvider = "BooksData")
    public void addBook(String isbn, String aisle){
        RestAssured.baseURI = "http://216.10.245.166";

        // Add Book
        String response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(Payload.addBook(isbn, aisle))
                .when()
                .post("/Library/Addbook.php")
                .then()
//                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        JsonPath jsonPath = ReUsableMethods.rawToJson(response);
        String id = jsonPath.get("ID");
        System.out.println(id);

        // Delete Book

    }

    // Data Provider For Parametrization
    @DataProvider(name = "BooksData")
    public Object [][] getData(){
        return new Object [][] {{"qwerty", "12345"}, {"asdfg", "67890"}, {"zxcvb", "13579"}};
    }

}
