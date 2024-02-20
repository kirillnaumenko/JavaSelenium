package finalTask.core;

import finalTask.Configuration;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import finalTask.Urls;
import finalTask.pages.FrontPage;

import java.net.MalformedURLException;
import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest implements TestWatcher {
    public static WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = Browser.getInstance().getBrowser();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @AfterEach
    public void cleanUp(){
        Browser.getInstance().disposeBrowser();
    }

    public FrontPage openWebSite(){
        FrontPage frontPage = new FrontPage(driver);
        driver.get(Urls.MAGENTO_TESTING_URL);

        return frontPage;
    }

    @Override
    public void testFailed(ExtensionContext extensionContext, Throwable throwable) {
        this.saveScreenshot();
        Allure.addAttachment("Browser", ((RemoteWebDriver) driver).getCapabilities().getBrowserName());
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = ".png")
    public byte[] saveScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
