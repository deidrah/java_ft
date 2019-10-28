package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.appManager.TestBase;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

public class AddUser extends TestBase {

  @Test
  public void testAddUser() throws Exception {
    app.goToMainPage();
    List<UserData> before = app.getUserHelper().getUserList();
    UserData user = new UserData("Test", "Test", "Test 5", "555555555", "test@test.pl", "test1");
    app.getUserHelper().createUser(user);
    List<UserData> after = app.getUserHelper().getUserList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(user);
    Comparator<? super UserData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
