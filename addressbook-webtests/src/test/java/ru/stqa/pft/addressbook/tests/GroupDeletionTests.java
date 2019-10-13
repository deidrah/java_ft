package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;

public class GroupDeletionTests extends TestBase {
  @Test
  public void testGroupDeletionTests() throws Exception {
    app.goToMainPage();
    app.getNavigationHelper().gotToGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteGroup();
    app.getGroupHelper().returnToGroupPage();
  }

}
