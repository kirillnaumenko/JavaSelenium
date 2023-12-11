package task60;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverSingleton {
    private static WebDriver driver;

    public static WebDriver getInstance() {
        if (driver == null) {
            driver = new FirefoxDriver();
        }
        return driver;
    }

    public static void disposeDriver(){
        driver.close();
        driver = null;
    }
}
