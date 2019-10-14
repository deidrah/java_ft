package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class UserModificationTests extends TestBase {
    @Test
    public void modifyUser() {
        app.goToMainPage();
        app.getUserHelper().fillUserForm(new UserData("Test", "Lalala", "Test 4", "55545555", "test2@test.pl"));
        app.getUserHelper().editUser();
        app.getUserHelper().updateUser();
        app.getUserHelper().returnToHomePage();
    }
}