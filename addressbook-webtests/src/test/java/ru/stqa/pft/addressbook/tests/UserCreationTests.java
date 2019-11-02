package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appManager.TestBase;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserCreationTests extends TestBase {

  @Test(enabled = false)
  public void testAddUser() throws Exception {
    app.goToMainPage();
    Users before = app.user().all();
    UserData user = new UserData().withFirstName("Test1").withLastName("Test3");
    app.user().createUser(user);
    Users after = app.user().all();
    Assert.assertEquals(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(user.withId(after.stream().mapToInt((u) -> u.getId()).max().getAsInt()))));
  }
}
