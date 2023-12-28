package task60.pageFactory;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.testng.Assert;
import task60.pageFactory.core.BaseTest;
import task60.pageFactory.pages.FrontPage;

import java.util.stream.Stream;

public class Tests extends BaseTest {

    static Stream<Arguments> credentials() {
        return Stream.of(
                Arguments.of("javaautomation@test.com", "Test12345")
        );
    }

    @ParameterizedTest
    @MethodSource("credentials")
    public void loginTest(String user, String password){

        FrontPage frontPage = openWebSite()
                .clickSignIn()
                .logIn(user, password);
        Assert.assertTrue(frontPage.isUserLoggedIn(), "User is not logged in");
    }

    @ParameterizedTest
    @MethodSource("credentials")
    public void logoutTest(String user, String password){

        FrontPage frontPage = openWebSite()
                .clickSignIn()
                .logIn(user, password)
                .signOut();
        Assert.assertFalse(frontPage.isUserLoggedIn(), "User is not logged out");
    }
}
