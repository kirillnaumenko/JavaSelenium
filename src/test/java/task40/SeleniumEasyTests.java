package task40;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    public void point5Test() {
        By multiselectValuesLocator = By.cssSelector("#multi-select option");

        driver.get(Urls.task5Url);

        List<String> countries = this.getRandomCountries(3);

        for (String country: countries) {
            WebElement element = driver.findElements(multiselectValuesLocator)
                    .stream().filter(x -> x.getText().equals(country)).findFirst().orElse(null);
            if (element != null) {
                element.click();
                Assert.assertTrue(element.isSelected(), "Element was found but not selected");
            } else {
                Assert.fail(String.format("Element {0} was not found", country));
            }
        }
    }

    @Test
    public void point6JavaScriptConfirmBoxOkTest() {
        By clickMeButtonLocator = By.xpath("//button[@onclick = 'myConfirmFunction()']");
        By statusMessageLocator = By.id("confirm-demo");

        driver.get(Urls.task6Url);

        WebElement clickMeButton = driver.findElement(clickMeButtonLocator);
        clickMeButton.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();

        WebElement statusMessage = driver.findElement(statusMessageLocator);

        Assert.assertEquals(statusMessage.getText(), "You pressed OK!");
    }

    @Test
    public void point6JavaScriptConfirmBoxCancelTest() {
        By clickMeButtonLocator = By.xpath("//button[@onclick = 'myConfirmFunction()']");
        By statusMessageLocator = By.id("confirm-demo");

        driver.get(Urls.task6Url);

        WebElement clickMeButton = driver.findElement(clickMeButtonLocator);
        clickMeButton.click();
        Alert alert = driver.switchTo().alert();
        alert.dismiss();

        WebElement statusMessage = driver.findElement(statusMessageLocator);

        Assert.assertEquals(statusMessage.getText(), "You pressed Cancel!");
    }

    @Test
    public void point6JavaScriptPromptBoxTest() {
        By clickMeButtonLocator = By.xpath("//button[@onclick = 'myPromptFunction()']");
        By statusMessageLocator = By.id("prompt-demo");
        String testMessage = "Test message";

        driver.get(Urls.task6Url);

        WebElement clickMeButton = driver.findElement(clickMeButtonLocator);
        clickMeButton.click();
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(testMessage);
        alert.accept();

        WebElement statusMessage = driver.findElement(statusMessageLocator);

        Assert.assertTrue(statusMessage.getText().contains(testMessage));
    }

    @Test
    public void point7WaitForUserTest() {
        By getNewUserButtonLocator = By.id("save");
        By photoLocator = By.cssSelector("#loading img");

        driver.get(Urls.task7Url);

        WebElement getNewUserButton = driver.findElement(getNewUserButtonLocator);
        getNewUserButton.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(photoLocator));
    }

    @Test
    public void point8RefreshDownloadTest() {
        driver.get(Urls.task8Url);
        WebElement downloadButton = driver.findElement(By.id("cricle-btn"));
        WebElement downloadBar = driver.findElement(By.cssSelector(".percenttext"));

        downloadButton.click();

        int maxIterations = 50;
        int currentIteration = 0;
        while (true) {
            String progressText = downloadBar.getText();
            int progress = Integer.parseInt(progressText.replaceAll("%", ""));

            if (progress >= 50) {
                driver.navigate().refresh();
                break;
            }

            currentIteration++;
            if (currentIteration >= maxIterations) {
                Assert.fail("Reached maximum iterations");
                break;
            }
        }
    }

    @Test
    public void point9ReadTableTest() {
        driver.get(Urls.task9Url);
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
