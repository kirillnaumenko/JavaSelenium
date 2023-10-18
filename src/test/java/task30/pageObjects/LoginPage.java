package task30.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    @FindBy(id = "passp-field-login")
    WebElement userNameInput;

    @FindBy(id = "passp-field-passwd")
    WebElement passwordInput;

    @FindBy(id = "passp:sign-in")
    WebElement loginButton;

    @FindBy(css = ".Spin2")
    WebElement loadingSpinner;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public MailPage login(String userName, String password){
        userNameInput.sendKeys(userName);
        loginButton.click();
        wait.until(ExpectedConditions.invisibilityOf(loadingSpinner));

        // Plan B to avoid exception with javascript not completed before driver interaction
        //wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        passwordInput.sendKeys(password);
        loginButton.click();
        MailPage mailPage = new MailPage(driver);
        wait.until(ExpectedConditions.visibilityOf(mailPage.composeButton));

        return  mailPage;
    }
}
