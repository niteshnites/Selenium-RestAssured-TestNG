package selenium_practice;

import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

public class Selenium4Automation {
    public static void main(String[] args) throws InterruptedException {

        // Launch
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php");

        WebElement practiceForm = driver.findElement(By.id("practiceForm"));
        String formHeading = driver.findElement(RelativeLocator.with(By.tagName("h1")).below(practiceForm)).getText();
        System.out.println(formHeading);

        driver.quit();

    }
}
