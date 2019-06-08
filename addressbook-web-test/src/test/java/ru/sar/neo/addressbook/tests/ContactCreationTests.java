package ru.sar.neo.addressbook.tests;

import org.testng.annotations.*;
import ru.sar.neo.addressbook.appmanager.ApplicationManager;
import ru.sar.neo.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

  @Test
  public void ContactCreationTests() throws Exception {
    app.gotoContactAddPage();
    app.fillContactForm(new ContactData("Germany, Munich", "Valerie", "Saryanidi", "+7 987 333 33 33", "vsaryanidi@gmail.com"));
    app.submitContactCreation();
    app.returnToHomePage();
  }
}
