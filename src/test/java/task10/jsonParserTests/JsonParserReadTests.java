package task10.jsonParserTests;

import com.google.gson.JsonSyntaxException;
import org.junit.gen5.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testng.Assert;
import parser.JsonParser;
import parser.NoSuchFileException;
import parser.Parser;
import shop.Cart;
import java.io.File;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class JsonParserReadTests {

    @ParameterizedTest
    @ValueSource(strings = { "sample-cart.json" })
    public void readFromFileTest(String fileName) {
        Parser parser = new JsonParser();
        Cart cart;

        cart = parser.readFromFile(new File("./src/test/java/task10/TestData/" + fileName));
        cart.showItems();

        Assert.assertNotNull(cart, "Fail to parse cart data from file");
    }

    @ParameterizedTest
    @ValueSource(strings = { "empty-items-cart.json" })
    public void readFromEmptyItemsTest(String fileName) {
        Assertions.assertThrows(NullPointerException.class, () -> {
            new JsonParser().readFromFile(new File("./src/test/java/task10/TestData/" + fileName)).showItems();
        });
    }

    @ParameterizedTest
    @ValueSource(strings = { "wrong-datatype-cart.json" })
    public void readFromWrongDataTypeTest(String fileName) {
        Assertions.assertThrows(NumberFormatException.class, () -> {
            new JsonParser().readFromFile(new File("./src/test/java/task10/TestData/" + fileName)).showItems();
        });
    }

    @ParameterizedTest
    @ValueSource(strings = { "json-with-errors.json" })
    public void readFromFileWithSyntaxErrorExceptionTest(String fileName) {
        Assertions.assertThrows(JsonSyntaxException.class, () -> {
            new JsonParser().readFromFile(new File("./src/test/java/task10/TestData/" + fileName));
        });
    }

    @ParameterizedTest
    @ValueSource(strings = { "xmlfile.xml" })
    public void readFromWrongExtensionTypeTest(String fileName) {
        Assertions.assertThrows(JsonSyntaxException.class, () -> {
            new JsonParser().readFromFile(new File("./src/test/java/task10/TestData/" + fileName)).showItems();
        });
    }


    @ParameterizedTest
    @ValueSource(strings = { "non-existing.json"})
    public void readFromFileNoSuchFileExceptionTest(String fileName) {
        Assertions.assertThrows(NoSuchFileException.class, () -> {
            new JsonParser().readFromFile(new File("./src/test/java/task10/TestData/" + fileName));
        });
    }

    @ParameterizedTest
    @ValueSource(strings = { "non-existing.json", "json-with-errors.json", "empty-items-cart.json","wrong-datatype-cart.json", "xmlfile.xml" })
    public void readFromFileExceptionsTest(String fileName) {
        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> new JsonParser().readFromFile(new File("./src/test/java/task10/TestData/" + fileName)).showItems());
    }
}
