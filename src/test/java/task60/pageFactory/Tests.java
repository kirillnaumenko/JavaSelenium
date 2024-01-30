package task60.pageFactory;

import io.qameta.allure.AllureId;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import task60.pageFactory.core.BaseTest;
import task60.pageFactory.pages.FrontPage;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Tests extends BaseTest {

    static Stream<Arguments> credentials() {
        return Stream.of(
                Arguments.of("javaautomation@test.com", "Test12345")
        );
    }

    @ParameterizedTest
    @MethodSource("credentials")
    @Feature("Login feature")
    @DisplayName("Login test")
    @Description("Verify that user can log in under specific credentials")
    @AllureId("TC 1234")
    public void loginTest(String user, String password){

        FrontPage frontPage = openWebSite()
                .clickSignIn()
                .logIn(user, password);
        assertTrue(frontPage.isUserLoggedIn(), "User is not logged in");
    }

    @ParameterizedTest
    @MethodSource("credentials")
    @Feature("Logout feature")
    @DisplayName("Logout test")
    @Description("Verify that user can log out")
    @AllureId("TC 5678")
    public void logoutTest(String user, String password){

        FrontPage frontPage = openWebSite()
                .clickSignIn()
                .logIn(user, password)
                .signOut();
        assertFalse(frontPage.isUserLoggedIn(), "User is not logged out");
    }
}
