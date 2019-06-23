package ru.sar.neo.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase{

  @Test
  public void ContactDeletionTests() throws Exception {
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().confirmDeletionContact();
    app.getNavigationHelper().goToHomePage();
  }

}
