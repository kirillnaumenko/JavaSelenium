package finalTask.pages.accountDetailsPage;

import finalTask.Urls;
import finalTask.core.BasePage;
import finalTask.dataModels.AddressModel;
import finalTask.pages.ConfirmationModal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class AddressBookTab extends BasePage {
    @FindBy(id = "telephone")
    private WebElement phoneNumber;
    @FindBy(xpath = "//button[@role = \"add-address\"]")
    private WebElement addNewAddressButton;
    @FindBy(xpath = "//button[@title =\"Save Address\"]")
    private WebElement saveAddressButton;
    @FindBy(id = "street_1")
    private WebElement streetAddress;
    @FindBy(id = "city")
    private WebElement city;
    @FindBy(id = "region_id")
    private WebElement stateOrProvince;
    @FindBy(id = "zip")
    private WebElement zipCode;
    @FindBy(id = "country")
    private WebElement country;

    public AddressBookTab(WebDriver driver) {
        super(driver);

    }

    public AddressBookTab addNewAddress(AddressModel model){

        // Navigating like that due to bug with new address button in application
        this.driver.get(Urls.MAGENTO_TESTING_URL_NEW_ADDRESS);
        wait.until(ExpectedConditions.visibilityOf(this.phoneNumber));

        this.phoneNumber.sendKeys(model.getPhoneNumber());
        this.streetAddress.sendKeys(model.getStreetAddress());
        this.city.sendKeys(model.getCity());
        new Select(this.stateOrProvince).selectByVisibleText(model.getStateOrProvince());
        this.zipCode.sendKeys(model.getZipCode());
        new Select(this.country).selectByVisibleText(model.getCountry());
        this.saveAddressButton.click();

        return this;
    }

    public void deleteAddress(String street){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("additional-addresses-table")));
        WebElement table = driver.findElement(By.cssSelector("#additional-addresses-table tbody"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        WebElement row = rows.stream()
                .filter(x -> x.findElements(By.tagName("td")).get(2).getText().equals(street))
                .findFirst()
                .orElse(null);

        List<WebElement> columns = row.findElements(By.tagName("td"));
        columns.get(8).findElement(By.cssSelector(".action.delete")).click();
        ConfirmationModal modal = new ConfirmationModal(driver);
        modal.clickOk();
    }

    public List<AddressModel> getAddresses(){
        List<AddressModel> tableModels = new ArrayList<>();
        WebElement table = driver.findElement(By.cssSelector("#additional-addresses-table tbody"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        for (int i = 0; i < rows.size(); i++) {
            List<WebElement> columns = rows.get(i).findElements(By.tagName("td"));

            String street = columns.get(2).getText();
            String city = columns.get(3).getText();
            String country = columns.get(4).getText();
            String state = columns.get(5).getText();
            String zipCode = columns.get(6).getText();
            String phone = columns.get(7).getText();

            AddressModel model = new AddressModel(street, city, state, zipCode, country, phone);
            tableModels.add(model);
        }

        return tableModels;
    }
}
