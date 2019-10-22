package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class UserDeletionTests extends TestBase {

    @Test
    public void deleteUser() {
        app.goToMainPage();
        if (! app.getUserHelper().isThereAUser()) {
            app.getUserHelper().createUser(new UserData("Test", "Test", "Test 5", "555555555", "test@test.pl", "test1"), true);
        }
        app.getUserHelper().selectUser();
        app.getUserHelper().deleteUser();
        app.getUserHelper().acceptAlert();
        app.getUserHelper().goToHomePage();
    }
}
