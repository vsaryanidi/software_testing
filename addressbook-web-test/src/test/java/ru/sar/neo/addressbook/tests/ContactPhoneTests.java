package ru.sar.neo.addressbook.tests;

import org.testng.annotations.*;
import ru.sar.neo.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase{

  @Test

  public void testContactPhones() {
    app.contact().goToHomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getHome_phone(), equalTo(contactInfoFromEditForm.getHome_phone()));
    assertThat(contact.getMobile_phone(), equalTo(contactInfoFromEditForm.getMobile_phone()));
    assertThat(contact.getWork_phone(), equalTo(contactInfoFromEditForm.getWork_phone()));
  }
}
