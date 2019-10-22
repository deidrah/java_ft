package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

public class AddGroup extends TestBase{

  @Test
  public void testGroupCreation() throws Exception {
    app.goToMainPage();
    app.getNavigationHelper().gotToGroupPage();
    app.getGroupHelper().createGroup(new GroupData("Test", null, null));
  }

}
