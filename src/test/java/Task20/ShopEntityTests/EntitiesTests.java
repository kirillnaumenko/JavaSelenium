package Task20.ShopEntityTests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;
import static org.junit.jupiter.api.Assertions.*;

public class EntitiesTests {

    @DataProvider(name = "cartParameters")
    public Object[][] incorrectFilesData(){
        return new Object[][] {{ 10, 20,36 },
                { 20, 30, 60 }};
    }

    @Test(dataProvider = "cartParameters", groups = { "smoke" })
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

    @Test(dataProvider = "cartParameters", description = "BUG: total not recalculated after item removing", groups = { "smoke", "bug" })
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

    @Test(groups = { "regression" })
    void createRealItemTest(){
        double expectedPrice = 100;
        double expectedWeight = 1560;
        String expectedDescription = "Weight: " + expectedWeight;
        RealItem car = new RealItem();
        car.setName("Audi");
        car.setPrice(expectedPrice);
        car.setWeight(expectedWeight);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(expectedPrice, car.getPrice());
        softAssert.assertEquals(expectedWeight, car.getWeight());
        softAssert.assertTrue((car.toString()).contains(expectedDescription));

        softAssert.assertAll();
    }

    @Test(groups = { "regression" })
    void createVirtualItemTest(){
        double expectedPrice = 100;
        double expectedDiskSize = 1560;
        String expectedDescription = "Size on disk: " + expectedDiskSize;
        VirtualItem disk = new VirtualItem();
        disk.setName("Windows");
        disk.setPrice(expectedPrice);
        disk.setSizeOnDisk(expectedDiskSize);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(expectedPrice, disk.getPrice());
        softAssert.assertEquals(expectedDiskSize, disk.getSizeOnDisk());
        softAssert.assertTrue((disk.toString()).contains(expectedDescription));

        softAssert.assertAll();
    }
}
