package uni_rest;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class ApiAutomationExample {

    public static void main(String[] args) {
        // Base URL
        String baseUrl = "https://reqres.in/api/users?page=2";

        // 1. GET Request: Retrieve all posts
        HttpResponse<JsonNode> getResponse = Unirest.get(baseUrl)
                .header("Accept", "application/json")
                .asJson();

        System.out.println("GET Status: " + getResponse.getStatus());
        System.out.println("GET Response: " + getResponse.getBody());

        // 2. POST Request: Create a new post
        HttpResponse<JsonNode> postResponse = Unirest.post(baseUrl)
                .header("Content-Type", "application/json")
                .body("{\"title\":\"foo\",\"body\":\"bar\",\"userId\":1}")
                .asJson();

        System.out.println("POST Status: " + postResponse.getStatus());
        System.out.println("POST Response: " + postResponse.getBody());

        // Extract ID of the created post for future actions
        int createdPostId = postResponse.getBody().getObject().getInt("id");

        // 3. PUT Request: Update the post
        HttpResponse<JsonNode> putResponse = Unirest.put(baseUrl + "/" + createdPostId)
                .header("Content-Type", "application/json")
                .body("{\"id\":" + createdPostId + ",\"title\":\"updated title\",\"body\":\"updated body\",\"userId\":1}")
                .asJson();

        System.out.println("PUT Status: " + putResponse.getStatus());
        System.out.println("PUT Response: " + putResponse.getBody());

        // 4. DELETE Request: Delete the post
        HttpResponse<JsonNode> deleteResponse = Unirest.delete(baseUrl + "/" + createdPostId)
                .asJson();

        System.out.println("DELETE Status: " + deleteResponse.getStatus());
        System.out.println("DELETE Response: " + deleteResponse.getBody());
    }
}

