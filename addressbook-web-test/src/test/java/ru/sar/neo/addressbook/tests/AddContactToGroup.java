package ru.sar.neo.addressbook.tests;

import org.testng.annotations.Test;

import ru.sar.neo.addressbook.model.ContactData;
import ru.sar.neo.addressbook.model.GroupData;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

public class AddContactToGroup extends TestBase {

  @Test
  public void testAddContactToGroup () {
    app.contact().goToHomePage();
    ContactData contactBefore = app.db().contacts().iterator().next();
    GroupData groupBefore= app.db().groups().iterator().next();
    app.contact().addContactToGroup(contactBefore.inGroup(groupBefore));
    app.db().updateContact(contactBefore);
    app.db().updateGroup(groupBefore);
    ContactData afterContactToAddGroup = app.db().contacts().iterator().next();
    GroupData afterGroupToAdd= app.db().groups().iterator().next();
    assertThat(afterContactToAddGroup.getGroups(), hasItem(groupBefore));
    assertThat(afterGroupToAdd.getContacts(), hasItem(contactBefore));
  }

}
