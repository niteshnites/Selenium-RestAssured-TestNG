

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

/**
 * Class to read Json data from file using "json simple"
 */
public class ReadJsonFile {
    public static void main(String[] args) throws Exception {
    new ReadJsonFile().simpleJsonReader();
    new ReadJsonFile().complexJsonReader();
    }

    public void simpleJsonReader() throws Exception {
        FileReader json_file = new FileReader(System.getProperty("user.dir") + "\\src\\main\\resources\\simple.json");
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(json_file);

        System.out.println(jsonObject);
        System.out.println(jsonObject.get("name"));
        System.out.println(jsonObject.get("city"));
        System.out.println(jsonObject.get("job"));
        System.out.println(jsonObject.get("cars"));
        JSONArray jsonArray = (JSONArray) jsonObject.get("cars");
        System.out.println(jsonArray.get(0));
        System.out.println(jsonArray.get(1));
    }

    public void complexJsonReader() throws Exception {
        FileReader fileReader = new FileReader(System.getProperty("user.dir") + "\\src\\main\\resources\\complex.json");
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray)jsonParser.parse(fileReader);

        JSONObject jsonObject = (JSONObject)jsonArray.get(0);
        System.out.println(jsonObject);
        System.out.println(jsonObject.get("name"));
        System.out.println(jsonObject.get("city"));
        System.out.println(jsonObject.get("job"));
        System.out.println(jsonObject.get("cars"));
        JSONArray jsonArray2 = (JSONArray) jsonObject.get("cars");
        System.out.println(jsonArray2.get(0));
        System.out.println(jsonArray2.get(1));

        jsonObject = (JSONObject)jsonArray.get(1);
        System.out.println(jsonObject);
        System.out.println(jsonObject.get("name"));
        System.out.println(jsonObject.get("city"));
        System.out.println(jsonObject.get("job"));
        System.out.println(jsonObject.get("cars"));
        jsonArray2 = (JSONArray) jsonObject.get("cars");
        System.out.println(jsonArray2.get(0));
        System.out.println(jsonArray2.get(1));
    }

}
