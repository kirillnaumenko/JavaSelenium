package Task20.JsonParserTests;

import com.google.gson.JsonSyntaxException;
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
    private static final String  _testDataRoot = "./src/test/java/Task20/TestData/";

    @DataProvider(name = "correctFiles")
    public Object[][] correctFilesData(){
        return new Object[][] {{ _testDataRoot + "sample-cart.json" }};
    }

    @DataProvider(name = "incorrectFiles")
    public Object[][] incorrectFilesData(){
        return new Object[][] {{ _testDataRoot + "empty-items-cart.json" },
                { _testDataRoot + "json-with-errors.json" },
                { _testDataRoot + "wrong-datatype-cart.json" },
                { _testDataRoot + "xmlfile.xml" },
                { _testDataRoot + "non-existing-file.json" }};
    }

    @Test(dataProvider = "correctFiles")
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
    })
    public void readFromEmptyItemsTest(String filePath) {
        new JsonParser().readFromFile(new File(filePath))
                .showItems();
    }
}
