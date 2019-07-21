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
import ru.sar.neo.addressbook.model.Groups;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class ContactModificationTests extends TestBase{

  @BeforeMethod

  public void ensurePreconditions(){

      if (app.db().contacts().size() == 0){
        app.contact().goToHomePage();
        File photo = new File("src/test/resources/photo_2019-07-17_20-05-36.jpg");
        Groups groups = app.db().groups();
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
                .withPhoto(new File("src/test/resources/photo_2019-07-17_20-05-36.jpg"))
                .inGroup(groups.iterator().next()),true);
    }
  }

  @Test
  public void testContactModification () {
    app.contact().goToHomePage();
    File photo = new File("src/test/resources/photo_2019-07-17_20-05-36.jpg");
    Groups groups = app.db().groups();
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId())
            .withFirstname("Valerie")
            .withLastname("Saryanidi")
            .withHome_phone("+7 987 333 33 33")
            .withMobile_phone("22222")
            .withWork_phone("3333")
            .withAddress("Germany, Munich")
            .withEmail("vsaryanidi@gmail.com")
            .withEmail1("5454@hff.gh")
            .withEmail2("ghghgh@jfjf.ru")
            .withPhoto(new File("src/test/resources/photo_2019-07-17_20-05-36.jpg"))
            .inGroup(groups.iterator().next());
    app.contact().modify(contact);
    Contacts after = app.db().contacts();
    assertEquals(app.contact().count(), before.size());
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));

    verifyContactListInUI();
  }


}
