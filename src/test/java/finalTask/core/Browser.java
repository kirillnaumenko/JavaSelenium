package finalTask.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public final class Browser {
    private static Browser instance;
    private WebDriver driver;

    private Browser() {
        driver = new FirefoxDriver();
    }
    public static Browser getInstance() {
        if (instance == null) {
            instance = new Browser();
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
}
