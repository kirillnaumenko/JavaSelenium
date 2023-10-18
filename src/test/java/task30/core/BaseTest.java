package task30.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import task30.pageObjects.YandexFrontPage;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected String user;
    protected String password;

    @Parameters( {"browser", "user", "password"})
    @BeforeMethod
    public void setUp(String browser, String user, String password) {

        switch (browser){
            case ("firefox"):
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        this.user = user;
        this.password = password;
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public YandexFrontPage openFrontPage() {
        YandexFrontPage frontPage = new YandexFrontPage(this.driver);

        return frontPage.open();
    }
}
