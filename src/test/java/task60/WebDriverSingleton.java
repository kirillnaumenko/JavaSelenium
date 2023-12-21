package task60;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public final class WebDriverSingleton {
    private static WebDriverSingleton instance;
    private WebDriver driver;

    private WebDriverSingleton() {
        driver = new FirefoxDriver();
    }
    public static WebDriverSingleton getInstance() {
        if (instance == null) {
            instance = new WebDriverSingleton();
        }
        return instance;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void disposeDriver(){
        driver.close();
        instance = null;
    }
}
