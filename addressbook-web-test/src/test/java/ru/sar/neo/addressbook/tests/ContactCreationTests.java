package ru.sar.neo.addressbook.tests;

import org.testng.annotations.*;
import ru.sar.neo.addressbook.model.ContactData;
import ru.sar.neo.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class ContactCreationTests extends TestBase {

  @DataProvider

  public Iterator<Object[]> validContacts() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    //List<File> photo = new ArrayList<File>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
    String line = reader.readLine();
    while (line != null) {
      String[] split = line.split(";");
      list.add(new Object[]{(new ContactData()
              .withFirstname(split[0])
              .withLastname(split[1])
              .withHome_phone(split[2])
              .withMobile_phone(split[3])
              .withWork_phone(split[4])
              .withAddress(split[5])
              .withEmail(split[6])
              .withEmail1(split[7])
              .withEmail2(split[8])
              .withGroup(split[9]))});
      line = reader.readLine();
    }
    return list.iterator();
  }

  @Test (dataProvider = "validContacts")

  public void testContactCreation(ContactData contact) throws Exception {
    app.contact().goToHomePage();
    Contacts before = app.contact().all();
    app.contact().gotoContactAddPage();
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
