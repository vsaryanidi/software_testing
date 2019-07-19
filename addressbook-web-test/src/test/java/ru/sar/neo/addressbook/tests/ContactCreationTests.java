package ru.sar.neo.addressbook.tests;

import org.testng.annotations.*;
import ru.sar.neo.addressbook.model.ContactData;
import ru.sar.neo.addressbook.model.Contacts;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() {
    List<Object[]> list = new ArrayList<Object[]>();
    List<File> photo = new ArrayList<File>();
    list.add(new Object[] {new ContactData().withFirstname("Valerie").withLastname("Saryanidi")
            .withAddress("Germany, Munich").withHome_phone("+7 987 333 33 33")
            .withEmail("vsaryanidi@gmail.com").withGroup("TestGroup").withPhoto(new File("src/test/resources/photo_2019-07-17_20-05-36.jpg"))});
    list.add(new Object[] {new ContactData().withFirstname("Valerie").withLastname("Saryanidi")
            .withAddress("Germany, Munich1").withHome_phone("+7 987 333 33 88")
            .withEmail("vsaryanidi1@gmail.com").withGroup("TestGroup1").withPhoto(new File("src/test/resources/photo_2019-07-17_20-05-36.jpg"))});
    return list.iterator();
  }

  @Test (dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) throws Exception {
    app.contact().goToHomePage();
    Contacts before = app.contact().all();
    app.contact().gotoContactAddPage();
    File photo = new File("src/test/resources/photo_2019-07-17_20-05-36.jpg");
    app.contact().fillContactForm(contact,true);
    app.contact().submitContactCreation();
    app.contact().goToHomePage();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }


  @Test
  public void testBadContactCreation()  {
    app.contact().goToHomePage();
    Contacts before = app.contact().all();
    app.contact().gotoContactAddPage();
    ContactData contact = new ContactData().withFirstname("Valerie'").withLastname("Saryanidi").withAddress("Germany, Munich").withHome_phone("+7 987 333 33 33").withEmail("vsaryanidi@gmail.com").withGroup("TestGroup");
    app.contact().fillContactForm(contact,true);
    app.contact().submitContactCreation();
    app.contact().goToHomePage();

    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
  }
}
