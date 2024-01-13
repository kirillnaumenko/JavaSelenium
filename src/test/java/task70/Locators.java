package task70;

import org.openqa.selenium.By;

public class Locators {
    public static final By LOGIN_PAGE_BUTTON_LOCATOR = By.id("header-login-button");
    public static final By USER_NAME_INPUT_LOCATOR = By.id("passp-field-login");
    public static final By PASSWORD_INPUT_LOCATOR = By.xpath("//input[@name = 'passwd']");
    public static final By LOGIN_BUTTON_LOCATOR = By.id("passp:sign-in");
    public static final By LOADING_SPINNER_LOCATOR = By.cssSelector(".Spin2");
}
