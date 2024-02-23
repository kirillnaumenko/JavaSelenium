package finalTask.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LocalDriver implements IDriverRunner{
    @Override
    public WebDriver setupDriver() {
        return new FirefoxDriver();
    }
}
