package ru.sar.neo.addressbook.tests;

import org.testng.annotations.*;
import ru.sar.neo.addressbook.model.ContactData;
import ru.sar.neo.addressbook.model.Contacts;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class ContactCreationTests extends TestBase{

  @Test
  public void ContactCreationTests()  {
    app.contact().goToHomePage();
    Contacts before = (Contacts) app.contact().all();
    app.contact().gotoContactAddPage();
    ContactData contact = new ContactData().withFirstname("Valerie").withLastname("Saryanidi").withAddress("Germany, Munich").withHome_phone("+7 987 333 33 33").withEmail("vsaryanidi@gmail.com").withGroup("TestGroup");
    app.contact().fillContactForm(contact,true);
    app.contact().submitContactCreation();
    app.contact().goToHomePage();
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}
