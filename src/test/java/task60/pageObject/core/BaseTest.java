package task60.pageObject.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import task60.Urls;
import task60.WebDriverSingleton;
import task60.pageObject.pages.FrontPage;

import java.time.Duration;

public abstract class BaseTest {
    public WebDriver driver;

    @BeforeEach
    public void setUp(){
        driver = WebDriverSingleton.getInstance().getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterEach
    public void cleanUp(){
        WebDriverSingleton.getInstance().disposeDriver();
    }

    public FrontPage openWebSite(){
        FrontPage frontPage = new FrontPage(driver);
        driver.get(Urls.magentoTestingUrl);

        return frontPage;
    }
}
