package task20.jsonParserTests;

import com.google.gson.JsonSyntaxException;
import jdk.jfr.Description;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import parser.JsonParser;
import parser.NoSuchFileException;
import parser.Parser;
import shop.Cart;
import java.io.File;

@Log4j2
public class JsonParserReadTests {
    private static final String  TEST_DATA_ROOT = "./src/test/java/Task20/TestData/";

    @DataProvider(name = "correctFiles")
    public Object[][] correctFilesData(){
        return new Object[][] {{ TEST_DATA_ROOT + "sample-cart.json" }};
    }

    @DataProvider(name = "incorrectFiles")
    public Object[][] incorrectFilesData(){
        return new Object[][] {{ TEST_DATA_ROOT + "empty-items-cart.json" },
                { TEST_DATA_ROOT + "json-with-errors.json" },
                { TEST_DATA_ROOT + "wrong-datatype-cart.json" },
                { TEST_DATA_ROOT + "xmlfile.xml" },
                { TEST_DATA_ROOT + "non-existing-file.json" }};
    }

    @Test(dataProvider = "correctFiles", description = "Read correct json file and parse to model")
    public void readFromFileTest(String filePath){
        Parser parser = new JsonParser();
        Cart cart;

        cart = parser.readFromFile(new File(filePath));
        cart.showItems();

        Assert.assertNotNull(cart, "Fail to parse cart data from file");
    }

    @Test(dataProvider = "incorrectFiles", expectedExceptions = {
            NumberFormatException.class,
            NullPointerException.class,
            JsonSyntaxException.class,
            NoSuchFileException.class
    }, description = "Read incorrect json files, parse and verify exceptions")
    public void readFromEmptyItemsTest(String filePath) {
        new JsonParser().readFromFile(new File(filePath))
                .showItems();
    }
}
