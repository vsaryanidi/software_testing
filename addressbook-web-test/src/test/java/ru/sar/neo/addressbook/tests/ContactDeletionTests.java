package ru.sar.neo.addressbook.tests;

import org.testng.annotations.*;

import static org.testng.Assert.assertTrue;

public class ContactDeletionTests extends TestBase{

  @Test
  public void ContactDeletionTests() throws Exception {
    app.returnToHomePage();
    app.selectContact();
    app.acceptNextAlert = true;
    app.deleteSelectedContact();
    app.confirmDeletionContact();
    app.getGroupHelper().returnToGroupPage(); /*по аналогии*/
  }

}
