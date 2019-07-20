package ru.sar.neo.addressbook.tests;

import com.thoughtworks.xstream.XStream;
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
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class ContactCreationTests extends TestBase {

  @DataProvider

  public Iterator<Object[]> validContacts() throws IOException {
    try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(ContactData.class);
      List<ContactData> groups = (List<ContactData>) xstream.fromXML(xml);
      return groups.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
    }
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
