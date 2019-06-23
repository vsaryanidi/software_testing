package ru.sar.neo.addressbook.tests;

import org.testng.annotations.Test;
import ru.sar.neo.addressbook.model.ContactData;
import ru.sar.neo.addressbook.model.GroupData;

public class ContactDeletionTests extends TestBase{

  @Test
  public void ContactDeletionTests() throws Exception {
    app.getContactHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Germany, Munich", "Valerie", "Saryanidi", "+7 987 333 33 33", "vsaryanidi@gmail.com", "TestGroup"), true);
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().confirmDeletionContact();
    app.getContactHelper().goToHomePage();
  }

}
