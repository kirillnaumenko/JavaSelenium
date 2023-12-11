package task60.pageObject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import task60.pageObject.core.BasePage;

public class SignInPage extends BasePage {
    private By emailInputLocator = By.id("email");
    private By passwordInputLocator = By.id("pass");
    private By signInButtonLocator = By.id("send2");

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    public void enterEmail(String email){
        wait.until(ExpectedConditions.presenceOfElementLocated(emailInputLocator));
        driver.findElement(emailInputLocator).sendKeys(email);
    }

    public void enterPassword(String password){
        wait.until(ExpectedConditions.presenceOfElementLocated(passwordInputLocator));
        driver.findElement(passwordInputLocator).sendKeys(password);
    }

    public void clickSignIn(){
        wait.until(ExpectedConditions.presenceOfElementLocated(signInButtonLocator));
        driver.findElement(signInButtonLocator).click();
    }

    public FrontPage logIn(String email, String password){
        FrontPage frontPage = new FrontPage(driver);
        this.enterEmail(email);
        this.enterPassword(password);
        this.clickSignIn();

        return frontPage;

    }
}
