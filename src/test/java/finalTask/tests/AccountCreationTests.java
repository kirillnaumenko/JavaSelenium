package finalTask.tests;

import finalTask.dataModels.AccountModel;
import finalTask.pages.accountDetailsPage.AccountDetailsPage;
import io.qameta.allure.AllureId;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import finalTask.core.BaseTest;
import finalTask.pages.FrontPage;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AccountCreationTests extends BaseTest {

    static Stream<Arguments> credentials() {
        return Stream.of(
                Arguments.of("javaautomation@test.com", "Test12345")
        );
    }

    @ParameterizedTest
    @MethodSource("credentials")
    @Description("Verify that user can log in under specific credentials")
    @Feature("Logout feature")
    @AllureId("TC 5678")
    public void loginTest(String user, String password){

        FrontPage frontPage = openWebSite()
                .clickSignIn()
                .logIn(user, password);
        assertTrue(frontPage.isUserLoggedIn(), "User is not logged in");
    }

   @Test
   @Description("Verify that account can be created")
   @Feature("Create account")
   @AllureId("TC 5678")
   public void createAccountTest(){
       AccountModel model = new AccountModel("FirstName" + UUID.randomUUID(),
               "LastName" + UUID.randomUUID(),
               "Email" + UUID.randomUUID(),
               "Test123?");
       AccountDetailsPage infoPage = openWebSite()
                .clickCreateAccount()
                .createAccount(model);

       assertTrue(infoPage.isAccountCreated(), "Account was not created");
    }
}
