package finalTask;


import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;

@Log4j2
public class Configuration {
    private static final String CONFIG_FILE_PATH = "src/test/java/finalTask/config.json";

    private static JSONObject config;

    static {
        JSONParser jsonParser = new JSONParser();
        try {
            // Read the JSON file and parse it
            Object obj = jsonParser.parse(new FileReader(CONFIG_FILE_PATH));

            // Convert the parsed object to a JSONObject
            config = (JSONObject) obj;
        } catch (IOException | ParseException e) {
            log.error(e.getMessage());
        }
    }

    public static String getRunner() {
        return (String)config.get("runner");
    }

    public static String getBrowser() {
        return (String)((JSONObject) config.get("local")).get("browser");
    }

    public static String getSelenoidUrl() {
        return (String)((JSONObject) config.get("selenoid")).get("url");
    }

    public static String getSelenoidPort() {
        return (String)((JSONObject) config.get("selenoid")).get("port");
    }

    public static String getSauceLabsUrl() {
        return (String)((JSONObject) config.get("saucelabs")).get("url");
    }

    public static String getSauceLabsUsername() {
        return (String)((JSONObject) config.get("saucelabs")).get("username");
    }

    public static String getSauceLabsAccessKey() {
        return (String)((JSONObject) config.get("saucelabs")).get("accesskey");
    }
}
