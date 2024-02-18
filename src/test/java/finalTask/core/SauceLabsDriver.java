package finalTask.core;

import finalTask.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.internal.ConfigurationGroupMethods;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SauceLabsDriver implements IDriverRunner{
    @Override
    public WebDriver setupDriver() throws MalformedURLException {
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Linux");
        browserOptions.setBrowserVersion("latest");
        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("username", Configuration.getSauceLabsUsername());
        sauceOptions.put("accessKey", Configuration.getSauceLabsAccessKey());
        browserOptions.setCapability("sauce:options", sauceOptions);

        URL url = new URL(Configuration.getSauceLabsUrl());
        WebDriver driver = new RemoteWebDriver(url, browserOptions);
        return driver;
    }
}
