package ru.sar.neo.addressbook.tests;

import org.testng.annotations.*;
import ru.sar.neo.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

  @Test
  public void ContactCreationTests() throws Exception {
    app.getNavigationHelper().gotoContactAddPage();
    app.getContactHelper().fillContactForm(new ContactData("Germany, Munich", "Valerie", "Saryanidi", "+7 987 333 33 33", "vsaryanidi@gmail.com", "TestGroup"), true);
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().goToHomePage();
  }
}
