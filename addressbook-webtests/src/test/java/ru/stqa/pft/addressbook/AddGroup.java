package ru.stqa.pft.addressbook;

import org.testng.annotations.*;

public class AddGroup extends TestBase{

  @Test
  public void testGroupCreation() throws Exception {
    goToMainPage();
    gotToGroupPage();
    groupAddingInit();
    fillingGroupForm(new GroupData("Test", "Test", "Test"));
    submitGroupForm();
    returnToGroupPage();
  }

}
