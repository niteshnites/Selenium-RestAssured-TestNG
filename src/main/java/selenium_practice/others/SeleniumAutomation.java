package selenium_practice.others;

import dev.failsafe.internal.util.Assert;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;

public class SeleniumAutomation {
    public static void main(String[] args) throws InterruptedException {

        // Launch
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php");

        WebElement studentRegistrationHeading = driver.findElement(By.xpath("//h1[text()='Student Registration Form']"));
        Assert.isTrue(studentRegistrationHeading.isDisplayed(), "Student Registration Form Heading should be present");

        driver.findElement(By.id("name")).sendKeys("Nitesh");
        driver.findElement(By.id("email")).sendKeys("niteshnites3@gmail.com");
        driver.findElement(By.id("gender")).click();
        driver.findElement(By.id("mobile")).sendKeys("7009848309");
        driver.findElement(By.id("dob")).sendKeys("20011996");
        driver.findElement(By.id("subjects")).sendKeys("Testing Script Using Selenium Java");

        driver.findElement(By.xpath("//label[text()='Sports']/../input")).click();
        driver.findElement(By.xpath("//label[text()='Reading']/../input")).click();

        WebElement picture = driver.findElement(By.xpath("//input[@type='file']"));
        String filePath = "C:\\Users\\a877743\\OneDrive - Eviden\\Desktop\\JAVA\\Selenium-RestAssured-TestNG\\src\\main\\resources\\NiTesH.jpeg";
        picture.sendKeys(filePath);
        Thread.sleep(2000);

        driver.findElement(By.xpath("//textarea[@id='picture']"))
                .sendKeys("Sharma Dhaba, Near GPO, PO Dalhousie, Teh Dalhousie, Pin Code 176304");

        WebElement stateDropdown = driver.findElement(By.id("state"));
        Select selectState = new Select(stateDropdown);
        selectState.selectByVisibleText("Uttar Pradesh");

        WebElement cityDropdown = driver.findElement(By.id("city"));
        Select selectCity = new Select(cityDropdown);
        selectCity.selectByIndex(1);

        WebElement loginButton = driver.findElement(By.xpath("//input[@value='Login']"));
        File submitButtonScreen = loginButton.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(submitButtonScreen, new File("target/screenshots/submit.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("target/screenshots/test.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Pass");
        driver.quit();

    }
}
