package task30;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
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
        driver.findElement(Locators.loginPageButtonLocator).click();

        // Enter user
        driver.findElement(Locators.userNameInputLocator).sendKeys(user);
        driver.findElement(Locators.loginButtonLocator).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(Locators.loadingSpinnerLocator));

        // Enter password and login
        driver.findElement(Locators.passwordInputLocator).sendKeys(password);
        driver.findElement(Locators.loginButtonLocator).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(Locators.composeEmailButton));

        // Verify that we navigated to email page
        Assert.assertTrue(driver.findElement(Locators.composeEmailButton).isDisplayed());
    }

    private void imitateThatImDefinitelyNotARobot() {
        By imNotRobotCheckBoxLocator = By.id("js-button");
        List<WebElement> elements = driver.findElements(imNotRobotCheckBoxLocator);
        if (!elements.isEmpty()){
            driver.findElement(imNotRobotCheckBoxLocator).click();
        }
    }
}
