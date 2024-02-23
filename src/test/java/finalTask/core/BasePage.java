package finalTask.core;

import finalTask.Urls;
import finalTask.pages.FrontPage;
import finalTask.pages.ShoppingCartPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    public FrontPage navigateToFrontPage(){
        this.driver.get(Urls.MAGENTO_TESTING_URL);

        return new FrontPage(driver);
    }

    public ShoppingCartPage navigateToShoppingCart(){
        this.driver.get(Urls.MAGENTO_TESTING_URL_CART);

        return new ShoppingCartPage(driver);
    }
}
