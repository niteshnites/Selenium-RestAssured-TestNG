package selenium_practice.others;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

public class WindowTabHandling {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://example.com");

        // Open a new tab (and window)
//        driver.executeScript("window.open('https://anotherexample.com', '_blank');");

        // Get window handles and switch
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            driver.switchTo().window(handle);
            System.out.println("Current window URL: " + driver.getCurrentUrl());
        }

        // Close the current window
        driver.close();

        driver.quit();
    }
}
