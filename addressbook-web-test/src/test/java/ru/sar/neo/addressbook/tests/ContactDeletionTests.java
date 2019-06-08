package ru.sar.neo.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase{

  @Test
  public void ContactDeletionTests() throws Exception {
    app.getContactHelper().goToHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().confirmDeletionContact();
    app.getContactHelper().goToHomePage();
  }

}
