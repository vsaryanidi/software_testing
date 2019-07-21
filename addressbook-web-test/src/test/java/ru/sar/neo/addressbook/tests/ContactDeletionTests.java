package ru.sar.neo.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sar.neo.addressbook.model.ContactData;
import ru.sar.neo.addressbook.model.Contacts;
import ru.sar.neo.addressbook.model.GroupData;
import ru.sar.neo.addressbook.model.Groups;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class ContactDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
      if (app.db().contacts().size() == 0){
      Groups groups = app.db().groups();
      app.contact().goToHomePage();
      app.contact().create(new ContactData().
              withFirstname("Valerie").
              withLastname("Saryanidi").
              withAddress("Germany, Munich").
              withHome_phone("+7 987 333 33 33").
              withEmail("vsaryanidi@gmail.com")
              .inGroup(groups.iterator().next()),true);
    }
  }

  @Test
  public void ContactDeletionTests() throws Exception {

    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().goToHomePage();
    app.contact().delete(deletedContact);
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(deletedContact)));

    verifyContactListInUI();
  }

}
