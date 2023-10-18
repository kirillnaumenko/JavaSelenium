package task30.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MailPage extends BasePage{
    @FindBy(xpath = "//a[@href = '#compose']")
    public WebElement composeButton;

    public MailPage(WebDriver driver) {
        super(driver);
    }
}
