package pojoToJson;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import jsonToPojo.Book;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class WriteBookJson {

    public static void main(String[] args) throws IOException {
        new WriteBookJson().jacksonWriteValueFromList();
    }
    public void jacksonWriteValueFromMap() throws IOException {
        File json_file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\user.json");
        HashMap <String, Object> map = new HashMap<>();
        map.put("name", "John Deo");
        map.put("email", "john.doe@example.com");
        map.put("roles", new String[]{"Member", "Admin"});
        map.put("admin", true);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(json_file, map);
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        objectWriter.writeValue(json_file, map);
    }
    public void jacksonWriteValueFromPOJO() throws IOException {
        Book book = new Book("Thinking in Java", "978-0131872486", "1998",
                new String[]{"Bruce Eckel"});
        File json_file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\writeBook.json");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(json_file, book);
    }
    public void jacksonWriteValueFromList() throws IOException {
        List<Book> books = Arrays.asList(
                new Book("Thinking in Java", "978-0131872486", "1998",
                        new String[]{"Bruce Eckel"}),
                new Book("Head First Java", "0596009208", "2010",
                        new String[]{"Kathy Sierra", "Bert Bates"})
        );
        File json_file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\writeBooks.json");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(json_file, books);
    }
}
