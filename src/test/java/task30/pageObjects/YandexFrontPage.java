package task30.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class YandexFrontPage extends BasePage{
    @FindBy(id = "header-login-button")
    public WebElement loginPageButton;

    @FindBy(id = "js-button")
    public WebElement imNotRobotInput;

    public YandexFrontPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage navigateToLoginPage(){
        this.loginPageButton.click();
        LoginPage loginPage = new LoginPage(this.driver);
        wait.until(ExpectedConditions.visibilityOf(loginPage.loginButton));

        return loginPage;
    }

    public YandexFrontPage open() {
        this.driver.get("https://mail.yandex.ru/");
        this.imitateThatImDefinitelyNotARobot();

        return this;
    }

    public YandexFrontPage imitateThatImDefinitelyNotARobot() {
        By imNotRobotCheckBoxLocator = By.id("js-button");
        List<WebElement> elements = driver.findElements(imNotRobotCheckBoxLocator);
        if (!elements.isEmpty()){
            imNotRobotInput.click();
        }

        return this;
    }
}
