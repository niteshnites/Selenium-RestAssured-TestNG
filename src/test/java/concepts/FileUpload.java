package concepts;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUpload {
    public static void main(String[] args) {
        // Set Base URI
        RestAssured.baseURI = "https://demoqa.com";

        // Prepare the request specification for file upload
        RequestSpecification requestSpecification = RestAssured.given();

        // Define the file to upload
        File fileToUpload = new File("path/to/your/file.txt");

        // Make the POST request to upload the file
        Response response = requestSpecification
                .multiPart("file", fileToUpload)  // Specify the field name and the file
                .request(Method.POST, "/upload"); // Replace with the actual file upload endpoint

        // To Download
        File downloadFile = new File("path/to/your/download.txt");

        try (FileOutputStream fos = new FileOutputStream(downloadFile)) {
            fos.write(response.getBody().asByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Print the response
        System.out.println("Response Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
    }
}
