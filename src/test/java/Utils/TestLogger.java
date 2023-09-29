package Utils;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class TestLogger {
    private static final Logger logger = LogManager.getLogger(TestLogger.class);

    private TestLogger() {
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void initializeLogger() {
        BasicConfigurator.configure();
    }
}
