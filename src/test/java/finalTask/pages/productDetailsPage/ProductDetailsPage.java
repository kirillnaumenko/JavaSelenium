package finalTask.pages.productDetailsPage;

import finalTask.core.BasePage;
import finalTask.pages.accountDetailsPage.MyWishlistTab;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductDetailsPage extends BasePage {
    @FindBy(css = ".towishlist")
    private WebElement addToWishlistButton;
    @FindBy(css = ".product-item")
    private WebElement otherProducts;

    @FindBy(css = ".swatch-option.text")
    private List<WebElement> sizes;

    @FindBy(css = ".product-item-link")
    private WebElement name;

    @FindBy(css = ".price")
    private WebElement price;

    @FindBy(css = ".swatch-option.color")
    private List<WebElement> colors;

    @FindBy(id = "product-addtocart-button")
    private WebElement addToCartButton;

    @FindBy(css = ".message-success")
    private WebElement succsessMessage;

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    public MyWishlistTab clickAddToWishlist(){
        wait.until(ExpectedConditions.visibilityOfAllElements(otherProducts));
        addToWishlistButton.click();

        return new MyWishlistTab(driver);
    }

    public ProductDetailsPage addToCart(){
        sizes.stream().findFirst().get().click();
        colors.stream().findFirst().get().click();

        addToCartButton.click();
        wait.until(ExpectedConditions.visibilityOf(succsessMessage));

        return this;
    }

    public int getPrice() {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(price.getText());
        matcher.find();
        String digits = matcher.group();

        return Integer.parseInt(digits);
    }
}
