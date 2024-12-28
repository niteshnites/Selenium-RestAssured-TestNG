package rest_assured_practice.main.files;

import io.restassured.path.json.JsonPath;

public class ReUsableMethods {

	public static JsonPath rawToJson(String response) {
        return new JsonPath(response);
	}

}
