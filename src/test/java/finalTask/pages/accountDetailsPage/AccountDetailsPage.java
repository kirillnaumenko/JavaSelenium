package finalTask.pages.accountDetailsPage;

import finalTask.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AccountDetailsPage extends BasePage {
    @FindBy(css = ".message-success")
    private WebElement successfulCreationMessage;

    @FindBy(xpath = "//ul//*[contains(text(),'Address Book')]")
    private WebElement addressBookTab;

    public AccountDetailsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAccountCreated(){
        wait.until(ExpectedConditions.visibilityOf(successfulCreationMessage));

        return successfulCreationMessage.isDisplayed();
    }

    public AddressBookTab openAddressBookTab(){
        this.addressBookTab.click();

        return new AddressBookTab(driver);
    }
}
