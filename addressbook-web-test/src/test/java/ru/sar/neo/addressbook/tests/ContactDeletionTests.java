package ru.sar.neo.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sar.neo.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    app.contact().goToHomePage();
    if (app.contact().list().size() == 0){
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

    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().delete(index);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
    Assert.assertEquals(before,after);

  }

}
