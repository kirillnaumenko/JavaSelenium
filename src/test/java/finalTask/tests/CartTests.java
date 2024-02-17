package finalTask.tests;

import finalTask.core.BaseTest;
import finalTask.pages.FrontPage;
import finalTask.pages.ShoppingCartPage;
import finalTask.pages.accountDetailsPage.AddressBookTab;
import finalTask.pages.accountDetailsPage.MyWishlistTab;
import finalTask.pages.productDetailsPage.ProductDetailsPage;
import io.qameta.allure.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartTests extends BaseTest {

    List<String> cleanUpList = new ArrayList<>();
    static Stream<Arguments> products() {
        return Stream.of(
                Arguments.of(new ArrayList<>(List.of("Radiant Tee", "Argus All-Weather Tank", "Hero Hoodie")))
        );
    }

    @ParameterizedTest
    @MethodSource("products")
    @Description("Verify that user can add products to cart")
    public void addProductToCart(ArrayList<String> productList){
        cleanUpList = productList;
        int expectedSubTotal = 0;

        FrontPage frontPage = openWebSite()
                .clickSignIn()
                .logIn("javaautomation@test.com", "Test12345");

        for (String product: productList) {
            ProductDetailsPage detailsPage = frontPage.openProductDetails(product);
            expectedSubTotal += detailsPage.getPrice();
            detailsPage.addToCart();
            frontPage = detailsPage.navigateToFrontPage();
        }

        ShoppingCartPage cart = frontPage.navigateToShoppingCart();

        assertEquals(expectedSubTotal, cart.getSubTotal(), "Subtotal is wrong");
        for (String product: productList) {
            assertTrue(cart.isProductInCart(product), "Product is not in the cart");
        }
    }

    @AfterEach
    public void cleanUp(){
        for (String product: cleanUpList) {
            new ShoppingCartPage(driver).deleteProduct(product);
        }
        super.cleanUp();
    }
}
