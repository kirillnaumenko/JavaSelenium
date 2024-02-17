package finalTask.pages;

import finalTask.core.BasePage;
import finalTask.dataModels.AccountModel;
import finalTask.pages.accountDetailsPage.AccountDetailsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateAccountPage extends BasePage {
    @FindBy(id = "firstname")
    private WebElement firstNameInput;
    @FindBy(id = "lastname")
    private WebElement lastNameInput;
    @FindBy(id = "email_address")
    private WebElement emailInput;
    @FindBy(id = "password")
    private WebElement passwordInput;
    @FindBy(id = "password-confirmation")
    private WebElement confirmPasswordInput;
    @FindBy(css = ".primary .action")
    private WebElement createAccountButton;

    public CreateAccountPage(WebDriver driver) {
        super(driver);
    }

    public AccountDetailsPage createAccount(AccountModel account){
        firstNameInput.sendKeys(account.getFirstName());
        lastNameInput.sendKeys(account.getLastName());
        emailInput.sendKeys(account.getEmail());
        passwordInput.sendKeys(account.getPassword());
        confirmPasswordInput.sendKeys(account.getPassword());
        createAccountButton.click();

        AccountDetailsPage accountPage = new AccountDetailsPage(this.driver);

        return accountPage;
    }
}
