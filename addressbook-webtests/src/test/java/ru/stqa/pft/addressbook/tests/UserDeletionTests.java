package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appManager.TestBase;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.List;

public class UserDeletionTests extends TestBase {

    @Test
    public void deleteUser() {
        app.goToMainPage();
        if (! app.getUserHelper().isThereAUser()) {
            app.getUserHelper().createUser(new UserData("Test", "Test", "Test 5", "555555555", "test@test.pl", "test1"));
        }
        List<UserData> before = app.getUserHelper().getUserList();
        app.getUserHelper().selectUser(before.size() - 1);
        app.getUserHelper().deleteUser();
        app.getUserHelper().acceptAlert();
        app.getUserHelper().goToHomePage();
        List<UserData> after = app.getUserHelper().getUserList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}
