package task60.pageFactory.core;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import task60.Urls;
import task60.WebDriverSingleton;
import task60.pageFactory.pages.FrontPage;
import java.time.Duration;

public abstract class BaseTest implements TestWatcher {
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
