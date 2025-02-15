package selenium_practice.others;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyFile {
    public static void main(String[] args) {
        Properties properties = new Properties();

        try {
            // Load properties file from the resources folder
            FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
            properties.load(fis);

            // Read the ChromeDriver path from the properties file
            String chromeDriverPath = properties.getProperty("chrome.driver.path");

            if (chromeDriverPath == null || chromeDriverPath.isEmpty()) {
                throw new RuntimeException("ChromeDriver path is not specified in the properties file.");
            }

            // Set the ChromeDriver system property
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);

            // Print confirmation
            System.out.println("ChromeDriver path set to: " + chromeDriverPath);

            // Here, you can instantiate your WebDriver
            // WebDriver driver = new ChromeDriver();

        } catch (IOException e) {
            System.err.println("Failed to load properties file: " + e.getMessage());
        }
    }
}

