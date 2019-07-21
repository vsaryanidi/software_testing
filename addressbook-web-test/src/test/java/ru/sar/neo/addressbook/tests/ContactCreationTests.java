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
    Contacts before = app.db().contacts();
    app.contact().gotoContactAddPage();
    app.contact().fillContactForm(contact,true);
    app.contact().submitContactCreation();
    app.contact().goToHomePage();
    Contacts after = app.db().contacts();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

    verifyContactListInUI();
  }


  @Test
  public void testBadContactCreation()  {
    app.contact().goToHomePage();
    Contacts before = app.db().contacts();
    app.contact().gotoContactAddPage();
    ContactData contact = new ContactData()
            .withFirstname("Valerie'")
            .withLastname("Saryanidi")
            .withHome_phone("+7 987 333 33 33")
            .withMobile_phone("22222")
            .withWork_phone("3333")
            .withAddress("Germany, Munich")
            .withEmail("vsaryanidi@gmail.com")
            .withEmail1("5454@hff.gh")
            .withEmail2("ghghgh@jfjf.ru")
            .withPhoto(new File("src/test/resources/photo_2019-07-17_20-05-36.jpg"))
            .withGroup("Group 2");
    app.contact().fillContactForm(contact,true);
    app.contact().submitContactCreation();
    app.contact().goToHomePage();
    Contacts after = app.db().contacts();
    assertThat(app.contact().count(), equalTo(before.size()));
    assertThat(after, equalTo(before));

    verifyContactListInUI();
  }

}
