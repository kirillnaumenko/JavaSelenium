package task40;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SeleniumEasyTests {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp(){
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @AfterEach
    public void cleanUp(){
        driver.quit();
    }

    @Test
    public void Point5Test() {
        By multiselectValuesLocator = By.cssSelector("#multi-select option");

        driver.get("https://demo.seleniumeasy.com/basic-select-dropdown-demo.html");

        List<String> countries = this.getRandomCountries(3);

        for (String country: countries) {
            WebElement element = driver.findElements(multiselectValuesLocator)
                    .stream().filter(x -> x.getText().equals(country)).findFirst().orElse(null);
            element.click();

            Assert.assertTrue(element.isSelected());
        }
    }

    @Test
    public void Point6JavaScriptConfirmBoxOkTest() {
        By clickMeButtonLocator = By.xpath("//button[@onclick = 'myConfirmFunction()']");
        By statusMessageLocator = By.id("confirm-demo");

        driver.get("https://demo.seleniumeasy.com/javascript-alert-box-demo.html");

        WebElement clickMeButton = driver.findElement(clickMeButtonLocator);
        clickMeButton.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();

        WebElement statusMessage = driver.findElement(statusMessageLocator);

        Assert.assertEquals(statusMessage.getText(), "You pressed OK!");
    }

    @Test
    public void Point6JavaScriptConfirmBoxCancelTest() {
        By clickMeButtonLocator = By.xpath("//button[@onclick = 'myConfirmFunction()']");
        By statusMessageLocator = By.id("confirm-demo");

        driver.get("https://demo.seleniumeasy.com/javascript-alert-box-demo.html");

        WebElement clickMeButton = driver.findElement(clickMeButtonLocator);
        clickMeButton.click();
        Alert alert = driver.switchTo().alert();
        alert.dismiss();

        WebElement statusMessage = driver.findElement(statusMessageLocator);

        Assert.assertEquals(statusMessage.getText(), "You pressed Cancel!");
    }

    @Test
    public void Point6JavaScriptPromptBoxTest() {
        By clickMeButtonLocator = By.xpath("//button[@onclick = 'myPromptFunction()']");
        By statusMessageLocator = By.id("prompt-demo");
        String testMessage = "Test message";

        driver.get("https://demo.seleniumeasy.com/javascript-alert-box-demo.html");

        WebElement clickMeButton = driver.findElement(clickMeButtonLocator);
        clickMeButton.click();
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(testMessage);
        alert.accept();

        WebElement statusMessage = driver.findElement(statusMessageLocator);

        Assert.assertTrue(statusMessage.getText().contains(testMessage));
    }

    @Test
    public void Point7WaitForUserTest() {
        By getNewUserButtonLocator = By.id("save");
        By photoLocator = By.cssSelector("#loading img");

        driver.get("https://demo.seleniumeasy.com/dynamic-data-loading-demo.html");

        WebElement getNewUserButton = driver.findElement(getNewUserButtonLocator);
        getNewUserButton.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(photoLocator));
    }

    @Test
    public void Point8RefreshDownloadTest() {
        driver.get("https://demo.seleniumeasy.com/bootstrap-download-progress-demo.html");
        WebElement downloadButton = driver.findElement(By.id("cricle-btn"));
        WebElement downloadBar = driver.findElement(By.cssSelector(".percenttext"));

        downloadButton.click();

        while (true) {
            String progressText = downloadBar.getText();
            int progress = Integer.parseInt(progressText.replaceAll("%", ""));

            if (progress >= 50) {
                driver.navigate().refresh();
                break;
            }
        }
    }

    @Test
    public void Point9ReadTableTest() {
        driver.get("https://demo.seleniumeasy.com/table-sort-search-demo.html");
        Select pageSize = new Select(driver.findElement(By.cssSelector("#example_length select")));
        pageSize.selectByValue("10");

        List<TableModel> tableModels = this.readTable();
        List<TableModel> filteredModels = tableModels.stream()
                .filter(x -> x.salary < 300000 & x.age > 10).collect(Collectors.toList());
    }

    private List<TableModel> readTable(){

        List<TableModel> tableModels = new ArrayList<>();
        Boolean endOfPaging = false;

        while (!endOfPaging){
            WebElement table = driver.findElement(By.cssSelector("#example tbody"));
            List<WebElement> rows = table.findElements(By.tagName("tr"));
            for (int i = 1; i < rows.size(); i++) {
                List<WebElement> columns = rows.get(i).findElements(By.tagName("td"));

                String name = columns.get(0).getText();
                String position = columns.get(1).getText();
                String office = columns.get(2).getText();
                int age = Integer.parseInt(columns.get(3).getText());
                String startDate = columns.get(4).getText();
                String salary = columns.get(5).getText();

                TableModel model = new TableModel(name, position, office, age, startDate, salary);
                tableModels.add(model);
            }

            WebElement nextButton = driver.findElement(By.id("example_next"));
            if (!nextButton.getAttribute("class").contains("disabled")){
                nextButton.click();
            }else {
                endOfPaging = true;
            }
        }

//        for (int i = 0; i <= pageCountToRead; i++) {
//            WebElement table = driver.findElement(By.id("example"));
//            List<WebElement> rows = table.findElements(By.tagName("tr"));
//            for (int e = 1; e < rows.size(); e++) {
//                List<WebElement> columns = rows.get(i).findElements(By.tagName("td"));
//
//                String name = columns.get(0).getText();
//                String position = columns.get(1).getText();
//                String office = columns.get(2).getText();
//                int age = Integer.parseInt(columns.get(3).getText());
//                String startDate = columns.get(4).getText();
//                String salary = columns.get(5).getText();
//
//                TableModel model = new TableModel(name, position, office, age, startDate, salary);
//                tableModels.add(model);
//            }
//
//            By nextButtonLocator = By.id("example_next");
//            driver.findElement(nextButtonLocator).click();
//        }

        return tableModels;
    }

    private List<String> getRandomCountries(int count)
    {
        Random rand = new Random();
        List<String> selectedCountries = new ArrayList<>();
        List<String> givenList = Arrays.asList("California", "Florida", "New Jersey", "New York", "Ohio", "Texas", "Pennsylvania", "Washington");

        for (int i = 0; i < count; i++) {
            int randomIndex = rand.nextInt(givenList.size());
            String randomCountry = givenList.get(randomIndex);
            selectedCountries.add(randomCountry);
        }

        return selectedCountries;
    }

}
