package task70;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class LoginTest{
    private WebDriver driver;
    private WebDriverWait wait;
    private String user;
    private String password;

    @BeforeTest
    public void setUp(){
        user = "java.automation";
        password = "javatraining101";
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.get("https://mail.yandex.ru/");
    }

    @AfterTest
    public void cleanUp(){
        driver.quit();
    }

    @Test()
    public void loginTest() {
        // Navigate to login page
        this.imitateThatImDefinitelyNotARobot();
        this.takeScreenshot(driver);
        driver.findElement(Locators.loginPageButtonLocator).click();

        // Enter user
        driver.findElement(Locators.userNameInputLocator).sendKeys(user);
        driver.findElement(Locators.loginButtonLocator).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(Locators.loadingSpinnerLocator));

        // Enter password and login
        driver.findElement(Locators.passwordInputLocator).sendKeys(password);
    }

    private void imitateThatImDefinitelyNotARobot() {
        By imNotRobotCheckBoxLocator = By.id("js-button");
        List<WebElement> elements = driver.findElements(imNotRobotCheckBoxLocator);
        if (!elements.isEmpty()){
            driver.findElement(imNotRobotCheckBoxLocator).click();
        }
    }

    private File takeScreenshot(WebDriver driver) {
        try {
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("src/test/java/task70/screenshotsRepository/screenshot.png"));
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        } catch (IOException e) {
            System.out.println("Failed to save screenshot: " + e.getMessage());
            return null;
        }
    }
}
