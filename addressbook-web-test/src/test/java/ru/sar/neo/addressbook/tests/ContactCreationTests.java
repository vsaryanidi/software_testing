package ru.sar.neo.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.sar.neo.addressbook.model.ContactData;

import java.util.List;

public class ContactCreationTests extends TestBase{

  @Test
  public void ContactCreationTests() throws Exception {
    app.getContactHelper().goToHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().gotoContactAddPage();
    app.getContactHelper().fillContactForm(new ContactData("Germany, Munich", "Valerie", "Saryanidi", "+7 987 333 33 33", "vsaryanidi@gmail.com", "TestGroup"), true);
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);
  }
}
