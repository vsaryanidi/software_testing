package ru.sar.neo.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.sar.neo.addressbook.model.ContactData;

import static org.testng.Assert.assertTrue;

public class ContactHelper extends HelperBase{
  public boolean acceptNextAlert = true;


  public ContactHelper(FirefoxDriver wd) {
    super(wd);
  }


  public String closeAlertAndGetItsText() {
    try {
      Alert alert = wd.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }

  public void confirmDeletionContact() {
    wd.switchTo().alert().accept();
  }

  public void deleteSelectedContact() {
    acceptNextAlert = true;
    click(By.xpath("//input[@value='Delete']"));
  }

  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void returnToHomePage() {
    click(By.linkText("home"));
  }

  public void submitContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHome_phone());
    type(By.name("email"), contactData.getEmail());
  }

}
