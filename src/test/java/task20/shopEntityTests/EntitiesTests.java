package task20.shopEntityTests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

public class EntitiesTests {

    @DataProvider(name = "cartParameters")
    public Object[][] incorrectFilesDataTest(){
        return new Object[][] {{ 10, 20,36 },
                { 20, 30, 60 }};
    }

    @Test(dataProvider = "cartParameters", groups = { "smoke" }, description = "Add different items to cart and verify total")
    public void addItemsToCartTest(double firstPrice, double secondPrice, double expectedTotal){

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

        Assert.assertEquals(expectedTotal , cart.getTotalPrice());
    }

    @Test(dataProvider = "cartParameters", description = "BUG: total not recalculated after item removing", groups = { "smoke", "bug" })
    public void removeItemsFromCartTest(double firstPrice, double secondPrice, double expectedTotal){

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

        Assert.assertEquals(expectedTotal , cart.getTotalPrice());
    }

    @Test(groups = { "regression" }, description = "Create real item and verify fields")
    public void createRealItemTest(){
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

    @Test(groups = { "regression" }, description = "Create virtual item and verify fields")
    public void createVirtualItemTest(){
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
