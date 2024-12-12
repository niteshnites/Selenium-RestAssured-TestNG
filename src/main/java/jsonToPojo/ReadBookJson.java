package jsonToPojo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ReadBookJson {
    public static void main(String[] args) throws Exception{
        new ReadBookJson().jacksonReaderAsList();
    }

    public void jacksonReaderAsMap() throws IOException {
        FileReader json_file = new FileReader(System.getProperty("user.dir") + "\\src\\main\\resources\\book.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Map<?, ?> map = objectMapper.readValue(json_file, Map.class);
        System.out.println(map);
        System.out.println(map.get("title"));
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }

    public void jacksonReaderAsPOJO() throws IOException{
        FileReader json_file = new FileReader(System.getProperty("user.dir") + "\\src\\main\\resources\\book.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Book book = objectMapper.readValue(json_file, Book.class);
        System.out.println(book.getTitle());
        System.out.println(book.getIsbn());
        System.out.println(book.getYear());
        System.out.println(Arrays.toString(book.getAuthors()));
    }

    public void jacksonReaderAsList() throws IOException{
        FileReader json_file = new FileReader(System.getProperty("user.dir") + "\\src\\main\\resources\\books.json");
        ObjectMapper objectMapper = new ObjectMapper();
        List <Book> books = Arrays.asList(objectMapper.readValue(json_file, Book[].class));
        System.out.println(books.get(0).getTitle());
        System.out.println(books.get(0).getYear());
        System.out.println(books.get(0).getIsbn());
        System.out.println(Arrays.asList(books.get(0).getAuthors()));
        System.out.println(books.get(1).getTitle());
        System.out.println(books.get(1).getYear());
        System.out.println(books.get(1).getIsbn());
        System.out.println(Arrays.asList(books.get(1).getAuthors()));
    }
}
