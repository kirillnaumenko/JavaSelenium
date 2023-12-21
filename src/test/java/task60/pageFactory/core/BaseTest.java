package task60.pageFactory.core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import task60.Urls;
import task60.WebDriverSingleton;
import task60.pageFactory.pages.FrontPage;

import java.time.Duration;

public abstract class BaseTest {
    public WebDriver driver;

    @BeforeEach
    public void setUp(){
        driver = WebDriverSingleton.getInstance().getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @AfterEach
    public void cleanUp(){
        WebDriverSingleton.getInstance().disposeDriver();
    }

    public FrontPage openWebSite(){
        FrontPage frontPage = new FrontPage(driver);
        driver.get(Urls.MAGENTO_TESTING_URL);

        return frontPage;
    }
}
