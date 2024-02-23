package finalTask.core;

import finalTask.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public final class Browser{
    private static Browser instance;
    private WebDriver driver;

    private Browser() {
        IDriverRunner runner = null;
        switch (Configuration.getRunner()){
            case "local":
                runner = new LocalDriver();
                break;
            case "selenoid":
                runner = new SelenoidDriver();
                break;
            case "saucelabs":
                runner = new SauceLabsDriver();
                break;
        }
        driver = runner.setupDriver();
    }
    public static Browser getInstance() {
        if (instance == null) {
            instance = new Browser();
        }
        return instance;
    }

    public WebDriver getBrowser() {return driver;}

    public void disposeBrowser(){
        driver.close();
        instance = null;
    }
}
