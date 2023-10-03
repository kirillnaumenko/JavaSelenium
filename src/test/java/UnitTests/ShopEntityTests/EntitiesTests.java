package UnitTests.ShopEntityTests;

import UnitTests.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class EntitiesTests extends BaseTest {

    static Stream<Arguments> cartPrices() {
        return Stream.of(
                Arguments.of(10.0, 20.0, 36.0),
                Arguments.of(20.0, 30.0, 60.0)
        );
    }

    @ParameterizedTest
    @MethodSource("cartPrices")
    void addItemsToCartTest(double firstPrice, double secondPrice, double expectedTotal){

        Cart cart = new Cart("Test cart");
        RealItem car = new RealItem();
        car.setName("Audi");
        car.setPrice(firstPrice);
        car.setWeight(1560);
        cart.addRealItem(car);

        VirtualItem disk = new VirtualItem();
        disk.setName("Windows");
        disk.setPrice(secondPrice);
        disk.setSizeOnDisk(23);
        cart.addVirtualItem(disk);

        assertEquals(expectedTotal , cart.getTotalPrice());
    }

    @ParameterizedTest
    @MethodSource("cartPrices")
    @DisplayName("BUG: total not recalculated after item removing")
    void removeItemsFromCartTest(double firstPrice, double secondPrice, double expectedTotal){

        Cart cart = new Cart("Test cart");
        RealItem car = new RealItem();
        car.setName("Audi");
        car.setPrice(firstPrice);
        car.setWeight(1560);
        cart.addRealItem(car);

        VirtualItem disk = new VirtualItem();
        disk.setName("Windows");
        disk.setPrice(secondPrice);
        disk.setSizeOnDisk(23);
        cart.addVirtualItem(disk);

        cart.deleteRealItem(car);

        assertNotEquals(expectedTotal , cart.getTotalPrice());
    }

    @Test
    void createRealItemTest(){
        double expectedPrice = 100;
        double expectedWeight = 1560;
        String expectedDescription = "Weight: " + expectedWeight;
        RealItem car = new RealItem();
        car.setName("Audi");
        car.setPrice(expectedPrice);
        car.setWeight(expectedWeight);

        assertAll("Check cart object",
                () -> assertEquals(expectedPrice, car.getPrice()),
                () -> assertEquals(expectedWeight, car.getWeight()),
                () -> assertThat(car.toString()).contains(expectedDescription)
        );
    }

    @Test
    void createVirtualItemTest(){
        double expectedPrice = 100;
        double expectedDiskSize = 1560;
        String expectedDescription = "Size on disk: " + expectedDiskSize;
        VirtualItem disk = new VirtualItem();
        disk.setName("Windows");
        disk.setPrice(expectedPrice);
        disk.setSizeOnDisk(expectedDiskSize);

        assertAll("Check cart object",
                () -> assertEquals(expectedPrice, disk.getPrice()),
                () -> assertEquals(expectedDiskSize, disk.getSizeOnDisk()),
                () -> assertThat(disk.toString()).contains(expectedDescription)
        );
    }
}
