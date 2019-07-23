package ru.sar.neo.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hibernate.sql.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ru.sar.neo.addressbook.model.ContactData;
import ru.sar.neo.addressbook.model.GroupData;
import ru.sar.neo.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

public class RemoveContactFromGroup extends TestBase{

  private ContactData contactBefore;

  @BeforeTest

  public void ensurePrecondition () {

    Groups groups = app.db().groups();
    app.contact().goToHomePage();

    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Group 1"));
    }
    Groups group = app.db().groups();
    if (app.db().contacts().size() == 0) {
      File photo = new File("src/test/resources/photo_2019-07-17_20-05-36.jpg");
      app.contact().create(new ContactData()
              .withFirstname("Valerie")
              .withLastname("Saryanidi")
              .withHome_phone("+7 987 333 33 33")
              .withMobile_phone("22222")
              .withWork_phone("3333")
              .withAddress("Germany, Munich")
              .withEmail("vsaryanidi@gmail.com")
              .withEmail1("5454@hff.gh")
              .withEmail2("ghghgh@jfjf.ru")
              .withPhoto(photo)
              .inGroup(group.iterator().next()),true);
    }

    if (contactBefore == null) {
      app.contact().goToHomePage();
      contactBefore = app.db().contacts().iterator().next().inGroup(app.db().groups().iterator().next());
      app.contact().addContactToGroup(contactBefore);
    }
  }
  @Test
  public void testRemoveContactFromGroup () {
    ContactData contactBefore = app.db().contacts().iterator().next();
    GroupData groupBefore= app.db().groups().iterator().next();
    app.contact().goToHomePage();
    app.contact().removeContactFromGroup(contactBefore.inGroup(groupBefore));
    app.db().refreshContact(contactBefore);
    app.db().refreshGroup(groupBefore);
    ContactData afterContactRemoveFromGroup = app.db().contacts().iterator().next();
    GroupData afterGroup= app.db().groups().iterator().next();
    assertThat(afterContactRemoveFromGroup.getGroups(), not(groupBefore));
    assertThat(afterGroup.getContacts(), not(contactBefore));
  }
}
