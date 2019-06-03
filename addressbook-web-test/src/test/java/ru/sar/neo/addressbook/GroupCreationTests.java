package ru.sar.neo.addressbook;

import org.testng.annotations.*;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {

    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("TestGroup", "Test", "Group For Test"));
    submitGroupCreation();
    returnToGroupPage();

  }

}
