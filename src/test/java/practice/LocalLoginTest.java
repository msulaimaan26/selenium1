package practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

public class LocalLoginTest {

    private WebDriver driver;
    private String fileUrl;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);

        File htmlFile = new File("webpage/login.html");
        fileUrl = htmlFile.toURI().toString();
        driver.get(fileUrl);
    }

    @Test(priority = 1)
    public void testValidLogin() throws InterruptedException {
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("admin123");
        driver.findElement(By.id("loginBtn")).click();

        Thread.sleep(3000);

        WebElement message = driver.findElement(By.id("message"));
        Assert.assertEquals(message.getText(), "Login Successful!");
    }

    @Test(priority = 2)
    public void testInvalidLogin() throws InterruptedException {
        driver.findElement(By.id("username")).sendKeys("wronguser");
        driver.findElement(By.id("password")).sendKeys("wrongpass");
        driver.findElement(By.id("loginBtn")).click();

        Thread.sleep(3000);

        WebElement message = driver.findElement(By.id("message"));
        Assert.assertEquals(message.getText(), "Invalid Credentials!");
    }

    @Test(priority = 3)
    public void testBackgroundColorChange() throws InterruptedException {
        WebElement body = driver.findElement(By.tagName("body"));
        String initialColor = body.getCssValue("background-color");
        Assert.assertEquals(initialColor, "rgba(240, 240, 240, 1)");

        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("admin123");
        driver.findElement(By.id("loginBtn")).click();

        Thread.sleep(3000);

        String updatedColor = body.getCssValue("background-color");
        Assert.assertEquals(updatedColor, "rgba(144, 238, 144, 1)");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
