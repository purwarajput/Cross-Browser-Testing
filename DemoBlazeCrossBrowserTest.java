import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

public class DemoBlazeCrossBrowserTest {

    public static void main(String[] args) throws InterruptedException {
        String browser = "chrome"; // Start with Chrome
        boolean flag = true; // Loop control for both browsers
        while (flag) {
            if (browser.equals("chrome")) {
                runTestOnBrowser(browser); // Run on Chrome
                browser = "edge"; // Switch to Edge after Chrome test
            } else if (browser.equals("edge")) {
                runTestOnBrowser(browser); // Run on Edge
                flag = false; // End loop after Edge test
            }
        }
        System.out.println("Both tests completed and browsers closed.");
    }

    public static void runTestOnBrowser(String browser) {
        WebDriver driver = null;

        try {
            if (browser.equals("chrome")) {
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\Purwa\\OneDrive\\Documents\\chromedriver-win64\\chromedriver.exe");
                driver = new ChromeDriver();
            } else if (browser.equals("edge")) {
                System.setProperty("webdriver.edge.driver", "C:\\Users\\Purwa\\OneDrive\\Documents\\msedgedriver.exe");
                driver = new EdgeDriver();
            }

            driver.manage().window().maximize();
            driver.get("https://www.demoblaze.com/");

            // Correct screenshot naming based on browser
            takeScreenshot(driver, "homepage_" + browser);
            performLoginAndProductSelection(driver, browser);  // Pass browser name to method for screenshot naming

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    public static void performLoginAndProductSelection(WebDriver driver, String browser) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement loginButton = driver.findElement(By.id("login2"));
        loginButton.click();
        Thread.sleep(1000);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")));
        driver.findElement(By.id("loginusername")).sendKeys("purwa1");
        Thread.sleep(1000);
        driver.findElement(By.id("loginpassword")).sendKeys("Purwa");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[text()='Log in']")).click();
        Thread.sleep(1000);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));
        WebElement usernameDisplay = driver.findElement(By.id("nameofuser"));
        Assert.assertTrue(usernameDisplay.getText().contains("Welcome purwa1"));
        Thread.sleep(1000);

        takeScreenshot(driver, "login_success_" + browser);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Phones"))).click();
        Thread.sleep(1000);
        takeScreenshot(driver, "phone_section_" + browser);
        driver.navigate().back();
        Thread.sleep(1000);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Laptops"))).click();
        Thread.sleep(1000);
        takeScreenshot(driver, "laptop_section_" + browser);
        driver.navigate().back();
        Thread.sleep(1000);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Monitors"))).click();
        Thread.sleep(1000);
        takeScreenshot(driver, "monitor_section_" + browser);
        driver.navigate().back();
        Thread.sleep(1000);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Phones"))).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Samsung galaxy s6"))).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"tbodyid\"]/div[2]/div/a"))).click();
        Thread.sleep(1000);

        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(1000);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"cartur\"]"))).click();
        Thread.sleep(1000);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/button"))).click();
        Thread.sleep(1000);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"name\"]"))).sendKeys("Purwa");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"country\"]"))).sendKeys("India");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"city\"]"))).sendKeys("Chandigarh");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"card\"]"))).sendKeys("1234567890");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"month\"]"))).sendKeys("February");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"year\"]"))).sendKeys("2027");
        Thread.sleep(1000);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[2]"))).click();
        Thread.sleep(1000);


        takeScreenshot(driver, "purchase_success_" + browser);
    }

    public static void takeScreenshot(WebDriver driver, String fileName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File destination = new File("C:\\Users\\Purwa\\SS\\" + fileName + ".png");
        Files.copy(source.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Screenshot taken: " + fileName);
    }
}
