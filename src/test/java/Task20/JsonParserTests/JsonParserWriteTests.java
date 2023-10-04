package Task20.JsonParserTests;

import lombok.extern.log4j.Log4j2;
import org.junit.gen5.api.Assertions;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import parser.JsonParser;
import parser.Parser;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import java.io.File;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Log4j2
public class JsonParserWriteTests {

    private Cart cart;

    @BeforeTest
    public void setUp(){
        cart = new Cart("sample-cart" + LocalDateTime.now().getSecond());
    }

    @AfterTest
    public void cleanUp(){
        File cartFile = new File("src/main/resources/" + cart.getCartName()+ ".json");
        if (cartFile.delete()) {
            log.info("Deleted the file: " + cartFile.getName());
        } else {
            log.error("Failed to delete the file.");
        }
    }

    @Test
    public void writeObjectToFileTest(){
        RealItem car = new RealItem();
        car.setName("Audi");
        car.setPrice(32026.9);
        car.setWeight(1560);

        VirtualItem disk = new VirtualItem();
        disk.setName("Windows");
        disk.setPrice(11);
        disk.setSizeOnDisk(20000);

        cart.addRealItem(car);
        cart.addVirtualItem(disk);

        Parser parser = new JsonParser();
        parser.writeToFile(cart);

        Cart actualCart = parser.readFromFile(new File("src/main/resources/" + cart.getCartName()+ ".json"));

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(cart.getCartName(), actualCart.getCartName(), "Cart name is wrong");
        softAssert.assertEquals(cart.getTotalPrice(), actualCart.getTotalPrice(), "Cart price is wrong");

        softAssert.assertAll();
    }

    @Test
    public void writeEmptyObject(){
        Parser parser = new JsonParser();
        parser.writeToFile(cart);

        Cart actualCart = parser.readFromFile(new File("src/main/resources/" + cart.getCartName()+ ".json"));

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(cart.getCartName(), actualCart.getCartName(), "Cart name is wrong");
        softAssert.assertEquals(cart.getTotalPrice(), actualCart.getTotalPrice(), "Cart price is wrong");

        softAssert.assertAll();
    }

    @Test(expectedExceptions = NullPointerException.class)
    @Ignore("Some reason to ignore test")
    public void writeNullObject(){
        new JsonParser().writeToFile(null);
    }
}
