package task60.pageFactory.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import task60.pageFactory.core.BasePage;

public class FrontPage extends BasePage {

    @FindBy(css = ".authorization-link")
    private WebElement signInButton;

    @FindBy(css = ".action.switch")
    private WebElement accountDetailsDropdown;

    @FindBy(css = ".authorization-link")
    private WebElement signOutMenuItem;

    public FrontPage(WebDriver driver) {
        super(driver);
    }

    public SignInPage clickSignIn(){
        SignInPage signInPage = new SignInPage(driver);
        signInButton.click();

        return signInPage;
    }

    public FrontPage signOut(){
        accountDetailsDropdown.click();
        wait.until(ExpectedConditions.visibilityOf(signOutMenuItem));
        signOutMenuItem.click();

        return this;
    }

    public boolean isUserLoggedIn(){
        boolean state = !signInButton.isDisplayed();

        return  state;
    }
}
