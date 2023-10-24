package task30;

import org.openqa.selenium.By;

public class Locators {
    public static By loginPageButtonLocator = By.id("header-login-button");
    public static By userNameInputLocator = By.id("passp-field-login");
    public static By passwordInputLocator = By.xpath("//input[@name = 'passwd']");
    public static By loginButtonLocator = By.id("passp:sign-in");
    public static By loadingSpinnerLocator = By.cssSelector(".Spin2");
    public static By composeEmailButton = By.linkText("#compose");
}
