package finalTask.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public final class LocalBrowser implements IDriverRunner{
    private static LocalBrowser instance;
    private WebDriver driver;

    private LocalBrowser() {
        driver = new FirefoxDriver();
    }
    public static LocalBrowser getInstance() {
        if (instance == null) {
            instance = new LocalBrowser();
        }
        return instance;
    }

    public WebDriver getBrowser() {
        return driver;
    }

    public void disposeBrowser(){
        driver.close();
        instance = null;
    }

    @Override
    public WebDriver setupDriver() {
        return LocalBrowser.getInstance().getBrowser();
    }
}
