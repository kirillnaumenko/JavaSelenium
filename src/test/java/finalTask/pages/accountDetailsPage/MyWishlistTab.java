package finalTask.pages.accountDetailsPage;

import finalTask.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyWishlistTab extends BasePage {
    @FindBy(css = ".products-grid .product-item-link")
    private List<WebElement> products;
    public MyWishlistTab(WebDriver driver) {
        super(driver);
    }

    public List<String> getProductsList(){
        wait.until(ExpectedConditions.visibilityOfAllElements(products));
        List<String> names = new ArrayList<>();
        for (WebElement product: products.stream().collect(Collectors.toList())) {
            names.add(product.getText());
        }

        return names;
    }
}
