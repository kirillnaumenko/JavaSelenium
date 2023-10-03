package UnitTests.JsonParserTests;

import UnitTests.BaseTest;
import Utils.TestLogger;
import org.junit.gen5.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import parser.JsonParser;
import parser.Parser;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;
import java.io.File;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonParserWriteTests extends BaseTest {
    private Cart cart;

    @BeforeEach
    void SetUp(){
        cart = new Cart("sample-cart" + LocalDateTime.now().getSecond());
    }

    @AfterEach
    void CleanUp(){
        File cartFile = new File("src/main/resources/" + cart.getCartName()+ ".json");
        if (cartFile.delete()) {
            TestLogger.getLogger().info("Deleted the file: " + cartFile.getName());
        } else {
            TestLogger.getLogger().error("Failed to delete the file.");
        }
    }

    @Test
    void writeObjectToFileTest(){
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
        assertAll("Check cart object",
                () -> assertEquals(cart.getCartName(), actualCart.getCartName()),
                () -> assertEquals(cart.getTotalPrice(), actualCart.getTotalPrice())
        );
    }

    @Test
    void writeEmptyObject(){
        Parser parser = new JsonParser();
        parser.writeToFile(cart);

        Cart actualCart = parser.readFromFile(new File("src/main/resources/" + cart.getCartName()+ ".json"));
        assertAll("Check cart object",
                () -> assertEquals(cart.getCartName(), actualCart.getCartName()),
                () -> assertEquals(cart.getTotalPrice(), actualCart.getTotalPrice())
        );
    }

    @Disabled("Some reason to disable test")
    @Test
    void writeNullObject(){
        Assertions.assertThrows(NullPointerException.class, () -> {
            new JsonParser().writeToFile(null);
        });
    }
}
