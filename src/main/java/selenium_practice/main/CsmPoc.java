package selenium_practice.main;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CsmPoc {
    static WebDriver driver;
    public static void main(String[] args) {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

        driver.get("https://outlook.office.com/mail/");

        WebElement emailOrPhoneTextField = driver.findElement(By.xpath("//input[@type='email']"));
        shouldBeDisplayed(emailOrPhoneTextField);
        emailOrPhoneTextField.sendKeys("niteshnites3@gmail.com");
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        shouldBeDisplayed(driver.findElement(By.xpath("//div[contains(text(), 'Stay signed in?')]")));
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        // WebElement newMailButton = driver.findElement(By.xpath("//span[text()='New mail']"));
        // shouldBeDisplayed(newMailButton);
        // newMailButton.click();
        // shouldBeDisplayed(driver.findElement(By.xpath("//a/span[text()='Outlook']")));
        driver.findElement(By.xpath("//span[text()='New mail']")).click();

        WebElement toTextField = driver.findElement(By.xpath("//div[@aria-label='To']"));
        shouldBeDisplayed(toTextField);
        toTextField.sendKeys("niteshnites3@gmail.com");

        driver.findElement(By.xpath("//div[@aria-label='Cc']")).sendKeys("niteshnites3@gmail.com");

        driver.findElement(By.xpath("//input[@type='text' and @placeholder='Add a subject']")).sendKeys("SUB: TEST MAIL BY SELENIUM USER");

        driver.findElement(By.xpath("//span[contains(text(),' to insert files and more')]")).click();
        // shouldBeDisplayed(driver.findElement(By.xpath("(//div[@class='elementToProof'])[1]")));

        if(driver.findElement(By.xpath("//div[contains(@aria-label, 'Message body')]")).isDisplayed()){
            driver.findElement(By.xpath("//div[contains(@aria-label, 'Message body')]")).sendKeys("BODY: TEST MAIL BODY BY SELENIUM USER ...\nTo create CSM Use Case for testing");
            System.out.println("AREAL LABEL MESSAGE BODY...........");
        } else {
            driver.findElement(By.xpath("(//div[@class='elementToProof'])[1]")).sendKeys("BODY: TEST MAIL BODY BY SELENIUM USER ...\nTo create CSM Use Case for testing");
        }

        driver.findElement(By.xpath("//button[@aria-label='Send']")).click();
        System.out.println(driver.getTitle());

    }

    public static void shouldBeDisplayed(WebElement element){
        long findStart = System.currentTimeMillis();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(element));
        long findEnd = (System.currentTimeMillis() - findStart);
        System.out.println(element + " web element is visible in .. " + findEnd + "ms");
        ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", element);
    }
}

