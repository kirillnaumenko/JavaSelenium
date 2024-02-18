package task60.pageObject.core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import finalTask.Urls;
import finalTask.core.LocalBrowser;
import task60.pageObject.pages.FrontPage;

import java.time.Duration;

public abstract class BaseTest {
    public WebDriver driver;

    @BeforeEach
    public void setUp(){
        driver = LocalBrowser.getInstance().getBrowser();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterEach
    public void cleanUp(){
        LocalBrowser.getInstance().disposeBrowser();
    }

    public FrontPage openWebSite(){
        FrontPage frontPage = new FrontPage(driver);
        driver.get(Urls.MAGENTO_TESTING_URL);

        return frontPage;
    }
}
