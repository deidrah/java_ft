package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.UserData;

public class AddUser extends TestBase {

  @Test
  public void testAddUser() throws Exception {
    app.goToMainPage();
    app.getUserHelper().addNewUser();
    app.getUserHelper().fillUserForm(new UserData("Test", "Test", "Test 5", "555555555", "test@test.pl"));
    app.getUserHelper().submitUserForm();
    app.getUserHelper().returnToHomePage();
  }
}
