package ru.stqa.pft.addressbook;

import org.testng.annotations.*;

public class GroupDeletionTests extends TestBase {
  @Test
  public void testGroupDeletionTests() throws Exception {
    goToMainPage();
    gotToGroupPage();
    selectGroup();
    deleteGroup();
    returnToGroupPage();
  }

}
