package UnitTests;

import Utils.TestLogger;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    @BeforeAll
    public static void Initialize(){
        TestLogger.initializeLogger();
    }
}
