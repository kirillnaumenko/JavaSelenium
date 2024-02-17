package finalTask.tests;

import finalTask.core.BaseTest;
import finalTask.pages.FrontPage;
import finalTask.pages.accountDetailsPage.MyWishlistTab;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WishListTests extends BaseTest {

    @Test
    @Description("Verify that user can add products to wishlist")
    public void addProductToWishlist(){
        MyWishlistTab wishlistTab = openWebSite()
                .clickSignIn()
                .logIn("javaautomation@test.com", "Test12345")
                .openProductDetails("Radiant Tee")
                .clickAddToWishlist();
        List<String> products = wishlistTab.getProductsList();

        assertTrue(products.contains("Radiant Tee"), "Product was not added to wishlist");

    }
}
