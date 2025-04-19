package selenium_practice.main;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CsmPoc {
    static WebDriver driver;
    public static void main(String[] args) {
//        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver\\chromedriver.exe");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

        driver.get("https://outlook.office.com/mail/");

        shouldBeDisplayed(By.xpath("//input[@type='email']"));
        WebElement emailOrPhoneTextField = driver.findElement(By.xpath("//input[@type='email']"));
        emailOrPhoneTextField.sendKeys("niteshnites21@gmail.com");
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        shouldBeDisplayed(By.xpath("//input[@type='password']"));
        WebElement passwordField = driver.findElement(By.xpath("//input[@type='password']"));
        passwordField.sendKeys("_ADD_YOUR_PASSWORD_HERE_");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        shouldBeDisplayed(By.xpath("//div[contains(text(), 'Stay signed in?')] | //h1[contains(text(), 'Stay signed in?')]"));
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        shouldBeDisplayed(By.xpath("//span[text()='New mail']"));
        WebElement newMailButton = driver.findElement(By.xpath("//span[text()='New mail']"));
        newMailButton.click();
//        shouldBeDisplayed(By.xpath("//a/span[text()='Outlook']"));
//        driver.findElement(By.xpath("//span[text()='New mail']")).click();

        shouldBeDisplayed(By.xpath("//div[@aria-label='To']"));
        WebElement toTextField = driver.findElement(By.xpath("//div[@aria-label='To']"));
        toTextField.sendKeys("outlook_82118CEEFEE92BBA@outlook.com");

//        driver.findElement(By.xpath("//div[@aria-label='Cc']")).sendKeys("niteshnites21@gmail.com");

        driver.findElement(By.xpath("//input[@type='text' and @placeholder='Add a subject']")).sendKeys("SUB: TEST MAIL BY SELENIUM USER");

        // shouldBeDisplayed(//div[@class='elementToProof'])[1]"));
//        driver.findElement(By.xpath("//span[contains(text(),' to insert files and more')]")).click();

        if(driver.findElement(By.xpath("//div[contains(@aria-label, 'Message body')]")).isDisplayed()){
            driver.findElement(By.xpath("//div[contains(@aria-label, 'Message body')]")).sendKeys("BODY: TEST MAIL BODY BY SELENIUM USER ...\nTo create CSM Use Case for testing");
            System.out.println("AREAL LABEL MESSAGE BODY...........");
        } else {
            driver.findElement(By.xpath("(//div[@class='elementToProof'])[1]")).sendKeys("BODY: TEST MAIL BODY BY SELENIUM USER ...\nTo create CSM Use Case for testing");
        }

        shouldBeDisplayed(By.xpath("//button[@aria-label='Send']"));
        driver.findElement(By.xpath("//button[@aria-label='Send']")).click();
        System.out.println(driver.getTitle());

    }

    public static void shouldBeDisplayed(By locator) {
        if (driver == null || locator == null) {
            throw new IllegalArgumentException("Driver and locator must not be null.");
        }

        final int maxRetries = 3;
        int attempts = 0;
        boolean isDisplayed = false;

        while (attempts < maxRetries && !isDisplayed) {
            try {
                long startTime = System.currentTimeMillis();
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

                List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

                if (!elements.isEmpty()) {
                    WebElement firstVisible = elements.get(0);
                    long duration = System.currentTimeMillis() - startTime;
                    System.out.println(locator + " is visible in " + duration + " ms");

                    // Highlight first visible element
                    ((JavascriptExecutor) driver)
                            .executeScript("arguments[0].style.border='3px solid red'", firstVisible);

                    isDisplayed = true;
                }
            } catch (StaleElementReferenceException e) {
                attempts++;
                System.out.println("StaleElementReferenceException caught. Retrying... Attempt " + attempts);
            } catch (TimeoutException e) {
                System.err.println("Timeout waiting for element to be visible: " + locator);
                break;
            } catch (Exception e) {
                System.err.println("Unexpected error while waiting for element: " + locator);
                break;
            }
        }

        if (!isDisplayed) {
            throw new RuntimeException("Failed to verify element visibility after " + maxRetries + " attempts: " + locator);
        }
    }


}

