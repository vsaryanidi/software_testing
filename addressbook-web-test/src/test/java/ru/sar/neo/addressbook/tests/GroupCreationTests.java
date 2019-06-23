package ru.sar.neo.addressbook.tests;

import org.testng.annotations.*;
import ru.sar.neo.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().createGroup(new GroupData("TestGroup", null, null));

  }

}
