package finalTask.core;

import finalTask.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class SelenoidDriver implements IDriverRunner{
    @Override
    public WebDriver setupDriver(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("firefox");
        capabilities.setVersion("121.0");
        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL("http://" + Configuration.getSelenoidUrl() + ":" + Configuration.getSelenoidPort() + "/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return driver;
    }
}
