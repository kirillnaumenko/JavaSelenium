package task60.pageObject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import task60.pageObject.core.BasePage;

public class FrontPage extends BasePage {
    private By signInButtonLocator = By.cssSelector(".authorization-link");
    private By accountDetailsDropdownLocator = By.cssSelector(".action.switch");
    private By signOutMenuItemLocator = By.cssSelector(".authorization-link");

    public FrontPage(WebDriver driver) {
        super(driver);
    }

    public SignInPage clickSignIn(){
        SignInPage signInPage = new SignInPage(driver);
        driver.findElement(signInButtonLocator).click();

        return signInPage;
    }

    public FrontPage signOut(){
        driver.findElement(accountDetailsDropdownLocator).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(signOutMenuItemLocator));
        driver.findElement(signOutMenuItemLocator).click();

        return this;
    }

    public boolean isUserLoggedIn(){
        boolean state = !driver.findElement(signInButtonLocator).isDisplayed();

        return  state;
    }
}
