package ru.sar.neo.addressbook.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class ContactDeletionTests extends TestBase{

  @Test
  public void ContactDeletionTests() throws Exception {
    selectContact();
    acceptNextAlert = true;
    deleteSelectedContact();
    confirmDeletionContact();
    app.getGroupHelper().returnToGroupPage(); /*по аналогии*/
  }

}
