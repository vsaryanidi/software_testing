package ru.sar.neo.addressbook.tests;
import org.testng.annotations.Test;
import ru.sar.neo.addressbook.model.ContactData;
import ru.sar.neo.addressbook.model.GroupData;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification () {
    app.getContactHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Germany, Munich", "Valerie", "Saryanidi", "+7 987 333 33 33", "vsaryanidi@gmail.com", "TestGroup"), true);
    }
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Germany, Munich", "Valerie", "Saryanidi", "+7 987 333 33 33", "vsaryanidi@gmail.com", null), false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().goToHomePage();
  }
}
