package ru.sar.neo.addressbook.tests;
import javafx.scene.effect.SepiaTone;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sar.neo.addressbook.model.ContactData;
import ru.sar.neo.addressbook.model.Contacts;
import ru.sar.neo.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class ContactModificationTests extends TestBase{

  @BeforeMethod

  public void ensurePreconditions(){

    app.contact().goToHomePage();
    if (app.contact().all().size() == 0){
      app.contact().create(new ContactData().withFirstname("Valerie").
              withLastname("Saryanidi").withAddress("Germany, Munich").
              withHome_phone("+7 987 333 33 33").withEmail("vsaryanidi@gmail.com").withGroup("TestGroup"),true);
    }
  }

  @Test
  public void testContactModification () {

    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Valerie").
            withLastname("Saryanidi").withAddress("Germany, Munich").
            withHome_phone("+7 987 333 33 33").withEmail("vsaryanidi@gmail.com").withGroup("TestGroup");
    app.contact().modify(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));

  }


}
