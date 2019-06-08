package ru.sar.neo.addressbook.tests;
import org.testng.annotations.Test;
import ru.sar.neo.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification () {
    app.getContactHelper().goToHomePage();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Germany, Munich", "Valerie", "Saryanidi", "+7 987 333 33 33", "vsaryanidi@gmail.com"));
    app.getContactHelper().submitContactModification();
    app.getContactHelper().goToHomePage();
  }
}
