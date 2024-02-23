package finalTask.pages;

import finalTask.core.BasePage;
import finalTask.pages.productDetailsPage.ProductDetailsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShoppingCartPage extends BasePage {
    @FindBy(css = ".totals.sub")
    private WebElement subtotal;

    @FindBy(css = ".cart.item")
    private List<WebElement> cartItems;

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public int getSubTotal() {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(subtotal.getText());
        matcher.find();
        String digits = matcher.group();

        return Integer.parseInt(digits);
    }

    public boolean isProductInCart(String productName){
        wait.until(ExpectedConditions.visibilityOfAllElements(cartItems));
        boolean findProduct = cartItems.stream()
                .filter(x -> x.findElement(By.cssSelector(".product-item-name a")).getText().contains(productName))
                .findFirst().isPresent();

        return findProduct;
    }

    public ShoppingCartPage deleteProduct(String productName){
        wait.until(ExpectedConditions.visibilityOfAllElements(cartItems));
        WebElement productDelete = cartItems.stream()
                .filter(x -> x.findElement(By.cssSelector(".product-item-name a")).getText().contains(productName))
                .findFirst().get();
        productDelete.findElement(By.cssSelector(".action-delete")).click();

        return this;
    }
}
