package ru.sar.neo.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sar.neo.addressbook.model.ContactData;
import ru.sar.neo.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

public class ContactDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    app.contact().goToHomePage();
    if (app.contact().all().size() == 0){
      app.contact().create(new ContactData().
              withFirstname("Valerie").
              withLastname("Saryanidi").
              withAddress("Germany, Munich").
              withHome_phone("+7 987 333 33 33").
              withEmail("vsaryanidi@gmail.com").
              withGroup("TestGroup"),true);
    }
  }

  @Test
  public void ContactDeletionTests() throws Exception {

    Set<ContactData> before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedContact);
    Assert.assertEquals(before,after);

  }

}
