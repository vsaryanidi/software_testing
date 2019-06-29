package ru.sar.neo.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.sar.neo.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {

  @Test

  public void testGroupModification() {

    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupHelper().getGroupCount();
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("TestGroup", null, null));
    }
    app.getGroupHelper().selectGroup(before - 1);
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("TestGroup", "Test", "Group For Test"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before);
  }
}