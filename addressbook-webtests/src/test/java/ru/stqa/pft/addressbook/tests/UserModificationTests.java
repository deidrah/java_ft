package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appManager.TestBase;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

public class UserModificationTests extends TestBase {
    @Test
    public void modifyUser() {
        app.goToMainPage();
        List<UserData> before = app.getUserHelper().getUserList();
        app.getUserHelper().editUser(before.size() - 1);
        UserData user = new UserData(before.get(before.size() - 1).getId(), "Test", "Lalala", "Test 4", "55545555", "test2@test.pl", "test1");
        app.getUserHelper().fillUserForm(user, false);
        app.getUserHelper().updateUser();
        app.getUserHelper().goToHomePage();
        List<UserData> after = app.getUserHelper().getUserList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(user);
        Comparator<? super UserData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}