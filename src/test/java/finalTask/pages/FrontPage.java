package finalTask.pages;

import finalTask.Urls;
import finalTask.pages.accountDetailsPage.AccountDetailsPage;
import finalTask.pages.productDetailsPage.ProductDetailsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import finalTask.core.BasePage;

import java.util.List;

public class FrontPage extends BasePage {

    @FindBy(css = ".authorization-link")
    private WebElement signInButton;

    @FindBy(xpath = "//a[text() = \"Create an Account\"]")
    private WebElement createAccountButton;

    @FindBy(css = ".action.switch")
    private WebElement accountDetailsDropdown;

    @FindBy(css = ".authorization-link")
    private WebElement signOutMenuItem;

    @FindBy(css = ".products-grid .product-item")
    private List<WebElement> trendingProducts;

    public FrontPage(WebDriver driver) {
        super(driver);
    }

    public SignInPage clickSignIn(){
        SignInPage signInPage = new SignInPage(driver);
        signInButton.click();

        return signInPage;
    }

    public CreateAccountPage clickCreateAccount(){
        createAccountButton.click();
        CreateAccountPage createAccountPage = new CreateAccountPage(driver);

        return createAccountPage;
    }

    public FrontPage signOut(){
        accountDetailsDropdown.click();
        wait.until(ExpectedConditions.visibilityOf(signOutMenuItem));
        signOutMenuItem.click();

        return this;
    }

    public AccountDetailsPage navigateToAccountDetails(){
        AccountDetailsPage page = new AccountDetailsPage(driver);
        driver.get(Urls.MAGENTO_TESTING_URL_ACCOUNT_DETAILS);

        return page;
    }

    public boolean isUserLoggedIn(){
        boolean state = !signInButton.isDisplayed();

        return  state;
    }

    public ProductDetailsPage openProductDetails(String productName){
        wait.until(ExpectedConditions.visibilityOfAllElements(trendingProducts));
        WebElement product = trendingProducts.stream()
                .filter(x -> x.getText().contains(productName))
                .findFirst()
                .get();
        product.click();

        return new ProductDetailsPage(driver);
    }
}
