package ru.sar.neo.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HelperBase{

  public NavigationHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void gotoGroupPage() {
        click(By.linkText("groups"));
  }

  public void gotoContactAddPage() {
    click(By.linkText("add new"));
  }
}
