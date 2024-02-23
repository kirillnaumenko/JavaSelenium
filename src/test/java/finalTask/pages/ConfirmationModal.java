package finalTask.pages;

import finalTask.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ConfirmationModal extends BasePage {
    @FindBy(css = ".modal-footer .action-primary")
    private WebElement okButton;

    public ConfirmationModal(WebDriver driver) {
        super(driver);
    }

    public void clickOk(){
        wait.until(ExpectedConditions.visibilityOf(okButton));
        okButton.click();
    }
}
