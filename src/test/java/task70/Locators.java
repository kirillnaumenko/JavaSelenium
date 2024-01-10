package task70;

import org.openqa.selenium.By;

public class Locators {
    public static final By loginPageButtonLocator = By.id("header-login-button");
    public static final By userNameInputLocator = By.id("passp-field-login");
    public static final By passwordInputLocator = By.xpath("//input[@name = 'passwd']");
    public static final By loginButtonLocator = By.id("passp:sign-in");
    public static final By loadingSpinnerLocator = By.cssSelector(".Spin2");
}
