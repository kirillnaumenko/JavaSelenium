package finalTask.core;

import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public interface IDriverRunner {
    public WebDriver setupDriver() throws MalformedURLException;
}
