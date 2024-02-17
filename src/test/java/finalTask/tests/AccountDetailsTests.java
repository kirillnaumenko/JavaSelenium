package finalTask.tests;

import finalTask.core.BaseTest;
import finalTask.dataModels.AddressModel;
import finalTask.pages.accountDetailsPage.AddressBookTab;
import io.qameta.allure.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AccountDetailsTests extends BaseTest {

    String street;
    String city;

    @Test
    @Description("Verify that usr can add address")
    public void addAccountAddress(){
        street = "Street" + UUID.randomUUID();
        city = "City" + UUID.randomUUID();
        AddressModel model = new AddressModel(street, city, "Alaska", "62356", "United States", "453453245236");
        AddressBookTab addressTab = openWebSite()
                .clickSignIn()
                .logIn("javaautomation@test.com", "Test12345")
                .navigateToAccountDetails()
                .openAddressBookTab()
                .addNewAddress(model);
        List<AddressModel> test = addressTab.getAddresses();
        Optional<AddressModel> actualAddedAddress = addressTab.getAddresses().stream()
                .filter(x -> x.getStreetAddress().equals(street) & x.getCity().equals(city)).findFirst();

        assertTrue(actualAddedAddress.isPresent(), "Address was not added");
    }

    @AfterEach
    public void cleanUp(){
        new AddressBookTab(driver).deleteAddress(street);
        super.cleanUp();
    }
}
