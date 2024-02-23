package finalTask.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import finalTask.core.BasePage;

public class SignInPage extends BasePage {
    @FindBy(id = "email")
    private WebElement emailInput;
    @FindBy(id = "pass")
    private WebElement passwordInput;
    @FindBy(id = "send2")
    private WebElement signInButton;

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    public void enterEmail(String email){
        wait.until(ExpectedConditions.visibilityOf(emailInput));
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password){
        wait.until(ExpectedConditions.visibilityOf(passwordInput));
        passwordInput.sendKeys(password);
    }

    public void clickSignIn(){
        wait.until(ExpectedConditions.visibilityOf(signInButton));
        signInButton.click();
    }

    public FrontPage logIn(String email, String password){
        FrontPage frontPage = new FrontPage(driver);
        this.enterEmail(email);
        this.enterPassword(password);
        this.clickSignIn();

        return frontPage;
    }
}
