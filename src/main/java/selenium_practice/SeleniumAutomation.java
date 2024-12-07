package selenium_practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class SeleniumAutomation {
    public static void main(String[] args) {

        // Launch
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php");

        WebElement studentRegistrationHeading = driver.findElement(By.xpath("//h1[text()='Student Registration Form']"));
        studentRegistrationHeading.isDisplayed();

        driver.findElement(By.id("name")).sendKeys("Nitesh");
        driver.findElement(By.id("email")).sendKeys("niteshnites3@gmail.com");
        driver.findElement(By.id("gender")).click();
        driver.findElement(By.id("mobile")).sendKeys("7009848309");



        System.out.println("Pass");
        driver.quit();

    }
}
