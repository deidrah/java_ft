package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class UserDeletionTests extends TestBase {

    @Test
    public void deleteUser() {
        app.goToMainPage();
        app.getUserHelper().selectUser();
        app.getUserHelper().deleteUser();
        app.getUserHelper().acceptAlert();
        app.getUserHelper().goToHomePage();
    }
}
