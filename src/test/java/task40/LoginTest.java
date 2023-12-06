package task40;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

public class LoginTest{
    private WebDriver driver;
    private WebDriverWait wait;

    static Stream<Arguments> credentials() {
        return Stream.of(
                Arguments.of("java.automation", "javatraining101"),
                Arguments.of("java.automation2023", "javatraining101")
        );
    }

    @BeforeEach
    public void setUp(){
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.get("https://mail.yandex.ru/");
    }

    @AfterEach
    public void cleanUp(){
        driver.quit();
    }

    @ParameterizedTest
    @MethodSource("credentials")
    public void loginTest(String user, String password) throws InterruptedException {
        // Navigate to login page
        this.imitateThatImDefinitelyNotARobot();
        driver.findElement(Locators.loginPageButtonLocator).click();

        // Enter user
        driver.findElement(Locators.userNameInputLocator).sendKeys(user);
        driver.findElement(Locators.loginButtonLocator).click();
        Thread.sleep(3000); // it's not really implicit/explicit wait. We just pause an execution thread for some time

        // Enter password and login
        driver.findElement(Locators.passwordInputLocator).sendKeys(password);
        driver.findElement(Locators.loginButtonLocator).click();

        // Explicit waiter for point 4 of task
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
