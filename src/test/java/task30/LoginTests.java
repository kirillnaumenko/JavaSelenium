package task30;

import org.testng.Assert;
import org.testng.annotations.Test;
import task30.core.BaseTest;
import task30.pageObjects.LoginPage;
import task30.pageObjects.MailPage;

public class LoginTests extends BaseTest {

    @Test()
    public void loginTest() {
        LoginPage loginPage = openFrontPage()
                .navigateToLoginPage();

        MailPage mailPage = loginPage.login(user, password);

        Assert.assertTrue(mailPage.composeButton.isDisplayed());
    }
}
